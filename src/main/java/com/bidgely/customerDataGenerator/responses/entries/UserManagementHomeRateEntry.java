package com.bidgely.customerDataGenerator.responses.entries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserManagementHomeRateEntry {
	private String  planName;

	private String  rateType;

	private String  ratePlanId;

	private Integer utilityId;

	private String  billCycleCode;

	private String  masterTariffId;

	private Double  dailyUsageUpAlertPercent;

	private String  utilityPassword;

	private String  utilityName;

	private Integer billStart;

	private String  rateQuery;

	private Double  rate;

	private Integer lseId;

	private String  utilityUserName;

	private Double  dailyUsageDownAlertPercent;

	private Integer planNumber;

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getRateType() {
		return rateType;
	}

	public void setRateType(String rateType) {
		this.rateType = rateType;
	}

	public String getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(String ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public Integer getUtilityId() {
		return utilityId;
	}

	public void setUtilityId(Integer utilityId) {
		this.utilityId = utilityId;
	}

	public String getBillCycleCode() {
		return billCycleCode;
	}

	public void setBillCycleCode(String billCycleCode) {
		this.billCycleCode = billCycleCode;
	}

	public String getMasterTariffId() {
		return masterTariffId;
	}

	public void setMasterTariffId(String masterTariffId) {
		this.masterTariffId = masterTariffId;
	}

	public Double getDailyUsageUpAlertPercent() {
		return dailyUsageUpAlertPercent;
	}

	public void setDailyUsageUpAlertPercent(Double dailyUsageUpAlertPercent) {
		this.dailyUsageUpAlertPercent = dailyUsageUpAlertPercent;
	}

	public String getUtilityPassword() {
		return utilityPassword;
	}

	public void setUtilityPassword(String utilityPassword) {
		this.utilityPassword = utilityPassword;
	}

	public String getUtilityName() {
		return utilityName;
	}

	public void setUtilityName(String utilityName) {
		this.utilityName = utilityName;
	}

	public Integer getBillStart() {
		return billStart;
	}

	public void setBillStart(Integer billStart) {
		this.billStart = billStart;
	}

	public String getRateQuery() {
		return rateQuery;
	}

	public void setRateQuery(String rateQuery) {
		this.rateQuery = rateQuery;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getLseId() {
		return lseId;
	}

	public void setLseId(Integer lseId) {
		this.lseId = lseId;
	}

	public String getUtilityUserName() {
		return utilityUserName;
	}

	public void setUtilityUserName(String utilityUserName) {
		this.utilityUserName = utilityUserName;
	}

	public Double getDailyUsageDownAlertPercent() {
		return dailyUsageDownAlertPercent;
	}

	public void setDailyUsageDownAlertPercent(Double dailyUsageDownAlertPercent) {
		this.dailyUsageDownAlertPercent = dailyUsageDownAlertPercent;
	}

	public Integer getPlanNumber() {
		return planNumber;
	}

	public void setPlanNumber(Integer planNumber) {
		this.planNumber = planNumber;
	}

	public boolean equals(Object obj) {

		if (obj instanceof UserManagementHomeRateEntry) {
			UserManagementHomeRateEntry userManagementHomeRateEntry = (UserManagementHomeRateEntry) obj;
			if (!(this.utilityPassword == null ? userManagementHomeRateEntry.getUtilityPassword() == null
					: this.utilityPassword.equals(userManagementHomeRateEntry.getUtilityPassword()))
					|| !(this.dailyUsageDownAlertPercent == null
					? userManagementHomeRateEntry.getDailyUsageDownAlertPercent() == null
					: this.dailyUsageDownAlertPercent
					.equals(userManagementHomeRateEntry.getDailyUsageDownAlertPercent()))
					|| !(this.utilityId == null ? userManagementHomeRateEntry.getUtilityId() == null
					: this.utilityId.equals(userManagementHomeRateEntry.getUtilityId()))
					|| !(this.dailyUsageUpAlertPercent == null
					? userManagementHomeRateEntry.getDailyUsageUpAlertPercent() == null
					: this.dailyUsageUpAlertPercent
					.equals(userManagementHomeRateEntry.getDailyUsageUpAlertPercent()))
					|| !(this.ratePlanId == null ? userManagementHomeRateEntry.getRatePlanId() == null
					: this.ratePlanId.equals(userManagementHomeRateEntry.getRatePlanId()))
					|| !(this.planName == null ? userManagementHomeRateEntry.getPlanName() == null
					: this.planName.equals(userManagementHomeRateEntry.getPlanName()))
					|| !(this.rateQuery == null ? userManagementHomeRateEntry.getRateQuery() == null
					: this.rateQuery.equals(userManagementHomeRateEntry.getRateQuery()))
					|| !(this.planNumber == null ? userManagementHomeRateEntry.getPlanNumber() == null
					: this.planNumber.equals(userManagementHomeRateEntry.getPlanNumber()))
					|| !(this.rateType == null ? userManagementHomeRateEntry.getRateType() == null
					: this.rateType.equals(userManagementHomeRateEntry.getRateType()))
					|| !(this.masterTariffId == null ? userManagementHomeRateEntry.getMasterTariffId() == null
					: this.masterTariffId.equals(userManagementHomeRateEntry.getMasterTariffId()))
					|| !(this.rate == null ? userManagementHomeRateEntry.getRate() == null
					: this.rate.equals(userManagementHomeRateEntry.getRate()))
					|| !(this.billStart == null ? userManagementHomeRateEntry.getBillStart() == null
					: this.billStart.equals(userManagementHomeRateEntry.getBillStart()))
					|| !(this.utilityUserName == null ? userManagementHomeRateEntry.getUtilityUserName() == null
					: this.utilityUserName.equals(userManagementHomeRateEntry.getUtilityUserName()))
					|| !(this.billCycleCode == null ? userManagementHomeRateEntry.getBillCycleCode() == null
					: this.billCycleCode.equals(userManagementHomeRateEntry.getBillCycleCode()))
					|| !(this.utilityName == null ? userManagementHomeRateEntry.getUtilityName() == null
					: this.utilityName.equals(userManagementHomeRateEntry.getUtilityName()))
					|| !(this.lseId == null ? userManagementHomeRateEntry.getLseId() == null
					: this.lseId.equals(userManagementHomeRateEntry.getLseId()))) {
				return false;
			}

		}
		return true;
	}
}
