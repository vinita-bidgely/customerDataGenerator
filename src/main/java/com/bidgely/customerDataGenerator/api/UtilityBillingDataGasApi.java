package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.responses.UtilityBillingDataResponse;
import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;

import java.util.Map;
import java.util.UUID;

public class UtilityBillingDataGasApi
		extends BaseBidgelyApi<UtilityBillingDataResponse> {

	public UtilityBillingDataGasApi(String beUrl,
			IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, Apis.GetUriFor.UTILITY_BILLING_DATA, UtilityBillingDataResponse.class);
	}

	public UtilityBillingDataResponse get(UUID userUUID, int t0, int t1)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.T0.toString(), t0 +
				"").put(Apis.UriSubstitutorKeys.T1.toString(), t1 + "&measurementType=GAS").build();
		return get(valueMap);
	}

	public Response getAndCaptureResponse(UUID userUUID, int t0,
			int t1)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.T0.toString(), t0 +
				"").put(Apis.UriSubstitutorKeys.T1.toString(), t1 + "&measurementType=GAS").build();
		return getAndCaptureResponse(valueMap);
	}
}

