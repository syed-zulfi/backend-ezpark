package com.apptech.apps.easypark.exceptions;

import org.springframework.security.core.AuthenticationException;

import com.apptech.apps.easypark.security.JWTToken;

public class JwtExpiredTokenException extends AuthenticationException {

	private JWTToken token;
	private static final long serialVersionUID = 752222184382321138L;
	
	public JwtExpiredTokenException(String msg) {
		super(msg);
	}

	public JwtExpiredTokenException(JWTToken token, String msg, Throwable t) {
		super(msg, t);
	}

}
