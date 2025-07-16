package com.bidgely.responses.apiResponses;

import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Map;

@XmlRootElement
public class MetaUserHomeApiResponse {

	public static final Logger logger = LoggerFactory.getLogger(MetaUserHomeApiResponse.class);

	Integer defNTypeId;

	String  billCycleCode;

	Boolean firstTimeHanConnectEmailed;

	String  masterTariffId;

	Integer yearBuilt;

	Double  bathrooms;

	Boolean dailyUsageDownAlert;

	Integer numberOfAdults;

	String  city;

	String  numOccupants;

	Integer nhood;

	String  timezone;

	String  phoneNumber;

	String  notifyEmailState;

	String  disconnectsSentPerWeek;

	Boolean sentSurvey1;

	String  utilityunknown;

	String  dailyUsageUpAlert;

	Long    enrollmentTime;

	String  supplementaryNhoodIds;

	String  lastProfileTime;

	Boolean emailsBlocked;

	String  notifyEmailGBState;

	Boolean sentSurvey3;

	Boolean notifyEmailStatusChange;

	Boolean sentSurvey2;

	Integer plannumber;

	String  thermostats;

	String  utilityName;

	String  addressType;

	Integer bedrooms;

	Boolean firstTimeGBConnectNotified;

	String  monthlySummaryList;

	String  rateQuery;

	String  livingArea;

	String  country;

	String  nsp;

	String  weeklySummaryList;

	String  planunknown;

	String  blackList;

	Integer billstart;

	String  waterHeatingType;

	String  nst;

	String  lseId;

	Double  dailyUsageDownAlertPercent;

	Integer lastMonthKwh;

	Boolean sentMissingData;

	String  occupantType;

	String  ownershipType;

	String  rateType;

	String  state;

	Double  dailyUsageUpAlertPercent;

	String  firstDataProcessTime;

	Boolean notificationsBlocked;

	Double  dailyPeakToUUsageUpAlertPercent;

	Boolean solarUser;

	Integer defNhoodId;

	Integer dwelling;

	Boolean checkedDILSO;

	String  ratesSchedule;

	String  path;

	Boolean dashboardLoaded;

	Integer utilityid;

	String heatingType;

	String  zip;

	String  ratePlanId;

	Double  defaultRate;

	String  spaceHeatingType;

	String  utilityPassword;

	Boolean notifyEmailMonthlyStatus;

	Double  lotSize;

	String  releaseList;

	String  dailyUsageDownAlertList;

	Boolean qualifiedForSurvey;

	String  meterId;

	Integer totalRooms;

	String  address;

	Integer numberOfChildren;

	String  notifyEmailWeeklyStatus;

	String  dailyTierChangeAlertList;

	Boolean firstTimeGBConnectEmailed;

	String  dailyPeakToUUsageUpAlertList;

	Boolean dailyTierChangeAlert;

	String  utilityUserName;

	Boolean dailyPeakToUUsageUpAlert;

	String  dailyUsageUpAlertList;

	Boolean sentMissingOutHan;

	String  serial;

	Integer lastMonthBill;

	Boolean showDevicePairing;

	String  secondaryPhoneNumber;

	String  mailingAddress;

	String  latitude;

	String  longitude;

	String userBusinessType;

	String userSegment;

	public Integer getDefNTypeId() {
		return defNTypeId;
	}

	public String getUserSegment() {
		return userSegment;
	}

	public String getBillCycleCode() {
		return billCycleCode;
	}

	public boolean isFirstTimeHanConnectEmailed() {
		return firstTimeHanConnectEmailed;
	}

	public String getMasterTariffId() {
		return masterTariffId;
	}

	public Integer getYearBuilt() {
		return yearBuilt;
	}

	public double getBathrooms() {
		return bathrooms;
	}

	public boolean isDailyUsageDownAlert() {
		return dailyUsageDownAlert;
	}

	public Integer getNumberOfAdults() {
		return numberOfAdults;
	}

	public String getCity() {
		return city;
	}

	public String getNumOccupants() {
		return numOccupants;
	}

