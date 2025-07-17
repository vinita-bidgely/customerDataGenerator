package com.bidgely.customerDataGenerator.commonExecutionResources;


import com.bidgely.customerDataGenerator.context.ContextKeys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
@PropertySource({
		"classpath:application.properties",
		"classpath:global.properties",
		"classpath:pilot-specific.properties"
})
public class ExecutionConfiguration {

	@Value("${executionEnvironment.utility:}")
	private String utilityName;

	@Value("${executionEnvironment.pilotId:}")
	private String pilotId;

	@Value("${executionEnvironment.pilotToken:}")
	private String pilotToken;

	@Value("${executionEnvironment.currencyUnit:$}")
	private String currencyUnit;

	@Value("${userDefaults.emailPrefix:bidgelyqa+}")
	private String emailPrefix;

	@Value("${userDefaults.country:US}")
	private String country;

	@Value("${userDefaults.city:}")
	private String city;

	@Value("${userDefaults.zip:}")
	private String zip;

	@Value("${userDefaults.state:}")
	private String state;

	@Value("${userDefaults.timezone:America/New_York}")
	private String timezone;

	@Value("${userDefaults.billCycleCode:}")
	private String billCycleCode;

	@Value("${userDefaults.ratePlanId:}")
	private String ratePlanId;

	@Value("${userDefaults.locale:en_US}")
	private String locale;

	@Value("${userDefaults.utilityName:}")
	private String userUtilityName;

	@Value("${userDefaults.fuelType:ELECTRIC}")
	private String fuelType;

	@Value("${userDefaults.billCycleCodePrefix:}")
	private String billCycleCodePrefix;

	@Value("${userDefaults.address:}")
	private String address;

	@Value("${userDefaults.phoneNumber:}")
	private String phoneNumber;

	@Value("${userDefaults.phoneType:HOME}")
	private String phoneType;

	@Value("${userDefaults.postalCode:}")
	private String postalCode;

	@Value("${userDefaults.jurisdictionCode:}")
	private String jurisdictionCode;

	@Value("${userDefaults.transitionBillingEnabled:false}")
	private String transitionBillingEnabled;

	@Value("${consumptionDataDefaults.idUnit:kWh}")
	private String idUnit;

	@Value("${consumptionDataDefaults.idUnitHER:kWh}")
	private String idUnitHER;

	@Value("${consumptionDataDefaults.pulse:}")
	private String pulse;

	@Value("${consumptionDataDefaults.csdUnit:}")
	private String csdUnit;

	@Value("${pilotDefaults.rawDataUnit:kWh}")
	private String rawDataUnit;

	@Value("${deviceDefaults.deviceId:}")
	private String deviceId;

	@Value("${deviceDefaults.platform:}")
	private String platform;

	@Value("${deviceDefaults.platformToken:}")
	private String platformToken;

	@Value("${applicationDefaults.app:}")
	private String app;

	@Value("${applicationDefaults.appVersion:}")
	private String appVersion;

	@Value("${sftp.workingdirectory:}")
	private String sftpWorkingDirectory;

	@Value("${dateFormat.AMIRawFile:}")
	private String amiRawFileDateFormat;

	@Value("${dateFormat.invoiceFile:}")
	private String invoiceFileDateFormat;

	@Value("${prefix.AMIRawFile:}")
	private String amiRawFilePrefix;

	@Value("${suffix.AMIRawFile:}")
	private String amiRawFileSuffix;

	@Value("${prefix.invoiceFile:}")
	private String invoiceFilePrefix;

	@Value("${suffix.invoiceFile:}")
	private String invoiceFileSuffix;

	@Value("${separator.invoiceFile:}")
	private String invoiceFileSeparator;

	@Value("${prefix.userFile:}")
	private String userFilePrefix;

	@Value("${suffix.userFile:}")
	private String userFileSuffix;

	@Value("${prefix.meterFile:}")
	private String meterFilePrefix;

	@Value("${suffix.meterFile:}")
	private String meterFileSuffix;

	@Value("${alwaysOn:}")
	private String alwaysOn;

	@Value("${cooking:}")
	private String cooking;

	@Value("${refrigeration:}")
	private String refrigeration;

	@Value("${laundry:}")
	private String laundry;

