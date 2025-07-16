package com.bidgely.context;

public enum ContextKeys {
	ConsumptionDataDefaults_Pulse("consumptionDataDefaults.pulse"),
	ConsumptionDataDefaults_CsdUnit("consumptionDataDefaults.csdUnit"),
	ConsumptionDataDefaults_IdUnit("consumptionDataDefaults.idUnit"),
	ConsumptionDataDefaults_IdUnitHER("consumptionDataDefaults.idUnitHER"),

	ExecutionEnvironment_Utility("executionEnvironment.utility"),
	ExecutionEnvironment_DataType("executionEnvironment.dataType"),
	ExecutionEnvironment_TestType("executionEnvironment.testType"),
	ExecutionEnvironment_Component("executionEnvironment.component"),
	ExecutionEnvironment_BaseURL("executionEnvironment.baseUrl"),
	ExecutionEnvironment_PilotId("executionEnvironment.pilotId"),
	ExecutionEnvironment_PilotToken("executionEnvironment.pilotToken"),
	ExecutionEnvironment_AuthToken("executionEnvironment.authToken"),
	ExecutionEnvironment_WebTests_BaseUrl(
			"executionEnvironment.webTests.baseUrl"),
	ExecutionEnvironment_Currency_Unit("executionEnvironment.currencyUnit"),

	UserDefaults_EmailPrefix("userDefaults.emailPrefix"),
	UserDefaults_FirstNamePrefix("userDefaults.firstNamePrefix"),
	UserDefaults_LastName("userDefaults.lastName"),
	UserDefaults_Password("userDefaults.password"),
	UserDefaults_Status("userDefaults.status"),
	UserDefaults_ExternalAccountId("userDefaults.externalAccountId"),
	UserDefaults_Address("userDefaults.address"),
	UserDefaults_Country("userDefaults.country"),
	UserDefaults_City("userDefaults.city"),
	UserDefaults_Zip("userDefaults.zip"),
	UserDefaults_State("userDefaults.state"),
	UserDefaults_DwellingType("userDefaults.dwellingType"),
	UserDefaults_Size("userDefaults.size"),
	UserDefaults_YearBuilt("userDefaults.yearBuilt"),
	UserDefaults_BedroomCount("userDefaults.bedroomCount"),
	UserDefaults_BathroomCount("userDefaults.bathroomCount"),
	UserDefaults_Timezone("userDefaults.timezone"),
	UserDefaults_RatePlanId("userDefaults.ratePlanId"),
	UserDefaults_Rate("userDefaults.rate"),
	UserDefaults_BillCycleCode("userDefaults.billCycleCode"),
	UserDefaults_BillingCycleType("userDefaults.billingCycleType"),
	UserDefaults_BillCycleCodePrefix("userDefaults.billCycleCodePrefix"),
	UserDefaults_DisableShowDevicePairing(
			"userDefaults.disableShowDevicePairing"),
	UserDefaults_Locale("userDefaults.locale"),
	UserDefaults_AddressType("userDefaults.addressType"),
	UserDefaults_CountryCode("userDefaults.countryCode"),
	UserDefaults_Locality("userDefaults.locality"),
	UserDefaults_NumOccupants("userDefaults.numOccupants"),
	UserDefaults_OccupantType("userDefaults.occupantType"),
	UserDefaults_OwnershipType("userDefaults.ownershipType"),
	UserDefaults_PhoneNumber("userDefaults.phoneNumber"),
	UserDefaults_PhoneType("userDefaults.phoneType"),
	UserDefaults_PostalCode("userDefaults.postalCode"),
	UserDefaults_PropertyAgeType("userDefaults.propertyAgeType"),
	UserDefaults_PropertyType("userDefaults.propertyType"),
	UserDefaults_HasSolar("userDefaults.hasSolar"),
	UserDefaults_ShowDevicePairing("userDefaults.showDevicePairing"),
	UserDefaults_SpaceHeatingType("userDefaults.spaceHeatingType"),
	UserDefaults_WaterHeatingType("userDefaults.waterHeatingType"),
	UserDefaults_BillStart("userDefaults.billStart"),
	UserDefaults_DailyUsageDownAlertPercent(
			"userDefaults.dailyUsageDownAlertPercent"),
	UserDefaults_DailyUsageUpAlertPercent(
			"userDefaults.dailyUsageUpAlertPercent"),
	UserDefaults_LseId("userDefaults.lseId"),
	UserDefaults_MasterTariffId("userDefaults.masterTariffId"),
	UserDefaults_PlanName("userDefaults.planName"),
	UserDefaults_RateQuery("userDefaults.rateQuery"),
	UserDefaults_RateType("userDefaults.rateType"),
	UserDefaults_UtilityName("userDefaults.utilityName"),
	UserDefaults_StoveHeatingType("userDefaults.stoveHeatingType"),
	UserDefaults_GrillHeatingType("userDefaults.grillHeatingType"),
	UserDefaults_OvenHeatingType("userDefaults.ovenHeatingType"),
	UserDefaults_PlanNumber("userDefaults.planNumber"),
	UserDefaults_JurisdictionCode("userDefaults.jurisdictionCode"),
	UserDefaults_FuelType("userDefaults.fuelType"),
	UserDefaults_TransitionBillingEnabled(
			"userDefaults.transitionBillingEnabled"),

	ApplicationDefaults_App("applicationDefaults.app"),
	ApplicationDefaults_AppVersion("applicationDefaults.appVersion"),

