package com.apptech.apps.easypark.constants;

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
