package com.apptech.apps.easypark.security.config;

public enum TKNClaims {
	ID("id"),
	NAME("name"),
	EMAIL("email"),
	ROLE("type"),
	USERNAME("username");

	private String val;
	private TKNClaims(String val) {
		this.val=val;
	}
	
	public String val(){
		return val;
	}
}
