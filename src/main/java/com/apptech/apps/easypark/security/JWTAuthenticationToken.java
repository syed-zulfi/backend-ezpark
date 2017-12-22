package com.apptech.apps.easypark.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JWTAuthenticationToken extends AbstractAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7025737103427693800L;

	private RawAccessToken rawAccessToken;
	private UserContext userContext;

	public JWTAuthenticationToken(RawAccessToken unsafeToken) {
		super(null);
		this.rawAccessToken = unsafeToken;
		super.setAuthenticated(false);
	}
	/**
	 * 
	 * @param userContext
	 * @param authorities
	 */

	public JWTAuthenticationToken(UserContext userContext,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.userContext = userContext;
		super.setAuthenticated(true);
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		if (authenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}
		super.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return rawAccessToken;
	}

	@Override
	public Object getPrincipal() {
		return this.userContext;
	}

}
