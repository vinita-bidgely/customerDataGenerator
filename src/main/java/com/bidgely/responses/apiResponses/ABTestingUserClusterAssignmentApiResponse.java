package com.bidgely.responses.apiResponses;

import com.bidgely.responses.entries.ABTestingUserClusterAssignmentApiResponseEntries;


/**
 * ABTestingUserClusterAssignmentApiResponse is a response class for the API that handles user cluster assignments in A/B testing.
 * It contains the request ID, payload with user cluster assignment details, and any error messages.
 *
 * @author Vinita
 */

public class ABTestingUserClusterAssignmentApiResponse {
	private String requestId;
	private ABTestingUserClusterAssignmentApiResponseEntries payload;
	private String error;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public ABTestingUserClusterAssignmentApiResponseEntries getPayload() {
		return payload;
	}

	public void setPayload(ABTestingUserClusterAssignmentApiResponseEntries payload) {
		this.payload = payload;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}

