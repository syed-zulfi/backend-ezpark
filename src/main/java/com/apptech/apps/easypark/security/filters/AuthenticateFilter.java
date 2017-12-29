package com.apptech.apps.easypark.security.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.apptech.apps.easypark.constants.AppConstants;
import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.security.config.Settings;
import com.apptech.apps.easypark.security.factory.SecurityUtil;
import com.apptech.apps.easypark.security.service.LoginUserService;

public class AuthenticateFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authManager;
	private Settings settings;
	private LoginUserService loginUserService;
	private static final Logger log = LoggerFactory.getLogger(AuthenticateFilter.class);

	public AuthenticateFilter(AuthenticationManager authManager, Settings settings,
			LoginUserService loginUserService) {
		this.authManager = authManager;
		this.settings = settings;
		this.loginUserService = loginUserService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		log.info("Authenticating user");
		Authentication authenticate = null;
		try {
			User creds = SecurityUtil.extractUserCredentials(req);
			authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),
					creds.getPassword(), new ArrayList<>()));
		} catch (IOException exception) {

		}
		return authenticate;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String ezpToken = SecurityUtil.generateToken(settings, loginUserService.getPublicDetails());
		res.setHeader(settings.getToken().getHeaderlabel(),
				settings.getToken().getPrefixlabel() + AppConstants.SPACE + ezpToken);
		res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));

	}
}
