package com.bidgely.configs;


import com.bidgely.qa.core.context.variables.ExecutionVariables;
import com.bidgely.exceptions.BidgelyExceptions;
import com.bidgely.configs.BaseConfig.BaseConfig;

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
	private String rawDataStructureFilePath;
	private int startTimeStamp;
	private int endTimeStamp;
	private String usersFileLocation;
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

	public String getRawDataStructureFilePath(ExecutionVariables executionVariables) throws BidgelyExceptions {
		return rawDataStructureFilePath!=null && !rawDataStructureFilePath.isEmpty() ?getSubstitutedFilePaths(rawDataStructureFilePath, executionVariables) :null;
	}

	public String getUsersFileLocation(ExecutionVariables executionVariables) throws
			BidgelyExceptions {
		return getSubstitutedFilePaths(usersFileLocation, executionVariables);
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

	public String getBillCycleCode(ExecutionVariables executionVariables) {
		return executionVariables.containsKey(billCycleCode)? executionVariables.get(billCycleCode).toString(): billCycleCode;
	}

	public String getMeterContractDateFormat() {
		return meterContractDateFormat;
	}

	public String getUserPrefFile(ExecutionVariables executionVariables) throws BidgelyExceptions {
		return userPrefFile!=null && !userPrefFile.isEmpty()? getSubstitutedFilePaths(userPrefFile, executionVariables): userPrefFile;
	}

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
}
