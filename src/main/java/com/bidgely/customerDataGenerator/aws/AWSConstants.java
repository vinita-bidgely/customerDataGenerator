package com.bidgely.customerDataGenerator.aws;

public class AWSConstants {

	public enum Namespaces {
		BIDGELY_AUTOMATION("BIDGELY/AUTOMATION");

		private String name;

		Namespaces(String name) {
			this.name = name;
		};

		public String getName() {
			return this.name;
		}
	};

	public enum Dimensions {
		STATUS_CODE("StatusCode"),
		RECEIVED_EMPTY_RESPONSE("ReceivedEmptyResponse");

		private String name;

		Dimensions(String name) {
			this.name = name;
		};

		public String getName() {
			return this.name;
		}
	}
}

