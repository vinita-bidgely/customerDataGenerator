package com.bidgely.customerDataGenerator.configs;


import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import java.util.List;

public class RecreateProdUserDataConfig
		extends BaseConfig {

	private String localUserUUID;   // user we want to create raw file
	private String  prodBaseUrl;
	private String prodAccessToken;
	private String filePrefix;
	private String fileSuffix;
	private Integer gatewayId;
	private String timeZone;
	private String dateFormat;
	private String houseType;
	private String subPilot;
	private String inputMeterFuel;  /* comma separated string for eg: AMI-ELECTRIC,AMI-GAS e.t.c */
	private int startTimeStamp;
	private int endTimeStamp;
	private List<String> usersList; // List of user UUIDs from config
	private String fileUploadBucket; // for storing files
	private String ingestionBucket; // pilot specific bucket to for creating user

	private boolean constructUserFile;
	private boolean constructMeterFile;
	private boolean constructRawFile;
	private boolean constructInvoiceFile;
	private String billCycleCode; /* to modify bcCode, as it is differ from env to env */
	private String meterContractDateFormat = "yyyy-MM-dd";  /* Date format for serviceStart & end Dates formatting */
	private String userPrefFile;
	private String t0 = "1";
	private String t1 = "2042876338";
	private String enrollmentTime;
	private String clusterId;
	private String defNhoodId;
	private String notificationUserType;
	private String destinationPilotName;
	private String destinationPilotId;
	private String sourcePilotId;
	private String sourcePilotName;

	// if user already exist we fetch user details(customerId, premiseId, e.t.c) from Api's,
	// otherWise we directly use it for execution variable as we provide in userCreator class
	private boolean createNewUser;

	public int getEndTimeStamp() {
		return endTimeStamp;
	}

	public int getStartTimeStamp() {
		return startTimeStamp;
	}

	public String getLocalUserUUID() {
		return localUserUUID;
	}

	public boolean isCreateNewUser() {
		return createNewUser;
	}

	public String getInputMeterFuel() {
		return inputMeterFuel.toUpperCase();
	}

	public String getTimeZone() {
		return timeZone;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public String getHouseType() {
		return houseType;
	}

	public String getSubPilot() {
		return subPilot;
	}

	public Integer getGatewayId() {
		return gatewayId;
	}


	public String getProdBaseUrl() {
		return prodBaseUrl;
	}

	public String getProdAccessToken() {
		return prodAccessToken;
	}

	public String getFilePrefix() {
		return filePrefix;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public String getIngestionBucket() {
		return ingestionBucket;
	}

	// Get list of users from config
	public List<String> getUsersList() {
		return usersList;
	}

	public String getFileUploadBucket() {
		return fileUploadBucket;
	}

	public boolean isConstructUserFile() {
		return constructUserFile;
	}

	public boolean isConstructMeterFile() {
		return constructMeterFile;
	}

	public boolean isConstructRawFile() {
		return constructRawFile;
	}

	public boolean isConstructInvoiceFile() {
		return constructInvoiceFile;
	}

	public String getBillCycleCode() {
		return billCycleCode;
	}

	public String getMeterContractDateFormat() {
		return meterContractDateFormat;
	}

	// todo - Will implememt later as per requirement
//	public String getUserPrefFile(ExecutionVariables executionVariables) throws BidgelyExceptions {
//		return userPrefFile!=null && !userPrefFile.isEmpty()? getSubstitutedFilePaths(userPrefFile, executionVariables): userPrefFile;
//	}

	public String getT0() {
		return t0;
	}

	public String getT1() {
		return t1;
	}

	public String getEnrollmentTime() {
		return enrollmentTime;
	}

	public String getClusterId() {
		return clusterId;
	}

	public String getDefNhoodId() {
		return defNhoodId;
	}

	public String getNotificationUserType() {
		return notificationUserType;
	}

	public String getDestinationPilotId() {
		return destinationPilotId==null || destinationPilotId.isEmpty()? null :destinationPilotId;
	}

	public String getDestinationPilotName() {
		return destinationPilotName==null || destinationPilotName.isEmpty()? null :destinationPilotName;
	}
	public String getSourcePilotId() {
		return sourcePilotId;
	}
	public void setSourcePilotId(String sourcePilotId) {
		this.sourcePilotId = sourcePilotId;
	}

	public String getSourcePilotName() {
		return sourcePilotName;
	}

	public void setSourcePilotName(String sourcePilotName) {
		this.sourcePilotName = sourcePilotName;
	}

	// Setter methods for API support
	public void setProdBaseUrl(String prodBaseUrl) {
		this.prodBaseUrl = prodBaseUrl;
	}

	public void setProdAccessToken(String prodAccessToken) {
		this.prodAccessToken = prodAccessToken;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}

	public void setSubPilot(String subPilot) {
		this.subPilot = subPilot;
	}

	public void setInputMeterFuel(String inputMeterFuel) {
		this.inputMeterFuel = inputMeterFuel;
	}

	public void setIngestionBucket(String ingestionBucket) {
		this.ingestionBucket = ingestionBucket;
	}

	public void setFileUploadBucket(String fileUploadBucket) {
		this.fileUploadBucket = fileUploadBucket;
	}

	public void setConstructUserFile(boolean constructUserFile) {
		this.constructUserFile = constructUserFile;
	}

	public void setConstructRawFile(boolean constructRawFile) {
		this.constructRawFile = constructRawFile;
	}

	public void setConstructInvoiceFile(boolean constructInvoiceFile) {
		this.constructInvoiceFile = constructInvoiceFile;
	}

	public void setBillCycleCode(String billCycleCode) {
		this.billCycleCode = billCycleCode;
	}

	public void setUsersList(List<String> usersList) {
		this.usersList = usersList;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setT0(String t0) {
		this.t0 = t0;
	}

	public void setT1(String t1) {
		this.t1 = t1;
	}

	public void setDestinationPilotName(String destinationPilotName) {
		this.destinationPilotName = destinationPilotName;
	}

	public void setDestinationPilotId(String destinationPilotId) {
		this.destinationPilotId = destinationPilotId;
	}

}
