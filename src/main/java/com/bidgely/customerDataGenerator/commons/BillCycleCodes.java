package com.bidgely.customerDataGenerator.commons;

import com.bidgely.customerDataGenerator.api.Apis;
import com.bidgely.customerDataGenerator.api.Apis.GetUriFor;
import com.bidgely.customerDataGenerator.api.BaseBidgelyApi;
import com.bidgely.customerDataGenerator.context.ContextKeys;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.responses.BillCycleCodeWithIdentifierResponse;
import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.responses.entries.BillCycleCodeResponseEntry;

import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;
import org.apache.commons.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;
import java.util.Properties;

import static java.lang.reflect.Array.get;

public class BillCycleCodes extends BaseBidgelyApi<BillCycleCodeWithIdentifierResponse> {

	private final String            baseUrl;

	@Qualifier("authTokenManager")
	private IAuthTokenManager authTokenManager;

	private final String            payload = "{\"billCycleCode\": \"${billCycleCode}\",\"utilityID\": \"${utilityId}\",\"validFrom\": \"${validFrom}\",\"validTo\": \"${validTo}\",\"fromMonth\": \"${fromMonth}\",\"toMonth\": \"${toMonth}\",\"fromDay\": \"${fromDay}\",\"toDay\": \"${toDay}\",\"utilityName\": \"${utilityName}\"}";

	public BillCycleCodes(String beUrl, IAuthTokenManager authTokenManager)
			throws BidgelyExceptions {
		super(beUrl, authTokenManager, GetUriFor.GET_BILLING_CYCLE_WITH_IDENTIFIER_FOR_PILOT, BillCycleCodeWithIdentifierResponse.class);
		this.baseUrl = beUrl;
		this.authTokenManager = authTokenManager;
	}

//	private Properties loadUtilityProperties() {
//		return loadUtilityProperties(null);
//	}

//	private Properties loadUtilityProperties(String utilityName) {
//		Properties utilityProperties = new Properties();
//		try {
//			if (utilityName != null && !utilityName.isEmpty()) {
//				// Load utility-specific properties
//				String utilityPropertiesPath = "/" + utilityName + ".properties";
//				utilityProperties.load(BillCycleCodes.class.getResourceAsStream(utilityPropertiesPath));
//			} else {
//				// Load default utility.properties
//				utilityProperties.load(BillCycleCodes.class.getResourceAsStream("/utility.properties"));
//			}
//		} catch (IOException e) {
//			throw new RuntimeException("Could not load utility properties for: " + utilityName, e);
//		}
//		return utilityProperties;
//	}

	/**
	 * This method returns the billing cycle code for the given date
	 *
	 * @param executionContext
	 * @param date
	 * @param pilotId
	 * @param utilityName
	 * @return
	 * @throws BidgelyExceptions
	 */
//	public String getBillingCycleCodeAt(ExecutionContext executionContext,
//			int date, int pilotId, String utilityName)
//			throws BidgelyExceptions {
//
//		Properties utilityProperties = loadUtilityProperties(utilityName);
//		String billCycleCodePrefix = utilityProperties.getProperty("userDefaults.billCycleCodePrefix");
//		String timeZone = utilityProperties.getProperty("userDefaults.timezone");
//		if (date > 31 || date < 1) {
//			throw new BidgelyExceptions("Invalid date: " + date);
//		}
//
//		String billingCycleCode = billCycleCodePrefix + date;
//
//		if (!checkIfBillingCycleCodeExists(billingCycleCode, pilotId)) {
//			boolean transitionBillingEnabled = Boolean.valueOf(utilityProperties.getProperty("userDefaults.transitionBillingEnabled"));
//			createBillingCycleCode(billingCycleCode, date, pilotId, utilityName, timeZone, transitionBillingEnabled);
//		}
//		return billingCycleCode;
//	}

//	public String getBillingCycleCodeAt(ExecutionContext executionContext,
//			int date, int pilotId, String utilityName,
//			String billCycleCodePrefix)
//			throws BidgelyExceptions {
//
//		Properties utilityProperties = loadUtilityProperties(utilityName);
//		String timeZone = utilityProperties.getProperty("userDefaults.timezone");
//		if (date > 31 || date < 1) {
//			throw new BidgelyExceptions("Invalid date: " + date);
//		}
//
//		String billingCycleCode = billCycleCodePrefix + date;
//
//		if (!checkIfBillingCycleCodeExists(billingCycleCode, pilotId)) {
//			boolean transitionBillingEnabled = Boolean.valueOf(utilityProperties.getProperty("userDefaults.transitionBillingEnabled"));
//			createBillingCycleCode(billingCycleCode, date, pilotId, utilityName, timeZone, transitionBillingEnabled);
//		}
//		return billingCycleCode;
//	}

