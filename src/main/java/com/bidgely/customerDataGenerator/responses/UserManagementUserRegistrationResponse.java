package com.bidgely.customerDataGenerator.responses;

import com.bidgely.customerDataGenerator.responses.entries.PublicApiErrorEntry;
import com.bidgely.customerDataGenerator.responses.entries.UserManagementUserRegistrationEntry;
import com.bidgely.customerDataGenerator.commons.SharedResources;


public class UserManagementUserRegistrationResponse {

	private PublicApiErrorEntry                 error;

	private String                              requestId;

	private UserManagementUserRegistrationEntry payload;

	private String                              message;

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

	public UserManagementUserRegistrationEntry getuserManagementUserRegistrationEntry() {
		return payload;
	}

	public void setuserManagementUserRegistrationEntry(
			UserManagementUserRegistrationEntry payload) {
		this.payload = payload;
	}

	public String getComparisonErrorMessage() {
		return this.message;
	}

	public boolean equals(Object obj) {

		if (obj instanceof UserManagementUserRegistrationResponse) {
			UserManagementUserRegistrationResponse userManagementUserRegistrationResponse = (UserManagementUserRegistrationResponse) obj;

			if (!(this.payload == null
					? userManagementUserRegistrationResponse.getuserManagementUserRegistrationEntry() == null
					: this.payload.equals(userManagementUserRegistrationResponse.getuserManagementUserRegistrationEntry())) ||
					!(this.error == null
							? userManagementUserRegistrationResponse.getError() == null
							: this.error.equals(userManagementUserRegistrationResponse.getError()))) {
				message = "Repsonse value mismatch \nExpected :" +
						SharedResources.gson.toJson(payload) + "\nActual :" +
						SharedResources.gson.toJson(userManagementUserRegistrationResponse.getuserManagementUserRegistrationEntry());
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
}
