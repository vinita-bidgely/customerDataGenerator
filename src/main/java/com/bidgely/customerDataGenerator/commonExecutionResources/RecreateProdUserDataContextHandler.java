package com.bidgely.customerDataGenerator.commonExecutionResources;

import com.bidgely.customerDataGenerator.context.ContextKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Context Data Handler for RecreateProdUserDataTestStepExecutor
 * Manages all the context data requirements for the migrated class
 */
@Component
public class RecreateProdUserDataContextHandler {

	/**
	 * Initialize ExecutionContext with all required properties for RecreateProdUserDataTestStepExecutor
	 */
	public ExecutionContext createExecutionContext(String utilityName, String pilotId, String pilotToken, String authToken) {
		ExecutionContext executionContext = new ExecutionContext();

		// Set core properties used in RecreateProdUserDataTestStepExecutor
		executionContext.setProperty(ContextKeys.UserDefaults_UtilityName, utilityName);
		executionContext.setProperty(ContextKeys.ExecutionEnvironment_PilotId, pilotId);
		executionContext.setProperty(ContextKeys.ExecutionEnvironment_PilotToken, pilotToken);
		executionContext.setProperty(ContextKeys.ExecutionEnvironment_AuthToken, authToken);

		// Set default properties
		executionContext.setProperty(ContextKeys.UserDefaults_Timezone, "UTC");
		executionContext.setProperty(ContextKeys.UserDefaults_EmailPrefix, utilityName + "_user");
		executionContext.setProperty(ContextKeys.UserDefaults_Address, "Default Address");
		executionContext.setProperty(ContextKeys.UserDefaults_City, "Default City");
		executionContext.setProperty(ContextKeys.UserDefaults_State, "CA");
		executionContext.setProperty(ContextKeys.UserDefaults_Zip, "00000");
		executionContext.setProperty(ContextKeys.UserDefaults_BillCycleCode, "DEFAULT_BC");
		executionContext.setProperty(ContextKeys.UserDefaults_RatePlanId, "DEFAULT_RP");

		// Set file prefixes and suffixes
		executionContext.setProperty(ContextKeys.Prefix_InvoiceFile, utilityName.toUpperCase() + "_INVOICE");
		executionContext.setProperty(ContextKeys.Prefix_UserFile, utilityName.toUpperCase() + "_USER");
		executionContext.setProperty(ContextKeys.Prefix_MeterFile, utilityName.toUpperCase() + "_METER");
		executionContext.setProperty(ContextKeys.Prefix_AMIRawFile, utilityName.toUpperCase() + "_RAW");

		// Set file suffixes
		executionContext.setProperty(ContextKeys.Suffix_InvoiceFile, ".csv");
		executionContext.setProperty(ContextKeys.Suffix_UserFile, ".csv");
		executionContext.setProperty(ContextKeys.Suffix_MeterFile, ".csv");
		executionContext.setProperty(ContextKeys.Suffix_AMIRawFile, ".csv");

		// Set date formats
		executionContext.setProperty(ContextKeys.DateFormat_InvoiceFile, "yyyy-MM-dd");
		executionContext.setProperty(ContextKeys.DateFormat_AMIRawFile, "yyyy-MM-dd HH:mm:ss");

		// Set separators
		executionContext.setProperty(ContextKeys.Separator_InvoiceFile, "|");

		// Set email validation
		executionContext.setProperty(ContextKeys.EmailValidation_PropertyFilepath, "/email/validation/");

		return executionContext;
	}

	/**
	 * Initialize ExecutionVariables with common variables used in RecreateProdUserDataTestStepExecutor
	 */
	public ExecutionVariables createExecutionVariables() {
		ExecutionVariables executionVariables = new ExecutionVariables();

		// Initialize common variables that might be used
		executionVariables.put("customerId", null);
		executionVariables.put("contractId", null);
		executionVariables.put("premiseId", null);
		executionVariables.put("secondaryId", null);
		executionVariables.put("externalAccountId", null);
		executionVariables.put("servicePointId", null);
		executionVariables.put("servicePointIdNumber", null);
		executionVariables.put("partnerUserId", null);
		executionVariables.put("contractAccountId", null);
		executionVariables.put("sdpId", null);

		// Initialize data stream IDs for different meter types
		executionVariables.put("dataStreamId_1", null);
		executionVariables.put("dataStreamId_2", null);
		executionVariables.put("dataStreamId_3", null);
		executionVariables.put("dataStreamId_4", null);
		executionVariables.put("dataStreamId_5", null);

		return executionVariables;
	}

