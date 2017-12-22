package com.apptech.apps.easypark.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class RESTAuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest arg0, HttpServletResponse arg1,
			AuthenticationException arg2) throws IOException, ServletException {
		arg1.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");

	}

}
