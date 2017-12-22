package com.apptech.apps.easypark.security;

import org.springframework.security.core.authority.AuthorityUtils;

import com.apptech.apps.easypark.dao.entity.User;

public class LoginUser extends
		org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;

	
	public LoginUser(User user) {
		super(user.getEmail(), user.getPassword(), AuthorityUtils
				.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}

	/**
	 * 
	 * @return
	 */

	public User getUser() {
		return user;
	}

	/**
	 * 
	 * @return
	 */
	public String getUserId() {
		return user.getUsername();
	}

	/**
	 * 
	 * @return
	 */
	public String getRole() {
		return user.getRole().toString();
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "LoginUser [user=" + user + "]" +super.toString();
	}
	
	
}
