package com.apptech.apps.easypark.security.constants;

public enum JWTClaimField {
	ID("id"),
	NAME("name"),
	EMAIL("email"),
	ROLE("type"),
	USERNAME("username");

	private String val;
	private JWTClaimField(String val) {
		this.val=val;
	}
	
	public String val(){
		return val;
	}
}