	/**
	 * This method checks in the given billCycleCode exists in the system
	 *
	 * @param billingCycleCode
	 * @return true is the billCycleCode exists
	 * @throws BidgelyExceptions
	 */
	public boolean checkIfBillingCycleCodeExists(String billingCycleCode,
			int pilotId) throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String>builder()
				.put(Apis.UriSubstitutorKeys.PILOT_ID.toString(), String.valueOf(pilotId))
				.put(Apis.UriSubstitutorKeys.IDENTIFIER_ID.toString(), billingCycleCode).build();
		BillCycleCodeWithIdentifierResponse response = get(valueMap);
		if (response.getBillCycleCodes().isEmpty())
			return false;
		long validTo = response.getBillCycleCodes().get(
				response.getBillCycleCodes().size() - 1).getValidTo();
		Date date = new Date();
		if (validTo < ((date.getTime() / 1000) + (30 * 86400))) {
			return false;
		}
		return (!response.getBillCycleCodes().isEmpty());
	}

	/**
	 * Creates the billCycleCode for the given startDate
	 *
	 * @param billingCycleCode
	 * @param startDate
	 * @throws BidgelyExceptions
	 * @throws ParseException
	 */
	private void createBillingCycleCode(String billingCycleCode, int startDate,
			int pilotId, String utilityName, String timeZone,
			boolean isTransisitonBillingEnabled)
			throws BidgelyExceptions {

		for (Cycles cycle : Cycles.getCyclesStartingAt(startDate)) {

			Map<String, String> payloadValueMap = null;

			if (isTransisitonBillingEnabled) {
				try {
					int year = Year.now().getValue();
					SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
					long fromEpoch = dateFormat.parse(
							String.valueOf(Integer.parseInt(cycle.fromMonth) +
									1) + "/" + cycle.fromDay + "/" +
									year).getTime() /
							1000;
					if (Integer.parseInt(cycle.fromMonth) >
							Integer.parseInt(cycle.toMonth)) {
						year++;
					}
					long toEpoch = dateFormat.parse(
							String.valueOf(Integer.parseInt(cycle.toMonth) +
									1) + "/" +
									String.valueOf(
											Integer.parseInt(cycle.toDay) + 1) +
									"/" + year).getTime() /
							1000;
					payloadValueMap = ImmutableMap.<String, String>builder().put("billCycleCode", billingCycleCode).put("utilityId",
							pilotId +
									"").put("validFrom", String.valueOf(fromEpoch)).put("validTo", String.valueOf(toEpoch)).put("fromMonth", cycle.fromMonth).put("toMonth", cycle.toMonth).put("fromDay", cycle.fromDay).put("toDay", cycle.toDay).put("utilityName", utilityName).build();
				}
				catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				payloadValueMap = ImmutableMap.<String, String>builder().put("billCycleCode", billingCycleCode).put("utilityId",
						pilotId +
								"").put("validFrom", "1000000000").put("validTo", "2000000000").put("fromMonth", cycle.fromMonth).put("toMonth", cycle.toMonth).put("fromDay", cycle.fromDay).put("toDay", cycle.toDay).put("utilityName", utilityName).build();
			}
			StrSubstitutor payloadSub = new StrSubstitutor(payloadValueMap);

			@SuppressWarnings("static-access") Response response = SharedResources.restAssured
					.given()
					.header("Authorization",
							"Bearer " + authTokenManager.getOauthToken())
					.contentType("application/json")
					.when()
					.body(payloadSub.replace(payload))
					.post(baseUrl +
							Apis.GetUriFor.UTILITY_BILLING_CYCLES.toString(new HashMap<>()));

			if (response.getStatusCode() != 200 &&
					response.getStatusCode() != 400) {
				Validate.isResponseStatusOk(response);
			}
		}

	}

	public String getBillingCycleCodeAt(String billingCycleCodePrefix, String userTimezone, int date, int pilotId, String utilityName, Properties defaultUtilityProperties)
			throws BidgelyExceptions {

//		Properties utilityProperties = loadProperties(utilityName);
		String billCycleCodePrefix = billingCycleCodePrefix;
		String timeZone =userTimezone;
		if (date > 31 || date < 1) {
			throw new BidgelyExceptions("Invalid date: " + date);
		}

		String billingCycleCode = billCycleCodePrefix + date;

		if (!checkIfBillingCycleCodeExists(billingCycleCode, pilotId)) {
			boolean transitionBillingEnabled = Boolean.valueOf(defaultUtilityProperties.getProperty("userDefaults.transitionBillingEnabled"));
			createBillingCycleCode(billingCycleCode, date, pilotId, utilityName, timeZone, transitionBillingEnabled);
		}
		return billingCycleCode;
	}
}


class Cycles {

	String fromMonth;
	String toMonth;
	String fromDay;
	String toDay;

