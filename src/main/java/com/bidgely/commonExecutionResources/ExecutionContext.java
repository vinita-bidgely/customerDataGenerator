package com.bidgely.commonExecutionResources;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Standalone ExecutionContext implementation
 * Replaces the original test framework ExecutionContext
 */
public class ExecutionContext {

	private final Map<String, String> properties = new HashMap<>();
	private final Properties systemProperties = new Properties();

	public ExecutionContext() {
		// Initialize with system properties
		systemProperties.putAll(System.getProperties());
	}

	/**
	 * Get property value
	 */
	public String getProperty(String key) {
		// First check our custom properties
		if (properties.containsKey(key)) {
			return properties.get(key);
		}

		// Then check system properties
		return systemProperties.getProperty(key, "");
	}

	/**
	 * Get property value with default
	 */
	public String getProperty(String key, String defaultValue) {
		String value = getProperty(key);
		return value != null && !value.isEmpty() ? value : defaultValue;
	}

	/**
	 * Set property value
	 */
	public void setProperty(String key, String value) {
		properties.put(key, value);
	}

	/**
	 * Check if property exists
	 */
	public boolean hasProperty(String key) {
		return properties.containsKey(key) || systemProperties.containsKey(key);
	}

	/**
	 * Get all properties
	 */
	public Map<String, String> getAllProperties() {
		Map<String, String> allProps = new HashMap<>(properties);
		for (String key : systemProperties.stringPropertyNames()) {
			allProps.put(key, systemProperties.getProperty(key));
		}
		return allProps;
	}

	/**
	 * Clear all custom properties
	 */
	public void clear() {
		properties.clear();
	}

	/**
	 * Load properties from file
	 */
	public void loadProperties(String filePath) {
		try {
			Properties props = new Properties();
			props.load(getClass().getResourceAsStream(filePath));
			for (String key : props.stringPropertyNames()) {
				setProperty(key, props.getProperty(key));
			}
		} catch (Exception e) {
			throw new RuntimeException("Failed to load properties from: " + filePath, e);
		}
	}
}