package com.bidgely.commonExecutionResources;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Standalone ExecutionVariables implementation
 * Replaces the original test framework ExecutionVariables
 */
public class ExecutionVariables {

	private final Map<String, Object> variables = new HashMap<>();

	/**
	 * Put a variable
	 */
	public void put(String key, Object value) {
		variables.put(key, value);
	}

	/**
	 * Get a variable
	 */
	public Object get(String key) {
		return variables.get(key);
	}

	/**
	 * Get a variable as String
	 */
	public String getString(String key) {
		Object value = variables.get(key);
		return value != null ? value.toString() : null;
	}

	/**
	 * Get a variable as String with default
	 */
	public String getString(String key, String defaultValue) {
		String value = getString(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * Get a variable as Integer
	 */
	public Integer getInteger(String key) {
		Object value = variables.get(key);
		if (value instanceof Integer) {
			return (Integer) value;
		}
		if (value instanceof String) {
			try {
				return Integer.parseInt((String) value);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	/**
	 * Get a variable as Integer with default
	 */
	public Integer getInteger(String key, Integer defaultValue) {
		Integer value = getInteger(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * Get a variable as Boolean
	 */
	public Boolean getBoolean(String key) {
		Object value = variables.get(key);
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		if (value instanceof String) {
			return Boolean.parseBoolean((String) value);
		}
		return null;
	}

	/**
	 * Get a variable as Boolean with default
	 */
	public Boolean getBoolean(String key, Boolean defaultValue) {
		Boolean value = getBoolean(key);
		return value != null ? value : defaultValue;
	}

	/**
	 * Check if variable exists
	 */
	public boolean containsKey(String key) {
		return variables.containsKey(key);
	}

	/**
	 * Remove a variable
	 */
	public Object remove(String key) {
		return variables.remove(key);
	}

	/**
	 * Clear all variables
	 */
	public void clear() {
		variables.clear();
	}

	/**
	 * Get all variable keys
	 */
	public Set<String> keySet() {
		return variables.keySet();
	}

	/**
	 * Get all variables as a copy
	 */
	public Map<String, Object> getAll() {
		return new HashMap<>(variables);
	}

	/**
	 * Get size of variables
	 */
	public int size() {
		return variables.size();
	}

	/**
	 * Check if variables are empty
	 */
	public boolean isEmpty() {
		return variables.isEmpty();
	}

	/**
	 * Put all variables from another map
	 */
	public void putAll(Map<String, Object> map) {
		variables.putAll(map);
	}

	/**
	 * Get variable with type casting
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(String key, Class<T> type) {
		Object value = variables.get(key);
		if (value != null && type.isInstance(value)) {
			return (T) value;
		}
		return null;
	}
}