	public Cycles(String fromMonth, String toMonth, String fromDay,
			String toDay) {
		this.fromMonth = fromMonth;
		this.toMonth = toMonth;
		this.fromDay = fromDay;
		this.toDay = toDay;
	}

	public static List<Cycles> getCyclesStartingAt(int date) {

		if (date == 1) {
			return getCyclesStartingAt1();
		}

		if (date == 29) {
			return getCyclesStartingAt29();
		}

		if (date == 30) {
			return getCyclesStartingAt30();
		}

		if (date == 31) {
			return getCyclesStartingAt31();
		}

		List<Cycles> cycles = new ArrayList<>();

		cycles.add(new Cycles("0", "1", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("1", "2", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("2", "3", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("3", "4", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("4", "5", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("5", "6", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("6", "7", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("7", "8", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("8", "9", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("9", "10", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("10", "11", String.valueOf(date), String.valueOf(
				date -
						1)));
		cycles.add(new Cycles("11", "0", String.valueOf(date), String.valueOf(
				date -
						1)));

		return cycles;
	}

	private static List<Cycles> getCyclesStartingAt1() {
		List<Cycles> cycles = new ArrayList<>();

		cycles.add(new Cycles("0", "0", "1", "31"));
		cycles.add(new Cycles("1", "1", "1", "28"));
		cycles.add(new Cycles("2", "2", "1", "31"));
		cycles.add(new Cycles("3", "3", "1", "30"));
		cycles.add(new Cycles("4", "4", "1", "31"));
		cycles.add(new Cycles("5", "5", "1", "30"));
		cycles.add(new Cycles("6", "6", "1", "31"));
		cycles.add(new Cycles("7", "7", "1", "31"));
		cycles.add(new Cycles("8", "8", "1", "30"));
		cycles.add(new Cycles("9", "9", "1", "31"));
		cycles.add(new Cycles("10", "10", "1", "30"));
		cycles.add(new Cycles("11", "11", "1", "31"));

		return cycles;
	}

	private static List<Cycles> getCyclesStartingAt29() {
		List<Cycles> cycles = new ArrayList<>();

		cycles.add(new Cycles("0", "1", "29", "28"));
		cycles.add(new Cycles("1", "2", "28", "28"));
		cycles.add(new Cycles("2", "3", "29", "28"));
		cycles.add(new Cycles("3", "4", "29", "28"));
		cycles.add(new Cycles("4", "5", "29", "28"));
		cycles.add(new Cycles("5", "6", "29", "28"));
		cycles.add(new Cycles("6", "7", "29", "28"));
		cycles.add(new Cycles("7", "8", "29", "28"));
		cycles.add(new Cycles("8", "9", "29", "28"));
		cycles.add(new Cycles("9", "10", "29", "28"));
		cycles.add(new Cycles("10", "11", "29", "28"));
		cycles.add(new Cycles("11", "0", "29", "28"));

		return cycles;
	}

	private static List<Cycles> getCyclesStartingAt30() {
		List<Cycles> cycles = new ArrayList<>();

		cycles.add(new Cycles("0", "1", "30", "27"));
		cycles.add(new Cycles("1", "2", "28", "29"));
		cycles.add(new Cycles("2", "3", "30", "29"));
		cycles.add(new Cycles("3", "4", "30", "29"));
		cycles.add(new Cycles("4", "5", "30", "29"));
		cycles.add(new Cycles("5", "6", "30", "29"));
		cycles.add(new Cycles("6", "7", "30", "29"));
		cycles.add(new Cycles("7", "8", "30", "29"));
		cycles.add(new Cycles("8", "9", "30", "29"));
		cycles.add(new Cycles("9", "10", "30", "29"));
		cycles.add(new Cycles("10", "11", "30", "29"));
		cycles.add(new Cycles("11", "0", "30", "29"));

		return cycles;
	}

	private static List<Cycles> getCyclesStartingAt31() {
		List<Cycles> cycles = new ArrayList<>();

		cycles.add(new Cycles("0", "1", "31", "27"));
		cycles.add(new Cycles("1", "2", "28", "30"));
		cycles.add(new Cycles("2", "3", "31", "29"));
		cycles.add(new Cycles("3", "4", "30", "30"));
		cycles.add(new Cycles("4", "5", "31", "29"));
		cycles.add(new Cycles("5", "6", "30", "30"));
		cycles.add(new Cycles("6", "7", "31", "30"));
		cycles.add(new Cycles("7", "8", "31", "29"));
		cycles.add(new Cycles("8", "9", "30", "30"));
		cycles.add(new Cycles("9", "10", "31", "29"));
		cycles.add(new Cycles("10", "11", "30", "30"));
		cycles.add(new Cycles("11", "0", "31", "30"));

		return cycles;
	}
}
