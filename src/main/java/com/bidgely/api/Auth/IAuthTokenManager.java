package com.bidgely.api.Auth;

import com.bidgely.exceptions.BidgelyExceptions;

public interface IAuthTokenManager {
	public String getOauthToken()
			throws BidgelyExceptions;

	public String getOauthToken(String utilityName)
			throws BidgelyExceptions;
}
