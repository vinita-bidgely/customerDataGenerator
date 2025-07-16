package com.bidgely.customerDataGenerator.commonExecutionResources;

import com.bidgely.customerDataGenerator.context.ContextKeys;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.commons.Validate;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ExecutionContext {

	private final Properties executionProperties;

	public ExecutionContext() {
		this.executionProperties = new Properties();
	}

	
	public ExecutionContext(Properties props) {
		Validate.notNull(props);
		// System.setProperties(props);
		this.executionProperties = new Properties(props);
	}

	
	public ExecutionContext(String filePath) throws BidgelyExceptions {
		Validate.notNull(filePath);
		this.executionProperties = new Properties();
		try {
			loadFile(new FileInputStream(filePath));
		} catch (IOException e) {
			throw new BidgelyExceptions("Unable to load execution context file - " + e.getMessage());
		}
	}

	
	public void loadFile(InputStream inputStream) throws BidgelyExceptions {
		try {
			Properties properties = new Properties();
			properties.load(inputStream);
			appendProperties(properties);
		} catch (IOException e) {
			throw new BidgelyExceptions(e);
		}
	}

	
	public String getProperty(ContextKeys contextKey) throws BidgelyExceptions {
		return this.getProperty(contextKey, false);
	}

	public String getProperty(ContextKeys contextKey, boolean ignoreNulls) throws BidgelyExceptions {
		return this.getProperty(contextKey.toString(), ignoreNulls);
	}

	
	public String getProperty(ContextKeys contextKey, String defaultValue) {
		return this.getProperty(contextKey.toString(), defaultValue);
	}

	
	public String getProperty(String key) throws BidgelyExceptions {
		return getProperty(key, false);
	}

	public String getProperty(String key, boolean ignoreNulls) throws BidgelyExceptions {
		// String returnString = System.getProperty(key);
		String returnString = executionProperties.getProperty(key);

		if (!ignoreNulls && returnString == null) {
			throw new BidgelyExceptions("Property not defined for " + key);
		}
		return returnString;
	}

	
	public String getProperty(String key, String defaultValue) {
		return executionProperties.getProperty(key, defaultValue);
		// return System.getProperty(key, defaultValue);
	}

	
	public void addProperty(ContextKeys key, String value) {
		Validate.notNull(key);
		Validate.notNull(value);
		executionProperties.put(key.toString(), value);
		// System.setProperty(key.toString(), value);
	}

	public void appendProperties(Properties props) {

	}

	public boolean containsProperty(String property){
		return executionProperties.containsKey(property);
	}

	public void setProperty(ContextKeys contextKeys, String utilityName) {
	}
}
