package com.bidgely.customerDataGenerator.responses.apiResponses;

import com.bidgely.customerDataGenerator.responses.entries.EndPointsEntry;

import java.util.List;

public class EndPointsResponse {
	private String               error;

	private String               requestId;

	private List<EndPointsEntry> payload;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public List<EndPointsEntry> getPayload() {
		return payload;
	}

	public void setPayload(List<EndPointsEntry> payload) {
		this.payload = payload;
	}

	public boolean equals(Object obj) {

		if (obj instanceof EndPointsResponse) {
			EndPointsResponse endPointsResponse = (EndPointsResponse) obj;

			if (this.payload != null) {
				if (!this.payload.equals(endPointsResponse.getPayload())) {
					return false;
				}
			}
			if (this.error != null) {
				if (!this.error.equals(endPointsResponse.getError())) {
					return false;
				}
			}
			if (this.requestId != null) {
				if (!this.requestId.equals(endPointsResponse.getRequestId())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}

