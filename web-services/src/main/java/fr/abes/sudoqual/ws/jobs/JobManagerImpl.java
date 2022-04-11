package fr.abes.sudoqual.ws.jobs;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.abes.sudoqual.ws.Context;
import fr.abes.sudoqual.ws.jobs.JobExecution.State;

class JobManagerImpl implements JobManager {
	
	private static final Logger logger = LoggerFactory.getLogger(JobManagerImpl.class);

	private final HashMap<String, Service> serviceRegister;

	private final HashMap<Integer, MutableJobExecution> jobExecutionPool;
	private final HashMap<Integer, Thread> jobThreadMap;

	private final int ttlInSeconds;

	public JobManagerImpl() {
		this.jobExecutionPool = new HashMap<>();
		this.serviceRegister = new HashMap<>();
		this.jobThreadMap = new HashMap<>();
		
		this.ttlInSeconds = Context.CONF.getInt("service.results.ttl")*100;
		if(ttlInSeconds <= 0) {
			throw new IllegalArgumentException("The result TTL (Time To Live) must be greater than or equal to 1.");
		}
		
	}
	
	@Override
	public final void cacheRecovery() throws IllegalStateException {
		if(!jobExecutionPool.isEmpty()) {
			throw new IllegalStateException();
		}
		try {
			InputAndResultManager.instance().listEntries().forEach(this::cacheRecoveryConsumer);
		} catch (IOException e) {
			logger.warn("IOException during cache recovery", e);
		}
	}
	
	private void cacheRecoveryConsumer(InputAndResultManagerImpl.Entry entry) {
		if(entry.getService() == null || entry.getInput() == null 
				|| entry.getLastUpdate().isBefore(Instant.now().minusSeconds(this.ttlInSeconds))) {
			InputAndResultManager.instance().delete(entry.getHexaname());
			return;
		}
		
		String serviceName = jsonStringAsService(entry.getService());
		Job job = Job.instance(serviceName, entry.getInput());
		int jobId = JobManager.idFromFilename(entry.getHexaname());
		MutableJobExecution jobExecution = this.create(jobId, job);
		this.jobExecutionPool.put(jobId, jobExecution);
			
		if(entry.getResult() != null) {
			jobExecution.setState(State.DONE);
			jobExecution.setLastUpdateInstant(entry.getLastUpdate());
		} else {
			Service service = serviceRegister.get(serviceName);
			if(service == null) {
				logger.warn("Try to load a cache from a service no longer available: {}", serviceName);
				this._deleteJob(JobManager.idFromFilename(entry.getHexaname()));
				return;
			}
			Thread jobThread = new Thread(new JobExecutor(service, jobExecution));
			this.jobThreadMap.put(jobId, jobThread);
			jobThread.start();
		}	
	}
	
	/**
	 * 
	 * @param id
	 * @return the job with the specified id if it exists, null otherwise.
	 */
	@Override
	public JobExecution get(int id) {
		return this.jobExecutionPool.get(id);
	}
	
	@Override
	public JobExecution get(String hexaname) {
		return this.jobExecutionPool.get(JobManager.idFromFilename(hexaname));
	}
	
	@Override
	public JobExecution getOrCreate(String serviceName, String input) {
		Service service = serviceRegister.get(serviceName);
		if(service == null) {
			throw new IllegalArgumentException("The specified service does not exist.");
		}
		if(input == null) {
			throw new IllegalArgumentException("The input parameter must be not null.");
		}
		
		Job job = Job.instance(serviceName, input);
		int jobId = job.hashCode();

		JobExecution jobExecution;
		synchronized(this) {
    		boolean foundPreviousExecution = false;
    		// looks for previous execution, increments jobId if there are hash collisions.
    		do {
    			jobExecution = this.jobExecutionPool.get(jobId);
        		if(jobExecution != null) {
        			if(job.equals(jobExecution.getJob())) {
        				foundPreviousExecution = true;
        			} else {
        				++jobId;
        			}
        		}
    		} while(jobExecution != null && !foundPreviousExecution);
    		
    		if(!foundPreviousExecution) {
    			assert jobExecution == null : "inconsistent, jobExecution should be null";
    			jobExecution = create(jobId, service, job);
    		}
		}
		
		return jobExecution;
	}
	
	private JobExecution create(int jobId, Service service, Job job) {
		if(this.jobExecutionPool.get(jobId) != null) {
			throw new IllegalArgumentException("A job with the specified id (" + jobId + ") already exists.");
		}
		
		InputAndResultManager.instance().saveInput(JobManager.filenameFromId(jobId), job.getInput());
		InputAndResultManager.instance().saveService(JobManager.filenameFromId(jobId), serviceAsJSONString(job.getServiceName()));
		
		MutableJobExecution jobExecution = this.create(jobId, job);
		this.jobExecutionPool.put(jobId, jobExecution);
		
		Thread jobThread = new Thread(new JobExecutor(service, jobExecution));
		this.jobThreadMap.put(jobId, jobThread);
		jobThread.start();
		
		// cleaning
		new Thread(new MrClean(this.ttlInSeconds, this.jobExecutionPool.values())).start();

		return jobExecution;
	}
	
	@Override
	public String getResult(int jobId) {
		return InputAndResultManager.instance().getResult(JobManager.filenameFromId(jobId));
	}
	
	@Override
	public String getResult(String hexaname) {
		return InputAndResultManager.instance().getResult(hexaname);
	}
	
	@Override
	public String getInput(int jobId) {
		return InputAndResultManager.instance().getInput(JobManager.filenameFromId(jobId));
	}
	
