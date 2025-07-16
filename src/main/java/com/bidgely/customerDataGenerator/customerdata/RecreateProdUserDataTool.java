package com.bidgely.customerDataGenerator.customerdata;

import com.bidgely.customerDataGenerator.api.*;
import com.bidgely.customerDataGenerator.commonExecutionResources.RecreateProdUserDataContextHandler;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
//import com.bidgely.qa.core.api.responses.*;
import com.bidgely.customerDataGenerator.responses.*;
import com.bidgely.customerDataGenerator.responses.InvoiceDataList.MetaUserApiResponse;
//import com.bidgely.responses.*;
import com.bidgely.customerDataGenerator.aws.S3;
import com.bidgely.customerDataGenerator.commons.RetryCommand;
import com.bidgely.customerDataGenerator.commons.SharedResources;
import com.bidgely.customerDataGenerator.commons.Validate;
import com.bidgely.customerDataGenerator.commonExecutionResources.ExecutionVariables;
// import com.bidgely.customerDataGenerator.commonExecutionResources.ExecutionContext;
import com.bidgely.customerDataGenerator.configs.RecreateProdUserDataConfig;
import com.bidgely.customerDataGenerator.commons.BillCycleCodes;
import com.bidgely.customerDataGenerator.responses.entries.GBConsumptionDataResponseEntry;
import com.bidgely.customerDataGenerator.responses.entries.UserManagementUserRegistrationEntry;
import com.bidgely.customerDataGenerator.responses.entries.UtilityBillingDataResponseEntry;
import com.google.common.reflect.TypeToken;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class RecreateProdUserDataTool {

	@Autowired
	MetaUserHomeGatewaysApi metaUserHomeGatewaysApi;

	@Autowired
	MetaUserHomeGatewayApi metaUserHomeGatewayApi;

	@Autowired
	MetaUserHomeGatewayMeterApi metaUserHomeGatewaysMeterApi;

	@Autowired
	GetLabelTimeStamps labelTimestampsApi;

	@Autowired
	EndPointsApi endPointsApi;

	@Autowired
	UtilityBillingDataApi utilityBillingDataApi;

	@Autowired
	UtilityBillingDataGasApi utilityBillingDataGasApi;

	@Autowired
	GBRawConsumptionDataApi gbRawConsumptionDataApi;

	@Autowired
	private BillCycleCodes billCycleCodes;

	@Autowired
	TokensApi tokensApi;

	@Autowired
	MetaUserHomeApi metaUserHomeApi;

	@Autowired
	MetaUserApi metaUserApi;

	@Autowired
	ABTestingExperimentUserAssignmentToClusterApi abTestingExperimentUserAssignmentToClusterApi;

	@Autowired
	S3 s3;

	private static final Logger logger = LoggerFactory.getLogger(RecreateProdUserDataTool.class);
	String prodBaseUrl;
	String prodAccessToken;
	File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
	private final RetryCommand<String> retryCommand = new RetryCommand<>(3,5000);

	String customerId;
	String contractId;
	String premiseId;
	boolean hasSolar;
	String firstName;
	String lastName;
	String userUUID;
	String billCycleCode;
	String phoneNumber;
	String email;
	String businessName;

	// Create ExecutionVariables instance to manage execution variables
	private ExecutionVariables executionVariables;

	public void execute(String config) throws BidgelyExceptions {
		
		// Initialize execution variables
		executionVariables = new ExecutionVariables();

		RecreateProdUserDataConfig recreateProdUserDataConfig = SharedResources.gson.fromJson(config, RecreateProdUserDataConfig.class);

		prodBaseUrl = recreateProdUserDataConfig.getProdBaseUrl();
		prodAccessToken = recreateProdUserDataConfig.getProdAccessToken();

		String timeZone = recreateProdUserDataConfig.getTimeZone();
		String dateFormat = recreateProdUserDataConfig.getDateFormat();

		// todo - Is this needed
		String rawDataStructureFilePath = recreateProdUserDataConfig.getRawDataStructureFilePath(executionVariables);
		// todo - take it req payload
		String usersFileLocation = recreateProdUserDataConfig.getUsersFileLocation(executionVariables);
		List<String> meterFuelList = Arrays.stream(recreateProdUserDataConfig.getInputMeterFuel().split(","))
				.map(String::trim).collect(Collectors.toList());

		// todo - get this from an api req payload i.e billCycleCode
		String billCycleCodeOfLowerEnv = recreateProdUserDataConfig.getBillCycleCode(executionVariables);

		// todo - get this from an api req payload i.e userPrefFilePath
		String userPrefFilePath = recreateProdUserDataConfig.getUserPrefFile(executionVariables);
		String t0 = recreateProdUserDataConfig.getT0();
		String t1 = recreateProdUserDataConfig.getT1();

		/* reading production users UUID's as list from the file */
		List<String> listOfUsers = Collections.emptyList();
		try {
			File file = new File(usersFileLocation);
			listOfUsers = Files.readAllLines(file.toPath()); // Read all lines
		} catch (IOException e) {
			throw new BidgelyExceptions("IOException occurred: " + e);
		}

		/* reading raw file format from file */
		String contentFormat = "";
		if(recreateProdUserDataConfig.isConstructRawFile() && rawDataStructureFilePath!=null && !rawDataStructureFilePath.isEmpty()) {
			try {
				File file = new File(rawDataStructureFilePath);
				contentFormat = new String(Files.readAllBytes(file.toPath()));
			} catch (IOException e) {
				throw new BidgelyExceptions("IOException occurred: " + e);
			}
		}

		String utilityName;
		String pilotId;
		if(recreateProdUserDataConfig.getDestinationPilotName()!=null && recreateProdUserDataConfig.getDestinationPilotId()!=null){
			utilityName = recreateProdUserDataConfig.getDestinationPilotName();
			pilotId = recreateProdUserDataConfig.getDestinationPilotId();
		}else{
			//Todo - fetch from api req payload
			utilityName = recreateProdUserDataConfig.getSourcePilotName();
			//Todo - fetch from api req payload
			pilotId = recreateProdUserDataConfig.getSourcePilotId();
		}
		//Todo - fetch from api req payload
		String sourceUtilityName = recreateProdUserDataConfig.getSourcePilotName();

		Properties environmentProperties = new Properties();
		Properties pilotProperties = new Properties();
		Properties dafaultProperties = new Properties();
		try {
			environmentProperties.load(RecreateProdUserDataTool.class.getResourceAsStream(
					"/globalProperties/global.properties"));

			dafaultProperties.load(RecreateProdUserDataTool.class.getResourceAsStream(
					"/globalProperties/consolidated.properties"));
			String utilityPropertiesPath = "/" + sourceUtilityName + ".properties";
			String propertyFilePath = environmentProperties.getProperty("email.validation.propertyFilepath") + utilityPropertiesPath;
			pilotProperties.load(new FileReader(propertyFilePath));
		}
		catch (IOException e) {
			throw new BidgelyExceptions("Error while loading the "+ utilityName + "pilot context properties file");
		}

		String propertiesJson = SharedResources.gson.toJson(pilotProperties);
		HashMap<String, String> propertiesMap = SharedResources.gson.fromJson(propertiesJson, new TypeToken<HashMap<String, String>>(){}.getType());


		/* Mandatory to provide invoice file context values */
		String invoiceFilePrefix = propertiesMap.get("prefix.invoiceFile");
		String invoiceFileSuffix = propertiesMap.get("suffix.invoiceFile");
		String invoiceValueSeparator = propertiesMap.get("separator.invoiceFile");
		String dateFormatStr = propertiesMap.get("dateFormat.invoiceFile");
		String billingCycleCodePrefix = propertiesMap.get("userDefaults.billCycleCodePrefix");
		String userTimezone = propertiesMap.get("userDefaults.timezone");
		DateFormat invoiceDateFormat = new SimpleDateFormat(dateFormatStr);
		invoiceDateFormat.setTimeZone(TimeZone.getTimeZone(propertiesMap.get("userDefaults.timezone").trim()));

		DateFormat meterContractDateFormat = new SimpleDateFormat(recreateProdUserDataConfig.getMeterContractDateFormat());
		meterContractDateFormat.setTimeZone(TimeZone.getTimeZone(propertiesMap.get("userDefaults.timezone").trim()));

		String metaUserHomeGatewaysMeterApi;
		String gbRawConsumptionDataApi;
		String utilityBillingDataApi;
		String userDetailsApi;
		String metaUserDetailsApi;
		GBConsumptionDataResponse gbRawConsumptionDataResponse;
		try {
			for (String userUUID : listOfUsers) {
				userUUID = userUUID.trim();
				this.userUUID = userUUID;
				logger.info("Starting file construction for the prod-user {}", userUUID);

				boolean containsActiveMeters = false;
				String metaTokenOFCreatedUser = null;
				for(String meterFuel: meterFuelList) {
					String gwsId = getGWSId(meterFuel);
					metaUserHomeGatewaysMeterApi = String.format("/meta/users/%s/homes/1/gws/%s", userUUID, gwsId);
					MetaUserHomeGatewayMeterResponse metaUserHomeGatewayResponse = SharedResources.gson.fromJson(sendGetRequest(metaUserHomeGatewaysMeterApi),MetaUserHomeGatewayMeterResponse.class);
					/* Considering only active meters */
					if(!metaUserHomeGatewayResponse.getToken().toLowerCase().contains("inactive") &&
							metaUserHomeGatewayResponse.getContractEnd().equalsIgnoreCase("0")){
						metaTokenOFCreatedUser = getValuesFromToken(sourceUtilityName,metaUserHomeGatewayResponse.getToken(),gwsId,executionVariables);
						containsActiveMeters = true;
					}

				}
				if(!containsActiveMeters) {
					logger.error("user:{} does not contains any active meters, Hence skipping this user",userUUID);
					continue;
				}

				userDetailsApi = String.format("/v2.0/users/%s", userUUID);
				UserManagementUserRegistrationEntry userDetailsResponse = SharedResources.gson.fromJson(sendGetRequest(userDetailsApi), UserManagementUserRegistrationResponse.class).getuserManagementUserRegistrationEntry();
				this.hasSolar = userDetailsResponse.getHomeAccounts().getHasSolar();
				String billCycleCodeOftheProdUser = userDetailsResponse.getHomeAccounts().getRate().getBillCycleCode();
				if(billCycleCodeOftheProdUser!=null && billCycleCodes.checkIfBillingCycleCodeExists(billCycleCodeOftheProdUser, Integer.parseInt(pilotId))) {
					this.billCycleCode = billCycleCodeOftheProdUser;
				}else if(billCycleCodeOftheProdUser!=null && billCycleCodeOftheProdUser.matches("\\d+")){
					this.billCycleCode = billCycleCodes.getBillingCycleCodeAt(billingCycleCodePrefix, userTimezone, Integer.parseInt(billCycleCodeOftheProdUser), Integer.parseInt(pilotId), utilityName, dafaultProperties);
				} else{
					this.billCycleCode = billCycleCodeOfLowerEnv!=null? billCycleCodeOfLowerEnv: "";
				}
				logger.warn("considering billCycleCode:{} for the prod-user:{}",this.billCycleCode,userUUID);

				if(utilityName.contains("smb")){
					this.businessName = userDetailsResponse.getHomeAccounts().getBusinessName();
				}

				metaUserDetailsApi = String.format("/meta/users/%s", userUUID);
				MetaUserApiResponse metaUserApiResponse = SharedResources.gson.fromJson(sendGetRequest(metaUserDetailsApi), MetaUserApiResponse.class);
				this.firstName = metaUserApiResponse.getFname();
				this.lastName = metaUserApiResponse.getLname();
				phoneNumber = SharedResources.generateRandomString("DDDDDDDDDD");
				email = propertiesMap.get("userDefaults.emailPrefix").trim() + this.phoneNumber + "@bidgely.com";

				if (recreateProdUserDataConfig.isConstructUserFile()) {
					String userFilePrefix = propertiesMap.get("prefix.userFile").trim();
					String userFileSuffix = propertiesMap.get("suffix.userFile").trim();

					StringBuilder userFileData=new StringBuilder();
					if(recreateProdUserDataConfig.isConstructMeterFile()){ //for EM,ameren,amerensmb,edison,ei
						userFileData.append(getUserFileData(userDetailsResponse, utilityName, executionVariables, "", "", "", ""));
					}else {
						for(String meterFuel: meterFuelList) {
							String gwsId = getGWSId(meterFuel);
							metaUserHomeGatewaysMeterApi = String.format("/meta/users/%s/homes/1/gws/%s", userUUID, gwsId);
							MetaUserHomeGatewayMeterResponse metaUserHomeGatewayResponse = SharedResources.gson.fromJson(sendGetRequest(metaUserHomeGatewaysMeterApi),MetaUserHomeGatewayMeterResponse.class);

							String contractStart = "";
							String contractEnd = "";
							/* Considering only active meters */
							if(!metaUserHomeGatewayResponse.getToken().toLowerCase().contains("inactive") &&
									metaUserHomeGatewayResponse.getContractEnd().equalsIgnoreCase("0")){
								contractStart = convertEpochToDateFormat(meterContractDateFormat, Integer.parseInt(metaUserHomeGatewayResponse.getContractStart()));
								contractEnd = convertEpochToDateFormat(meterContractDateFormat, Integer.parseInt(metaUserHomeGatewayResponse.getContractEnd()));
							}

							userFileData.append(getUserFileData(userDetailsResponse, utilityName, executionVariables, meterFuel, contractStart, contractEnd, gwsId));
							userFileData.append(System.lineSeparator());
						}
					}
					String userFilename = userFilePrefix + userUUID + userFileSuffix;
					createAndUploadFileToBucket(recreateProdUserDataConfig, utilityName, userFilename, userFileData.toString(), 5);
				}

				if(userPrefFilePath!=null && !userPrefFilePath.isEmpty()){
					File userPrefFile = new File(userPrefFilePath);
					String userPrefFileData = new String(Files.readAllBytes(userPrefFile.toPath()));
					userPrefFileData = new StrSubstitutor(executionVariables).replace(userPrefFileData);
					createAndUploadFileToBucket(recreateProdUserDataConfig, utilityName, userPrefFile.getName(), userPrefFileData,2);
				}

				if (recreateProdUserDataConfig.isConstructMeterFile()) {
					String meterFilePrefix = propertiesMap.get("prefix.meterFile").trim();
					String meterFileSuffix = propertiesMap.get("suffix.meterFile").trim();
					StringBuilder meterFileData=new StringBuilder();
					for(String meterFuel: meterFuelList) {
						String gwsId = getGWSId(meterFuel);
						metaUserHomeGatewaysMeterApi = String.format("/meta/users/%s/homes/1/gws/%s", userUUID, gwsId);
						MetaUserHomeGatewayMeterResponse metaUserHomeGatewayResponse = SharedResources.gson.fromJson(sendGetRequest(metaUserHomeGatewaysMeterApi),MetaUserHomeGatewayMeterResponse.class);

						String contractStart = "";
						String contractEnd = "";
						/* Considering only active meters */
						if(!metaUserHomeGatewayResponse.getToken().toLowerCase().contains("inactive") &&
								metaUserHomeGatewayResponse.getContractEnd().equalsIgnoreCase("0")){
							contractStart = convertEpochToDateFormat(meterContractDateFormat, Integer.parseInt(metaUserHomeGatewayResponse.getContractStart()));
							contractEnd = convertEpochToDateFormat(meterContractDateFormat, Integer.parseInt(metaUserHomeGatewayResponse.getContractEnd()));
						}
						meterFileData.append(getMeterFileData(userDetailsResponse, utilityName, executionVariables, meterFuel, contractStart, contractEnd, gwsId));
						meterFileData.append(System.lineSeparator());
					}
					String meterFilename = meterFilePrefix + userUUID + meterFileSuffix;
					createAndUploadFileToBucket(recreateProdUserDataConfig, utilityName, meterFilename, meterFileData.toString(), 5);
				}

				if (recreateProdUserDataConfig.isConstructInvoiceFile() && invoiceFilePrefix.toLowerCase().contains("opower")) {
					StringBuilder invoiceData= new StringBuilder();
					for(String meterFuel: meterFuelList) {
						String gwsId = getGWSId(meterFuel);
						String meterType = meterFuel.split("-")[0];
						String measurementType = meterFuel.split("-")[1];

						metaUserHomeGatewaysMeterApi = String.format("/meta/users/%s/homes/1/gws/%s", userUUID, gwsId);
						MetaUserHomeGatewayMeterResponse metaUserHomeGatewayMeterResponse = SharedResources.gson.fromJson(sendGetRequest(metaUserHomeGatewaysMeterApi),MetaUserHomeGatewayMeterResponse.class);

						utilityBillingDataApi = String.format("/billingdata/users/%s/homes/1/utilitydata?t0=%s&t1=%s&measurementType=%s", userUUID, t0, t1, measurementType);
						UtilityBillingDataResponse utilityBillingDataResponse = SharedResources.gson.fromJson(sendGetRequest(utilityBillingDataApi), UtilityBillingDataResponse.class);
						String contractStart = convertEpochToDateFormat(meterContractDateFormat, Integer.parseInt(metaUserHomeGatewayMeterResponse.getContractStart()));
						String contractEnd = convertEpochToDateFormat(meterContractDateFormat, Integer.parseInt(metaUserHomeGatewayMeterResponse.getContractEnd()));

						invoiceData.append(getOpowerFileData(utilityBillingDataResponse, userDetailsResponse, executionVariables, utilityName, meterType, measurementType, invoiceDateFormat, invoiceValueSeparator, contractStart, contractEnd, gwsId, dateFormatStr));
					}

					String invoiceFilename = invoiceFilePrefix + userUUID + invoiceFileSuffix;
					createAndUploadFileToBucket(recreateProdUserDataConfig, utilityName, invoiceFilename, invoiceData.toString(), 40);
				}

				//                if(metaTokenOFCreatedUser!=null) {
				//                    String uuid = updateUserDetails(pilotId + metaTokenOFCreatedUser, recreateProdUserDataConfig);
				////                    executionVariables.put("userUUID",UUID.fromString(uuid));
				//                }

				if (recreateProdUserDataConfig.isConstructRawFile()) {
					String rawFilePrefix = propertiesMap.get("prefix.AMIRawFile").trim();
					String rawFileSuffix = propertiesMap.get("suffix.AMIRawFile").trim();
					DateFormat rawFileDateFormat = new SimpleDateFormat(propertiesMap.get("dateFormat.AMIRawFile").trim());
					rawFileDateFormat.setTimeZone(TimeZone.getTimeZone(propertiesMap.get("userDefaults.timezone").trim()));

					String rawData;
					for(String meterFuel: meterFuelList) {
						if(meterFuel.contains("NSM")) continue;
						String gwsId = getGWSId(meterFuel);

						gbRawConsumptionDataApi = String.format("/streams/users/%s/homes/1/gws/%s/gb.json?t0=%s&t1=%s", userUUID, gwsId, t0, t1);
						gbRawConsumptionDataResponse = SharedResources.gson.fromJson(sendGetRequest(gbRawConsumptionDataApi), GBConsumptionDataResponse.class);
						if(gbRawConsumptionDataResponse.isEmpty()) {
							logger.error("Raw data is not available for the user:{} of {} type",userUUID,meterFuel);
							continue;
						}

						int duration = gbRawConsumptionDataResponse.get(0).getDuration();
						rawFilePrefix = rawFilePrefix.replaceAll("RAW_D_\\d+_S", "RAW_D_" + duration + "_S");

						rawData=getRawData(gbRawConsumptionDataResponse, executionVariables, utilityName, contentFormat, rawFileDateFormat, gwsId, meterFuel);
						String rawFilename = rawFilePrefix + userUUID + rawFileSuffix;
						createAndUploadFileToBucket(recreateProdUserDataConfig, utilityName, rawFilename, rawData, 30);
					}
				}

				if (recreateProdUserDataConfig.isConstructInvoiceFile() && invoiceFilePrefix.toLowerCase().contains("invoice")) {
					StringBuilder invoiceData= new StringBuilder();
					for(String meterFuel: meterFuelList) {
						String gwsId = getGWSId(meterFuel);
						String meterType = meterFuel.split("-")[0];
						String measurementType = meterFuel.split("-")[1];

						utilityBillingDataApi = String.format("/billingdata/users/%s/homes/1/utilitydata?t0=%s&t1=%s&measurementType=%s", userUUID, t0, t1, measurementType);
						UtilityBillingDataResponse utilityBillingDataResponse = SharedResources.gson.fromJson(sendGetRequest(utilityBillingDataApi), UtilityBillingDataResponse.class);

						invoiceData.append(getInvoiceData(utilityBillingDataResponse, executionVariables, utilityName, meterType, measurementType, invoiceDateFormat, invoiceValueSeparator,gwsId, dateFormatStr));
					}

					String invoiceFilename = invoiceFilePrefix + userUUID + invoiceFileSuffix;
					createAndUploadFileToBucket(recreateProdUserDataConfig, utilityName, invoiceFilename, invoiceData.toString(), 40);
				}


			}
		}catch (Exception e) {
			throw new BidgelyExceptions("Exception occurred: " + e);
		}
	}

	/* making API GET requests */
	private String sendGetRequest(String apiUrl) throws BidgelyExceptions {
		logger.info("Apis - {}" ,apiUrl);
		return retryCommand.run(() -> {
			try {
				Response apiResponse =
						SharedResources.restAssured.given()
								.header("Authorization", "Bearer " + prodAccessToken)
								.when().get(prodBaseUrl + apiUrl);
				Validate.isResponseStatusOk(apiResponse);
				return apiResponse
						.then()
						.extract()
						.asString();
			} catch (Exception e) {
				throw new RuntimeException("Error Occurred:"+e);
			}
		});
	}

	/* updating created user details in lower env's */
	private String updateUserDetails(String metaTokenOfUser, RecreateProdUserDataConfig recreateProdUserDataConfig) throws BidgelyExceptions {
		String uuid = tokensApi.get(metaTokenOfUser).split("/")[2];
		logger.info("User created in {} environment with uuid: {}",System.getProperty("my.env"),uuid);

		if(recreateProdUserDataConfig.getClusterId()!=null){
			abTestingExperimentUserAssignmentToClusterApi.postAndCaptureResponse(uuid, recreateProdUserDataConfig.getClusterId());
		}
		if(recreateProdUserDataConfig.getDefNhoodId()!=null){
			String payload = String.format("{\"defNhoodId\": \"%s\"}", recreateProdUserDataConfig.getDefNhoodId());
			metaUserHomeApi.post(UUID.fromString(uuid), payload);
		}
		if(recreateProdUserDataConfig.getEnrollmentTime()!=null){
			String payload = String.format("{\"enrollmentTime\": \"%s\"}", recreateProdUserDataConfig.getEnrollmentTime());
			metaUserHomeApi.post(UUID.fromString(uuid), payload);
		}
		if(recreateProdUserDataConfig.getNotificationUserType()!=null){
			String payload = String.format("{\"notificationUserType\": \"%s\"}", recreateProdUserDataConfig.getNotificationUserType());
			metaUserApi.post(UUID.fromString(uuid), payload);
		}
		return uuid;
	}

	/* extracting user specific id's from user's token */
	private String getValuesFromToken(String utilityName, String token, String gwsId, ExecutionVariables executionVariables){
		String metaTokenOFCreatedUser = null;
		switch (utilityName.toLowerCase().trim()) {
		case "tpu":
		case "desc":
		case "oge":
		case "nwn":
		case "nsp":
		case "nspsmb":
		case "scg":
		case "scgsmb":
		case "ei":
			token = token.split(":")[1];
			customerId = token.split("_")[0];
			contractId = token.split("_")[1];
			premiseId = token.split("_")[2];
			String dataStreamId = SharedResources.generateRandomString("DDDDDDDDD");
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, dataStreamId);
			if(utilityName.equalsIgnoreCase("scg")) metaTokenOFCreatedUser = ":" + customerId+"_"+contractId+"_"+premiseId+"_"+dataStreamId;
			else if(utilityName.equalsIgnoreCase("nwn")) metaTokenOFCreatedUser = ":" + customerId+"_"+contractId+"_"+premiseId+"_"+dataStreamId+"_NSM";
			else metaTokenOFCreatedUser = ":" + customerId+"_"+contractId+"_"+premiseId;
			break;
		case "tep":
		case "ues":
		case "pnmtod":
		case "ppca":
		case "bhermpidres":
		case "bhermputres":
			token = token.split(":")[1];
			customerId = token.split("_")[0];
			if(contractId==null) contractId = SharedResources.generateRandomString("DDDDDDDDD");
			premiseId = token.split("_")[2];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, SharedResources.generateRandomString("DDDDDDDDD"));
			metaTokenOFCreatedUser = ":" + token; //customerId__premiseId
			break;
		case "em":
			token = token.split(":")[1];
			customerId = token.split("_")[0];
			contractId = token.split("_")[0]+" "+token.split("_")[2];
			premiseId = token.split("_")[2];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, SharedResources.generateRandomString("DDDDDDDDD"));
			break;
		case "ameren":
		case "amerensmb":
			token = token.split(":")[1];
			if(customerId==null) customerId =  SharedResources.generateRandomString("DDDDDDDDD");
			contractId = token.split("_")[1];
			premiseId = token.split("_")[2];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, SharedResources.generateRandomString("DDDDDDDDD"));
			metaTokenOFCreatedUser = ":_" +contractId+"_"+premiseId; //_contractId_premiseId
			break;
		case "ouc":
			token = token.split(":")[1];
			customerId = token.split("_")[0];
			contractId = premiseId = token.split("_")[2];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, SharedResources.generateRandomString("DDDDDDDDD"));
			break;
		case "edison":
			token = token.split(":")[1];
			customerId = token.split("_")[0];
			contractId = token.split("_")[1];
			premiseId = "";
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, SharedResources.generateRandomString("DDDDDDDDD"));
			metaTokenOFCreatedUser = ":" + customerId+"_"+contractId+"_";
			break;
		case "sdge":
		case "sdgesmb":
			token = token.split(":")[1];
			premiseId = token.split("_")[2];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "secondaryId", token.split("_")[0]);
			if(customerId==null) customerId = SharedResources.generateRandomString("DDDDDDDDDDDDD");
			if (RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointIdNumber")==null) {
				String servicePointIdNumber = "000" + SharedResources.generateRandomString("D");
				RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "servicePointIdNumber", servicePointIdNumber);
				RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "servicePointId", this.premiseId + "-" +servicePointIdNumber);
			}
			metaTokenOFCreatedUser = ":" + RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "secondaryId")+"__"+premiseId; //secondaryId__premiseId
			break;
		case "pnm":
			token = token.split(":")[1];
			int half = token.split("_")[1].length()/2;
			if(customerId==null) customerId = token.split("_")[1].substring(0,half);
			if(premiseId==null) premiseId = token.split("_")[1].substring(half);
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "secondaryId", token.split("_")[1]);
			break;
		case "ppl":
			if(customerId==null) customerId =  SharedResources.generateRandomString("DDDDDDDDD");
			contractId = token.split(":")[1];
			premiseId = token.split(":")[2];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, SharedResources.generateRandomString("DDDDDDDDD"));
			metaTokenOFCreatedUser = ":"+contractId+":"+premiseId;
			break;
		case "cva":
			token = token.split(":")[1];
			customerId = token.split("_")[1];
			premiseId = token.split("_")[2];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "servicePointId", token.split("_")[3]);
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "secondaryId", SharedResources.generateRandomString("DDDDDDD"));
			metaTokenOFCreatedUser = ":1_"+customerId+"_"+premiseId+"_"+ RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId")+"_NSM";
			break;
		case "pseg":
			token = token.split(":")[1];
			if(customerId==null) customerId = SharedResources.generateRandomString("DDDDDDDDD");
			contractId = token.split("_")[1];
			premiseId = token.split("_")[2];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, "");
			metaTokenOFCreatedUser = ":_" + contractId + "_" + premiseId + "_";
			break;
		case "honi":
			token = token.split(":")[1];
			contractId = token.split("_")[0];
			premiseId = SharedResources.generateRandomString("DDDDDDDDDDD");
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "partnerUserId", token.split("_")[1]);
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "contractAccountId", SharedResources.generateRandomString("DDDDDDDDDDDDD"));
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId,SharedResources.generateRandomString("DDDDDDDDD"));
			metaTokenOFCreatedUser = ":" + token; //contractId_partnerUserId_
			break;
		case "vse":
			token = token.split(":")[1];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "externalAccountId", token);
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "servicePointId", token.split("-")[1]);
			metaTokenOFCreatedUser = ":" + RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "externalAccountId"); //externalAccountId
			break;
		case "nve":
			token = token.split(":")[1];
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "externalAccountId", SharedResources.generateRandomString("DDDDDDDDDDD"));
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "servicePointId_"+gwsId, token); //token
			metaTokenOFCreatedUser = ":" + RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId_2"); //servicePointId
			break;
		case "pporres":
			token = token.split(":")[1];
			contractId = token.split("_")[0];
			customerId = token.split("_")[1];
			premiseId = token.split("_")[2];
			dataStreamId = SharedResources.generateRandomString("DDDDDDDDD");
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "dataStreamId_"+gwsId, dataStreamId);
			metaTokenOFCreatedUser = ":" + contractId+"_"+customerId+"_"+premiseId+"_"+dataStreamId;
			break;
		case "pp":
			token = token.split(":")[1];
			customerId = token.split(":")[0];
			premiseId = token.split(":")[1];
			metaTokenOFCreatedUser = ":" + customerId+":"+premiseId+":GB_MONTH";
			break;
		default:
			logger.error("utility is not defined for extracting valid data from meter token");
		}
		if(customerId!=null) RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "customerId",customerId);
		if(contractId!=null) RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "contractId",contractId);
		if(premiseId!=null)  RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "premiseId", premiseId);
		return metaTokenOFCreatedUser;
	}

	private String getRawData(GBConsumptionDataResponse gbRawConsumptionDataResponse, ExecutionVariables executionVariables, String utilityName, String contentFormat, DateFormat rawFileDateFormat, String gwsId, String meterFuel) {
		StringBuilder updatedContext = new StringBuilder();
		String PIPE = "|";

		String meterType = meterFuel.split("-")[0];
		String measurementType = meterFuel.split("-")[1];
		Date date;
		String timeStamp;
		/* This check is to differentiate multiple consumption raw file, with single consumption raw file */
		switch (utilityName.toLowerCase()) {
		case "oge":
		case "scg":
		case "scgsmb":
		case "ei":
			int granularity = gbRawConsumptionDataResponse.get(0).getDuration();
			Map<String, List<String>> dateToValuesMap = new TreeMap<>();
			for (GBConsumptionDataResponseEntry entry : gbRawConsumptionDataResponse) {
				String dateKey = rawFileDateFormat.format(new Date(entry.getTime() * 1000L));
				double consumption = entry.getValue() / 1000;
				if(utilityName.equalsIgnoreCase("scg") || utilityName.equalsIgnoreCase("scgsmb")){
					consumption = entry.getValue() / 29300; //converting wh to therms
				}
				dateToValuesMap
						.computeIfAbsent(dateKey, k -> new ArrayList<>())
						.add(String.valueOf(consumption));
			}

			for (Map.Entry<String, List<String>> entry : dateToValuesMap.entrySet()) {
				List<String> values = new ArrayList<>(entry.getValue());
				//                    int missingCount = 86400/granularity - values.size();
				//                    for (int i = 0; i < missingCount; i++) { //replacing missing dataPoints with zero
				//                        values.add("0");
				//                    }
				String consumptions = String.join(PIPE, values);

				updatedContext
						.append(customerId).append(PIPE)
						.append(contractId).append(PIPE)
						.append(premiseId).append(PIPE)
						.append(RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_" + gwsId)).append(PIPE)
						.append(entry.getKey()).append(PIPE)
						.append(consumptions).append(PIPE)
						.append("0").append(PIPE)
						.append("0")
						.append(System.lineSeparator());
			}
			break;
		case "tep":
		case "tpu":
		case "ues":
		case "ameren":
		case "nsp":
		case "nspsmb":
		case "edison":
		case "ppca":
		case "ppl":
		case "bhermpidres":
		case "bhermputres":
		case "amerensmb":
		case "pporres":
		case "desc":
			String dataStreamId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_" + gwsId);
			for (GBConsumptionDataResponseEntry entry : gbRawConsumptionDataResponse) {
				date = new Date(entry.getTime() * 1000L);
				/* for some pilots like NSP & Ameren, requires epochTimeStamp  */
				timeStamp = !rawFileDateFormat.format(date).isEmpty() ? rawFileDateFormat.format(date) : entry.getTime().toString();
				updatedContext.append(customerId);
				updatedContext.append(PIPE);
				updatedContext.append(contractId);
				updatedContext.append(PIPE);
				updatedContext.append(premiseId);
				updatedContext.append(PIPE);
				updatedContext.append(dataStreamId);
				updatedContext.append(PIPE);
				updatedContext.append(timeStamp);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getValue() / 1000);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getDirection());
				updatedContext.append(PIPE);
				updatedContext.append(entry.getDataQuality());
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "pnmtod":
			PIPE = ",";
			dataStreamId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_" + gwsId);
			String row1 = this.hasSolar? "TD0226": "VY12348";
			for (GBConsumptionDataResponseEntry entry : gbRawConsumptionDataResponse) {
				date = new Date(entry.getTime() * 1000L);
				timeStamp = !rawFileDateFormat.format(date).isEmpty() ? rawFileDateFormat.format(date) : entry.getTime().toString();
				updatedContext.append(row1);
				updatedContext.append(PIPE);
				updatedContext.append(dataStreamId);
				updatedContext.append(PIPE);
				updatedContext.append(customerId+"-"+premiseId);
				updatedContext.append(PIPE);
				updatedContext.append("03");
				updatedContext.append(PIPE);
				updatedContext.append(timeStamp);
				updatedContext.append(PIPE);
				updatedContext.append("0.00"); /* making these values as zeroes as these are rejected by transformer and consider only 3rd channel(net consumption) */
				updatedContext.append(PIPE);
				updatedContext.append("0.00");
				updatedContext.append(PIPE);
				updatedContext.append(entry.getValue() / 1000);
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "sdge":
		case "sdgesmb":
			PIPE = ",";
			String servicePointIdNumber = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointIdNumber");
			String measurementUnit = measurementType.equalsIgnoreCase("gas")? "Therms": "KWH";
			for (GBConsumptionDataResponseEntry entry : gbRawConsumptionDataResponse) {
				date = new Date(entry.getTime() * 1000L);
				/* for some pilots like NSP & Ameren, requires epochTimeStamp  */
				timeStamp = !rawFileDateFormat.format(date).isEmpty() ? rawFileDateFormat.format(date) : entry.getTime().toString();
				updatedContext.append(this.customerId);
				updatedContext.append(PIPE);
				updatedContext.append(this.premiseId);
				updatedContext.append(PIPE);
				updatedContext.append(servicePointIdNumber);
				updatedContext.append(PIPE);
				updatedContext.append(measurementType.charAt(0)); /* E or G */
				updatedContext.append(PIPE);
				updatedContext.append("N");
				updatedContext.append(PIPE);
				updatedContext.append(measurementUnit);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getDuration());
				updatedContext.append(PIPE);
				updatedContext.append(entry.getDirection()+1);
				updatedContext.append(PIPE);
				updatedContext.append(timeStamp);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getValue() / 1000);
				updatedContext.append(PIPE);
				updatedContext.append("E0016"); //entry.getDataQuality()
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "pseg":
			String sdpId= SharedResources.generateRandomString(15);
			RecreateProdUserDataContextHandler.ExecutionVariableManager.set(executionVariables, "sdpId", sdpId);
			String direction1 = "0.0.9.4.1.1.12.0.0.0.0.0.0.0.0.3.72.0";
			String direction2 = "0.0.9.4.19.1.12.0.0.0.0.0.0.0.0.3.72.0";
			String source = "3.0.0";

			for (GBConsumptionDataResponseEntry entry : gbRawConsumptionDataResponse) {
				date = new Date(entry.getTime() * 1000L);
				/* for some pilots like NSP & Ameren, requires epochTimeStamp  */
				timeStamp = !rawFileDateFormat.format(date).isEmpty() ? rawFileDateFormat.format(date) : entry.getTime().toString();

				updatedContext.append("D");
				updatedContext.append(PIPE);
				updatedContext.append(sdpId);
				updatedContext.append(PIPE);
				updatedContext.append(premiseId);
				updatedContext.append(PIPE);
				updatedContext.append(direction1);
				updatedContext.append(PIPE);
				updatedContext.append(source);
				updatedContext.append(PIPE);
				updatedContext.append(timeStamp);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getValue() / 1000);
				updatedContext.append(PIPE);
				updatedContext.append(PIPE);
				updatedContext.append(PIPE);
				updatedContext.append(1);
				updatedContext.append(PIPE);
				updatedContext.append(1);
				updatedContext.append(PIPE);
				updatedContext.append(contractId);
				updatedContext.append(PIPE);
				updatedContext.append(System.lineSeparator());
				updatedContext.append("D");
				updatedContext.append(PIPE);
				updatedContext.append(sdpId);
				updatedContext.append(PIPE);
				updatedContext.append(premiseId);
				updatedContext.append(PIPE);
				updatedContext.append(direction2);
				updatedContext.append(PIPE);
				updatedContext.append(source);
				updatedContext.append(PIPE);
				updatedContext.append(timeStamp);
				updatedContext.append(PIPE);
				updatedContext.append(0.0);
				updatedContext.append(PIPE);
				updatedContext.append(PIPE);
				updatedContext.append(PIPE);
				updatedContext.append(1);
				updatedContext.append(PIPE);
				updatedContext.append(1);
				updatedContext.append(PIPE);
				updatedContext.append(contractId);
				updatedContext.append(PIPE);
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "honi":
			dataStreamId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_" + gwsId);

			for (GBConsumptionDataResponseEntry entry : gbRawConsumptionDataResponse) {
				date = new Date(entry.getTime() * 1000L);
				/* for some pilots like NSP & Ameren, requires epochTimeStamp  */
				timeStamp = !rawFileDateFormat.format(date).isEmpty() ? rawFileDateFormat.format(date) : entry.getTime().toString();
				updatedContext.append(dataStreamId + PIPE + timeStamp + PIPE + entry.getValue() / 1000 + PIPE + entry.getDirection() + PIPE + entry.getDataQuality());
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "vse":
			String externalAccountId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "externalAccountId");
			String servicePointId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId");
			for (GBConsumptionDataResponseEntry entry : gbRawConsumptionDataResponse) {
				date = new Date(entry.getTime() * 1000L);
				/* for some pilots like NSP & Ameren, requires epochTimeStamp  */
				timeStamp = !rawFileDateFormat.format(date).isEmpty() ? rawFileDateFormat.format(date) : entry.getTime().toString();
				updatedContext.append(externalAccountId);
				updatedContext.append(PIPE);
				updatedContext.append(servicePointId);
				updatedContext.append(PIPE);
				updatedContext.append(timeStamp);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getValue() / 1000);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getDirection());
				updatedContext.append(PIPE);
				updatedContext.append(entry.getDataQuality());
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "nve":
			externalAccountId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "externalAccountId");
			servicePointId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId_"+gwsId);
			PIPE=",";
			int lineNumber=1;
			for (GBConsumptionDataResponseEntry entry : gbRawConsumptionDataResponse) {
				updatedContext.append(lineNumber++);
				updatedContext.append(PIPE);
				updatedContext.append(externalAccountId);
				updatedContext.append(PIPE);
				updatedContext.append(servicePointId);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getTime()); //In epochTimestamp
				updatedContext.append(PIPE);
				updatedContext.append(entry.getValue() / 1000);
				updatedContext.append(PIPE);
				updatedContext.append(entry.getDirection());
				updatedContext.append(PIPE);
				updatedContext.append(entry.getDataQuality());
				updatedContext.append(System.lineSeparator());
			}
			break;
		default:
			logger.error("utility is not defined for preparing the raw data");
		}


		return updatedContext.toString();
	}

	/* creating the invoice data of the user */
	private String getInvoiceData(UtilityBillingDataResponse utilityBillingDataResponse,
			ExecutionVariables executionVariables , String utilityName, String meterType, String measurementType, DateFormat invoiceDateFormat, String invoiceValueSeparator, String gwsId, String dateFormat){
		StringBuilder updatedContext = new StringBuilder();
		String PIPE=invoiceValueSeparator;

		switch (utilityName.toLowerCase().trim()){
		case "tpu":
		case "oge":
		case "tep":
		case "nwn":
		case "ues":
		case "nsp":
		case "nspsmb":
		case "em":
		case "ameren":
		case "ouc":
		case "pnmtod":
		case "edison":
		case "ppca":
		case "ppl":
		case "pseg":
		case "bhermpidres":
		case "bhermputres":
		case "amerensmb":
		case "scg":
		case "scgsmb":
		case "ei":
		case "pporres":
			String dataStreamId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_"+gwsId);

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {
				List<InvoiceDataList> invoiceDataList = monthlyInvoice.getValue().getInvoiceDataList();
				/* This check is used when there is multimeter user with same fuel-type, then there will be duplication of data for eg:(AMI_E + AMR_E) */
				if(monthlyInvoice.getValue().getUserType() == null || (meterType.equalsIgnoreCase("AMI") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("GB")) ||
						(meterType.equalsIgnoreCase("AMR") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("AMR")) ||
						(meterType.equalsIgnoreCase("NSM") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("GB_MONTH"))) continue;

				if(invoiceDataList != null) {
					for (InvoiceDataList invoice : invoiceDataList) {
						String chargeName = (invoice.getChargeNameString() != null) ? invoice.getChargeNameString() : "";
						String chargeType = (invoice.getChargeType() != null) ? invoice.getChargeType() : "";
						Double consumption = invoice.getConsumption();
						Double cost = invoice.getCost();

						if(utilityName.equalsIgnoreCase("pseg")){
							switch (chargeName.toUpperCase()){
							case "TOU_PK":
								chargeName = "PEAK";
								break;
							case "TOU_MP":
								chargeName = "OFFPK";
								break;
							case "TOU_OP":
								chargeName = "SOFPK";
								break;
							}

							if(chargeType.equalsIgnoreCase("CONSUMPTION_BASED")){
								chargeType = "C-BSD";
							}
						}

						int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
						String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
						int j = monthlyInvoice.getValue().getBillingEndTs();
						String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);
						int duration = getBillingPeriodDuration(billingStartDate, billingEndDate, dateFormat);

						updatedContext.append(this.customerId);
						updatedContext.append(PIPE);
						updatedContext.append(this.contractId);
						updatedContext.append(PIPE);
						updatedContext.append(this.premiseId);
						updatedContext.append(PIPE);
						updatedContext.append(dataStreamId);
						if(!utilityName.equalsIgnoreCase("scg")) {
							updatedContext.append(PIPE);
							updatedContext.append(measurementType);
						}
						updatedContext.append(PIPE);
						updatedContext.append(this.billCycleCode);
						updatedContext.append(PIPE);
						updatedContext.append(billingStartDate);
						updatedContext.append(PIPE);
						updatedContext.append(billingEndDate);
						if(!utilityName.equalsIgnoreCase("scg")) {
							updatedContext.append(PIPE);
							updatedContext.append(duration);
						}
						updatedContext.append(PIPE);
						updatedContext.append(chargeName);
						updatedContext.append(PIPE);
						updatedContext.append(chargeType);
						updatedContext.append(PIPE);
						updatedContext.append(consumption);
						updatedContext.append(PIPE);
						updatedContext.append(cost);
						updatedContext.append(PIPE);
						updatedContext.append(meterType);
						updatedContext.append(PIPE);
						updatedContext.append(System.lineSeparator());
					}
				}
			}
			break;
		case "desc":
			dataStreamId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_"+gwsId);

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {
				List<InvoiceDataList> invoiceDataList = monthlyInvoice.getValue().getInvoiceDataList();

				if(invoiceDataList != null) {
					for (InvoiceDataList invoice : invoiceDataList) {
						String chargeName = (invoice.getChargeNameString() != null) ? invoice.getChargeNameString() : "";
						String chargeType = (invoice.getChargeType() != null) ? invoice.getChargeType() : "";
						Double consumption = invoice.getConsumption();
						Double cost = invoice.getCost();

						int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
						String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
						int j = monthlyInvoice.getValue().getBillingEndTs()-86400;
						String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);

						updatedContext.append(this.customerId);
						updatedContext.append(PIPE);
						updatedContext.append(this.contractId);
						updatedContext.append(PIPE);
						updatedContext.append(this.premiseId);
						updatedContext.append(PIPE);
						updatedContext.append(dataStreamId);
						updatedContext.append(PIPE);
						updatedContext.append(measurementType);
						updatedContext.append(PIPE);
						updatedContext.append(billingStartDate);
						updatedContext.append(PIPE);
						updatedContext.append(billingEndDate);
						updatedContext.append(PIPE);
						updatedContext.append(chargeName);
						updatedContext.append(PIPE);
						updatedContext.append(chargeType);
						updatedContext.append(PIPE);
						updatedContext.append(consumption);
						updatedContext.append(PIPE);
						updatedContext.append(cost);
						updatedContext.append(PIPE);
						updatedContext.append(meterType);
						updatedContext.append(PIPE);
						updatedContext.append(System.lineSeparator());
					}
				}
			}
			break;
		case "honi":
			dataStreamId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_"+gwsId);
			String partnerUserId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "partnerUserId");
			String contractAccountId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "contractAccountId");

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {
				List<InvoiceDataList> invoiceDataList = monthlyInvoice.getValue().getInvoiceDataList();

				if(invoiceDataList != null) {
					for (InvoiceDataList invoice : invoiceDataList) {
						String chargeName = (invoice.getChargeNameString() != null) ? invoice.getChargeNameString() : "";
						String chargeType = (invoice.getChargeType() != null) ? invoice.getChargeType() : "";
						Double consumption = invoice.getConsumption();
						Double cost = invoice.getCost();

						int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
						String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
						int j = monthlyInvoice.getValue().getBillingEndTs()-86400;
						String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);
						int duration = getBillingPeriodDuration(billingStartDate, billingEndDate, dateFormat);

						updatedContext.append(partnerUserId);
						updatedContext.append(PIPE);
						updatedContext.append(contractAccountId);
						updatedContext.append(PIPE);
						updatedContext.append(this.contractId);
						updatedContext.append(PIPE);
						updatedContext.append("");
						updatedContext.append(PIPE);
						updatedContext.append(dataStreamId);
						updatedContext.append(PIPE);
						updatedContext.append(measurementType);
						updatedContext.append(PIPE);
						updatedContext.append("");
						updatedContext.append(PIPE);
						updatedContext.append(billingStartDate);
						updatedContext.append(PIPE);
						updatedContext.append(billingEndDate);
						updatedContext.append(PIPE);
						updatedContext.append("");
						updatedContext.append(PIPE);
						updatedContext.append(chargeName);
						updatedContext.append(PIPE);
						updatedContext.append(chargeType);
						updatedContext.append(PIPE);
						updatedContext.append(consumption);
						updatedContext.append(PIPE);
						updatedContext.append(cost);
						updatedContext.append(PIPE);
						updatedContext.append(meterType);
						updatedContext.append(PIPE);
						updatedContext.append(System.lineSeparator());
					}
				}
			}
			break;
		case "vse":
			String externalAccountId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "externalAccountId");
			String servicePointId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId");

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {
				List<InvoiceDataList> invoiceDataList = monthlyInvoice.getValue().getInvoiceDataList();

				if(invoiceDataList != null) {
					for (InvoiceDataList invoice : invoiceDataList) {
						String chargeName = (invoice.getChargeNameString() != null) ? invoice.getChargeNameString() : "";
						String chargeType = (invoice.getChargeType() != null) ? invoice.getChargeType() : "";
						Double consumption = invoice.getConsumption();
						Double cost = invoice.getCost();

						int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
						String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
						int j = monthlyInvoice.getValue().getBillingEndTs()-86400;
						String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);
						int duration = getBillingPeriodDuration(billingStartDate, billingEndDate, dateFormat);

						updatedContext.append(externalAccountId);
						updatedContext.append(PIPE);
						updatedContext.append(servicePointId);
						updatedContext.append(PIPE);
						updatedContext.append("");
						updatedContext.append(PIPE);
						updatedContext.append(billingStartDate);
						updatedContext.append(PIPE);
						updatedContext.append(billingEndDate);
						updatedContext.append(PIPE);
						updatedContext.append(chargeName);
						updatedContext.append(PIPE);
						updatedContext.append(chargeType);
						updatedContext.append(PIPE);
						updatedContext.append(consumption);
						updatedContext.append(PIPE);
						updatedContext.append(cost);
						updatedContext.append(System.lineSeparator());
					}
				}
			}
			break;
		case "nve":
			externalAccountId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "externalAccountId");
			servicePointId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId_"+gwsId);
			PIPE=",";
			int lineNumber=1;
			if(measurementType.equalsIgnoreCase("gas")) lineNumber=100;

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {
				List<InvoiceDataList> invoiceDataList = monthlyInvoice.getValue().getInvoiceDataList();

				if(invoiceDataList != null) {
					for (InvoiceDataList invoice : invoiceDataList) {
						Double consumption = invoice.getConsumption();
						Double cost = invoice.getCost();

						int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
						String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
						int j = monthlyInvoice.getValue().getBillingEndTs()-86400;
						String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);

						updatedContext.append(lineNumber++);
						updatedContext.append(PIPE);
						updatedContext.append(externalAccountId);
						updatedContext.append(PIPE);
						updatedContext.append(servicePointId);
						updatedContext.append(PIPE);
						updatedContext.append(this.billCycleCode);
						updatedContext.append(PIPE);
						updatedContext.append(billingStartDate);
						updatedContext.append(PIPE);
						updatedContext.append(billingEndDate);
						updatedContext.append(PIPE);
						updatedContext.append(consumption);
						updatedContext.append(PIPE);
						updatedContext.append(PIPE);
						updatedContext.append(PIPE);
						updatedContext.append(cost);
						updatedContext.append(System.lineSeparator());
					}
				}
			}
			break;
		default:
			logger.error("utility is not defined for preparing the invoice data");
		}
		return updatedContext.toString();
	}

	private String getOpowerFileData(UtilityBillingDataResponse utilityBillingDataResponse, UserManagementUserRegistrationEntry userDetailsResponse, ExecutionVariables executionVariables, String utilityName, String meterType, String measurementType, DateFormat invoiceDateFormat, String invoiceValueSeparator, String contractStart, String contractEnd, String gwsId, String dateFormatStr) throws BidgelyExceptions {
		StringBuilder updatedContext = new StringBuilder();
		String PIPE=invoiceValueSeparator;

		JsonObject mailingAddressJson = SharedResources.gson.fromJson(userDetailsResponse.getHomeAccounts().getMailingAddress(), JsonObject.class);
		String zipCode = mailingAddressJson.get("zipcode").getAsString();
		String addressLine1 =mailingAddressJson.get("addressLine1").getAsString();
		String city = userDetailsResponse.getHomeAccounts().getCity();
		String state = userDetailsResponse.getHomeAccounts().getState();
		String ratePlanId = userDetailsResponse.getHomeAccounts().getRate().getRatePlanId();
		ratePlanId = ratePlanId!=null? ratePlanId: "";
		String latitude = userDetailsResponse.getHomeAccounts().getLatitude();
		String longitude = userDetailsResponse.getHomeAccounts().getLongitude();

		switch (utilityName.toLowerCase().trim()){
		case "sdge":
		case "sdgesmb":
			String servicePointId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId");
			String secondaryId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "secondaryId");
			PIPE="\u0009";

			String houseNumber = addressLine1.split(" ", 2)[0];
			String streetName = addressLine1.split(" ", 2)[1];
			String photovoltaic = this.hasSolar? "1": "0";

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {
				/* This check is used when there is multimeter user with same fuel-type, then there will be duplication of data for eg:(AMI_E + AMR_E) */
				if(monthlyInvoice.getValue().getUserType() == null || (meterType.equalsIgnoreCase("AMI") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("GB")) ||
						(meterType.equalsIgnoreCase("AMR") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("AMR")) ||
						(meterType.equalsIgnoreCase("NSM") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("GB_MONTH"))) continue;

				Double totalCost = measurementType.equalsIgnoreCase("ELECTRIC")? monthlyInvoice.getValue().getCost()
						: monthlyInvoice.getValue().getGasCost();
				Double totalconsumption = measurementType.equalsIgnoreCase("ELECTRIC")? monthlyInvoice.getValue().getValue()
						: monthlyInvoice.getValue().getGasValue();

				int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
				String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
				int j = monthlyInvoice.getValue().getBillingEndTs();
				String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);
				String duration = String.valueOf(getBillingPeriodDuration(billingStartDate, billingEndDate, dateFormatStr));

				if(utilityName.equalsIgnoreCase("sdge")) {
					updatedContext.append(this.customerId + PIPE + premiseId + PIPE + servicePointId + PIPE + secondaryId + PIPE + addressLine1 + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + city + PIPE + state + PIPE + zipCode + PIPE + this.firstName + PIPE + this.lastName + PIPE + phoneNumber + PIPE + phoneNumber + PIPE + email + PIPE + "Owner" + PIPE + "EN" + PIPE + "\"\"" + PIPE + "20131019" + PIPE + "\"\"" + PIPE + this.billCycleCode + PIPE + ratePlanId + PIPE + houseNumber + PIPE + streetName + PIPE + "\"\"" + PIPE + city + PIPE); //28
					updatedContext.append(state + PIPE + zipCode + PIPE + measurementType.charAt(0) + PIPE + "SFH" + PIPE + "2045.00" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + photovoltaic + PIPE + "\"\"" + PIPE + totalCost + PIPE + billingEndDate + PIPE + duration + PIPE + "A" + PIPE + totalconsumption + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + latitude + PIPE + longitude + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "SECONDARY" + PIPE + "K");
				} else if (utilityName.equalsIgnoreCase("sdgesmb")) {
					updatedContext.append(this.customerId + PIPE + premiseId + PIPE + servicePointId + PIPE + secondaryId + PIPE + addressLine1 + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + city + PIPE + state + PIPE + zipCode + PIPE + phoneNumber + PIPE + phoneNumber + PIPE + email + PIPE + "Owner" + PIPE + "EN" + PIPE + servicePointId + PIPE + "20131019" + PIPE + "\"\"" + PIPE + this.billCycleCode + PIPE + ratePlanId + PIPE + houseNumber + PIPE + streetName + PIPE + "\"\"" + PIPE + city + PIPE); //28
					updatedContext.append(state + PIPE + zipCode + PIPE + measurementType.charAt(0) + PIPE + "SFH" + PIPE + "2045.00" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + photovoltaic + PIPE + "\"\"" + PIPE + totalCost + PIPE + billingEndDate + PIPE + duration + PIPE + "A" + PIPE + totalconsumption + PIPE + "\"\"" + PIPE + this.businessName + PIPE + latitude + PIPE + longitude + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "\"\"" + PIPE + "SECONDARY" + PIPE + "K" + PIPE + "4000397358" + PIPE + "20210303" + PIPE + "20270307" + PIPE + "312120");
				}
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "pnm":
			secondaryId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "secondaryId");
			String accountId=  SharedResources.generateRandomString("DDDDDDDDDDDDDDDD")+"3408";
			servicePointId = premiseId+"3408";

			houseNumber = addressLine1.split(" ", 2)[0];
			streetName = addressLine1.split(" ", 2)[1];

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {
				/* This check is used when there is multimeter user with same fuel-type, then there will be duplication of data for eg:(AMI_E + AMR_E) */
				if(monthlyInvoice.getValue().getUserType() == null || (meterType.equalsIgnoreCase("AMI") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("GB")) ||
						(meterType.equalsIgnoreCase("AMR") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("AMR")) ||
						(meterType.equalsIgnoreCase("NSM") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("GB_MONTH"))) continue;

				Double totalCost = measurementType.equalsIgnoreCase("ELECTRIC")? monthlyInvoice.getValue().getCost()
						: monthlyInvoice.getValue().getGasCost();
				Double totalconsumption = measurementType.equalsIgnoreCase("ELECTRIC")? monthlyInvoice.getValue().getValue()
						: monthlyInvoice.getValue().getGasValue();

				int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
				String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
				int j = monthlyInvoice.getValue().getBillingEndTs();
				String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);
				String duration = String.valueOf(getBillingPeriodDuration(billingStartDate, billingEndDate, dateFormatStr));

				updatedContext.append(this.customerId+PIPE+premiseId+PIPE+secondaryId+PIPE+addressLine1+PIPE+PIPE+PIPE+city+PIPE+state+PIPE+zipCode+PIPE+this.firstName+PIPE+this.lastName+PIPE+phoneNumber+PIPE+PIPE+email+PIPE+"RESIDENTIAL"+PIPE+PIPE+PIPE+PIPE+PIPE+PIPE+PIPE+accountId+PIPE+contractStart+PIPE+contractEnd+PIPE+this.billCycleCode+PIPE+ratePlanId+PIPE+""+PIPE+servicePointId+PIPE+houseNumber+PIPE);
				updatedContext.append(streetName+PIPE+"1"+PIPE+city+PIPE+state+PIPE+zipCode+PIPE+""+PIPE+""+PIPE+""+PIPE+latitude+PIPE+longitude+PIPE+""+PIPE+""+PIPE+""+PIPE+"1996"+PIPE+""+PIPE+""+PIPE+""+PIPE+""+PIPE+""+PIPE+""+PIPE+totalconsumption+PIPE+billingEndDate+PIPE+duration+PIPE+"A"+PIPE+""+PIPE+""+PIPE+"Electronic"+PIPE+"N1A"+PIPE+"N"+PIPE+"Y"+PIPE+"N"+PIPE+""+PIPE+totalCost+PIPE+"98"+PIPE+"N");
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "cva":
			PIPE="\t";
			secondaryId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "secondaryId");
			servicePointId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId");

			houseNumber = addressLine1.split(" ", 2)[0];
			streetName = addressLine1.split(" ", 2)[1];

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {
				/* This check is used when there is multimeter user with same fuel-type, then there will be duplication of data for eg:(AMI_E + AMR_E) */
				if (monthlyInvoice.getValue().getUserType() == null || (meterType.equalsIgnoreCase("AMI") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("GB")) ||
						(meterType.equalsIgnoreCase("AMR") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("AMR")) ||
						(meterType.equalsIgnoreCase("NSM") && !monthlyInvoice.getValue().getUserType().equalsIgnoreCase("GB_MONTH")))
					continue;

				int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
				String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
				int j = monthlyInvoice.getValue().getBillingEndTs();
				String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);
				String duration = String.valueOf(getBillingPeriodDuration(billingStartDate, billingEndDate, dateFormatStr));

				Double totalCost = measurementType.equalsIgnoreCase("ELECTRIC") ? monthlyInvoice.getValue().getCost()
						: monthlyInvoice.getValue().getGasCost();
				Double totalconsumption = measurementType.equalsIgnoreCase("ELECTRIC") ? monthlyInvoice.getValue().getValue()
						: monthlyInvoice.getValue().getGasValue();

				updatedContext.append(this.customerId + PIPE + premiseId + PIPE + secondaryId + PIPE + addressLine1 + PIPE + PIPE + city + PIPE + state + PIPE + zipCode + PIPE + this.firstName + PIPE + this.lastName + PIPE + "0" + PIPE + "1" + PIPE + contractStart + PIPE + contractEnd + PIPE + this.billCycleCode + PIPE + "13" + PIPE + servicePointId + PIPE + houseNumber + PIPE + streetName + PIPE + "" + PIPE + city + PIPE + state + PIPE + zipCode + PIPE + "G" + PIPE + "T" + PIPE + totalconsumption + PIPE + billingEndDate + PIPE + duration + PIPE + "A" + PIPE + ratePlanId + PIPE + totalCost + PIPE + this.email);
				updatedContext.append(System.lineSeparator());
			}
			break;
		case "pp":
			PIPE=",";
			secondaryId = "";
			accountId = customerId + "0001001" + premiseId + "001";
			servicePointId = premiseId + "001";
			String accountNumber11Digit = customerId + "001";// 11 digits
			String pacConcatAgreementNumber = accountNumber11Digit + "001";
			String pacConcatAccountNumber = accountNumber11Digit + "004";

			houseNumber = addressLine1.split(" ", 2)[0];
			streetName = addressLine1.split(" ", 2)[1];

			for (Map.Entry<String, UtilityBillingDataResponseEntry> monthlyInvoice : utilityBillingDataResponse.entrySet()) {

				int i = Integer.parseInt(String.valueOf(monthlyInvoice.getKey()));
				String billingStartDate = convertEpochToDateFormat(invoiceDateFormat,i);
				int j = monthlyInvoice.getValue().getBillingEndTs();
				String billingEndDate = convertEpochToDateFormat(invoiceDateFormat,j);
				String duration = String.valueOf(getBillingPeriodDuration(billingStartDate, billingEndDate, dateFormatStr));

				Double totalCost = measurementType.equalsIgnoreCase("ELECTRIC") ? monthlyInvoice.getValue().getCost()
						: monthlyInvoice.getValue().getGasCost();
				Double totalConsumption = measurementType.equalsIgnoreCase("ELECTRIC") ? monthlyInvoice.getValue().getValue()
						: monthlyInvoice.getValue().getGasValue();

				updatedContext.append(this.customerId + PIPE + premiseId + PIPE + secondaryId + PIPE + addressLine1 + PIPE + PIPE + PIPE + city + PIPE + state + PIPE + zipCode + PIPE + this.firstName + PIPE + this.lastName + PIPE + phoneNumber +PIPE + email + PIPE + "0" + PIPE + accountId + PIPE + PIPE + PIPE + this.billCycleCode + PIPE + ratePlanId + PIPE + "" + PIPE + servicePointId + PIPE + houseNumber + PIPE + streetName + PIPE );
				updatedContext.append(""+PIPE+city+PIPE+state+PIPE+zipCode+PIPE+"E"+PIPE+"K"+PIPE+totalConsumption+PIPE+billingEndDate+PIPE+duration+PIPE+"A"+PIPE+totalCost+PIPE+accountNumber11Digit+PIPE+""+PIPE+pacConcatAgreementNumber+PIPE+"2017-12-02"+PIPE+"1"+PIPE+"2018"+PIPE+"500  E  BYRNES  RD OLD LASA"+PIPE+pacConcatAccountNumber+PIPE+"A"+PIPE+"A"+PIPE+"10");
				updatedContext.append(System.lineSeparator());
			}
			break;
		default:
			logger.error("utility is not defined for preparing the invoice data");
		}

		return updatedContext.toString();
	}

	public int getBillingPeriodDuration(String d1, String d2, String dateFormat) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
		LocalDate date1 = LocalDate.parse(d1, formatter);
		LocalDate date2 = LocalDate.parse(d2, formatter);
		return Math.abs((int) ChronoUnit.DAYS.between(date1, date2));
	}

	private String convertEpochToDateFormat(DateFormat dateFormat,Integer timeStamp){
		if(timeStamp==0){
			return "";
		}
		Date date = new Date(timeStamp * 1000L);
		return dateFormat.format(date);
	}

	/* creating the User Enrollment data of the user */
	private String getUserFileData(UserManagementUserRegistrationEntry userDetailsResponse, String utilityName, ExecutionVariables executionVariables, String meterFuel, String contractStart, String contractEnd, String gwsId) throws IOException, BidgelyExceptions {
		String userFileData = "";
		String PIPE="|";

		String meterType="";
		String measurementType="";
		if(!meterFuel.isEmpty()){
			meterType = meterFuel.split("-")[0];
			measurementType = meterFuel.split("-")[1];
		}

		JsonObject mailingAddressJson = SharedResources.gson.fromJson(userDetailsResponse.getHomeAccounts().getMailingAddress(), JsonObject.class);
		String mailingZipCode="";
		String mailingAddressLine1="";
		String mailingCity="";
		String mailingState="";
		if(mailingAddressJson!=null && !utilityName.equalsIgnoreCase("vse")) { //for HONI, mailingAddress is null
			mailingZipCode = mailingAddressJson.get("zipcode").getAsString();
			mailingAddressLine1 = mailingAddressJson.get("addressLine1").getAsString();
			mailingCity = mailingAddressJson.get("city").getAsString();
			mailingState = mailingAddressJson.get("state").getAsString();
		}
		String address = userDetailsResponse.getHomeAccounts().getAddress();
		String city = userDetailsResponse.getHomeAccounts().getCity();
		String state = userDetailsResponse.getHomeAccounts().getState();
		String zipCode = userDetailsResponse.getHomeAccounts().getPostalCode();
		String ratePlanId = userDetailsResponse.getHomeAccounts().getRate().getRatePlanId();
		String hasSolar = String.valueOf(userDetailsResponse.getHomeAccounts().getHasSolar());
		ratePlanId = ratePlanId!=null? ratePlanId: "";

		String dataStreamId = !gwsId.isEmpty() && executionVariables.containsKey("dataStreamId_"+gwsId)? RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_"+gwsId): "";
		String userSegment = utilityName.toLowerCase().contains("smb") ?"SMB" :"RES";

		switch (utilityName.toLowerCase()){
		case "tpu":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"EN"+"|"+"|"+"|"+dataStreamId+"|"+"ELECTRIC"+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2022-05-03"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"KWH History Operand";
			break;
		case "oge":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"|"+"OBTAINED"+"|"+"|"+dataStreamId+"|"+"ELECTRIC"+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2017-08-02"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "tep":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"TEP"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"EN"+"|"+"|"+"|"+dataStreamId+"|"+"E"+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2017-03-11"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "nwn":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"|"+"|"+"|"+dataStreamId+"|"+"GAS"+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"KWH History Operand";
			break;
		case "ues":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"UES"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"EN"+"|"+"|"+"|"+dataStreamId+"|"+"E"+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2017-03-11"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "nsp":
		case "nspsmb":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"NS"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"HOME"+"|"+"EN"+"|"+"|"+"FALSE"+"|"+dataStreamId+"|"+"ELECTRIC"+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "ouc":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"HOME"+"|"+"|"+"EN"+"|"+"|"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"labelTextAdded";
			break;
		case "em":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"CELL"+"|"+"EN"+"|"+"|"+"FALSE";
			break;
		case "ameren":
		case "amerensmb":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"|"+"|"+"";
			break;
		case "pnmtod":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"CELL"+"|"+"EN"+"|"+"OBTAINED"+"|"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"10/01/2023"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+"|";
			break;
		case "edison":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"|"+"OBTAINED"+"|"+"FALSE"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "ppca":
		case "bhermpidres":
		case "bhermputres":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"EN"+"|"+"OBTAINED"+"|"+"FALSE"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2021-03-08"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "ppl":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"|"+"OBTAINED"+"|"+"FALSE"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2000-07-28"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "pseg":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"HOME"+"|"+"EN"+"|"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2019-07-01"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "honi":
			dataStreamId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_"+gwsId);
			String partnerUserId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "partnerUserId");
			String contractAccountId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "contractAccountId");
			userFileData = partnerUserId+"|"+contractAccountId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"TAS"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"CELLULAR"+"|"+"EN"+"|"+"OBTAINED"+"|"+"FALSE"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2015-07-28"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "vse":
			String externalAccountId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "externalAccountId");
			String servicePointId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId");
			String encryptedCRMId = SharedResources.generateRandomString(20);
			userFileData = externalAccountId+"|"+servicePointId+"|"+measurementType+"|"+contractStart+"|"+"2030-01-01"+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+city+"|"+state+"|"+zipCode+"|"+ratePlanId+"|"+phoneNumber+"|"+"CELLULAR"+"|"+billCycleCode+"|"+hasSolar.toUpperCase()+"|"+"SK"+"|"+meterType+"|"+encryptedCRMId;
			break;
		case "scg":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"HOME"+"|"+"EN"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "scgsmb":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+"SMB-722513"+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"|"+"|"+"|"+dataStreamId+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"2012-07-26"+"|"+billCycleCode+"|"+"2011-07-26"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
			break;
		case "ei":
			userFileData = customerId+"|"+contractId+"|"+premiseId+"|"+userSegment+"|"+email+"|"+this.firstName+"|"+this.lastName+"|"+ address +"|"+"|"+"|"+"|"+city+"|"+state+"|"+zipCode+"|"+"|"+ mailingAddressLine1 +"|"+"|"+"|"+"|"+mailingCity+"|"+mailingState+"|"+mailingZipCode+"|"+phoneNumber+"|"+"|"+"EN"+"|"+"OBTAINED";
			break;
		case "nve":
			PIPE=",";
			externalAccountId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "externalAccountId");
			servicePointId = RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "servicePointId_"+gwsId);
			userFileData = "1"+PIPE+externalAccountId+PIPE+servicePointId+PIPE+measurementType+PIPE+"2010-01-01 07:00:01"+PIPE+"2030-01-01 07:00:01"+PIPE+email+PIPE+this.firstName+PIPE+this.lastName+PIPE+address+PIPE+PIPE+city+PIPE+state+PIPE+zipCode+PIPE+PIPE+PIPE+PIPE+billCycleCode+PIPE+PIPE+hasSolar.toUpperCase()+PIPE+PIPE+"EN"+PIPE;
			break;
		case "pporres":
			userFileData = customerId+PIPE+contractId+PIPE+premiseId+PIPE+userSegment+PIPE+email+PIPE+this.firstName+PIPE+this.lastName+PIPE+ address +PIPE+PIPE+PIPE+PIPE+city+PIPE+state+PIPE+zipCode+PIPE+PIPE+ mailingAddressLine1 +PIPE+PIPE+PIPE+PIPE+mailingCity+PIPE+mailingState+PIPE+mailingZipCode+PIPE+phoneNumber+PIPE+PIPE+"EN"+PIPE+"OBTAINED"+PIPE+"FALSE"+PIPE+dataStreamId+PIPE+measurementType+PIPE+contractStart+PIPE+contractEnd+PIPE+ratePlanId+PIPE+"2021-03-08"+PIPE+billCycleCode+PIPE+PIPE+hasSolar.toUpperCase()+PIPE+meterType+PIPE+PIPE+PIPE+PIPE+PIPE+PIPE;
			break;
		case "desc":
			userFileData = customerId+PIPE+contractId+PIPE+premiseId+PIPE+userSegment+PIPE+email+PIPE+this.firstName+PIPE+this.lastName+PIPE+ address +PIPE+PIPE+PIPE+PIPE+city+PIPE+state+PIPE+zipCode+PIPE+PIPE+ mailingAddressLine1 +PIPE+PIPE+PIPE+PIPE+mailingCity+PIPE+mailingState+PIPE+mailingZipCode+PIPE+phoneNumber+PIPE+PIPE+PIPE+PIPE+PIPE+dataStreamId+PIPE+measurementType+PIPE+contractStart+PIPE+contractEnd+PIPE+ratePlanId+PIPE+"2012-07-26"+PIPE+billCycleCode+PIPE+"2011-07-26"+PIPE+hasSolar.toUpperCase()+PIPE+meterType+PIPE;
			break;
		default:
			logger.error("utility is not defined for preparing the user enrollment data");
		}
		return userFileData;
	}

	private String getMeterFileData(UserManagementUserRegistrationEntry userDetailsResponse, String utilityName, ExecutionVariables executionVariables, String meterFuel, String contractStart, String contractEnd, String gwsId){
		String meterFileData = "";

		String meterType = meterFuel.split("-")[0];
		String measurementType = meterFuel.split("-")[1];

		String ratePlanId = userDetailsResponse.getHomeAccounts().getRate().getRatePlanId();
		String hasSolar = String.valueOf(userDetailsResponse.getHomeAccounts().getHasSolar());
		ratePlanId = ratePlanId!=null? ratePlanId: "";

		switch (utilityName.toLowerCase()){
		case "em":
		case "ameren":
		case "amerensmb":
		case "edison":
		case "ei":
			meterFileData = customerId+"|"+contractId+"|"+premiseId+"|"+ RecreateProdUserDataContextHandler.ExecutionVariableManager.getExecutionVariable(executionVariables, "dataStreamId_"+gwsId)+"|"+measurementType+"|"+contractStart+"|"+contractEnd+"|"+ratePlanId+"|"+"|"+billCycleCode+"|"+"|"+hasSolar.toUpperCase()+"|"+meterType+"|"+"";
		}
		return meterFileData;
	}

	/* creating and uploading the files to s3 bucket */
	private void createAndUploadFileToBucket(RecreateProdUserDataConfig recreateProdUserDataConfig, String utilityName, String filename, String contentOfFile, int sleepTimeInSeconds) throws BidgelyExceptions, InterruptedException {

		File dataFile = new File(tempDirectory, filename);
		dataFile.deleteOnExit();

		try (FileWriter writer = new FileWriter(dataFile)) {
			String contentToWrite = recreateProdUserDataConfig.isConstructUserFile()? contentOfFile
					: contentOfFile.substring(0, contentOfFile.lastIndexOf(System.lineSeparator()));
			writer.write(contentToWrite);
		} catch (IOException e) {
			throw new BidgelyExceptions("IO Exception occurred while writing content to the file: " + e);
		}

		String ingestionBucket = recreateProdUserDataConfig.getIngestionBucket();
		String fileUploadBucket = recreateProdUserDataConfig.getFileUploadBucket();
		if (ingestionBucket != null && !ingestionBucket.isEmpty()) {
			s3.uploadFile(ingestionBucket, dataFile.getName(), dataFile.getPath());
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = LocalDateTime.now().format(formatter);
		s3.uploadFile(fileUploadBucket+"/"+utilityName+"/"+formattedDate, dataFile.getName(), dataFile.getPath());
		logger.info("Waiting for {} seconds to get file proceess", sleepTimeInSeconds);
		Thread.sleep(sleepTimeInSeconds*1000L);
	}

	private String getGWSId(String meterFuel){
		switch (meterFuel.toUpperCase()){
		case "NSM-GAS":
		case "AMR-GAS":
		case "NSM-WATER":
			return "4";
		case "AMI-ELECTRIC":
			return "2";
		case "NSM-ELECTRIC":
		case "AMR-ELECTRIC":
			return "3";
		case "AMI-GAS":
		case "AMI-WATER":
			return "5";
		default:
			logger.error("Invalid Meter Fuel Type!");
		}
		return null;
	}
}

