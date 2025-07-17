package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.responses.InvoiceDataList;
import com.google.common.collect.ImmutableMap;

import java.util.Map;
import java.util.UUID;

public class MetaUserApi extends BaseBidgelyApi<InvoiceDataList.MetaUserApiResponse> {

	public MetaUserApi(String beUrl, IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, Apis.GetUriFor.META_USER, InvoiceDataList.MetaUserApiResponse.class);
	}


	public InvoiceDataList.MetaUserApiResponse get(UUID userUUID) throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder()
				.put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).build();

		return get(valueMap);
	}


	public void post(UUID userUUID, String updatePayload) throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder()
				.put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID.toString()).build();

		post(valueMap, updatePayload);
	}
}

