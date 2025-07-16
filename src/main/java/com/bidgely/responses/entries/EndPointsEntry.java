package com.bidgely.responses.entries;

import java.util.UUID;

public class EndPointsEntry {

	private String measurementType;

	private UUID endpointId;

	private String profile;

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public UUID getEndpointId() {
		return endpointId;
	}

	public void setEndpointId(UUID endpointId) {
		this.endpointId = endpointId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	public boolean equals(Object obj) {

		if (obj instanceof EndPointsEntry) {
			EndPointsEntry endPointsEntry = (EndPointsEntry) obj;

			if (this.measurementType != null) {
				if (!this.measurementType.equals(endPointsEntry.getMeasurementType())) {
					return false;
				}
			}
			if (this.endpointId != null) {
				if (!this.endpointId.equals(endPointsEntry.getEndpointId())) {
					return false;
				}
			}
			if (this.profile != null) {
				if (!this.profile.equals(endPointsEntry.getProfile())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}

