package com.bidgely.responses.entries;

public class UserManagementApplianceEntry {
	private String applianceType;

	private String fuelType;

	public String getApplianceType() {
		return applianceType;
	}

	public void setApplianceType(String applianceType) {
		this.applianceType = applianceType;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public boolean equals(Object obj) {
		if (obj instanceof UserManagementApplianceEntry) {
			UserManagementApplianceEntry userManagementApplianceEntry = (UserManagementApplianceEntry) obj;
			if (!(this.applianceType == null ? userManagementApplianceEntry.getApplianceType() == null
					: this.applianceType.equals(userManagementApplianceEntry.getApplianceType()))
					|| !(this.fuelType == null ? userManagementApplianceEntry.getFuelType() == null
					: this.fuelType.equals(userManagementApplianceEntry.getFuelType()))) {
				return false;
			}

		}
		return true;
	}
}