	@Override
	public String getInput(String hexaname) {
		return InputAndResultManager.instance().getInput(hexaname);
	}


	@Override
	public boolean register(String serviceName, Service service) {
		if(serviceName == null || service == null) {
			throw new IllegalArgumentException("parameters must be not null");
		}
		Service putIfAbsent = this.serviceRegister.putIfAbsent(serviceName, service);
		return putIfAbsent == null;
	}
	

	@Override
	public Set<String> getRegisteredServices() {
		return this.serviceRegister.keySet();
	}
	
	@Override
	public Service getRegisteredService(String serviceName) {
		return this.serviceRegister.get(serviceName);
	}
	
	@Override
	public @Nullable String getRegisteredNameForService(Service service) {
		for(Map.Entry<String, Service> e : this.serviceRegister.entrySet()) {
			if(e.getValue() == service) {
				return e.getKey();
			}
		}
		return null;
	}
	
	@Override
	public String getPathToJobStatus(int id) {
		 return Context.CONF.getString("service.path.jobs") + Integer.toHexString(id);
	}
		
	private MutableJobExecution create(int id, Job job) {
		return new JobExecutionImpl(id, job);
	}
	
	@Override
	public void deleteJob(int id) {
		this._stopJob(id);
		this._deleteJob(id);
	}
	
	@Override
	public Collection<JobExecution> listJobExecution() {
		return Collections.unmodifiableCollection(this.jobExecutionPool.values());
	}
	
	private void _stopJob(int id) {
		Thread thread = this.jobThreadMap.remove(id);
		if(thread != null) {
			thread.interrupt();
		}
	}

	private void _deleteJob(int id) {
		JobExecution job = this.jobExecutionPool.remove(id);
		if(job == null) {
			throw new IllegalArgumentException("No job found for the provided id: " + id);
		}
		InputAndResultManager.instance().delete(JobManager.filenameFromId(id));
	}
	

	@Override
	public String getPathToResult(int id) {
		return InputAndResultManager.instance().getPathToResult(JobManager.filenameFromId(id));
	}

	@Override
	public String getPathToInput(int id) {
		return InputAndResultManager.instance().getPathToInput(JobManager.filenameFromId(id));
	}

	private static String serviceAsJSONString(String serviceName) {
		JSONObject serviceJSON = new JSONObject();
		serviceJSON.put("service", serviceName);
		return serviceJSON.toString();
	}
	
	private static String jsonStringAsService(String serviceJSONString) {
		JSONObject serviceJSON = new JSONObject(serviceJSONString);
		return serviceJSON.getString("service");
	}
	
	// /////////////////////////////////////////////////////////////////////////
	//	PRIVATE CLASSES
	// /////////////////////////////////////////////////////////////////////////
	
	private static class JobExecutor implements Runnable {
		
		private static final Logger logger = LoggerFactory.getLogger(JobExecutor.class);
		
		private final Service service;
		private final MutableJobExecution jobExecution;
		
		public JobExecutor(Service service, MutableJobExecution jobExecution) {
			this.service = service;
			this.jobExecution = jobExecution;
		}
		
		@Override
		public void run() {
			try {
    			jobExecution.setState(State.IN_PROGRESS);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				int res = -1;
				try (PrintStream err = new PrintStream(new BufferedOutputStream(baos), false, StandardCharsets.UTF_8)) {
					try {
						String fileNameBase = JobManager.filenameFromId(this.jobExecution.getId());
						String inputFile = InputAndResultManager.instance().getRealPathToInput(fileNameBase);
						String outputFile = InputAndResultManager.instance().getRealPathToResult(fileNameBase);
						res = service.exec(inputFile, outputFile, err);
					} catch (InterruptedException e) {
						throw e;
					} catch (Exception e) {
						Job job = jobExecution.getJob();
						logger.error(String.format("An error occured during execution of '{}' with the following input: {}", job.getServiceName(), job.getInput()), e);
						String errorMsg = e.getMessage();
						if (errorMsg == null || errorMsg.isEmpty()) {
							errorMsg = e.toString();
						}
						jobExecution.setErrorMessage(errorMsg);
					}
					if(res != 0) {
						err.flush();
						jobExecution.setErrorMessage(baos.toString(StandardCharsets.UTF_8));
					}
				}
				jobExecution.setState((res == 0) ? State.DONE : State.FAIL);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); 
				jobExecution.setState(State.CANCELLED);
			}
		}
		
	}
	
	@Override
	public void close() {
		Collection<Service> values = serviceRegister.values();
		for(Service service : values) {
			service.close();
		}
		serviceRegister.clear();
	}
	
	// /////////////////////////////////////////////////////////////////////////
	//	PRIVATE CLASS
	// /////////////////////////////////////////////////////////////////////////

	
	private class MrClean implements Runnable {
		
		private final Instant timeToDead;
		private final Collection<MutableJobExecution> jobs;

		MrClean(long ttlInSeconds, Collection<MutableJobExecution> jobs) {
			this.jobs = Set.copyOf(jobs);
			this.timeToDead = Instant.now().minusSeconds(ttlInSeconds);
		}

		@Override
		public void run() {
			try {
    			for(MutableJobExecution j : jobs) {
    				if(j.getLastUpdateInstant().isBefore(this.timeToDead)) {
    					deleteJob(j.getId());
    				}
    			}
			} catch(Exception e) {
				logger.warn("An error occured during input and result file cleaning: ", e);
			}
		}
		
	}

}