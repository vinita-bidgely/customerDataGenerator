package com.bidgely.api;

import com.bidgely.api.Auth.IAuthTokenManager;
import com.bidgely.context.MeasurementType;
import com.bidgely.exceptions.BidgelyExceptions;
import com.bidgely.responses.apiResponses.LabelTimeStampResponse;
import com.google.common.collect.ImmutableMap;
import io.restassured.response.Response;

import java.util.Map;
import java.util.UUID;

public class GetLabelTimeStamps extends BaseBidgelyApi<LabelTimeStampResponse> {

	private IAuthTokenManager authTokenManager;

	public GetLabelTimeStamps(String beUrl,
			IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, ApisUrl.GetUriFor.LABEL_TIMESTAMPS, LabelTimeStampResponse.class);
		this.authTokenManager = authTokenManager;
	}

	public LabelTimeStampResponse getLabelTimestamps(UUID userId,
			MeasurementType measurementType, String stream)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userId.toString()).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.MEASUREMENT_TYPE.toString(), measurementType.toString()).put(Apis.UriSubstitutorKeys.STREAM.toString(), stream).build();
		return get_with_retry(valueMap);
	}

	public LabelTimeStampResponse getLabelTimestamp(UUID userId,
			MeasurementType measurementType, String stream)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userId.toString()).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.MEASUREMENT_TYPE.toString(), measurementType.toString()).put(Apis.UriSubstitutorKeys.STREAM.toString(), stream).build();
		return get(valueMap, authTokenManager.getOauthToken());
	}

	public Response getAndCaptureClientResponse(UUID userId,
			MeasurementType measurementType, String stream)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userId.toString()).put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.MEASUREMENT_TYPE.toString(), measurementType.toString()).put(Apis.UriSubstitutorKeys.STREAM.toString(), stream).build();
		return getAndCaptureResponse(valueMap);
	}
}

