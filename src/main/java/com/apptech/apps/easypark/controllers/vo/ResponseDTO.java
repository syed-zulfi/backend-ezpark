package com.apptech.apps.easypark.controllers.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;

public class ResponseDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2273903276473657462L;
	private String responseMessage;
	private Integer responseCode;
	private HttpStatus httpStatus;

	private Error error;
	private Object responseData;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	private ResponseDTO(){
		
	}
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	 

	public Object getResponseData() {
		return responseData;
	}
	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}
	public void createError() {
		Error error = new Error();
		this.error=error;
	}

	public Error getError() {
		return error;
	}

	public class Error {
		private String errorCode;
		private String errorDescription;

		public String getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(String errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorDescription() {
			return errorDescription;
		}

		public void setErrorDescription(String errorDescription) {
			this.errorDescription = errorDescription;
		}

	}
	 
	
	
	public static ResponseDTO create(){
		return new ResponseDTO();
	}

}
