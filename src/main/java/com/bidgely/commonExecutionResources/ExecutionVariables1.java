package com.bidgely.commonExecutionResources;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ExecutionVariables1 extends HashMap<String, Object> {

	public Object put(String key, Object value) {
		super.put(key, value);
		return value;
	}

	public Object get(String key) {
		return super.get(key);
	}

	public boolean containsKey(String key) {
		return super.containsKey(key);
	}

	// Initialize with default values
	public void initializeDefaults() {
		// Core user identifiers
		put("customerId", null);
		put("contractId", null);
		put("premiseId", null);
		put("userUUID", null);

		// Data stream IDs (will be populated dynamically)
		// put("dataStreamId_2", null); // for AMI-ELECTRIC
		// put("dataStreamId_3", null); // for NSM-ELECTRIC/AMR-ELECTRIC
		// put("dataStreamId_4", null); // for NSM-GAS/AMR-GAS/NSM-WATER
		// put("dataStreamId_5", null); // for AMI-GAS/AMI-WATER

		// Secondary IDs
		put("secondaryId", null);

		// Service Point IDs
		put("servicePointId", null);
		put("servicePointIdNumber", null);
		// put("servicePointId_2", null); // for specific gateways
		// put("servicePointId_3", null);
		// put("servicePointId_4", null);
		// put("servicePointId_5", null);

		// Partner and Contract IDs
		put("partnerUserId", null);
		put("contractAccountId", null);

		// External Account IDs
		put("externalAccountId", null);

		// SDP ID
		put("sdpId", null);

		// Test execution variables
		put("testInputParameters", new ArrayList<>());
		put("internal.executeTestInCurrentContext", true);
		put("internal.testCaseName", "RecreateProdUserDataTest");
		put("isSharedUser", false);
		put("internal.sharedUserName", null);
		put("internal.shareCurrentUser", false);
		put("internal.iterator", 0);
		put("internal.currentIteration", 0);
		put("internal.actionDrVariables", new HashMap<>());
		put("internal.actionDrReporter", null);
		put("internal.testCaseFileName", "RecreateProdUserData.json");
	}
}