	/**
	 * Load utility-specific properties from properties file
	 */
	public void loadUtilityProperties(ExecutionContext executionContext, String utilityName) {
		try {
			Properties utilityProps = new Properties();
			String propertiesFile = String.format("/utilities/%s.properties", utilityName);

			// Load utility-specific properties
			utilityProps.load(getClass().getResourceAsStream(propertiesFile));

			// Apply utility-specific properties to execution context
			for (String key : utilityProps.stringPropertyNames()) {
				executionContext.setProperty(key, utilityProps.getProperty(key));
			}

		} catch (Exception e) {
			// If utility properties file doesn't exist, use defaults
			System.out.println("Utility properties file not found for " + utilityName + ", using defaults");
		}
	}

	/**
	 * Get all required properties for a specific utility
	 */
	public Map<String, String> getRequiredProperties(String utilityName) {
		Map<String, String> requiredProps = new HashMap<>();

		// Core properties always required
		requiredProps.put(ContextKeys.UserDefaults_UtilityName, utilityName);
		requiredProps.put(ContextKeys.UserDefaults_Timezone, "UTC");
		requiredProps.put(ContextKeys.UserDefaults_EmailPrefix, utilityName + "_user");

		// Utility-specific properties
		switch (utilityName.toLowerCase()) {
		case "tpu":
			requiredProps.put(ContextKeys.UserDefaults_Address, "123 Main St");
			requiredProps.put(ContextKeys.UserDefaults_City, "San Francisco");
			requiredProps.put(ContextKeys.UserDefaults_State, "CA");
			requiredProps.put(ContextKeys.UserDefaults_Zip, "12345");
			break;
		case "scg":
			requiredProps.put(ContextKeys.UserDefaults_Address, "456 Broadway");
			requiredProps.put(ContextKeys.UserDefaults_City, "New York");
			requiredProps.put(ContextKeys.UserDefaults_State, "NY");
			requiredProps.put(ContextKeys.UserDefaults_Zip, "10001");
			break;
		case "nsp":
			requiredProps.put(ContextKeys.UserDefaults_Address, "789 Michigan Ave");
			requiredProps.put(ContextKeys.UserDefaults_City, "Chicago");
			requiredProps.put(ContextKeys.UserDefaults_State, "IL");
			requiredProps.put(ContextKeys.UserDefaults_Zip, "60601");
			break;
		default:
			// Use defaults for unknown utilities
			requiredProps.put(ContextKeys.UserDefaults_Address, "Default Address");
			requiredProps.put(ContextKeys.UserDefaults_City, "Default City");
			requiredProps.put(ContextKeys.UserDefaults_State, "CA");
			requiredProps.put(ContextKeys.UserDefaults_Zip, "00000");
		}

		return requiredProps;
	}

	/**
	 * Utility class to safely manage ExecutionVariables with getter and setter operations.
	 * Provides null-safe methods to prevent NullPointerException when accessing execution variables.
	 */
	public static class ExecutionVariableManager {

		private static final Logger logger = LoggerFactory.getLogger(ExecutionVariableManager.class);

		private final ExecutionVariables executionVariables;

		/**
		 * Constructor that takes ExecutionVariables instance
		 * @param executionVariables The execution variables to manage
		 */
		public ExecutionVariableManager(ExecutionVariables executionVariables) {
			this.executionVariables = executionVariables;
		}

		/**
		 * Static method to safely get a String value from executionVariables with null check
		 * @param executionVariables The execution variables to query
		 * @param key The key to retrieve
		 * @return The value as String, or empty string if null or not found
		 */
		public static String getExecutionVariable(ExecutionVariables executionVariables, String key) {
			Object value = executionVariables.get(key);
			return value != null ? value.toString() : "";
		}

