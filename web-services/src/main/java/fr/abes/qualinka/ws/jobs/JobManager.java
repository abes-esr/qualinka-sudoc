package fr.abes.qualinka.ws.jobs;

import java.util.Collection;
import java.util.Set;

import javax.annotation.Nullable;

public interface JobManager {
	
	/**
	 * Create a JobManager instance.
	 * @return a JobManager instance.
	 */
	static JobManager create() {
		return new JobManagerImpl();
	}
	
	/**
	 * Allows to recovery cache after application reboot. Must be called after service registration.
	 */
	void cacheRecovery();
	
	/**
	 * Gets the JobExecution with the given id if exists.
	 * @param id
	 * @return the JobExecution with the given id if exists, null otherwise.
	 */
	@Nullable JobExecution get(int id);
	
	/**
	 * Gets the JobExecution with the given hexaname if exists.
	 * @param hexaname 
	 * @return the JobExecution with the given hexaname if exists. null otherwise.
	 */
	@Nullable JobExecution get(String hexaname);
	
	/**
	 * Gets a JobExecution for the given service and input. If a corresponding JobExecution
	 * exists then it will return the existing one, otherwise it will create a new one.
	 * @param serviceName
	 * @param input
	 * @return a JobExecution for the given service and input.
	 */
	JobExecution getOrCreate(String serviceName, String input);

	/**
	 * Registers the given service as serviceName.
	 * @param serviceName
	 * @param service
	 * @return true if the service was correctly register, false if a service already exists for
	 * the given service name.
	 */
	boolean register(String serviceName, Service service);
	
	/**
	 * Gets the registered service 
	 * @param serviceName
	 * @return the registered service
	 */
	Service getRegisteredService(String serviceName);
	
	/**
	 * Gets names of all registered services.
	 * @return a Set of names of all registered services.
	 */
	Set<String> getRegisteredServices();
	
	/**
	 * Gets the application relative path to the job status. The application relative path is the
	 * path where we can make an HTTP request, it is relative to the root path of the application.
	 * @param id
	 * @return the application relative path to the job status.
	 */
	String getPathToJobStatus(int id);
	
	/**
	 * Gets the application relative path to the job result. The application relative path is the
	 * path where we can make an HTTP request, it is relative to the root path of the application.
	 * @param id
	 * @return the application relative path to the job result.
	 */
	String getPathToResult(int id);

	/**
	 * Gets the application relative path to the job input. The application relative path is the
	 * path where we can make an HTTP request, it is relative to the root path of the application.
	 * @param id
	 * @return the application relative path to the job input.
	 */
	String getPathToInput(int id);
	
	/**
	 * Deletes all information about the job execution with the given id. It will make best effort
	 * to cancel job processing if the job is not finish.
	 * @param id
	 */
	void deleteJob(int id);
	
	/**
	 * Closes the current JobManager instance and releases resources.
	 */
	void close();

	/**
	 * Lists all JobExecutions currently known by this JobManager.
	 * @return
	 */
	Collection<JobExecution> listJobExecution();
	
	/**
	 * Converts a job execution id intothe hexaname form.
	 * @param jobId
	 * @return the hexaname form of the given id.
	 */
	static String filenameFromId(int jobId) {
		return Integer.toHexString(jobId);
	}
	
	/**
	 * Converts a job execution hexaname into the id (integer) form.
	 * @param filename
	 * @return the id (integer) form of the given hexaname.
	 */
	static int idFromFilename(String filename) {
		return Integer.parseUnsignedInt(filename, 16);
	}

	/**
	 * Gets the job result, return an empty string if no result available.
	 * @param hexaname
	 * @return the job result, an empty string if no result available.
	 */
	String getResult(String hexaname);

	/**
	 * Gets the job result, return an empty string if no result available.
	 * @param jobId
	 * @return the job result, an empty string if no result available.
	 */
	String getResult(int jobId);

	/**
	 * Gets the job input, return an empty string if no result available.
	 * @param jobId
	 * @return the job input, an empty string if no result available.
	 */
	String getInput(String hexaname);

	/**
	 * Gets the job input, return an empty string if no result available.
	 * @param jobId
	 * @return the job input, an empty string if no result available.
	 */
	String getInput(int jobId);

	String getRegisteredNameForService(Service service);

}
