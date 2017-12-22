package com.apptech.apps.easypark.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apptech.apps.easypark.constants.ReturnCode;
import com.apptech.apps.easypark.controllers.vo.ResponseDTO;

public class ResponseUtil {

	public static ResponseEntity<ResponseDTO> buildResponse(ResponseDTO rdto) {
		if (null == rdto.getError()) {
			return new ResponseEntity<ResponseDTO>(rdto, rdto.getHttpStatus());
		} else {
			return new ResponseEntity<ResponseDTO>(rdto,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public static ResponseEntity<ResponseDTO> buildValidateResponse(
			ResponseDTO rdto) {
		if (rdto.getResponseCode().equals(ReturnCode.EMAIL_AVAILABLE.code())
				|| rdto.getResponseCode().equals(
						ReturnCode.UID_AVAILABLE.code())) {
			return new ResponseEntity<ResponseDTO>(rdto, HttpStatus.ACCEPTED);
		} else if (rdto.getResponseCode().equals(
				ReturnCode.EMAIL_DUPLICATE.code())
				|| rdto.getResponseCode().equals(
						ReturnCode.UID_UNAVAILABLE.code())) {
			return new ResponseEntity<ResponseDTO>(rdto,
					HttpStatus.NOT_ACCEPTABLE);

		} else {
			return new ResponseEntity<ResponseDTO>(rdto, HttpStatus.BAD_REQUEST);
		}
		/*
		 * HttpHeaders header = new HttpHeaders();
		 * header.setLocation(builder.path("/user/create").build().toUri());
		 */
	}

	/**
	 * 
	 * @param rc
	 * @param obj
	 * @return
	 */
	public static ResponseDTO createResponseDTO(ReturnCode rc, Object obj) {
		ResponseDTO rdto = ResponseDTO.create();
		rdto.setResponseMessage(rc.message());
		rdto.setResponseCode(rc.code().value());
		rdto.setHttpStatus(rc.code());
		rdto.setResponseObject(null);
		return rdto;
	}
}