		/**
		 * Static method to safely get a String value from executionVariables with null check
		 * @param executionVariables The execution variables to query
		 * @param key The key to retrieve
		 * @param defaultValue The default value to return if key is null or not found
		 * @return The value as String, or defaultValue if null or not found
		 */
		public static String getString(ExecutionVariables executionVariables, String key, String defaultValue) {
			Object value = executionVariables.get(key);
			return value != null ? value.toString() : defaultValue;
		}

		/**
		 * Static method to safely get an Integer value from executionVariables
		 * @param executionVariables The execution variables to query
		 * @param key The key to retrieve
		 * @return The value as Integer, or 0 if null or not found
		 */
		public static Integer getInteger(ExecutionVariables executionVariables, String key) {
			Object value = executionVariables.get(key);
			if (value == null) {
				return 0;
			}
			try {
				if (value instanceof Integer) {
					return (Integer) value;
				} else if (value instanceof String) {
					return Integer.parseInt((String) value);
				} else {
					return Integer.parseInt(value.toString());
				}
			} catch (NumberFormatException e) {
				logger.warn("Could not parse value '{}' as Integer for key '{}', returning 0", value, key);
				return 0;
			}
		}

		/**
		 * Static method to safely get an Integer value from executionVariables with default
		 * @param executionVariables The execution variables to query
		 * @param key The key to retrieve
		 * @param defaultValue The default value to return if key is null or not found
		 * @return The value as Integer, or defaultValue if null or not found
		 */
		public static Integer getInteger(ExecutionVariables executionVariables, String key, Integer defaultValue) {
			Object value = executionVariables.get(key);
			if (value == null) {
				return defaultValue;
			}
			try {
				if (value instanceof Integer) {
					return (Integer) value;
				} else if (value instanceof String) {
					return Integer.parseInt((String) value);
				} else {
					return Integer.parseInt(value.toString());
				}
			} catch (NumberFormatException e) {
				logger.warn("Could not parse value '{}' as Integer for key '{}', returning default", value, key);
				return defaultValue;
			}
		}

		/**
		 * Static method to safely get a Double value from executionVariables
		 * @param executionVariables The execution variables to query
		 * @param key The key to retrieve
		 * @return The value as Double, or 0.0 if null or not found
		 */
		public static Double getDouble(ExecutionVariables executionVariables, String key) {
			Object value = executionVariables.get(key);
			if (value == null) {
				return 0.0;
			}
			try {
				if (value instanceof Double) {
					return (Double) value;
				} else if (value instanceof String) {
					return Double.parseDouble((String) value);
				} else {
					return Double.parseDouble(value.toString());
				}
			} catch (NumberFormatException e) {
				logger.warn("Could not parse value '{}' as Double for key '{}', returning 0.0", value, key);
				return 0.0;
			}
		}

		/**
		 * Static method to safely get a Double value from executionVariables with default
		 * @param executionVariables The execution variables to query
		 * @param key The key to retrieve
		 * @param defaultValue The default value to return if key is null or not found
		 * @return The value as Double, or defaultValue if null or not found
		 */
		public static Double getDouble(ExecutionVariables executionVariables, String key, Double defaultValue) {
			Object value = executionVariables.get(key);
			if (value == null) {
				return defaultValue;
			}
			try {
				if (value instanceof Double) {
					return (Double) value;
				} else if (value instanceof String) {
					return Double.parseDouble((String) value);
				} else {
					return Double.parseDouble(value.toString());
				}
			} catch (NumberFormatException e) {
				logger.warn("Could not parse value '{}' as Double for key '{}', returning default", value, key);
				return defaultValue;
			}
		}

