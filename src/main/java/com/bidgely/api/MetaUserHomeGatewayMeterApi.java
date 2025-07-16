package com.bidgely.api;

import com.bidgely.api.Auth.IAuthTokenManager;
import com.bidgely.exceptions.BidgelyExceptions;
import com.bidgely.responses.MetaUserHomeGatewayMeterResponse;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.UUID;

public class MetaUserHomeGatewayMeterApi extends BaseBidgelyApi<MetaUserHomeGatewayMeterResponse> {
	public MetaUserHomeGatewayMeterApi(String beUrl, IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, ApisUrl.GetUriFor.META_USER_HOME_GATEWAY_METER, MetaUserHomeGatewayMeterResponse.class);
	}


	public MetaUserHomeGatewayMeterResponse get(UUID userUUID, int homeId, int gatewayId, int meterId)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String>builder()
				.put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString())
				.put(Apis.UriSubstitutorKeys.HOME.toString(), Integer.toString(homeId))
				.put(Apis.UriSubstitutorKeys.GATEWAY.toString(), Integer.toString(gatewayId))
				.put(Apis.UriSubstitutorKeys.METER.toString(), Integer.toString(meterId)).build();
		return get(valueMap);

	}


	public void post(UUID userUUID, String hId, String gwsNo, String mId, String payLoad)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String>builder()
				.put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString())
				.put(Apis.UriSubstitutorKeys.HOME.toString(), hId)
				.put(Apis.UriSubstitutorKeys.GATEWAY.toString(), gwsNo)
				.put(Apis.UriSubstitutorKeys.METER.toString(), mId).build();
		post(valueMap, payLoad);

	}
}

