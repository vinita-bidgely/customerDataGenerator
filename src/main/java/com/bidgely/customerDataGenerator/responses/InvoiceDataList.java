package com.bidgely.customerDataGenerator.responses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvoiceDataList {
	private String meterId;

	private Double consumption;

	private String measurementType;

	private String chargeName;

	private String chargeNameString;

	private String chargeType;

	private Double cost;

	public String getMeterId() {
		return meterId;
	}

	public void setMeterId(String meterId) {
		this.meterId = meterId;
	}

	public Double getConsumption() {
		return consumption;
	}

	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}

	public String getChargeNameString() {
		return chargeNameString;
	}
	public String getChargeName() {
		return chargeName;
	}

	public void setChargeName(String chargeName) {
		this.chargeName = chargeName;
	}

	public String

	getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof InvoiceDataList) {
			InvoiceDataList invoiceDataList = (InvoiceDataList) obj;
			if (this.meterId != null) {
				if (!this.meterId.equals(invoiceDataList.getMeterId())) {
					return false;
				}
			}
			if (this.consumption != null) {
				if (!this.consumption.equals(invoiceDataList.getConsumption())) {
					return false;
				}
			}
			if (this.measurementType != null) {
				if (!this.measurementType.equals(invoiceDataList.getMeasurementType())) {
					return false;
				}
			}
			if (this.chargeName != null) {
				if (!this.chargeName.equals(invoiceDataList.getChargeName())) {
					return false;
				}
			}
			if (this.chargeType != null) {
				if (!this.chargeType.equals(invoiceDataList.getChargeType())) {
					return false;
				}
			}
			if (this.cost != null) {
				if (!this.cost.equals(invoiceDataList.getCost())) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public static class MetaUserApiResponse {

		private static final Logger logger = LoggerFactory.getLogger(MetaUserApiResponse.class);
		private String  lname;

		private String  status;

		private String  externalUserId;

		private String  uname;

		private String  locale;

		private Integer pilotId;

		private String  secondaryEmail;

		private String  encrypted;

		private String  email;

		private String  notificationUserType;

		private String  userId;

		private String  hasViewedTour;

		private String  removed;

		private String  fname;

		private String  preferences;

		private String  userTag;

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getExternalUserId() {
			return externalUserId;
		}

		public void setExternalUserId(String externalUserId) {
			this.externalUserId = externalUserId;
		}

		public String getUname() {
			return uname;
		}

		public void setUname(String uname) {
			this.uname = uname;
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

		public String getSecondaryEmail() {
			return secondaryEmail;
		}

		public void setSecondaryEmail(String secondaryEmail) {
			this.secondaryEmail = secondaryEmail;
		}

		public String getEncrypted() {
			return encrypted;
		}

		public void setEncrypted(String encrypted) {
			this.encrypted = encrypted;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getNotificationUserType() {
			return notificationUserType;
		}

		public void setNotificationUserType(String notificationUserType) {
			this.notificationUserType = notificationUserType;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getHasViewedTour() {
			return hasViewedTour;
		}

		public void setHasViewedTour(String hasViewedTour) {
			this.hasViewedTour = hasViewedTour;
		}

		public String getRemoved() {
			return removed;
		}

		public void setRemoved(String removed) {
			this.removed = removed;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public String getPreferences() {
			return preferences;
		}

		public void setPreferences(String preferences) {
			this.preferences = preferences;
		}

		public String getUserTag() {
			return userTag;
		}

		public void setUserTag(String userTag) {
			this.userTag = userTag;
		}

		public boolean equals(Object obj) {

			if (obj instanceof MetaUserApiResponse) {
				MetaUserApiResponse metaUserApiResponse = (MetaUserApiResponse) obj;
				if (this.lname != null) {
					if (!this.lname.equals(metaUserApiResponse.getLname())) {
						logger.error("MetaUserAPIResponse : expected lname : " + this.lname + "\nActual lname :" + metaUserApiResponse.getLname());
						return false;
					}
				}

				if (this.status != null) {
					if (!this.status.equals(metaUserApiResponse.getStatus())) {
						logger.error("MetaUserAPIResponse : expected status : " + this.status + "\nActual status :" + metaUserApiResponse.getStatus());
						return false;
					}
				}

				if (this.externalUserId != null) {
					if (!this.externalUserId.equals(metaUserApiResponse.getExternalUserId())) {
						logger.error("MetaUserAPIResponse : expected externalUserId : " + this.externalUserId + "\nActual externalUserId :" + metaUserApiResponse.getExternalUserId());
						return false;
					}
				}

				if (this.uname != null) {
					if (!this.uname.equals(metaUserApiResponse.getUname())) {
						logger.error("MetaUserAPIResponse : expected uname : " + this.uname + "\nActual uname :" + metaUserApiResponse.getUname());
						return false;
					}
				}

				if (this.email != null) {
					if (!this.email.equals(metaUserApiResponse.getEmail())) {
						logger.error("MetaUserAPIResponse : expected email : " + this.email + "\nActual email :" + metaUserApiResponse.getEmail());
						return false;
					}
				}

				if (this.pilotId != null) {
					if (!this.pilotId.equals(metaUserApiResponse.getPilotId())) {
						logger.error("MetaUserAPIResponse : expected pilotId : " + this.pilotId + "\nActual pilotId :" + metaUserApiResponse.getPilotId());
						return false;
					}
				}

				if (this.encrypted != null) {
					if (!this.encrypted.equals(metaUserApiResponse.getEncrypted())) {
						logger.error("MetaUserAPIResponse : expected encrypted : " + this.encrypted + "\nActual encrypted :" + metaUserApiResponse.getEncrypted());
						return false;
					}
				}

				if (this.hasViewedTour != null) {
					if (!this.hasViewedTour.equals(metaUserApiResponse.getHasViewedTour())) {
						logger.error("MetaUserAPIResponse : expected hasViewedTour : " + this.hasViewedTour + "\nActual hasViewedTour :" + metaUserApiResponse.getHasViewedTour());
						return false;
					}
				}

				if (this.userId != null) {
					if (!this.userId.equals(metaUserApiResponse.getUserId())) {
						logger.error("MetaUserAPIResponse : expected userId : " + this.userId + "\nActual userId :" + metaUserApiResponse.getUserId());
						return false;
					}
				}

				if (this.email != null) {
					if (!this.email.equals(metaUserApiResponse.getEmail())) {
						logger.error("MetaUserAPIResponse : expected email : " + this.email + "\nActual email :" + metaUserApiResponse.getEmail());
						return false;
					}
				}

				if (this.removed != null) {
					if (!this.removed.equals(metaUserApiResponse.getRemoved())) {
						logger.error("MetaUserAPIResponse : expected removed : " + this.removed + "\nActual removed :" + metaUserApiResponse.getRemoved());
						return false;
					}
				}

				if (this.fname != null) {
					if (!this.fname.equals(metaUserApiResponse.getFname())) {
						logger.error("MetaUserAPIResponse : expected fname : " + this.fname + "\nActual fname :" + metaUserApiResponse.getFname());
						return false;
					}
				}

				if (this.preferences != null) {
					if (!this.preferences.equals(metaUserApiResponse.getPreferences())) {
						logger.error("MetaUserAPIResponse : expected preferences : " + this.preferences + "\nActual preferences :" + metaUserApiResponse.getPreferences());
						return false;
					}
				}

				if (this.userTag != null) {
					if (!this.userTag.equals(metaUserApiResponse.getUserTag())) {
						logger.error("MetaUserAPIResponse : expected userTag : " + this.userTag + "\nActual userTag :" + metaUserApiResponse.getUserTag());
						return false;
					}
				}

				if (this.notificationUserType != null) {
					if (!this.notificationUserType.equals(metaUserApiResponse.getNotificationUserType())) {
						logger.error("MetaUserAPIResponse : expected notificationUserType : " + this.notificationUserType + "\nActual notificationUserType :" + metaUserApiResponse.getNotificationUserType());
						return false;
					}
				}

				if (this.locale != null) {
					if (!this.locale.equals(metaUserApiResponse.getLocale())) {
						logger.error("MetaUserAPIResponse : expected locale : " + this.locale + "\nActual locale :" + metaUserApiResponse.getLocale());
						return false;
					}
				}
				return true;
			}
			return false;
		}
	}
}
