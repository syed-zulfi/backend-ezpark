package com.apptech.apps.easypark.security;

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
import com.apptech.apps.easypark.util.JWTUtil;

public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authManager;
	private JWTAuthSettings settings;
	private LoginUserService loginUserService;
	private static final Logger log = LoggerFactory
			.getLogger(JWTAuthenticateFilter.class);

	public JWTAuthenticateFilter(AuthenticationManager authManager,
			JWTAuthSettings settings, LoginUserService loginUserService) {
		this.authManager = authManager;
		this.settings = settings;
		this.loginUserService = loginUserService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
			HttpServletResponse res) throws AuthenticationException {
		log.info("Authenticating user");
		Authentication authenticate = null;
		try {
			User creds = JWTUtil.extractUserCredentials(req);
			authenticate = authManager
					.authenticate(new UsernamePasswordAuthenticationToken(creds
							.getUsername(), creds.getPassword(),
							new ArrayList<>()));
		} catch (IOException exception) {
			

		}
		return authenticate;

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain, Authentication auth)
			throws IOException, ServletException {

		String ezpToken = JWTUtil.generateToken(settings,
				loginUserService.getPublicDetails());
		res.getWriter().write(settings.getToken().getHeaderlabel()+":"+ settings.getToken()
				.getPrefixlabel() + AppConstants.SPACE + ezpToken);

		res.setHeader("Access-Control-Allow-Origin",
				req.getHeader("Origin"));

	}
}
