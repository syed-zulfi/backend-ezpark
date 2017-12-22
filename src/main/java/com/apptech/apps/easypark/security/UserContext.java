package com.apptech.apps.easypark.security;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

public class UserContext {

	private final String username;
	private final List<GrantedAuthority> grantedAuthority;

	UserContext(String username, List<GrantedAuthority> grantedAuthority) {
		this.username = username;
		this.grantedAuthority = grantedAuthority;
	}

	public String getUsername() {
		return username;
	}

	public List<GrantedAuthority> getGrantedAuthority() {
		return grantedAuthority;
	}

	public static UserContext create(String username,
			List<GrantedAuthority> authorities) {
		if (StringUtils.isBlank(username))
			throw new IllegalArgumentException("Username is blank: " + username);
		return new UserContext(username, authorities);
	}

}
