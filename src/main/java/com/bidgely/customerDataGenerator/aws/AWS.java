package com.bidgely.customerDataGenerator.aws;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;

public class AWS {
	public static AmazonCloudWatch getAmazonCloudWatchClientInstance() {
		if (System.getProperty("aws.profile") != null) {
			return new AmazonCloudWatchClient(new ProfileCredentialsProvider(System.getProperty("aws.profile")));
		}
		else {
			return new AmazonCloudWatchClient(new DefaultAWSCredentialsProviderChain());
		}
	}
}
