package com.bidgely.customerDataGenerator.commons;

import com.bidgely.customerDataGenerator.commonExecutionResources.ResponseStatusEnum;
import com.google.gson.Gson;
import io.restassured.response.Response;

public class Validate extends org.apache.commons.lang3.Validate {

	public static void isResponseStatusOk(Response response) {

		isTrue(response.getStatusCode() == ResponseStatusEnum.Status.OK.getStatusCode(), "Response Code: " +
				response.getStatusCode() + "; Headers: " +
				new Gson().toJson(response.getHeaders()));
	}

	public static void isResponseStatusCreated(Response response) {
		isTrue(response.getStatusCode() == ResponseStatusEnum.Status.CREATED.getStatusCode(), "Response Code: " +
				response.getStatusCode() + "; Headers: " +
				new Gson().toJson(response.getHeaders()));
	}
}
