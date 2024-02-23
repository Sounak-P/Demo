package com.automation.framework.exception;

/*
 * @author koustav
 */

public class TestDataNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TestDataNotFoundException() {
	}

	public TestDataNotFoundException(String message) {
		super(message);
	}

	public TestDataNotFoundException(Throwable cause) {
		super(cause);
	}

	public TestDataNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestDataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
