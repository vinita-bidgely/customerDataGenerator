package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.responses.apiResponses.MetaUserHomeGatewaysApiResponse;
import com.google.common.collect.ImmutableMap;
import com.bidgely.customerDataGenerator.api.Apis.GetUriFor;

import java.util.Map;
import java.util.UUID;

public class MetaUserHomeGatewaysApi extends BaseBidgelyApi<MetaUserHomeGatewaysApiResponse> {

	public MetaUserHomeGatewaysApi(String beUrl, IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, GetUriFor.META_USER_HOME_GATEWAYS, MetaUserHomeGatewaysApiResponse.class);
	}


	public MetaUserHomeGatewaysApiResponse get(UUID userUUID)
			throws BidgelyExceptions {

		Map<String, String> valueMap = ImmutableMap.<String, String> builder()
				.put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString())
				.put(Apis.UriSubstitutorKeys.HOME.toString(), "1").build();
		return get(valueMap);
	}
}

