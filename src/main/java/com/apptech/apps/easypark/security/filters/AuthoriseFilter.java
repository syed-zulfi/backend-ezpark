package com.apptech.apps.easypark.security.filters;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.apptech.apps.easypark.constants.AppConstants;
import com.apptech.apps.easypark.security.config.TKNClaims;
import com.apptech.apps.easypark.util.RequestUtil;
import com.apptech.apps.easypark.security.config.Role;
import com.apptech.apps.easypark.security.config.Settings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;

public class AuthoriseFilter extends BasicAuthenticationFilter {

	private static final Logger log = LoggerFactory.getLogger(AuthoriseFilter.class);
	final String HEADER_STRING;
	final String TOKEN_PREFIX;
	final String SECRET_KEY;

	public AuthoriseFilter(AuthenticationManager authenticationManager, Settings settings) {
		super(authenticationManager);
		HEADER_STRING = settings.getToken().getHeaderlabel();
		TOKEN_PREFIX = settings.getToken().getPrefixlabel() + AppConstants.SPACE;
		SECRET_KEY = settings.getToken().getSignature();
		log.info("Authorise Filter created");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		log.info("Performing internal filters");
		String header = req.getHeader(HEADER_STRING);
		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		log.info("Parsing authenticatin token...");
		String SECRET = TextCodec.BASE64.encode(SECRET_KEY);
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, AppConstants.EMPTY)).getBody().getSubject();
			Claims claims = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, AppConstants.EMPTY)).getBody();

			Collection<? extends GrantedAuthority> authorities = Arrays
					.asList(claims.get(TKNClaims.ROLE.val()).toString().split(AppConstants.COMMA)).stream()
					.map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

			if (user != null) {
				log.info("Token successfully parsed and returning claimes...");
				return new UsernamePasswordAuthenticationToken(user, null, authorities);
			}
			return null;
		}
		return null;
	}
}
