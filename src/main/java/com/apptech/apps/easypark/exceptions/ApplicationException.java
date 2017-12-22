package com.apptech.apps.easypark.exceptions;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1590731434040083787L;
	public static String REG_FAIL="Exception Occured while registering user";
	
	public ApplicationException(String messg, Throwable ex){
		super(messg, ex);
	}
	
	
}