		/**
		 * Static method to safely get a Boolean value from executionVariables
		 * @param executionVariables The execution variables to query
		 * @param key The key to retrieve
		 * @return The value as Boolean, or false if null or not found
		 */
		public static Boolean getBoolean(ExecutionVariables executionVariables, String key) {
			Object value = executionVariables.get(key);
			if (value == null) {
				return false;
			}
			if (value instanceof Boolean) {
				return (Boolean) value;
			} else if (value instanceof String) {
				return Boolean.parseBoolean((String) value);
			} else {
				return Boolean.parseBoolean(value.toString());
			}
		}

		/**
		 * Static method to safely get a Boolean value from executionVariables with default
		 * @param executionVariables The execution variables to query
		 * @param key The key to retrieve
		 * @param defaultValue The default value to return if key is null or not found
		 * @return The value as Boolean, or defaultValue if null or not found
		 */
		public static Boolean getBoolean(ExecutionVariables executionVariables, String key, Boolean defaultValue) {
			Object value = executionVariables.get(key);
			if (value == null) {
				return defaultValue;
			}
			if (value instanceof Boolean) {
				return (Boolean) value;
			} else if (value instanceof String) {
				return Boolean.parseBoolean((String) value);
			} else {
				return Boolean.parseBoolean(value.toString());
			}
		}

		/**
		 * Static method to check if a key exists in executionVariables
		 * @param executionVariables The execution variables to query
		 * @param key The key to check
		 * @return true if the key exists, false otherwise
		 */
		public static boolean containsKey(ExecutionVariables executionVariables, String key) {
			return executionVariables.containsKey(key);
		}

		/**
		 * Static method to check if a key exists and has a non-null value
		 * @param executionVariables The execution variables to query
		 * @param key The key to check
		 * @return true if the key exists and has a non-null value, false otherwise
		 */
		public static boolean hasValue(ExecutionVariables executionVariables, String key) {
			return executionVariables.containsKey(key) && executionVariables.get(key) != null;
		}

		/**
		 * Static method to set a value in executionVariables
		 * @param executionVariables The execution variables to modify
		 * @param key The key to set
		 * @param value The value to set
		 */
		public static void set(ExecutionVariables executionVariables, String key, Object value) {
			executionVariables.put(key, value);
		}

		/**
		 * Static method to set a String value in executionVariables
		 * @param executionVariables The execution variables to modify
		 * @param key The key to set
		 * @param value The String value to set
		 */
		public static void setString(ExecutionVariables executionVariables, String key, String value) {
			executionVariables.put(key, value);
		}

		/**
		 * Static method to set an Integer value in executionVariables
		 * @param executionVariables The execution variables to modify
		 * @param key The key to set
		 * @param value The Integer value to set
		 */
		public static void setInteger(ExecutionVariables executionVariables, String key, Integer value) {
			executionVariables.put(key, value);
		}

		/**
		 * Static method to set a Double value in executionVariables
		 * @param executionVariables The execution variables to modify
		 * @param key The key to set
		 * @param value The Double value to set
		 */
		public static void setDouble(ExecutionVariables executionVariables, String key, Double value) {
			executionVariables.put(key, value);
		}

		/**
		 * Static method to set a Boolean value in executionVariables
		 * @param executionVariables The execution variables to modify
		 * @param key The key to set
		 * @param value The Boolean value to set
		 */
		public static void setBoolean(ExecutionVariables executionVariables, String key, Boolean value) {
			executionVariables.put(key, value);
		}

		/**
		 * Static method to remove a key from executionVariables
		 * @param executionVariables The execution variables to modify
		 * @param key The key to remove
		 * @return The value that was removed, or null if key didn't exist
		 */
		public static Object remove(ExecutionVariables executionVariables, String key) {
			return executionVariables.remove(key);
		}

		// Instance methods (for backward compatibility and when you want to work with a specific instance)


		/**
		 * Safely get a String value from executionVariables with null check
		 * @param key The key to retrieve
		 * @param defaultValue The default value to return if key is null or not found
		 * @return The value as String, or defaultValue if null or not found
		 */
		public String getString(String key, String defaultValue) {
			return getString(executionVariables, key, defaultValue);
		}

