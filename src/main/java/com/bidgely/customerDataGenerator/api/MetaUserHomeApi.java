package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.responses.apiResponses.MetaUserHomeApiResponse;
import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;

import java.util.Map;
import java.util.UUID;

public class MetaUserHomeApi extends BaseBidgelyApi<MetaUserHomeApiResponse> {

	public MetaUserHomeApi(String beUrl, IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, ApisUrl.GetUriFor.META_USER_HOME, MetaUserHomeApiResponse.class);
	}


	public MetaUserHomeApiResponse get(UUID userUUID)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").build();

		return get(valueMap);
	}


	public void post(UUID userUUID, String updatePayload)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").build();

		post(valueMap, updatePayload);
	}


	public Response getAndCaptureClientResponse(UUID userUUID)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").build();
		return getAndCaptureResponse(valueMap);
	}
}

