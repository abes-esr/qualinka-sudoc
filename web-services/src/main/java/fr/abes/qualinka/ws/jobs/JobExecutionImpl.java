package fr.abes.qualinka.ws.jobs;

import java.time.Instant;

final class JobExecutionImpl implements MutableJobExecution {
	
	private final int id;
	private final Job job;
	private State state;
	private String errorMessage = null;
	private Instant lastUpdateInstant;
	private final Object lock = new Object();
	
	// /////////////////////////////////////////////////////////////////////////
	//	CONSTRUCTORS
	// /////////////////////////////////////////////////////////////////////////

	JobExecutionImpl(int id, Job job) {
		this.id = id;
		this.job = job;
		this.state = State.PENDING;
		this.lastUpdateInstant = Instant.now();
	}
	
	// /////////////////////////////////////////////////////////////////////////
	//	GETTERS
	// /////////////////////////////////////////////////////////////////////////
	
	@Override
	public int getId() {
		return id;
	}

	@Override
	public State getState() {
		return this.state;
	}

	@Override
	public Job getJob() {
		return this.job;
	}
	
	@Override
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	@Override
	public Instant getLastUpdateInstant() {
		synchronized (lock) {			
			return this.lastUpdateInstant;
		}
	}
	
	// /////////////////////////////////////////////////////////////////////////
	//	SETTERS
	// /////////////////////////////////////////////////////////////////////////

	@Override
	public void setState(State state) {
		this.state = state;
		this.updated();
	}
	
	@Override
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		this.updated();
	}
	
	private void updated() {
		synchronized (lock) {	
			this.lastUpdateInstant = Instant.now();
		}
	}
	
	@Override
	public void setLastUpdateInstant(Instant instant) {
		synchronized (lock) {			
			this.lastUpdateInstant = instant;
		}
	}
	// /////////////////////////////////////////////////////////////////////////
	//	OBJECT METHODS OVERRIDING
	// /////////////////////////////////////////////////////////////////////////

	@Override
	public boolean equals(Object other) {
		if(other == this) {
			return true;
		}
		if(other == null) {
			return false;
		}
		if(!(other.getClass() == this.getClass())) {
			return false;
		}
		JobExecutionImpl otherJobExecution = (JobExecutionImpl) other;
		return this.id == otherJobExecution.id;
	}
	
	@Override
	public int hashCode() {
		return this.id;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[JobExecution@")
		.append(Integer.toHexString(this.getId()))
		.append(": serviceName=");
		return sb.toString();
	}

}
