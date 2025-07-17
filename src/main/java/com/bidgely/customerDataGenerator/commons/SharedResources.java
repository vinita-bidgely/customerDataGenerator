package com.bidgely.customerDataGenerator.commons;

import com.bidgely.customerDataGenerator.context.ContextKeys;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.Client;
import io.restassured.RestAssured;

import java.io.IOException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SharedResources {

	public static final Gson        gson                    = new GsonBuilder().registerTypeAdapter(String.class, new EscapeStringSerializer()).setLenient().create();

	public static final Gson        gsonWithoutStringEscape = new GsonBuilder().setLenient().create();

	public static final Gson        gsonWithoutHtmlEscaping = new GsonBuilder().disableHtmlEscaping().setLenient().create();

	public static final Gson        gsonWithoutNullEscaping = new GsonBuilder().serializeNulls().create();

	public static final Client      client                  = Client.create();

	private static SecureRandom     rnd                     = new SecureRandom();

	public static final RestAssured restAssured             = new RestAssured();

	public static String generateRandomString(int len) {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		}
		return sb.toString();
	}

	public static String generateRandomString(String format) {
		try {
			return generateRandomString(Integer.parseInt(format));
		}
		catch (Exception e) {
			StringBuilder sb = new StringBuilder(format.length());
			for (char digit : format.toCharArray()) {
				if (digit == 'A') {
					sb.append(getRandomAlphabet());
				}
				else if (digit == 'D') {
					sb.append(getRandomDigit());
				}
			}
			return sb.toString();
		}
	}

	private static char getRandomAlphabet() {
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		return AB.charAt(rnd.nextInt(AB.length()));
	}

	public static char getRandomDigit() {
		String AB = "0123456789";
		return AB.charAt(rnd.nextInt(AB.length()));
	}

	public static String getRandom10Digits() {
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) +
				1_000_000_000L;
		return Long.toString(number);
	}

	public static boolean matchPattern(String pattern, String actual) {
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(actual);
		return m.matches();
	}

	public static String generateRandomStringWithSuffix(String suffix) {
		StringBuilder sb = new StringBuilder(suffix.length());
		sb.append(generateRandomString(9)).append(suffix);
		return sb.toString();
	}

	private static Properties loadUtilityProperties() {
		return loadUtilityProperties(null);
	}

	private static Properties loadUtilityProperties(String utilityName) {
		Properties utilityProperties = new Properties();
		try {
			if (utilityName != null && !utilityName.isEmpty()) {
				// Load utility-specific properties
				String utilityPropertiesPath = "/" + utilityName + ".properties";
				utilityProperties.load(SharedResources.class.getResourceAsStream(utilityPropertiesPath));
			} else {
				// Load default utility.properties
				utilityProperties.load(SharedResources.class.getResourceAsStream("/utility.properties"));
			}
		} catch (IOException e) {
			throw new RuntimeException("Could not load utility properties for: " + utilityName, e);
		}
		return utilityProperties;
	}

	public static Integer toEpoch(String date, String dateFormat)
			throws ParseException, BidgelyExceptions {
		return toEpoch(date, dateFormat, null);
	}

	public static Integer toEpoch(String date, String dateFormat, String utilityName)
			throws ParseException, BidgelyExceptions {
		final SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Properties utilityProperties = loadUtilityProperties(utilityName);
		String timezone = utilityProperties.getProperty("userDefaults.timezone");
		if (timezone == null) {
			throw new BidgelyExceptions("userDefaults.timezone property not found in utility properties file");
		}
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		final Long millis = sdf.parse(date).getTime() / 1000;
		return millis.intValue();
	}
}
