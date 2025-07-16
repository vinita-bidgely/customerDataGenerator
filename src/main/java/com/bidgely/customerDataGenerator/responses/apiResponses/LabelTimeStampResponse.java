package com.bidgely.customerDataGenerator.responses.apiResponses;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LabelTimeStampResponse {

	int first;

	int last;

	public int getFirst() {
		return first;
	}

	public int getLast() {
		return last;
	}
}
