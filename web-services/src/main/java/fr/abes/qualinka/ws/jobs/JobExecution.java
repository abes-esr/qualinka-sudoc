package fr.abes.qualinka.ws.jobs;

/**
 * This class represents a Job execution state. 
 * 
 * @author Clément Sipieter {@literal <clement@6pi.fr>}
 */
public interface JobExecution {
	
	// /////////////////////////////////////////////////////////////////////////
	//	
	// /////////////////////////////////////////////////////////////////////////
	
	enum State {
		PENDING,
		IN_PROGRESS,
		DONE,
		CANCELLED,
		FAIL
	}
	
	/**
	 * Gets the job execution id
	 * @return the job execution id
	 */
	int getId();
		
	/**
	 * Gets the job execution state
	 * @return the job execution state
	 */
	State getState();
	
	/**
	 * Gets the underlying job.
	 * @return the underlying job.
	 */
	Job getJob();

	/**
	 * Gets the error message if and only if the job execution state is FAIL.
	 * @return the error message if the job execution state is FAIL, null otherwise.
	 */
	String getErrorMessage();
		
}
