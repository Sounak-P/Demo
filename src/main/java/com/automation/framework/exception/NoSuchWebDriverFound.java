package com.automation.framework.exception;


public class NoSuchWebDriverFound extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	  public NoSuchWebDriverFound() {
	        super("Web Driver not found.");
	    }

	  public NoSuchWebDriverFound(String s) {
	        super(s);
	    }
	  
}
