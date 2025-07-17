package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.responses.apiResponses.MetaUserHomeGatewayApiResponse;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.UUID;

public class MetaUserHomeGatewayApi extends BaseBidgelyApi<MetaUserHomeGatewayApiResponse> {

	public MetaUserHomeGatewayApi(String beUrl, IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, Apis.GetUriFor.META_USER_HOME_GATEWAY, MetaUserHomeGatewayApiResponse.class);
	}


	public MetaUserHomeGatewayApiResponse get(UUID userUUID, String gws) throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder()
				.put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString())
				.put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.GATEWAY.toString(), gws)
				.build();
		return get(valueMap);
	}


	public void post(UUID userUUID, String updatePayload) throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder()
				.put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString())
				.put(Apis.UriSubstitutorKeys.HOME.toString(), "1").put(Apis.UriSubstitutorKeys.GATEWAY.toString(), "1")
				.build();

		post(valueMap, updatePayload);
	}
}

