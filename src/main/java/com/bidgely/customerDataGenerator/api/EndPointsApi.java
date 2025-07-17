package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.commons.SharedResources;
import com.bidgely.customerDataGenerator.commons.Validate;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.responses.apiResponses.EndPointsRegistrationResponse;
import com.bidgely.customerDataGenerator.responses.apiResponses.EndPointsResponse;
import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;

import java.util.Map;
import java.util.UUID;

public class EndPointsApi extends BaseBidgelyApi<EndPointsResponse> {
	public EndPointsApi(String beUrl, IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, Apis.GetUriFor.PUBLIC_API_USER_ENDPOINT, EndPointsResponse.class);
	}


	public EndPointsResponse get(UUID userUUID) throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).build();
		return get(valueMap);
	}


	public EndPointsRegistrationResponse put(UUID userUUID, String payload)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).build();
		Response response = putAndCaptureApiResponse(valueMap, payload, MediaType.APPLICATION_JSON);
		Validate.isResponseStatusCreated(response);
		String output = response.then()
				.extract()
				.asString();
		return SharedResources.gson.fromJson(output, EndPointsRegistrationResponse.class);
	}

	public Response putAndCaptureClientResponse(UUID userUUID,
			String payload)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).build();
		Response response = putAndCaptureApiResponse(valueMap, payload, MediaType.APPLICATION_JSON);
		return response;
	}

	public Response getAndCaptureClientResponse(UUID userUUID)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).build();
		return getAndCaptureResponse(valueMap);
	}
}

