package com.bidgely.customerDataGenerator.responses.entries;


/** * ABTestingUserClusterAssignmentApiResponseEntries is a response class for the API that handles user cluster assignments in A/B testing.
 * It contains details about the user, the assigned cluster, and any error or success messages.
 *
 * @author Vinita
 */


public class ABTestingUserClusterAssignmentApiResponseEntries {

	private String uuid;
	private String clusterId;
	private String error;
	private String success;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

}

