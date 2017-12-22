package com.apptech.apps.easypark.constants;

public enum Fields {
	EMAIL("email"), USERID("userid");

	String type;

	Fields(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