	@Value("${entertainment:}")
	private String entertainment;

	@Value("${lighting:}")
	private String lighting;

	@Value("${other:}")
	private String other;

	@Value("${electricVehicle:}")
	private String electricVehicle;

	@Value("${pool:}")
	private String pool;

	@Value("${airConditioning:}")
	private String airConditioning;

	@Value("${spaceHeating:}")
	private String spaceHeating;

	@Value("${waterHeating:}")
	private String waterHeating;

	@Value("${total:}")
	private String total;

//	@Value("${currency.unit:$}")
//	private String currencyUnit;

	@Value("${consumption.unit:kWh}")
	private String consumptionUnit;

	@Value("${dashboard.tips.page.first.entry:}")
	private String dashboardTipsPageFirstEntry;

	@Value("${dashboard.tips.page.last.entry:}")
	private String dashboardTipsPageLastEntry;

	@Value("${tips.page.appliance.name.mapping:}")
	private String tipsPageApplianceNameMapping;

	@Value("${reco.button.did.it:}")
	private String recoButtonDidIt;

	@Value("${reco.button.will.do.it:}")
	private String recoButtonWillDoIt;

	@Value("${kw.to.ccf.conversion.value:}")
	private String kwToCcfConversionValue;

	@Value("${email.validation.propertyFilepath:/tmp/automation/test-data/}")
	private String emailValidationPropertyFilepath;

	@Value("${generated.automation.property.path:/tmp/bidgely/automation/properties/}")
	private String generatedAutomationPropertyPath;

	@Value("${uploaders.backendApiHanDataUploader.connectTimeOut:60000}")
	private String hanDataUploaderConnectTimeout;

	@Value("${uploaders.backendApiHanDataUploader.readTimeOut:60000}")
	private String hanDataUploaderReadTimeout;

	@Value("${email.image.folderPath:/tmp/bidgely/}")
	private String emailImageFolderPath;

	@Value("${email.extracted.image.folderName:extracted/}")
	private String emailExtractedImageFolderName;

	@Value("${email.downloaded.recoImage.folderName:recommendations/}")
	private String emailDownloadedRecoImageFolderName;

	@Value("${properties.to.update:}")
	private String propertiesToUpdate;

	@Value("${pilot.heavy.test.components:}")
	private String pilotHeavyTestComponents;

	@Value("${pilotNameIdMap:}")
	private String pilotNameIdMap;

//	@Bean
//	public ExecutionContext1 executionContext() throws BidgelyExceptions {
//		ExecutionContext1 context = new ExecutionContext1();
//
//		// Load global properties first
//		loadGlobalProperties(context);
//
//		// Load pilot-specific properties (overrides global)
//		loadPilotSpecificProperties(context);
//
//		// Set properties from Spring @Value annotations
//		setExecutionContextProperties(context);
//
//		return context;
//	}

//	@Bean
//	public ExecutionVariables1 executionVariables() {
//		return new ExecutionVariables1();
//	}
//
//	private void loadGlobalProperties(ExecutionContext1 context) throws BidgelyExceptions {
//		try (InputStream inputStream = getClass().getResourceAsStream("/global.properties")) {
//			if (inputStream != null) {
//				Properties globalProps = new Properties();
//				globalProps.load(inputStream);
//				context.appendProperties(globalProps);
//			}
//		} catch (IOException e) {
//			throw new BidgelyExceptions("Failed to load global properties: " + e.getMessage());
//		}
//	}

//	private void loadPilotSpecificProperties(ExecutionContext1 context) throws BidgelyExceptions {
//		if (utilityName == null || utilityName.isEmpty()) {
//			return; // No pilot specified
//		}
//
//		String pilotPropertiesPath = "/execution/context/" + utilityName + "/consolidated.properties";
//		try (InputStream inputStream = getClass().getResourceAsStream(pilotPropertiesPath)) {
//			if (inputStream != null) {
//				Properties pilotProps = new Properties();
//				pilotProps.load(inputStream);
//				context.appendProperties(pilotProps);
//			}
//		} catch (IOException e) {
//			throw new BidgelyExceptions("Failed to load pilot properties for " + utilityName + ": " + e.getMessage());
//		}
//	}

