package fr.abes.qualinka.ws.jobs;

import java.util.Objects;

final class JobImpl implements Job {
	
	private final String serviceName;
	private final String input;
	private final int hashCode;

	public JobImpl(String serviceName, String input) {
		this.serviceName = serviceName;
		this.input = input;
		this.hashCode = 31 * this.serviceName.hashCode() + this.input.hashCode();
	}


	@Override
	public String getServiceName() {
		return this.serviceName;
	}

	@Override
	public String getInput() {
		return this.input;
	}

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
		JobImpl otherJob = (JobImpl) other;
		return Objects.equals(this.serviceName, otherJob.serviceName) &&
				Objects.equals(this.input, otherJob.input);
	}
	
	@Override
	public int hashCode() {
		return this.hashCode;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[Job@")
		.append(Integer.toHexString(this.hashCode()))
		.append(": serviceName=")
		.append(this.serviceName)
		.append(']');
		return sb.toString();
	}

}
