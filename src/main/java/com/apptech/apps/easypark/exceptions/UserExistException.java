package com.apptech.apps.easypark.exceptions;

import com.apptech.apps.easypark.constants.Fields;

public class UserExistException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 668982967407478381L;
	Fields field;
	
	public static String EMAIL_EXIST="Email id already availbale in our records";
	public static String USERID_EXIST="User id already available in our records";
	
	public UserExistException(String message, Throwable exe) {
		super(message,exe);
	}
	
	public UserExistException(String message, Fields field){
		super(message);
		this.field=field;
	}

	public Fields getField() {
		return field;
	}
	
	
}
