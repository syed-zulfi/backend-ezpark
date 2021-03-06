package com.apptech.apps.easypark.constants;

import org.springframework.http.HttpStatus;

public enum ReturnCode {

	REGESTERED(HttpStatus.CREATED,"User Created"),
	DECLINED(HttpStatus.CONFLICT, "User already registered"),
	APPLICATION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Application Error"),
	FORBID_ACCESS(HttpStatus.FORBIDDEN,"Access denied"),
	UID_AVAILABLE(HttpStatus.ACCEPTED,"User id available"),
	UID_UNAVAILABLE(HttpStatus.CONFLICT,"Duplicate user id"),
	EMAIL_AVAILABLE(HttpStatus.ACCEPTED,"Email id available"),
	EMAIL_DUPLICATE(HttpStatus.CONFLICT,"Duplicate email id"),
	BAD_INPUT(HttpStatus.BAD_REQUEST,"Invalid input"),
	EMPTY_RECORD(HttpStatus.PRECONDITION_FAILED,"Records not found"),
	SUCCESS(HttpStatus.OK,"Request processed successfully");
	
	
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
