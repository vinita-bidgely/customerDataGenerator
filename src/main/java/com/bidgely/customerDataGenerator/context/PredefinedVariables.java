package com.bidgely.customerDataGenerator.context;


public enum PredefinedVariables {

	UserUUID("userUUID"),


	Email("email"),

	Password("password"),

	ExternalAccountId("externalAccountId"),

	Timezone("timezone"),
	ExecuteTestInCurrentContext("internal.executeTestInCurrentContext"),
	TestCaseName("internal.testCaseName"),
	IsSharedUser("isSharedUser"),
	SharedUserName("internal.sharedUserName"),
	ShareCurrentUser("internal.shareCurrentUser"),
	Iterator("internal.iterator"),
	CurrentIteration("internal.currentIteration"),
	ActionDrVariables("internal.actionDrVariables"),
	ActionDrReporter("internal.actionDrReporter"),
	TestCaseFileName("internal.testCaseFileName");

	PredefinedVariables(String name) {
		this.variableName = name;
	}

	private String variableName;

	public String toString() {
		return variableName;
	}
}
