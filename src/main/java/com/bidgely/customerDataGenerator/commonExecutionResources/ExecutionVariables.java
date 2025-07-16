package com.bidgely.customerDataGenerator.commonExecutionResources;

import com.bidgely.customerDataGenerator.context.PredefinedVariables;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExecutionVariables extends HashMap<String, Object> {

	private static final long serialVersionUID = 8802893598265638669L;

	public Object get(PredefinedVariables name) {
		return get(name.toString());
	}

	public void put(PredefinedVariables name, Object value) {
		put(name.toString(), value);
	}

}