	DeviceDefaults_DeviceId("deviceDefaults.deviceId"),
	DeviceDefaults_Platform("deviceDefaults.platform"),
	DeviceDefaults_PlatformToken("deviceDefaults.platformToken"),

	Generators_RangeBoundGenerator_LowUsage_MinimumId(
			"generators.rangeBoundGenerator.low.minId"),
	Generators_RangeBoundGenerator_LowUsage_MaximumId(
			"generators.rangeBoundGenerator.low.maxId"),
	Generators_RangeBoundGenerator_DefaultUsage_MinimumId(
			"generators.rangeBoundGenerator.default.minId"),
	Generators_RangeBoundGenerator_DefaultUsage_MaximumId(
			"generators.rangeBoundGenerator.default.maxId"),
	Generators_RangeBoundGenerator_HighUsage_MinimumId(
			"generators.rangeBoundGenerator.high.minId"),
	Generators_RangeBoundGenerator_HighUsage_MaximumId(
			"generators.rangeBoundGenerator.high.maxId"),

	Generators_FlatFileConsumption_FilePath(
			"generators.flatConsumptionDataFileGenerator.filePath"),

	Converters_PublicApiConverter_UploadVersion(
			"converters.publicApiConverter.uploadVersion"),
	Converters_PublicApiConverter_MeterId(
			"converters.publicApiConverter.meterId"),
	Converters_PublicApiConverter_Model("converters.publicApiConverter.model"),
	Converters_PublicApiConverter_Type("converters.publicApiConverter.type"),
	Converters_PublicApiConverter_Description(
			"converters.publicApiConverter.description"),
	Converters_PublicApiConverter_RandomizeDurationPerFile(
			"converters.publicApiConverter.randomizeDurationPerFile"),
	Converters_PublicApiConverter_IdUnit(
			"converters.publicApiConverter.idUnit"),
	Converters_PublicApiConverter_CsdUnit(
			"converters.publicApiConverter.csdUnit"),
	Converters_PublicApiConverter_FileSplitDuration(
			"converters.publicApiConverter.fileSplitDuration"),

	Uploaders_S3DataUploader_BucketName("uploaders.s3DataUploader.bucketName"),
	Uploaders_S3DataUploader_AccessId("uploaders.s3DataUploader.accessId"),
	Uploaders_S3DataUploader_SecretKey("uploaders.s3DataUploader.secretKey"),
	Uploaders_S3DataUploader_UploadWaitTime(
			"uploaders.s3DataUploader.uploadWaitTime"),

	Uploaders_SftpDataUploader_Hostname("uploaders.sftpDataUploader.hostname"),
	Uploaders_SftpDataUploader_Port("uploaders.sftpDataUploader.port"),
	Uploaders_SftpDataUploader_Folder("uploaders.sftpDataUploader.folder"),
	Uploaders_SftpDataUploader_Username("uploaders.sftpDataUploader.username"),
	Uploaders_SftpDataUploader_Password("uploaders.sftpDataUploader.password"),

	Uploaders_BackendApiHanDataUploader_ConnectTimeOut(
			"uploaders.backendApiHanDataUploader.connectTimeOut"),
	Uploaders_BackendApiHanDataUploader_ReadTimeOut(
			"uploaders.backendApiHanDataUploader.readTimeOut"),

	EventTrigger_SQS_AggregationTrigger_QueueUrl(
			"eventTrigger.sqs.aggregationTrigger.queueUrl"),
	EventTrigger_SQS_AggregationTrigger_AccessKey(
			"eventTrigger.sqs.aggregationTrigger.accessKey"),
	EventTrigger_SQS_AggregationTrigger_SecretKey(
			"eventTrigger.sqs.aggregationTrigger.secretKey"),

	EventTrigger_SSH_SftpUpload_RemoteBox(
			"eventTrigger.ssh.sftpUpload.remoteBox"),
	EventTrigger_SSH_SftpUpload_Command("eventTrigger.ssh.sftpUpload.command"),

	EventTrigger_SSH_DissaggregationTrigger_RemoteBox(
			"eventTrigger.ssh.disaggregationTrigger.remoteBox"),
	EventTrigger_SSH_DissaggregationTrigger_Command(
			"eventTrigger.ssh.disaggregationTrigger.command"),

	SFTP_WorkingDirectory("sftp.workingdirectory"),

	PilotDefaults_RawDataUnit("pilotDefaults.rawDataUnit"),
	UserDefaults_BillingGroup("userDefaults.billingGroup"),
	ConsumptionDataDefaults_Offset("user.consumption.offset.value"),
	CostValueDefaults_Offset("user.cost.offset.value"),

	DateFormat_AMIRawFile("dateFormat.AMIRawFile"),
	DateFormat_InvoiceFile("dateFormat.invoiceFile"),

	Prefix_AMIRawFile("prefix.AMIRawFile"),
	Suffix_AMIRawFile("suffix.AMIRawFile"),

	Prefix_InvoiceFile("prefix.invoiceFile"),
	Suffix_InvoiceFile("suffix.invoiceFile"),
	Separator_InvoiceFile("separator.invoiceFile"),

	Prefix_UserFile("prefix.userFile"),
	Suffix_UserFile("suffix.userFile"),

	Prefix_MeterFile("prefix.meterFile"),
	Suffix_MeterFile("suffix.meterFile");

	String key;

	private ContextKeys(String key) {
		this.key = key;
	}

	public String toString() {
		return this.key;
	}
}
