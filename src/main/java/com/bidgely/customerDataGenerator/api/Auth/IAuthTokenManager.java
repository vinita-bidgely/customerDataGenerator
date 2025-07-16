package com.bidgely.customerDataGenerator.api.Auth;

import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;

public interface IAuthTokenManager {
	public String getOauthToken()
			throws BidgelyExceptions;

	public String getOauthToken(String utilityName)
			throws BidgelyExceptions;
}
