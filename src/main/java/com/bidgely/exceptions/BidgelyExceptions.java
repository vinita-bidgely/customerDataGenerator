package com.bidgely.exceptions;

public class BidgelyExceptions extends Exception{
	private static final long serialVersionUID = 1L;

	public BidgelyExceptions() {
		super();
	}

	public BidgelyExceptions(String errorMessage) {
		super(errorMessage);
	}

	public BidgelyExceptions(Exception e) {
		super(e.getMessage(), e);
	}
}