	public Integer getNhood() {
		return nhood;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getNotifyEmailState() {
		return notifyEmailState;
	}

	public String getDisconnectsSentPerWeek() {
		return disconnectsSentPerWeek;
	}

	public boolean isSentSurvey1() {
		return sentSurvey1;
	}

	public String getUtilityunknown() {
		return utilityunknown;
	}

	public String getDailyUsageUpAlert() {
		return dailyUsageUpAlert;
	}

	public long getEnrollmentTime() {
		return enrollmentTime;
	}

	public String getSupplementaryNhoodIds() {
		return supplementaryNhoodIds;
	}

	public String getLastProfileTime() {
		return lastProfileTime;
	}

	public boolean isEmailsBlocked() {
		return emailsBlocked;
	}

	public String getNotifyEmailGBState() {
		return notifyEmailGBState;
	}

	public boolean isSentSurvey3() {
		return sentSurvey3;
	}

	public boolean isNotifyEmailStatusChange() {
		return notifyEmailStatusChange;
	}

	public boolean isSentSurvey2() {
		return sentSurvey2;
	}

	public Integer getPlannumber() {
		return plannumber;
	}

	public String getThermostats() {
		return thermostats;
	}

	public String getUtilityName() {
		return utilityName;
	}

	public String getAddressType() {
		return addressType;
	}

	public Integer getBedrooms() {
		return bedrooms;
	}

	public boolean isFirstTimeGBConnectNotified() {
		return firstTimeGBConnectNotified;
	}

	public String getMonthlySummaryList() {
		return monthlySummaryList;
	}

	public String getRateQuery() {
		return rateQuery;
	}

	public String getLivingArea() {
		return livingArea;
	}
	public String getUserBusinessType() {
		return userBusinessType;
	}

	public String getCountry() {
		return country;
	}

	public String getNsp() {
		return nsp;
	}

	public String getWeeklySummaryList() {
		return weeklySummaryList;
	}

	public String getPlanunknown() {
		return planunknown;
	}

	public String getBlackList() {
		return blackList;
	}

	public Integer getBillstart() {
		return billstart;
	}

	public String getWaterHeatingType() {
		return waterHeatingType;
	}

	public String getHeatingType() {
		return heatingType;
	}

	public String getNst() {
		return nst;
	}

	public String getLseId() {
		return lseId;
	}

	public double getDailyUsageDownAlertPercent() {
		return dailyUsageDownAlertPercent;
	}

	public Integer getLastMonthKwh() {
		return lastMonthKwh;
	}

	public boolean isSentMissingData() {
		return sentMissingData;
	}

	public String getOccupantType() {
		return occupantType;
	}

	public String getOwnershipType() {
		return ownershipType;
	}

	public String getRateType() {
		return rateType;
	}

	public String getState() {
		return state;
	}

	public double getDailyUsageUpAlertPercent() {
		return dailyUsageUpAlertPercent;
	}

	public String getFirstDataProcessTime() {
		return firstDataProcessTime;
	}

	public boolean isNotificationsBlocked() {
		return notificationsBlocked;
	}

	public double getDailyPeakToUUsageUpAlertPercent() {
		return dailyPeakToUUsageUpAlertPercent;
	}

	public boolean isSolarUser() {
		return solarUser;
	}

	public Integer getDefNhoodId() {
		return defNhoodId;
	}

	public Integer getDwelling() {
		return dwelling;
	}

	public boolean isCheckedDILSO() {
		return checkedDILSO;
	}

	public String getRatesSchedule() {
		return ratesSchedule;
	}

	public String getPath() {
		return path;
	}

	public boolean isDashboardLoaded() {
		return dashboardLoaded;
	}

	public Integer getUtilityid() {
		return utilityid;
	}

	public String getZip() {
		return zip;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public double getDefaultRate() {
		return defaultRate;
	}

	public String getSpaceHeatingType() {
		return spaceHeatingType;
	}

	public String getUtilityPassword() {
		return utilityPassword;
	}

	public boolean isNotifyEmailMonthlyStatus() {
		return notifyEmailMonthlyStatus;
	}

	public double getLotSize() {
		return lotSize;
	}

	public String getReleaseList() {
		return releaseList;
	}

	public String getDailyUsageDownAlertList() {
		return dailyUsageDownAlertList;
	}

	public boolean isQualifiedForSurvey() {
		return qualifiedForSurvey;
	}

	public String getMeterId() {
		return meterId;
	}

	public Integer getTotalRooms() {
		return totalRooms;
	}

	public String getAddress() {
		return address;
	}

	public Integer getNumberOfChildren() {
		return numberOfChildren;
	}

	public String getNotifyEmailWeeklyStatus() {
		return notifyEmailWeeklyStatus;
	}

	public String getDailyTierChangeAlertList() {
		return dailyTierChangeAlertList;
	}

	public boolean isFirstTimeGBConnectEmailed() {
		return firstTimeGBConnectEmailed;
	}

	public String getDailyPeakToUUsageUpAlertList() {
		return dailyPeakToUUsageUpAlertList;
	}

	public boolean isDailyTierChangeAlert() {
		return dailyTierChangeAlert;
	}

	public String getUtilityUserName() {
		return utilityUserName;
	}

	public boolean isDailyPeakToUUsageUpAlert() {
		return dailyPeakToUUsageUpAlert;
	}

	public String getDailyUsageUpAlertList() {
		return dailyUsageUpAlertList;
	}

	public boolean isSentMissingOutHan() {
		return sentMissingOutHan;
	}

	public String getSerial() {
		return serial;
	}

	public Integer getLastMonthBill() {
		return lastMonthBill;
	}

	public boolean isShowDevicePairing() {
		return showDevicePairing;
	}

	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}

	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public Object getValue(String key) {
		Map<String, Object> keyValues = new ImmutableMap.Builder<String, Object>().put("defNTypeId", defNTypeId == null
				? "null"
				: defNTypeId).put("billCycleCode", billCycleCode == null
				? "null"
				: billCycleCode).put("firstTimeHanConnectEmailed", firstTimeHanConnectEmailed == null
				? "null"
				: firstTimeHanConnectEmailed).put("masterTariffId", masterTariffId == null
				? "null"
				: masterTariffId).put("yearBuilt", yearBuilt == null
				? "null"
				: yearBuilt).put("bathrooms", bathrooms == null
				? "null"
				: bathrooms).put("dailyUsageDownAlert", dailyUsageDownAlert == null
				? "null"
				: dailyUsageDownAlert).put("numberOfAdults", numberOfAdults == null
				? "null"
				: numberOfAdults).put("city", city == null
				? "null"
				: city).put("numOccupants", numOccupants == null
				? "null"
				: numOccupants).put("nhood", nhood == null
				? "null"
				: nhood).put("timezone", timezone == null
				? "null"
				: timezone).put("phoneNumber", phoneNumber == null
				? "null"
				: phoneNumber).put("notifyEmailState", notifyEmailState == null
				? "null"
				: notifyEmailState).put("disconnectsSentPerWeek", disconnectsSentPerWeek == null
				? "null"
				: disconnectsSentPerWeek).put("sentSurvey1", sentSurvey1 == null
				? "null"
				: sentSurvey1).put("utilityunknown", utilityunknown == null
				? "null"
				: utilityunknown).put("dailyUsageUpAlert", dailyUsageUpAlert == null
				? "null"
				: dailyUsageUpAlert).put("enrollmentTime", enrollmentTime == null
				? "null"
				: enrollmentTime).put("supplementaryNhoodIds", supplementaryNhoodIds == null
				? "null"
				: supplementaryNhoodIds).put("lastProfileTime", lastProfileTime == null
				? "null"
				: lastProfileTime).put("emailsBlocked", emailsBlocked == null
				? "null"
				: emailsBlocked).put("notifyEmailGBState", notifyEmailGBState == null
				? "null"
				: notifyEmailGBState).put("sentSurvey3", sentSurvey3 == null
				? "null"
				: sentSurvey3).put("notifyEmailStatusChange", notifyEmailStatusChange == null
				? "null"
				: notifyEmailStatusChange).put("sentSurvey2", sentSurvey2 == null
				? "null"
				: sentSurvey2).put("plannumber", plannumber == null
				? "null"
				: plannumber).put("thermostats", thermostats == null
				? "null"
				: thermostats).put("utilityName", utilityName == null
				? "null"
				: utilityName).put("addressType", addressType == null
				? "null"
				: addressType).put("bedrooms", bedrooms == null
				? "null"
				: bedrooms).put("firstTimeGBConnectNotified", firstTimeGBConnectNotified == null
				? "null"
				: firstTimeGBConnectNotified).put("monthlySummaryList", monthlySummaryList == null
				? "null"
				: monthlySummaryList).put("rateQuery", rateQuery == null
				? "null"
				: rateQuery).put("livingArea", livingArea == null
				? "null"
				: livingArea).put("country", country == null
				? "null"
				: country).put("nsp", nsp == null
				? "null"
				: nsp).put("weeklySummaryList", weeklySummaryList == null
				? "null"
				: weeklySummaryList).put("planunknown", planunknown == null
				? "null"
				: planunknown).put("blackList", blackList == null
				? "null"
				: blackList).put("billstart", billstart == null
				? "null"
				: billstart).put("waterHeatingType", waterHeatingType == null
				? "null"
				: waterHeatingType).put("nst", nst == null
				? "null"
				: nst).put("lseId", lseId == null
				? "null"
				: lseId).put("dailyUsageDownAlertPercent", dailyUsageDownAlertPercent == null
				? "null"
				: dailyUsageDownAlertPercent).put("lastMonthKwh", lastMonthKwh == null
				? "null"
				: lastMonthKwh).put("sentMissingData", sentMissingData == null
				? "null"
				: sentMissingData).put("occupantType", occupantType == null
				? "null"
				: occupantType).put("ownershipType", ownershipType == null
				? "null"
				: ownershipType).put("rateType", rateType == null
				? "null"
				: rateType).put("state", state == null
				? "null"
				: state).put("dailyUsageUpAlertPercent", dailyUsageUpAlertPercent == null
				? "null"
				: dailyUsageUpAlertPercent).put("firstDataProcessTime", firstDataProcessTime == null
				? "null"
				: firstDataProcessTime).put("notificationsBlocked", notificationsBlocked == null
				? "null"
				: notificationsBlocked).put("dailyPeakToUUsageUpAlertPercent", dailyPeakToUUsageUpAlertPercent == null
				? "null"
				: dailyPeakToUUsageUpAlertPercent).put("solarUser", solarUser == null
				? "null"
				: solarUser).put("defNhoodId", defNhoodId == null
				? "null"
				: defNhoodId).put("dwelling", dwelling == null
				? "null"
				: dwelling).put("checkedDILSO", checkedDILSO == null
				? "null"
				: checkedDILSO).put("ratesSchedule", ratesSchedule == null
				? "null"
				: ratesSchedule).put("path", path == null
				? "null"
				: path).put("dashboardLoaded", dashboardLoaded == null
				? "null"
				: dashboardLoaded).put("utilityid", utilityid == null
				? "null"
				: utilityid).put("heatingType",heatingType == null
				? "null"
				: heatingType).put("zip", zip == null
				? "null"
				: zip).put("ratePlanId", ratePlanId == null
				? "null"
				: ratePlanId).put("defaultRate", defaultRate == null
				? "null"
				: defaultRate).put("spaceHeatingType", spaceHeatingType == null
				? "null"
				: spaceHeatingType).put("utilityPassword", utilityPassword == null
				? "null"
				: utilityPassword).put("notifyEmailMonthlyStatus", notifyEmailMonthlyStatus == null
				? "null"
				: notifyEmailMonthlyStatus).put("lotSize", lotSize == null
				? "null"
				: lotSize).put("releaseList", releaseList == null
				? "null"
				: releaseList).put("dailyUsageDownAlertList", dailyUsageDownAlertList == null
				? "null"
				: dailyUsageDownAlertList).put("qualifiedForSurvey", qualifiedForSurvey == null
				? "null"
				: qualifiedForSurvey).put("meterId", meterId == null
				? "null"
				: meterId).put("totalRooms", totalRooms == null
				? "null"
				: totalRooms).put("address", address == null
				? "null"
				: address).put("numberOfChildren", numberOfChildren == null
				? "null"
				: numberOfChildren).put("notifyEmailWeeklyStatus", notifyEmailWeeklyStatus == null
				? "null"
				: notifyEmailWeeklyStatus).put("dailyTierChangeAlertList", dailyTierChangeAlertList == null
				? "null"
				: dailyTierChangeAlertList).put("firstTimeGBConnectEmailed", firstTimeGBConnectEmailed == null
				? "null"
				: firstTimeGBConnectEmailed).put("dailyPeakToUUsageUpAlertList", dailyPeakToUUsageUpAlertList == null
				? "null"
				: dailyPeakToUUsageUpAlertList).put("dailyTierChangeAlert", dailyTierChangeAlert == null
				? "null"
				: dailyTierChangeAlert).put("utilityUserName", utilityUserName == null
				? "null"
				: utilityUserName).put("dailyPeakToUUsageUpAlert", dailyPeakToUUsageUpAlert == null
				? "null"
				: dailyPeakToUUsageUpAlert).put("dailyUsageUpAlertList", dailyUsageUpAlertList == null
				? "null"
				: dailyUsageUpAlertList).put("sentMissingOutHan", sentMissingOutHan == null
				? "null"
				: sentMissingOutHan).put("serial", serial == null
				? "null"
				: serial).put("lastMonthBill", lastMonthBill == null
				? "null"
				: lastMonthBill).put("showDevicePairing", showDevicePairing == null
				? "null"
				: showDevicePairing).put("secondaryPhoneNumber", secondaryPhoneNumber == null
				? "null"
				: secondaryPhoneNumber).build();

		return keyValues.get(key);
	}

	public boolean equals(Object obj) {


		if (obj instanceof MetaUserHomeApiResponse) {
			MetaUserHomeApiResponse metaUserHomeApiResponse = (MetaUserHomeApiResponse) obj;
			if (this.defNTypeId != null) {
				if (!this.defNTypeId.equals(metaUserHomeApiResponse.getDefNTypeId())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected defNTypeId : " +
									this.defNTypeId +
									"\nActual defNTypeId :" +
									metaUserHomeApiResponse.getDefNTypeId());
					return false;
				}
			}

			if (this.billCycleCode != null) {
				if (!this.billCycleCode.equals(metaUserHomeApiResponse.getBillCycleCode())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected billCycleCode : " +
									this.billCycleCode +
									"\nActual billCycleCode :" +
									metaUserHomeApiResponse.getBillCycleCode());
					return false;
				}
			}

			if (this.firstTimeHanConnectEmailed != null) {
				if (!this.firstTimeHanConnectEmailed.equals(metaUserHomeApiResponse.isFirstTimeHanConnectEmailed())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected firstTimeHanConnectEmailed : " +
									this.firstTimeHanConnectEmailed +
									"\nActual firstTimeHanConnectEmailed :" +
									metaUserHomeApiResponse.isFirstTimeHanConnectEmailed());
					return false;
				}
			}

