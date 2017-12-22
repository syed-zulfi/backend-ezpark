package com.apptech.apps.easypark.constants;

import org.springframework.http.HttpStatus;

public enum ReturnCode {

	REGESTERED(HttpStatus.CREATED,"User Created"),
	DECLINED(HttpStatus.CONFLICT, "User already registered"),
	APPLICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Application Error"),
	UID_AVAILABLE(HttpStatus.ACCEPTED,"User id available"),
	UID_UNAVAILABLE(HttpStatus.CONFLICT,"Duplicate user id"),
	EMAIL_AVAILABLE(HttpStatus.ACCEPTED,"Email id available"),
	EMAIL_DUPLICATE(HttpStatus.CONFLICT,"Duplicate email id"),
	BAD_INPUT(HttpStatus.BAD_REQUEST,"Invalid input");
	
	
	HttpStatus code;
	String message;
	ReturnCode(HttpStatus code, String message){
		this.code=code;
		this.message=message;
		
	}
	
	public HttpStatus code(){
		return code;
	}
	
	public String message(){
		return message;
	}
}
