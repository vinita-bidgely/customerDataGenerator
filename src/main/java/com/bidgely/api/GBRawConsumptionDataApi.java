package com.bidgely.api;

import com.bidgely.api.Auth.IAuthTokenManager;
import com.bidgely.exceptions.BidgelyExceptions;
import com.bidgely.responses.GBConsumptionDataResponse;
import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;

import java.util.Map;
import java.util.UUID;

public class GBRawConsumptionDataApi
		extends BaseBidgelyApi<GBConsumptionDataResponse> {

	public GBRawConsumptionDataApi(String beUrl,
			IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, ApisUrl.GetUriFor.GB_RAW_CONSUMPTION_DATA, GBConsumptionDataResponse.class);
	}

	public GBConsumptionDataResponse get(UUID userUUID, int gws,
			int meters, int from,
			int to)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).put(Apis.UriSubstitutorKeys.GATEWAY.toString(), String.valueOf(gws)).put(Apis.UriSubstitutorKeys.METER.toString(), String.valueOf(meters)).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.T0.toString(), String.valueOf(from) +
				"").put(Apis.UriSubstitutorKeys.T1.toString(), String.valueOf(to) +
				"").build();
		return get(valueMap);
	}

	public Response getAndCaptureResponse(UUID userUUID, int gws,
			int meters, int from,
			int to)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).put(Apis.UriSubstitutorKeys.GATEWAY.toString(), String.valueOf(gws)).put(Apis.UriSubstitutorKeys.METER.toString(), String.valueOf(meters)).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.T0.toString(), String.valueOf(from) +
				"").put(Apis.UriSubstitutorKeys.T1.toString(), String.valueOf(to) +
				"").build();
		return getAndCaptureResponse(valueMap);
	}
}

