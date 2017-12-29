package com.apptech.apps.easypark.security.config;

public enum Role {
	PUBLIC("PUBLIC","/","/public"),
	ADMIN("ROLE_ADMIN","/secure/**/**"),
	OWNER("ROLE_OWNER","/secure/owner/**"),
	AGENT("ROLE_AGENT","/secure/agent/**");
	
	private String role;
	private String[] urlNS;
	
	Role(String role,String...urlNS){
		this.role=role;
		this.urlNS=urlNS;
	}

	public String getRole() {
		return role;
	}

	public String[] getUrlNS() {
		return urlNS;
	}
	
}
