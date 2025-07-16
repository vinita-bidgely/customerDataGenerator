package com.bidgely.commonExecutionResources;

import com.bidgely.context.ContextKeys;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class ExecutionContext1 {
	private final Properties executionProperties = new Properties();

	public ExecutionContext1() {
		loadDefaultProperties();
	}

	public String getProperty(ContextKeys contextKey) {
		return getProperty(contextKey.toString());
	}

	public String getProperty(String key) {
		return executionProperties.getProperty(key);
	}

	public String getProperty(String key, String defaultValue) {
		return executionProperties.getProperty(key, defaultValue);
	}

	private void loadDefaultProperties() {
		// Load from application.properties or environment variables
		executionProperties.setProperty("userDefaults.utilityName", "your-utility-name");
		executionProperties.setProperty("executionEnvironment.pilotId", "your-pilot-id");
		executionProperties.setProperty("userDefaults.timezone", "America/New_York");
		executionProperties.setProperty("prefix.invoiceFile", "invoice");

		// Additional properties that might be needed
		executionProperties.setProperty("userDefaults.emailPrefix", "test");
		executionProperties.setProperty("userDefaults.firstNamePrefix", "Test");
		executionProperties.setProperty("userDefaults.lastName", "User");
		executionProperties.setProperty("userDefaults.address", "123 Test St");
		executionProperties.setProperty("userDefaults.city", "Test City");
		executionProperties.setProperty("userDefaults.state", "CA");
		executionProperties.setProperty("userDefaults.zip", "12345");
		executionProperties.setProperty("userDefaults.country", "US");
		executionProperties.setProperty("userDefaults.phoneNumber", "555-123-4567");
		executionProperties.setProperty("userDefaults.password", "testPassword");
		executionProperties.setProperty("userDefaults.status", "ACTIVE");
		executionProperties.setProperty("userDefaults.externalAccountId", "EXT123");
		executionProperties.setProperty("userDefaults.dwellingType", "SINGLE_FAMILY");
		executionProperties.setProperty("userDefaults.size", "2000");
		executionProperties.setProperty("userDefaults.yearBuilt", "2000");
		executionProperties.setProperty("userDefaults.bedroomCount", "3");
		executionProperties.setProperty("userDefaults.bathroomCount", "2");
		executionProperties.setProperty("userDefaults.ratePlanId", "RATE001");
		executionProperties.setProperty("userDefaults.rate", "STANDARD");
		executionProperties.setProperty("userDefaults.billCycleCode", "BC001");
		executionProperties.setProperty("userDefaults.billingCycleType", "MONTHLY");
		executionProperties.setProperty("userDefaults.billCycleCodePrefix", "BC");
		executionProperties.setProperty("userDefaults.locale", "en_US");
		executionProperties.setProperty("userDefaults.addressType", "RESIDENTIAL");
		executionProperties.setProperty("userDefaults.countryCode", "US");
		executionProperties.setProperty("userDefaults.locality", "Test Locality");
		executionProperties.setProperty("userDefaults.numOccupants", "4");
		executionProperties.setProperty("userDefaults.occupantType", "FAMILY");
		executionProperties.setProperty("userDefaults.ownershipType", "OWNER");
		executionProperties.setProperty("userDefaults.phoneType", "MOBILE");
		executionProperties.setProperty("userDefaults.postalCode", "12345");
		executionProperties.setProperty("userDefaults.propertyAgeType", "MODERN");
		executionProperties.setProperty("userDefaults.propertyType", "SINGLE_FAMILY");
		executionProperties.setProperty("userDefaults.hasSolar", "false");
		executionProperties.setProperty("userDefaults.showDevicePairing", "true");
		executionProperties.setProperty("userDefaults.spaceHeatingType", "ELECTRIC");
		executionProperties.setProperty("userDefaults.waterHeatingType", "ELECTRIC");
		executionProperties.setProperty("userDefaults.billStart", "2023-01-01");
		executionProperties.setProperty("userDefaults.dailyUsageDownAlertPercent", "10");
		executionProperties.setProperty("userDefaults.dailyUsageUpAlertPercent", "20");
		executionProperties.setProperty("userDefaults.lseId", "LSE001");
		executionProperties.setProperty("userDefaults.masterTariffId", "TARIFF001");
		executionProperties.setProperty("userDefaults.planName", "Standard Plan");
		executionProperties.setProperty("userDefaults.rateQuery", "STANDARD");
		executionProperties.setProperty("userDefaults.rateType", "STANDARD");
		executionProperties.setProperty("userDefaults.stoveHeatingType", "ELECTRIC");
		executionProperties.setProperty("userDefaults.grillHeatingType", "ELECTRIC");
		executionProperties.setProperty("userDefaults.ovenHeatingType", "ELECTRIC");
		executionProperties.setProperty("userDefaults.planNumber", "PLAN001");
		executionProperties.setProperty("userDefaults.jurisdictionCode", "JC001");
		executionProperties.setProperty("userDefaults.fuelType", "ELECTRIC");
		executionProperties.setProperty("userDefaults.transitionBillingEnabled", "true");

		// Execution Environment properties
		executionProperties.setProperty("executionEnvironment.utility", "your-utility");
		executionProperties.setProperty("executionEnvironment.dataType", "HAN");
		executionProperties.setProperty("executionEnvironment.testType", "INTEGRATION");
		executionProperties.setProperty("executionEnvironment.component", "BACKEND");
		executionProperties.setProperty("executionEnvironment.baseUrl", "https://api.example.com");
		executionProperties.setProperty("executionEnvironment.pilotToken", "your-pilot-token");
		executionProperties.setProperty("executionEnvironment.authToken", "your-auth-token");
		executionProperties.setProperty("executionEnvironment.webTests.baseUrl", "https://web.example.com");
		executionProperties.setProperty("executionEnvironment.currencyUnit", "USD");

		// Application Defaults
		executionProperties.setProperty("applicationDefaults.app", "hawk");
		executionProperties.setProperty("applicationDefaults.appVersion", "1.0.0");

		// Device Defaults
		executionProperties.setProperty("deviceDefaults.deviceId", "DEVICE001");
		executionProperties.setProperty("deviceDefaults.platform", "WEB");
		executionProperties.setProperty("deviceDefaults.platformToken", "PLATFORM_TOKEN");

		// Generators
		executionProperties.setProperty("generators.rangeBoundGenerator.low.minId", "1");
		executionProperties.setProperty("generators.rangeBoundGenerator.low.maxId", "100");
		executionProperties.setProperty("generators.rangeBoundGenerator.default.minId", "101");
		executionProperties.setProperty("generators.rangeBoundGenerator.default.maxId", "200");
		executionProperties.setProperty("generators.rangeBoundGenerator.high.minId", "201");
		executionProperties.setProperty("generators.rangeBoundGenerator.high.maxId", "300");
		executionProperties.setProperty("generators.flatConsumptionDataFileGenerator.filePath", "/data/consumption");

		// Converters
		executionProperties.setProperty("converters.publicApiConverter.uploadVersion", "1.0");
		executionProperties.setProperty("converters.publicApiConverter.meterId", "METER001");
		executionProperties.setProperty("converters.publicApiConverter.model", "MODEL001");
		executionProperties.setProperty("converters.publicApiConverter.type", "TYPE001");
		executionProperties.setProperty("converters.publicApiConverter.description", "Test Meter");
		executionProperties.setProperty("converters.publicApiConverter.randomizeDurationPerFile", "false");
		executionProperties.setProperty("converters.publicApiConverter.idUnit", "UNIT001");
		executionProperties.setProperty("converters.publicApiConverter.csdUnit", "CSD001");
		executionProperties.setProperty("converters.publicApiConverter.fileSplitDuration", "3600");

		// Uploaders
		executionProperties.setProperty("uploaders.s3DataUploader.bucketName", "test-bucket");
		executionProperties.setProperty("uploaders.s3DataUploader.accessId", "your-access-id");
		executionProperties.setProperty("uploaders.s3DataUploader.secretKey", "your-secret-key");
		executionProperties.setProperty("uploaders.s3DataUploader.uploadWaitTime", "5000");
		executionProperties.setProperty("uploaders.sftpDataUploader.hostname", "sftp.example.com");
		executionProperties.setProperty("uploaders.sftpDataUploader.port", "22");
		executionProperties.setProperty("uploaders.sftpDataUploader.folder", "/upload");
		executionProperties.setProperty("uploaders.sftpDataUploader.username", "sftp-user");
		executionProperties.setProperty("uploaders.sftpDataUploader.password", "sftp-password");
		executionProperties.setProperty("uploaders.backendApiHanDataUploader.connectTimeOut", "30000");
		executionProperties.setProperty("uploaders.backendApiHanDataUploader.readTimeOut", "60000");

		// Event Triggers
		executionProperties.setProperty("eventTrigger.sqs.aggregationTrigger.queueUrl", "https://sqs.example.com/queue");
		executionProperties.setProperty("eventTrigger.sqs.aggregationTrigger.accessKey", "your-access-key");
		executionProperties.setProperty("eventTrigger.sqs.aggregationTrigger.secretKey", "your-secret-key");
		executionProperties.setProperty("eventTrigger.ssh.sftpUpload.remoteBox", "remote.example.com");
		executionProperties.setProperty("eventTrigger.ssh.sftpUpload.command", "upload-command");
		executionProperties.setProperty("eventTrigger.ssh.disaggregationTrigger.remoteBox", "remote.example.com");
		executionProperties.setProperty("eventTrigger.ssh.disaggregationTrigger.command", "disaggregate-command");

		// SFTP
		executionProperties.setProperty("sftp.workingdirectory", "/tmp");

		// Pilot Defaults
		executionProperties.setProperty("pilotDefaults.rawDataUnit", "KWH");
		executionProperties.setProperty("userDefaults.billingGroup", "GROUP001");
		executionProperties.setProperty("user.consumption.offset.value", "0");
		executionProperties.setProperty("user.cost.offset.value", "0");

		// Date Formats
		executionProperties.setProperty("dateFormat.AMIRawFile", "yyyy-MM-dd HH:mm:ss");
		executionProperties.setProperty("dateFormat.invoiceFile", "yyyy-MM-dd");

		// Prefixes and Suffixes
		executionProperties.setProperty("prefix.AMIRawFile", "RAW_");
		executionProperties.setProperty("suffix.AMIRawFile", ".csv");
		executionProperties.setProperty("prefix.invoiceFile", "INV_");
		executionProperties.setProperty("suffix.invoiceFile", ".csv");
		executionProperties.setProperty("separator.invoiceFile", "|");
		executionProperties.setProperty("prefix.userFile", "USER_");
		executionProperties.setProperty("suffix.userFile", ".csv");
		executionProperties.setProperty("prefix.meterFile", "METER_");
		executionProperties.setProperty("suffix.meterFile", ".csv");

		// Consumption Data Defaults
		executionProperties.setProperty("consumptionDataDefaults.pulse", "900");
		executionProperties.setProperty("consumptionDataDefaults.csdUnit", "KWH");
		executionProperties.setProperty("consumptionDataDefaults.idUnit", "KWH");
		executionProperties.setProperty("consumptionDataDefaults.idUnitHER", "KWH");
	}

	public void appendProperties(Properties globalProps) {
	}

	public void addProperty(ContextKeys contextKeys, String utilityName) {
	}
}
