package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class TokensApi extends BaseBidgelyApi<String> {

	protected TokensApi(String beUrl, IAuthTokenManager authTokenManager) {
		super(beUrl, authTokenManager, Apis.GetUriFor.TOKENS_API, String.class);
	}

	public String get(String token) throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder()
				.put(Apis.UriSubstitutorKeys.TOKEN.toString(), token).build();
		return get(valueMap);
	}

	public String getUser(String token) {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder()
				.put(Apis.UriSubstitutorKeys.TOKEN.toString(), token).build();
		return getUser(valueMap);
	}
}