			if (this.dailyUsageDownAlert != null) {
				if (!this.dailyUsageDownAlert.equals(metaUserHomeApiResponse.isDailyUsageDownAlert())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dailyUsageDownAlert : " +
									this.dailyUsageDownAlert +
									"\nActual dailyUsageDownAlert :" +
									metaUserHomeApiResponse.isDailyUsageDownAlert());
					return false;
				}
			}

			if (this.city != null) {
				if (!this.city.equals(metaUserHomeApiResponse.getCity())) {
					logger.error("expected city : " + this.city +
							"\nActual city :" +
							metaUserHomeApiResponse.getCity());
					return false;
				}
			}

			if (this.numOccupants != null) {
				if (!this.numOccupants.equals(metaUserHomeApiResponse.getNumOccupants())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected numOccupants : " +
									this.numOccupants +
									"\nActual numOccupants :" +
									metaUserHomeApiResponse.getNumOccupants());
					return false;
				}
			}

			if (this.timezone != null) {
				if (!this.timezone.equals(metaUserHomeApiResponse.getTimezone())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected timezone : " +
									this.timezone + "\nActual timezone :" +
									metaUserHomeApiResponse.getTimezone());
					return false;
				}
			}

			if (this.sentSurvey1 != null) {
				if (!this.sentSurvey1.equals(metaUserHomeApiResponse.isSentSurvey1())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected sentSurvey1 : " +
									this.sentSurvey1 +
									"\nActual sentSurvey1 :" +
									metaUserHomeApiResponse.isSentSurvey1());
					return false;
				}
			}

			if (this.dailyUsageUpAlert != null) {
				if (!this.dailyUsageUpAlert.equals(metaUserHomeApiResponse.getDailyUsageUpAlert())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dailyUsageUpAlert : " +
									this.dailyUsageUpAlert +
									"\nActual dailyUsageUpAlert :" +
									metaUserHomeApiResponse.getDailyUsageUpAlert());
					return false;
				}
			}

			if (this.supplementaryNhoodIds != null) {
				if (!this.supplementaryNhoodIds.equals(metaUserHomeApiResponse.getSupplementaryNhoodIds())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected supplementaryNhoodIds : " +
									this.supplementaryNhoodIds +
									"\nActual supplementaryNhoodIds :" +
									metaUserHomeApiResponse.getSupplementaryNhoodIds());
					return false;
				}
			}

			if (this.lastProfileTime != null) {
				if (!this.lastProfileTime.equals(metaUserHomeApiResponse.getLastProfileTime())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected lastProfileTime : " +
									this.lastProfileTime +
									"\nActual lastProfileTime :" +
									metaUserHomeApiResponse.getLastProfileTime());
					return false;
				}
			}

			if (this.emailsBlocked != null) {
				if (!this.emailsBlocked.equals(metaUserHomeApiResponse.isEmailsBlocked())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected emailsBlocked : " +
									this.emailsBlocked +
									"\nActual emailsBlocked :" +
									metaUserHomeApiResponse.isEmailsBlocked());
					return false;
				}
			}

			if (this.sentSurvey3 != null) {
				if (!this.sentSurvey3.equals(metaUserHomeApiResponse.isSentSurvey3())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected sentSurvey3 : " +
									this.sentSurvey3 +
									"\nActual sentSurvey3 :" +
									metaUserHomeApiResponse.isSentSurvey3());
					return false;
				}
			}

			if (this.notifyEmailStatusChange != null) {
				if (!this.notifyEmailStatusChange.equals(metaUserHomeApiResponse.isNotifyEmailStatusChange())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected notifyEmailStatusChange : " +
									this.notifyEmailStatusChange +
									"\nActual notifyEmailStatusChange :" +
									metaUserHomeApiResponse.isNotifyEmailStatusChange());
					return false;
				}
			}

			if (this.sentSurvey2 != null) {
				if (!this.sentSurvey2.equals(metaUserHomeApiResponse.isSentSurvey2())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected sentSurvey2 : " +
									this.sentSurvey2 +
									"\nActual sentSurvey2 :" +
									metaUserHomeApiResponse.isSentSurvey2());
					return false;
				}
			}

			if (this.plannumber != null) {
				if (!this.plannumber.equals(metaUserHomeApiResponse.getPlannumber())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected plannumber : " +
									this.plannumber +
									"\nActual plannumber :" +
									metaUserHomeApiResponse.getPlannumber());
					return false;
				}
			}

			if (this.firstTimeGBConnectNotified != null) {
				if (!this.firstTimeGBConnectNotified.equals(metaUserHomeApiResponse.isFirstTimeGBConnectNotified())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected firstTimeGBConnectNotified : " +
									this.firstTimeGBConnectNotified +
									"\nActual firstTimeGBConnectNotified :" +
									metaUserHomeApiResponse.isFirstTimeGBConnectNotified());
					return false;
				}
			}

			if (this.country != null) {
				if (!this.country.equals(metaUserHomeApiResponse.getCountry())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected country : " +
									this.country + "\nActual country :" +
									metaUserHomeApiResponse.getCountry());
					return false;
				}
			}

			if (this.billstart != null) {
				if (!this.billstart.equals(metaUserHomeApiResponse.getBillstart())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected billstart : " +
									this.billstart +
									"\nActual billstart :" +
									metaUserHomeApiResponse.getBillstart());
					return false;
				}
			}

			if (this.dailyUsageDownAlertPercent != null) {
				if (!this.dailyUsageDownAlertPercent.equals(metaUserHomeApiResponse.getDailyUsageDownAlertPercent())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dailyUsageDownAlertPercent : " +
									this.dailyUsageDownAlertPercent +
									"\nActual dailyUsageDownAlertPercent :" +
									metaUserHomeApiResponse.getDailyUsageDownAlertPercent());
					return false;
				}
			}

			if (this.sentMissingData != null) {
				if (!this.sentMissingData.equals(metaUserHomeApiResponse.isSentMissingData())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected sentMissingData : " +
									this.sentMissingData +
									"\nActual sentMissingData :" +
									metaUserHomeApiResponse.isSentMissingData());
					return false;
				}
			}

			if (this.rateType != null) {
				if (!this.rateType.equals(metaUserHomeApiResponse.getRateType())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected rateType : " +
									this.rateType + "\nActual rateType :" +
									metaUserHomeApiResponse.getRateType());
					return false;
				}
			}

			if (this.state != null) {
				if (!this.state.equals(metaUserHomeApiResponse.getState())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected state : " +
									this.state + "\nActual state :" +
									metaUserHomeApiResponse.getState());
					return false;
				}
			}

			if (this.dailyUsageUpAlertPercent != null) {
				if (!this.dailyUsageUpAlertPercent.equals(metaUserHomeApiResponse.getDailyUsageUpAlertPercent())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dailyUsageUpAlertPercent : " +
									this.dailyUsageUpAlertPercent +
									"\nActual dailyUsageUpAlertPercent :" +
									metaUserHomeApiResponse.getDailyUsageUpAlertPercent());
					return false;
				}
			}

			if (this.firstDataProcessTime != null) {
				if (!this.firstDataProcessTime.equals(metaUserHomeApiResponse.getFirstDataProcessTime())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected firstDataProcessTime : " +
									this.firstDataProcessTime +
									"\nActual firstDataProcessTime :" +
									metaUserHomeApiResponse.getFirstDataProcessTime());
					return false;
				}
			}

			if (this.defNTypeId != null) {
				if (!this.defNTypeId.equals(metaUserHomeApiResponse.getDefNTypeId())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected defNTypeId : " +
									this.defNTypeId +
									"\nActual defNTypeId :" +
									metaUserHomeApiResponse.getDefNTypeId());
					return false;
				}
			}

			if (this.notificationsBlocked != null) {
				if (!this.notificationsBlocked.equals(metaUserHomeApiResponse.isNotificationsBlocked())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected notificationsBlocked : " +
									this.notificationsBlocked +
									"\nActual notificationsBlocked :" +
									metaUserHomeApiResponse.isNotificationsBlocked());
					return false;
				}
			}

			if (this.dailyPeakToUUsageUpAlertPercent != null) {
				if (!this.dailyPeakToUUsageUpAlertPercent.equals(metaUserHomeApiResponse.getDailyPeakToUUsageUpAlertPercent())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dailyPeakToUUsageUpAlertPercent : " +
									this.dailyPeakToUUsageUpAlertPercent +
									"\nActual dailyPeakToUUsageUpAlertPercent :" +
									metaUserHomeApiResponse.getDailyPeakToUUsageUpAlertPercent());
					return false;
				}
			}

			if (this.solarUser != null) {
				if (!this.solarUser.equals(metaUserHomeApiResponse.isSolarUser())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected solarUser : " +
									this.solarUser +
									"\nActual solarUser :" +
									metaUserHomeApiResponse.isSolarUser());
					return false;
				}
			}

			if (this.defNhoodId != null) {
				if (!(metaUserHomeApiResponse.getDefNhoodId() >= 0)) {
					logger.error(
							"MetaUserHomeAPIResponse : expected defNhoodId : " +
									this.defNhoodId +
									"\nActual defNhoodId :" +
									metaUserHomeApiResponse.getDefNhoodId());
					return false;
				}
			}

			if (this.dwelling != null) {
				if (!this.dwelling.equals(metaUserHomeApiResponse.getDwelling())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dwelling : " +
									this.dwelling + "\nActual dwelling :" +
									metaUserHomeApiResponse.getDwelling());
					return false;
				}
			}

			if (this.checkedDILSO != null) {
				if (!this.checkedDILSO.equals(metaUserHomeApiResponse.isCheckedDILSO())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected checkedDILSO : " +
									this.checkedDILSO +
									"\nActual checkedDILSO :" +
									metaUserHomeApiResponse.isCheckedDILSO());
					return false;
				}
			}

			if (this.path != null) {
				if (!this.path.equals(metaUserHomeApiResponse.getPath())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected path : " +
									this.path + "\nActual path :" +
									metaUserHomeApiResponse.getPath());
					return false;
				}
			}

			if (this.dashboardLoaded != null) {
				if (!this.dashboardLoaded.equals(metaUserHomeApiResponse.isDashboardLoaded())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dashboardLoaded : " +
									this.dashboardLoaded +
									"\nActual dashboardLoaded :" +
									metaUserHomeApiResponse.isDashboardLoaded());
					return false;
				}
			}

			if (this.utilityid != null) {
				if (!this.utilityid.equals(metaUserHomeApiResponse.getUtilityid())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected utilityid : " +
									this.utilityid +
									"\nActual utilityid :" +
									metaUserHomeApiResponse.getUtilityid());
					return false;
				}
			}

			if (this.zip != null) {
				if (!this.zip.equals(metaUserHomeApiResponse.getZip())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected zip : " +
									this.zip + "\nActual zip :" +
									metaUserHomeApiResponse.getZip());
					return false;
				}
			}

			if (this.ratePlanId != null) {
				if (!this.ratePlanId.equals(metaUserHomeApiResponse.getRatePlanId())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected ratePlanId : " +
									this.ratePlanId +
									"\nActual ratePlanId :" +
									metaUserHomeApiResponse.getRatePlanId());
					return false;
				}
			}

			if (this.defaultRate != null) {
				if (!this.defaultRate.equals(metaUserHomeApiResponse.getDefaultRate())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected defaultRate : " +
									this.defaultRate +
									"\nActual defaultRate :" +
									metaUserHomeApiResponse.getDefaultRate());
					return false;
				}
			}

			if (this.notifyEmailMonthlyStatus != null) {
				if (!this.notifyEmailMonthlyStatus.equals(metaUserHomeApiResponse.isNotifyEmailMonthlyStatus())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected notifyEmailMonthlyStatus : " +
									this.notifyEmailMonthlyStatus +
									"\nActual notifyEmailMonthlyStatus :" +
									metaUserHomeApiResponse.isNotifyEmailMonthlyStatus());
					return false;
				}
			}

			if (this.lotSize != null) {
				if (!this.lotSize.equals(metaUserHomeApiResponse.getLotSize())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected lotSize : " +
									this.lotSize + "\nActual lotSize :" +
									metaUserHomeApiResponse.getLotSize());
					return false;
				}
			}

			if (this.releaseList != null) {
				if (!this.releaseList.equals(metaUserHomeApiResponse.getReleaseList())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected releaseList : " +
									this.releaseList +
									"\nActual releaseList :" +
									metaUserHomeApiResponse.getReleaseList());
					return false;
				}
			}

			if (this.qualifiedForSurvey != null) {
				if (!this.qualifiedForSurvey.equals(metaUserHomeApiResponse.isQualifiedForSurvey())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected qualifiedForSurvey : " +
									this.qualifiedForSurvey +
									"\nActual qualifiedForSurvey :" +
									metaUserHomeApiResponse.isQualifiedForSurvey());
					return false;
				}
			}

			if (this.address != null) {
				if (!this.address.equals(metaUserHomeApiResponse.getAddress())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected address : " +
									this.address + "\nActual address :" +
									metaUserHomeApiResponse.getAddress());
					return false;
				}
			}

			if (this.notifyEmailWeeklyStatus != null) {
				if (!this.notifyEmailWeeklyStatus.equals(metaUserHomeApiResponse.getNotifyEmailWeeklyStatus())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected notifyEmailWeeklyStatus : " +
									this.notifyEmailWeeklyStatus +
									"\nActual notifyEmailWeeklyStatus :" +
									metaUserHomeApiResponse.getNotifyEmailWeeklyStatus());
					return false;
				}
			}

			if (this.firstTimeGBConnectEmailed != null) {
				if (!this.firstTimeGBConnectEmailed.equals(metaUserHomeApiResponse.isFirstTimeGBConnectEmailed())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected firstTimeGBConnectEmailed : " +
									this.firstTimeGBConnectEmailed +
									"\nActual firstTimeGBConnectEmailed :" +
									metaUserHomeApiResponse.isFirstTimeGBConnectEmailed());
					return false;
				}
			}

			if (this.dailyTierChangeAlert != null) {
				if (!this.dailyTierChangeAlert.equals(metaUserHomeApiResponse.isDailyTierChangeAlert())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dailyTierChangeAlert : " +
									this.dailyTierChangeAlert +
									"\nActual dailyTierChangeAlert :" +
									metaUserHomeApiResponse.isDailyTierChangeAlert());
					return false;
				}
			}

			if (this.dailyPeakToUUsageUpAlert != null) {
				if (!this.dailyPeakToUUsageUpAlert.equals(metaUserHomeApiResponse.isDailyPeakToUUsageUpAlert())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected dailyPeakToUUsageUpAlert : " +
									this.dailyPeakToUUsageUpAlert +
									"\nActual dailyPeakToUUsageUpAlert :" +
									metaUserHomeApiResponse.isDailyPeakToUUsageUpAlert());
					return false;
				}
			}

			if (this.sentMissingOutHan != null) {
				if (!this.sentMissingOutHan.equals(metaUserHomeApiResponse.isSentMissingOutHan())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected sentMissingOutHan : " +
									this.sentMissingOutHan +
									"\nActual sentMissingOutHan :" +
									metaUserHomeApiResponse.isSentMissingOutHan());
					return false;
				}
			}

			if (this.showDevicePairing != null) {
				if (!this.showDevicePairing.equals(metaUserHomeApiResponse.isShowDevicePairing())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected showDevicePairing : " +
									this.showDevicePairing +
									"\nActual showDevicePairing :" +
									metaUserHomeApiResponse.isShowDevicePairing());
					return false;
				}
			}

			if (this.secondaryPhoneNumber != null) {
				if (!this.secondaryPhoneNumber.equals(metaUserHomeApiResponse.getSecondaryPhoneNumber())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected secondaryPhoneNumber : " +
									this.secondaryPhoneNumber +
									"\nActual secondaryPhoneNumber :" +
									metaUserHomeApiResponse.getSecondaryPhoneNumber());
					return false;
				}
			}

			if (this.mailingAddress != null) {
				if (!this.mailingAddress.equals(metaUserHomeApiResponse.getMailingAddress())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected mailingAddress : " +
									this.mailingAddress +
									"\nActual mailingAddress :" +
									metaUserHomeApiResponse.getMailingAddress());
					return false;
				}
			}

			if (this.latitude != null) {
				if(metaUserHomeApiResponse.getLatitude() == null) {
					logger.error(
							"MetaUserHomeAPIResponse : expected latitude : " +
									this.latitude + "\nActual latitude :" +
									" null");
				}
				if ((int) Double.parseDouble(this.latitude) !=
						(int) Double.parseDouble(metaUserHomeApiResponse.getLatitude())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected latitude : " +
									this.latitude + "\nActual latitude :" +
									metaUserHomeApiResponse.getLatitude());
					return false;
				}
			}

			if (this.longitude != null) {
				if(metaUserHomeApiResponse.getLongitude() == null) {
					logger.error(
							"MetaUserHomeAPIResponse : expected longitude : " +
									this.latitude + "\nActual longitude :" +
									" null");
				}

				if ((int) Double.parseDouble(this.longitude) !=
						(int) Double.parseDouble(metaUserHomeApiResponse.getLongitude())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected longitude : " +
									this.longitude +
									"\nActual longitude :" +
									metaUserHomeApiResponse.getLongitude());
					return false;
				}
			}

			if (this.livingArea != null) {
				if (!this.livingArea.equals(metaUserHomeApiResponse.getLivingArea())) {
					logger.error(
							"MetaUserHomeAPIResponse : expected livingArea : " +
									this.livingArea +
									"\nActual livingArea :" +
									metaUserHomeApiResponse.getLivingArea());
					return false;
				}
			}
		}


		return true;
	}

	private String getMethodName(NullPointerException e) {
		StackTraceElement[] stackTrace = e.getStackTrace();
		if (stackTrace.length > 0) {
			return stackTrace[0].getMethodName();
		}
		return "Unknown Method";
	}

}