		/**
		 * Safely get an Integer value from executionVariables
		 * @param key The key to retrieve
		 * @return The value as Integer, or 0 if null or not found
		 */
		public Integer getInteger(String key) {
			return getInteger(executionVariables, key);
		}

		/**
		 * Safely get an Integer value from executionVariables with default
		 * @param key The key to retrieve
		 * @param defaultValue The default value to return if key is null or not found
		 * @return The value as Integer, or defaultValue if null or not found
		 */
		public Integer getInteger(String key, Integer defaultValue) {
			return getInteger(executionVariables, key, defaultValue);
		}

		/**
		 * Safely get a Double value from executionVariables
		 * @param key The key to retrieve
		 * @return The value as Double, or 0.0 if null or not found
		 */
		public Double getDouble(String key) {
			return getDouble(executionVariables, key);
		}

		/**
		 * Safely get a Double value from executionVariables with default
		 * @param key The key to retrieve
		 * @param defaultValue The default value to return if key is null or not found
		 * @return The value as Double, or defaultValue if null or not found
		 */
		public Double getDouble(String key, Double defaultValue) {
			return getDouble(executionVariables, key, defaultValue);
		}

		/**
		 * Safely get a Boolean value from executionVariables
		 * @param key The key to retrieve
		 * @return The value as Boolean, or false if null or not found
		 */
		public Boolean getBoolean(String key) {
			return getBoolean(executionVariables, key);
		}

		/**
		 * Safely get a Boolean value from executionVariables with default
		 * @param key The key to retrieve
		 * @param defaultValue The default value to return if key is null or not found
		 * @return The value as Boolean, or defaultValue if null or not found
		 */
		public Boolean getBoolean(String key, Boolean defaultValue) {
			return getBoolean(executionVariables, key, defaultValue);
		}

		/**
		 * Check if a key exists in executionVariables
		 * @param key The key to check
		 * @return true if the key exists, false otherwise
		 */
		public boolean containsKey(String key) {
			return containsKey(executionVariables, key);
		}

		/**
		 * Check if a key exists and has a non-null value
		 * @param key The key to check
		 * @return true if the key exists and has a non-null value, false otherwise
		 */
		public boolean hasValue(String key) {
			return hasValue(executionVariables, key);
		}

		/**
		 * Set a value in executionVariables
		 * @param key The key to set
		 * @param value The value to set
		 */
		public void set(String key, Object value) {
			set(executionVariables, key, value);
		}

		/**
		 * Set a String value in executionVariables
		 * @param key The key to set
		 * @param value The String value to set
		 */
		public void setString(String key, String value) {
			setString(executionVariables, key, value);
		}

		/**
		 * Set an Integer value in executionVariables
		 * @param key The key to set
		 * @param value The Integer value to set
		 */
		public void setInteger(String key, Integer value) {
			setInteger(executionVariables, key, value);
		}

		/**
		 * Set a Double value in executionVariables
		 * @param key The key to set
		 * @param value The Double value to set
		 */
		public void setDouble(String key, Double value) {
			setDouble(executionVariables, key, value);
		}

		/**
		 * Set a Boolean value in executionVariables
		 * @param key The key to set
		 * @param value The Boolean value to set
		 */
		public void setBoolean(String key, Boolean value) {
			setBoolean(executionVariables, key, value);
		}

		/**
		 * Remove a key from executionVariables
		 * @param key The key to remove
		 * @return The value that was removed, or null if key didn't exist
		 */
		public Object remove(String key) {
			return remove(executionVariables, key);
		}

		/**
		 * Get the underlying ExecutionVariables instance
		 * @return The ExecutionVariables instance
		 */
		public ExecutionVariables getExecutionVariables() {
			return executionVariables;
		}

		/**
		 * Get the size of executionVariables
		 * @return The number of key-value pairs
		 */
		public int size() {
			return executionVariables.size();
		}

		/**
		 * Check if executionVariables is empty
		 * @return true if empty, false otherwise
		 */
		public boolean isEmpty() {
			return executionVariables.isEmpty();
		}

		/**
		 * Clear all entries from executionVariables
		 */
		public void clear() {
			executionVariables.clear();
		}
	}
}