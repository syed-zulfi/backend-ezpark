package com.apptech.apps.easypark.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.TextCodec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.apptech.apps.easypark.constants.JWTClaimField;

public class JWTAuthoriseFilter extends BasicAuthenticationFilter {

	public JWTAuthoriseFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse res, FilterChain chain) throws IOException,
			ServletException {
		final String HEADER_STRING = "Authorization";
		final String TOKEN_PREFIX = "Bearer ";

		String header = req.getHeader(HEADER_STRING);
		if (header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(
			HttpServletRequest request) {
		final String HEADER_STRING = "Authorization";
		final String TOKEN_PREFIX = "Bearer ";
		String SECRET = TextCodec.BASE64.encode("APP_TECH_EZPARK");
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			String user = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
					.getSubject();
			Claims claims = Jwts.parser().setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
					
			 Collection<? extends GrantedAuthority> authorities
             = Arrays.asList(claims.get(JWTClaimField.ROLE.val()).toString().split(",")).stream()
             .map(authority -> new SimpleGrantedAuthority(authority))
             .collect(Collectors.toList());

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null,
						authorities);
			}
			return null;
		}
		return null;
	}
}
