package com.bidgely.customerDataGenerator.responses.entries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.UUID;

/**
 * A POJO for UserManagementUserRegistrationEntry.
 *
 * @author Akshata
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserManagementUserRegistrationEntry {
	private String lastName;

	private Integer lastModified;

	private String status;

	private String userUrl;

	private String locale;

	private Integer pilotId;

	private String endPointsUrl;

	private Integer createdOn;

	private Integer lastLogin;

	private String email;

	private String partnerUserId;

	private Integer userId;

	private String userName;

	private UserManagementAttributesEntry attributes;

	private String roleId;

	private UUID uuid;

	private String firstName;

	private UserManagementHomeAccountsEntry homeAccounts;

	private String rolescope;

	private String userTag;

	private Integer lastAccessTimestamp;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getLastModified() {
		return lastModified;
	}

	public void setLastModified(Integer lastModified) {
		this.lastModified = lastModified;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserUrl() {
		return userUrl;
	}

	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Integer getPilotId() {
		return pilotId;
	}

	public void setPilotId(Integer pilotId) {
		this.pilotId = pilotId;
	}

	public String getEndPointsUrl() {
		return endPointsUrl;
	}

	public void setEndPointsUrl(String endPointsUrl) {
		this.endPointsUrl = endPointsUrl;
	}

	public Integer getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Integer createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Integer lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPartnerUserId() {
		return partnerUserId;
	}

	public void setPartnerUserId(String partnerUserId) {
		this.partnerUserId = partnerUserId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public UserManagementHomeAccountsEntry getHomeAccounts() {
		return homeAccounts;
	}

	public void setHomeAccounts(UserManagementHomeAccountsEntry homeAccounts) {
		this.homeAccounts = homeAccounts;
	}

	public UserManagementAttributesEntry getAttributes() {
		return attributes;
	}

	public void setAttributes(UserManagementAttributesEntry attributes) {
		this.attributes = attributes;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRolescope() {
		return rolescope;
	}

	public void setRolescope(String rolescope) {
		this.rolescope = rolescope;
	}

	public String getUserTag() {
		return userTag;
	}

	public void setUserTag(String userTag) {
		this.userTag = userTag;
	}

	public Integer getLastAccessTimestamp() {
		return lastAccessTimestamp;
	}

	public void setLastAccessTimestamp(Integer lastAccessTimestamp) {
		this.lastAccessTimestamp = lastAccessTimestamp;
	}

	public boolean equals(Object obj) {

		if (obj instanceof UserManagementUserRegistrationEntry) {
			UserManagementUserRegistrationEntry userManagementUserRegistrationEntry = (UserManagementUserRegistrationEntry) obj;

			if (this.firstName != null)
				return this.firstName.equals(userManagementUserRegistrationEntry.getFirstName());

			if (this.lastName != null)
				return this.lastName.equals(userManagementUserRegistrationEntry.getLastName());

			if (this.lastModified != null)
				return this.lastModified.equals(userManagementUserRegistrationEntry.getLastModified());

			if (this.status != null)
				return this.status.equals(userManagementUserRegistrationEntry.getStatus());

			if (this.userUrl != null)
				return this.userUrl.equals(userManagementUserRegistrationEntry.getUserUrl());

			if (this.locale != null)
				return this.locale.equals(userManagementUserRegistrationEntry.getLocale());

			if (this.pilotId != null)
				return this.pilotId.equals(userManagementUserRegistrationEntry.getPilotId());

			if (this.endPointsUrl != null)
				return this.endPointsUrl.equals(userManagementUserRegistrationEntry.getEndPointsUrl());

			if (this.createdOn != null)
				return this.createdOn.equals(userManagementUserRegistrationEntry.getCreatedOn());

			if (this.lastLogin != null)
				return this.lastLogin.equals(userManagementUserRegistrationEntry.getLastLogin());

			if (this.email != null)
				return this.email.equals(userManagementUserRegistrationEntry.getEmail());

			if (this.partnerUserId != null)
				return this.partnerUserId.equals(userManagementUserRegistrationEntry.getPartnerUserId());

			if (this.userId != null)
				return this.userId.equals(userManagementUserRegistrationEntry.getUserId());

			if (this.userName != null)
				return this.getUserName().equals(userManagementUserRegistrationEntry.getUserName());

			if (this.roleId != null)
				return this.roleId.equals(userManagementUserRegistrationEntry.getRoleId());

			if (this.rolescope != null)
				return this.rolescope.equals(userManagementUserRegistrationEntry.getRolescope());

			if (this.userTag != null)
				return this.userTag.equals(userManagementUserRegistrationEntry.getUserTag());

			if (this.uuid != null)
				return this.uuid.equals(userManagementUserRegistrationEntry.getUuid());

			if (this.attributes != null)
				return this.attributes.equals(userManagementUserRegistrationEntry.getAttributes());

			if (this.homeAccounts != null)
				return this.homeAccounts.equals(userManagementUserRegistrationEntry.getHomeAccounts());
		}
		return true;

	}
}
