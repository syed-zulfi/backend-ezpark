package com.apptech.apps.easypark.controllers.vo;

import org.springframework.http.HttpStatus;

public class ResponseDTO {

	private String responseMessage;
	private Integer responseCode;
	private HttpStatus httpStatus;

	private Error error;
	private Object responseObject;

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

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
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
