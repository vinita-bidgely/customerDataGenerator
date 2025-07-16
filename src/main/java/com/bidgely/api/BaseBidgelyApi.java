package com.bidgely.api;

import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.model.Dimension;
import com.amazonaws.services.cloudwatch.model.MetricDatum;
import com.amazonaws.services.cloudwatch.model.PutMetricDataRequest;
import com.amazonaws.services.cloudwatch.model.StandardUnit;
import com.bidgely.api.Auth.IAuthTokenManager;
import com.bidgely.aws.AWS;
import com.bidgely.aws.AWSConstants;
import com.bidgely.commonExecutionResources.ResponseStatusEnum;
import com.bidgely.commons.RetryCommand;
import com.bidgely.commons.SharedResources;
import com.bidgely.commons.Validate;
import com.bidgely.exceptions.BidgelyExceptions;
import io.restassured.response.Response;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class BaseBidgelyApi<T> {
	protected String    beUrl;
	protected String    authToken;
	protected ApisUrl.GetUriFor uri;
	private   Class<T>  responseClass;
	protected String    username;
	protected String    password;
	private RetryCommand<T> retryCommand = new RetryCommand<>(3,30000);
	private static final Logger log = LoggerFactory.getLogger(BaseBidgelyApi.class);


	@Autowired
	private AWS aws;
	private IAuthTokenManager authTokenManager;

	protected BaseBidgelyApi(String beUrl, IAuthTokenManager authTokenManager,
			ApisUrl.GetUriFor uri,
			Class<T> responseClass) {
		this.beUrl = beUrl;
		this.authTokenManager = authTokenManager;
		this.uri = uri;
		this.responseClass = responseClass;
	}

	protected BaseBidgelyApi(String beUrl, ApisUrl.GetUriFor uri, String username,
			String password,
			Class<T> responseClass) {
		this.beUrl = beUrl;
		this.uri = uri;
		this.username = username;
		this.password = password;
		this.responseClass = responseClass;
	}

	protected T get(Map<String, String> params) throws BidgelyExceptions {

		return retryCommand.run(() -> {
			try {
				Response apiResponse =
						SharedResources.restAssured
								.given()
								.header("Authorization",
										"Bearer " + authTokenManager.getOauthToken())
								.when()
								.get(beUrl + uri.toString(params));

				putMetric(apiResponse);
				Validate.isResponseStatusOk(apiResponse);

				String output = apiResponse
						.then()
						.extract()
						.asString();

				if (responseClass.equals(String.class)) {
					return (T) output;
				}
				return SharedResources.gson.fromJson(output, responseClass);

			} catch (BidgelyExceptions e) {
				log.warn("API Response is Null or Exception occurred", e);
				e.printStackTrace();
			}
			return null;
		});

	}


	protected T get_without_status_check(Map<String, String> params) throws BidgelyExceptions {

		return retryCommand.run(() -> {
			try {
				Response apiResponse =
						SharedResources.restAssured
								.given()
								.header("Authorization",
										"Bearer " + authTokenManager.getOauthToken())
								.when()
								.get(beUrl + uri.toString(params));

				putMetric(apiResponse);

				String output = apiResponse
						.then()
						.extract()
						.asString();

				if (responseClass.equals(String.class)) {
					return (T) output;
				}
				return SharedResources.gson.fromJson(output, responseClass);

			} catch (BidgelyExceptions e) {
				log.warn("API Response is Null or Exception occurred", e);
				e.printStackTrace();
			}
			return null;
		});

	}


	protected T getUser(Map<String, String> params){
		try {
			Response apiResponse =
					SharedResources.restAssured
							.given()
							.header("Authorization",
									"Bearer " + authTokenManager.getOauthToken())
							.when()
							.get(beUrl + uri.toString(params));

			putMetric(apiResponse);

			if(apiResponse.getStatusCode() != ResponseStatusEnum.Status.OK.getStatusCode()){
				return null;
			}

			String output = apiResponse
					.then()
					.extract()
					.asString();

			if (responseClass.equals(String.class)) {
				return (T) output;
			}
			return SharedResources.gson.fromJson(output, responseClass);

		} catch (BidgelyExceptions e) {
			log.warn("API Response is Null or Exception occurred", e);
			e.printStackTrace();
		}
		return null;
	}

	protected T get_with_retry(Map<String, String> params) throws BidgelyExceptions {

		String userUUID = null;
		if(params.containsKey("userUUID")){
			userUUID = params.get("userUUID");
		}
		String finalUserUUID = userUUID;
		final int[] attemptCount = {0};
		return retryCommand.run(() -> {
			try {
				attemptCount[0]++;
				if (attemptCount[0] == 2 && finalUserUUID != null) {
					try {
						Thread.sleep(60000);
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				Response apiResponse =
						SharedResources.restAssured
								.given()
								.header("Authorization",
										"Bearer " + authTokenManager.getOauthToken())
								.when()
								.get(beUrl + uri.toString(params));

				putMetric(apiResponse);
				Validate.isResponseStatusOk(apiResponse);

				String output = apiResponse
						.then()
						.extract()
						.asString();

				if (responseClass.equals(String.class)) {
					return (T) output;
				}
				return SharedResources.gson.fromJson(output, responseClass);

			} catch (BidgelyExceptions e) {
				log.warn("API Response is Null or Exception occurred", e);
				e.printStackTrace();
			}
			return null;
		});

	}

	protected T getWithAuthorizationPrefix(Map<String, String> params,
			String authorizationPrefix) throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", authorizationPrefix +
								authTokenManager.getOauthToken())
						.when()
						.get(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response.then().extract().asString();
		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	//TO DO : Remove the dependency of passing authToken
	//override above method
	protected T get(Map<String, String> params, String authToken)
			throws BidgelyExceptions {

		Response response = SharedResources.restAssured
				.given()
				.header("Authorization", "Bearer " + authToken)
				.when()
				.get(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response.then().extract().asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	protected Response getWithBaseUrl(Map<String, String> params, String baseUrl, String authToken)
			throws BidgelyExceptions {

		Response response = SharedResources.restAssured
				.given()
				.header("Authorization", "Bearer " + authToken)
				.when()
				.get(baseUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	protected Response getAndCaptureResponse(
			Map<String, String> params) throws BidgelyExceptions {
		Response response = SharedResources.restAssured
				.given()
				.header("Authorization", "Bearer " + authTokenManager.getOauthToken())
				.when()
				.get(beUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	//override
	protected Response getAndCaptureResponse(
			Map<String, String> params, String authToken)
			throws BidgelyExceptions {
		Response response = SharedResources.restAssured
				.given()
				.header("Authorization", "Bearer " + authToken)
				.when()
				.get(beUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	protected T getWithBasicAuthentication(Map<String, String> params)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.auth()
						.basic(username, password)
						.header("Accept", "application/vnd.xplenty+json")
						.when()
						.get(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response.then().extract().asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	protected T postWithBasicAuthentication(Map<String, String> params,
			MultivaluedMap<String, String> payload)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.auth()
						.basic(username, password)
						.header("Accept", "application/vnd.xplenty+json")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);

		String output = response
				.then()
				.extract()
				.asString();
		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	protected void post(Map<String, String> params, String payload)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	protected T postAndCaptureResponse(Map<String, String> params,
			String payload) throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response
				.then()
				.extract()
				.asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	protected Response postAndCaptureResponseWithAuthorizationPrefix(
			Map<String, String> params, String authorizationPrefix,
			String payload) throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", authorizationPrefix +
								authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	protected T postAndCaptureResponseWithoutBody(Map<String, String> params)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.post(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response
				.then()
				.extract()
				.asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	protected Response postAndCaptureApiResponse(
			Map<String, String> params, String payload)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		return response;
	}

	protected void post(Map<String, String> params, String payload,
			String mediaType) throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(mediaType)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	protected void post(Map<String, String> params, String payload,
			String mediaType, String authToken) throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", "Bearer " + authToken)
						.contentType(mediaType)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	protected T postAndCaptureResponse(Map<String, String> params,
			String payload, String mediaType)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(mediaType)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response
				.then()
				.extract()
				.asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	//override
	protected T postAndCaptureResponse(Map<String, String> params,
			String payload, String mediaType, String authToken)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", "Bearer " + authToken)
						.contentType(mediaType)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response
				.then()
				.extract()
				.asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	protected Response postAndCaptureApiResponse(
			Map<String, String> params, String payload, String mediaType)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(mediaType)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	protected Response postAndCaptureApiResponse(
			Map<String, String> params, String payload, String mediaType,
			String authToken)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", "Bearer " + authToken)
						.contentType(mediaType)
						.when()
						.body(payload)
						.post(beUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	protected void put(Map<String, String> params, String payload)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	protected void put(Map<String, String> params)
			throws BidgelyExceptions {
		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.put(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	protected void put(Map<String, String> params, String payload,
			String mediaType) throws BidgelyExceptions {
		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(mediaType)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	protected void put(Map<String, String> params, String payload,
			String mediaType, String authToken) throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", "Bearer " + authToken)
						.contentType(mediaType)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	protected T putAndCaptureResponse(Map<String, String> params,
			String payload) throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response
				.then()
				.extract()
				.asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	protected T putAndCaptureResponse(Map<String, String> params,
			String payload, String mediaType)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(mediaType)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response
				.then()
				.extract()
				.asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	//override
	protected T putAndCaptureResponse(Map<String, String> params,
			String payload, String mediaType, String authToken)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", "Bearer " + authToken)
						.contentType(mediaType)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);

		String output = response
				.then()
				.extract()
				.asString();

		if (responseClass.equals(String.class)) {
			return (T) output;
		}
		return SharedResources.gson.fromJson(output, responseClass);
	}

	protected Response putAndCaptureApiResponse(
			Map<String, String> params, String payload)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	protected Response putAndCaptureApiResponse(
			Map<String, String> params, String payload, String mediaType)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(mediaType)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	//override above one- already present

	protected Response putAndCaptureApiResponse(
			Map<String, String> params, String payload, String mediaType,
			String authToken) throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", "Bearer " + authToken)
						.contentType(mediaType)
						.when()
						.body(payload)
						.put(beUrl + uri.toString(params));

		putMetric(response);
		return response;
	}

	protected void delete(Map<String, String> params)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization",
								"Bearer " + authTokenManager.getOauthToken())
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.delete(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	protected void delete(Map<String, String> params, String authToken)
			throws BidgelyExceptions {

		Response response =
				SharedResources.restAssured
						.given()
						.header("Authorization", "Bearer " + authToken)
						.contentType(MediaType.APPLICATION_JSON)
						.when()
						.delete(beUrl + uri.toString(params));

		putMetric(response);
		Validate.isResponseStatusOk(response);
	}

	private void putMetric(Response response) {
		if (!System.getProperty("my.env").equalsIgnoreCase("qa")) {
			return;
		}

		String status = "xxx";
		switch (response.getStatusCode() / 100) {
		case 2: {
			status = "2xx";
			break;
		}
		case 4: {
			status = "4xx";
			break;
		}
		case 5: {
			status = "5xx";
			break;
		}

		}
		switch (uri) {
		case LABEL_TIMESTAMPS: {

			String emptyResponse = "false";
			if (response.then().extract().asString().isEmpty()) {
				emptyResponse = "true";
			}
			final AmazonCloudWatch cw = aws.getAmazonCloudWatchClientInstance();
			Dimension emptyResponseDimension = new Dimension().withName(AWSConstants.Dimensions.RECEIVED_EMPTY_RESPONSE.getName()).withValue(emptyResponse);
			MetricDatum datum = new MetricDatum().withMetricName(ApisUrl.GetUriFor.LABEL_TIMESTAMPS.name()).withUnit(
					StandardUnit.Count).withValue(1.0).withDimensions(emptyResponseDimension);
			PutMetricDataRequest request = new PutMetricDataRequest().withNamespace(AWSConstants.Namespaces.BIDGELY_AUTOMATION.getName()).withMetricData(datum);
			cw.putMetricData(request);
			break;
		}
		case GET_UUID_FROM_EMAIL:
		case TOKENS_API: {
			final AmazonCloudWatch cw = aws.getAmazonCloudWatchClientInstance();
			Dimension statusDimension = new Dimension().withName(AWSConstants.Dimensions.STATUS_CODE.getName()).withValue(status);
			MetricDatum datum = new MetricDatum().withMetricName(uri.name()).withUnit(StandardUnit.Count).withValue(1.0).withDimensions(statusDimension);
			PutMetricDataRequest request = new PutMetricDataRequest().withNamespace(AWSConstants.Namespaces.BIDGELY_AUTOMATION.getName()).withMetricData(datum);
			cw.putMetricData(request);
			break;
		}
		default: {
			// Do Nothing
		}

		}

	}
}
