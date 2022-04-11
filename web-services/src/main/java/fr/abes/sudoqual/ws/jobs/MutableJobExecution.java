package fr.abes.sudoqual.ws.jobs;

import java.time.Instant;

interface MutableJobExecution extends JobExecution {
	
	void setState(State state);
	
	void setErrorMessage(String msg);
	
	/**
	 * returns the instant of the last mutation of this instance.
	 * @return the instant of the last mutation of this instance
	 */
	Instant getLastUpdateInstant();
	
	void setLastUpdateInstant(Instant hack);
	
}
