package com.bidgely.customerDataGenerator.api;

import com.bidgely.customerDataGenerator.api.Auth.IAuthTokenManager;
import com.bidgely.customerDataGenerator.exceptions.BidgelyExceptions;
import com.bidgely.customerDataGenerator.responses.apiResponses.ABTestingUserClusterAssignmentApiResponse;
import com.google.common.collect.ImmutableMap;
import com.bidgely.customerDataGenerator.api.Apis.GetUriFor;

import java.util.Map;

public class ABTestingExperimentUserAssignmentToClusterApi
		extends BaseBidgelyApi<ABTestingUserClusterAssignmentApiResponse> {
	protected ABTestingExperimentUserAssignmentToClusterApi(String beUrl,
			IAuthTokenManager authToken) {
		super(beUrl, authToken, GetUriFor.AB_TESTING_USER_ASSIGNMENT_TO_A_CLUSTER, ABTestingUserClusterAssignmentApiResponse.class);
	}

	public ABTestingUserClusterAssignmentApiResponse postAndCaptureResponse(
			String userUUID, String clusterId)
			throws BidgelyExceptions {
		Map<String, String> valueMap = ImmutableMap.<String, String> builder().put(Apis.UriSubstitutorKeys.USER_UUID.toString(), userUUID).put(Apis.UriSubstitutorKeys.CLUSTER_ID.toString(), clusterId).build();
		return postAndCaptureResponseWithoutBody(valueMap);
	}
}
