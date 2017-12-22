package com.apptech.apps.easypark.security;

import java.util.List;
import java.util.stream.Collectors;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JWTAuthProvider implements AuthenticationProvider {

	private final JWTAuthSettings authSettings;

	public JWTAuthProvider(JWTAuthSettings authSettings) {
		this.authSettings = authSettings;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		RawAccessToken accessToken = (RawAccessToken) authentication
				.getCredentials();
		Jws<Claims> jwsClaim = accessToken.parseClaims(authSettings
				.getToken().getSignature());
		String subject = jwsClaim.getBody().getSubject();
		List<String> scopes = jwsClaim.getBody().get("scopes", List.class);
		List<GrantedAuthority> authorities = scopes.stream()
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		UserContext context = UserContext.create(subject, authorities);

		return new JWTAuthenticationToken(context,
				context.getGrantedAuthority());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JWTAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