	private void setExecutionContextProperties(ExecutionContext context) {
		// Load global properties
		Properties globalProperties = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("global.properties")) {
			if (input != null) {
				globalProperties.load(input);
			}
		} catch (IOException e) {
			// Log error but continue with default values
			System.err.println("Error loading global.properties: " + e.getMessage());
		}

		// Execution Environment Properties
		if (utilityName != null && !utilityName.isEmpty()) {
			context.addProperty(ContextKeys.ExecutionEnvironment_Utility, utilityName);
		}
		if (pilotId != null && !pilotId.isEmpty()) {
			context.addProperty(ContextKeys.ExecutionEnvironment_PilotId, pilotId);
		}
		if (pilotToken != null && !pilotToken.isEmpty()) {
			context.addProperty(ContextKeys.ExecutionEnvironment_PilotToken, pilotToken);
		}
		if (currencyUnit != null && !currencyUnit.isEmpty()) {
			context.addProperty(ContextKeys.ExecutionEnvironment_Currency_Unit, currencyUnit);
		}

		// User Defaults Properties
		if (emailPrefix != null && !emailPrefix.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_EmailPrefix, emailPrefix);
		}
		if (country != null && !country.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_Country, country);
		}
		if (city != null && !city.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_City, city);
		}
		if (zip != null && !zip.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_Zip, zip);
		}
		if (state != null && !state.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_State, state);
		}
		if (timezone != null && !timezone.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_Timezone, timezone);
		}
		if (billCycleCode != null && !billCycleCode.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_BillCycleCode, billCycleCode);
		}
		if (ratePlanId != null && !ratePlanId.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_RatePlanId, ratePlanId);
		}
		if (locale != null && !locale.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_Locale, locale);
		}
		if (userUtilityName != null && !userUtilityName.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_UtilityName, userUtilityName);
		}
		if (fuelType != null && !fuelType.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_FuelType, fuelType);
		}
		if (billCycleCodePrefix != null && !billCycleCodePrefix.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_BillCycleCodePrefix, billCycleCodePrefix);
		}
		if (address != null && !address.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_Address, address);
		}
		if (phoneNumber != null && !phoneNumber.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_PhoneNumber, phoneNumber);
		}
		if (phoneType != null && !phoneType.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_PhoneType, phoneType);
		}
		if (postalCode != null && !postalCode.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_PostalCode, postalCode);
		}
		if (jurisdictionCode != null && !jurisdictionCode.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_JurisdictionCode, jurisdictionCode);
		}
		if (transitionBillingEnabled != null && !transitionBillingEnabled.isEmpty()) {
			context.addProperty(ContextKeys.UserDefaults_TransitionBillingEnabled, transitionBillingEnabled);
		}

		// Consumption Data Defaults
		if (idUnit != null && !idUnit.isEmpty()) {
			context.addProperty(ContextKeys.ConsumptionDataDefaults_IdUnit, idUnit);
		}
		if (idUnitHER != null && !idUnitHER.isEmpty()) {
			context.addProperty(ContextKeys.ConsumptionDataDefaults_IdUnitHER, idUnitHER);
		}
		if (pulse != null && !pulse.isEmpty()) {
			context.addProperty(ContextKeys.ConsumptionDataDefaults_Pulse, pulse);
		}
		if (csdUnit != null && !csdUnit.isEmpty()) {
			context.addProperty(ContextKeys.ConsumptionDataDefaults_CsdUnit, csdUnit);
		}

		// Pilot Defaults
		if (rawDataUnit != null && !rawDataUnit.isEmpty()) {
			context.addProperty(ContextKeys.PilotDefaults_RawDataUnit, rawDataUnit);
		}

		// Device Defaults
		if (deviceId != null && !deviceId.isEmpty()) {
			context.addProperty(ContextKeys.DeviceDefaults_DeviceId, deviceId);
		}
		if (platform != null && !platform.isEmpty()) {
			context.addProperty(ContextKeys.DeviceDefaults_Platform, platform);
		}
		if (platformToken != null && !platformToken.isEmpty()) {
			context.addProperty(ContextKeys.DeviceDefaults_PlatformToken, platformToken);
		}

		// Application Defaults
		if (app != null && !app.isEmpty()) {
			context.addProperty(ContextKeys.ApplicationDefaults_App, app);
		}
		if (appVersion != null && !appVersion.isEmpty()) {
			context.addProperty(ContextKeys.ApplicationDefaults_AppVersion, appVersion);
		}

		// SFTP Properties
		if (sftpWorkingDirectory != null && !sftpWorkingDirectory.isEmpty()) {
			context.addProperty(ContextKeys.SFTP_WorkingDirectory, sftpWorkingDirectory);
		}

		// Date Format Properties
		if (amiRawFileDateFormat != null && !amiRawFileDateFormat.isEmpty()) {
			context.addProperty(ContextKeys.DateFormat_AMIRawFile, amiRawFileDateFormat);
		}
		if (invoiceFileDateFormat != null && !invoiceFileDateFormat.isEmpty()) {
			context.addProperty(ContextKeys.DateFormat_InvoiceFile, invoiceFileDateFormat);
		}

		// File Prefix/Suffix Properties
		if (amiRawFilePrefix != null && !amiRawFilePrefix.isEmpty()) {
			context.addProperty(ContextKeys.Prefix_AMIRawFile, amiRawFilePrefix);
		}
		if (amiRawFileSuffix != null && !amiRawFileSuffix.isEmpty()) {
			context.addProperty(ContextKeys.Suffix_AMIRawFile, amiRawFileSuffix);
		}
		if (invoiceFilePrefix != null && !invoiceFilePrefix.isEmpty()) {
			context.addProperty(ContextKeys.Prefix_InvoiceFile, invoiceFilePrefix);
		}
		if (invoiceFileSuffix != null && !invoiceFileSuffix.isEmpty()) {
			context.addProperty(ContextKeys.Suffix_InvoiceFile, invoiceFileSuffix);
		}
		if (invoiceFileSeparator != null && !invoiceFileSeparator.isEmpty()) {
			context.addProperty(ContextKeys.Separator_InvoiceFile, invoiceFileSeparator);
		}
		if (userFilePrefix != null && !userFilePrefix.isEmpty()) {
			context.addProperty(ContextKeys.Prefix_UserFile, userFilePrefix);
		}
		if (userFileSuffix != null && !userFileSuffix.isEmpty()) {
			context.addProperty(ContextKeys.Suffix_UserFile, userFileSuffix);
		}
		if (meterFilePrefix != null && !meterFilePrefix.isEmpty()) {
			context.addProperty(ContextKeys.Prefix_MeterFile, meterFilePrefix);
		}
		if (meterFileSuffix != null && !meterFileSuffix.isEmpty()) {
			context.addProperty(ContextKeys.Suffix_MeterFile, meterFileSuffix);
		}

		// Uploader Properties
		if (hanDataUploaderConnectTimeout != null && !hanDataUploaderConnectTimeout.isEmpty()) {
			context.addProperty(ContextKeys.Uploaders_BackendApiHanDataUploader_ConnectTimeOut, hanDataUploaderConnectTimeout);
		}
		if (hanDataUploaderReadTimeout != null && !hanDataUploaderReadTimeout.isEmpty()) {
			context.addProperty(ContextKeys.Uploaders_BackendApiHanDataUploader_ReadTimeOut, hanDataUploaderReadTimeout);
		}

//		// Properties to Update - fetch from global properties instead of context
//		String propertiesToUpdateFromGlobal = globalProperties.getProperty("properties.to.update", "");
//		if (propertiesToUpdateFromGlobal != null && !propertiesToUpdateFromGlobal.isEmpty()) {
//			context.addProperty(ContextKeys.Properties_To_Update, propertiesToUpdateFromGlobal);
//		}
//
//		// Pilot Heavy Test Components
//		if (pilotHeavyTestComponents != null && !pilotHeavyTestComponents.isEmpty()) {
//			context.addProperty(ContextKeys.Pilot_Heavy_Test_Components, pilotHeavyTestComponents);
//		}
//
//		// Pilot Name ID Map - fetch from global properties instead of context
//		String pilotNameIdMapFromGlobal = globalProperties.getProperty("pilotNameIdMap", "");
//		if (pilotNameIdMapFromGlobal != null && !pilotNameIdMapFromGlobal.isEmpty()) {
//			context.addProperty(ContextKeys.PilotNameIdMap, pilotNameIdMapFromGlobal);
//		}
	}
}