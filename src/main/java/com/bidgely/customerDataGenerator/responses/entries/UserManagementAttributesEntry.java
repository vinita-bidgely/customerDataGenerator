package com.bidgely.customerDataGenerator.responses.entries;

public class UserManagementAttributesEntry {
	private String hasLoadedDashboard;

	private String isTemporaryPassword;

	private String pairingState;

	public String getHasLoadedDashboard() {
		return hasLoadedDashboard;
	}

	public void setHasLoadedDashboard(String hasLoadedDashboard) {
		this.hasLoadedDashboard = hasLoadedDashboard;
	}

	public String getIsTemporaryPassword() {
		return isTemporaryPassword;
	}

	public void setIsTemporaryPassword(String isTemporaryPassword) {
		this.isTemporaryPassword = isTemporaryPassword;
	}

	public String getPairingState() {
		return pairingState;
	}

	public void setPairingState(String pairingState) {
		this.pairingState = pairingState;
	}

	public boolean equals(Object obj) {

		if (obj instanceof UserManagementAttributesEntry) {
			UserManagementAttributesEntry userManagementAttributesEntry = (UserManagementAttributesEntry) obj;

			if (!(this.hasLoadedDashboard == null ? userManagementAttributesEntry.getHasLoadedDashboard() == null
					: this.hasLoadedDashboard.equals(userManagementAttributesEntry.getHasLoadedDashboard()))
					|| !(this.isTemporaryPassword == null
					? userManagementAttributesEntry.getIsTemporaryPassword() == null
					: this.isTemporaryPassword.equals(userManagementAttributesEntry.isTemporaryPassword))
					|| !(this.pairingState == null ? userManagementAttributesEntry.getPairingState() == null
					: this.pairingState.equals(userManagementAttributesEntry.getPairingState()))) {
				return false;
			}

		}
		return true;

	}
}
