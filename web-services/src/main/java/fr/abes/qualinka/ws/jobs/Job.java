package fr.abes.qualinka.ws.jobs;

public interface Job {
	
	static Job instance(String serviceName, String input) {
		return new JobImpl(serviceName, input);
	}
	
	// /////////////////////////////////////////////////////////////////////////
	//	
	// /////////////////////////////////////////////////////////////////////////

	/**
	 * Returns the name of the service called.
	 * @return the name of the service called.
	 */
	String getServiceName();
	
	/**
	 * Returns the input of this job.
	 * @return the input of this job.
	 */
	String getInput();
	

}
