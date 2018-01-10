package com.apptech.apps.easypark.exceptions;

public class AccessDeniedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9045971185897721721L;
	public AccessDeniedException(String messg){
		super(messg);
	}
}
