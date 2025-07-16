package com.bidgely.customerDataGenerator.responses.apiResponses;

import com.bidgely.customerDataGenerator.responses.entries.EndPointsRegistrationEntry;
import com.bidgely.customerDataGenerator.responses.entries.PublicApiErrorEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EndPointsRegistrationResponse {
	private PublicApiErrorEntry error;

	private String                     requestId;

	private EndPointsRegistrationEntry payload;

	public PublicApiErrorEntry getError() {
		return error;
	}

	public void setError(PublicApiErrorEntry error) {
		this.error = error;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public EndPointsRegistrationEntry getPayload() {
		return payload;
	}

	public void setPayload(EndPointsRegistrationEntry payload) {
		this.payload = payload;
	}

	@Override
	public String toString() {
		return "ClassPojo [error = " + error + ", requestId = " + requestId + ", payload = " + payload + "]";
	}

	public boolean equals(Object obj) {

		if (obj instanceof EndPointsRegistrationResponse) {
			EndPointsRegistrationResponse endPointsRegistrationResponse = (EndPointsRegistrationResponse) obj;

			if (endPointsRegistrationResponse.getPayload() != null) {
				if (!endPointsRegistrationResponse.getPayload().equals(this.getPayload())) {
					return false;
				}
			}
			if (endPointsRegistrationResponse.getError() != null) {
				if (!endPointsRegistrationResponse.getError().equals(this.getError())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}

