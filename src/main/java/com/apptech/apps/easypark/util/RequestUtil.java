package com.apptech.apps.easypark.util;

import com.apptech.apps.easypark.controllers.vo.UserDTO;
import com.apptech.apps.easypark.security.config.Role;

public class RequestUtil {

	final static String OWNER = "OWNER";
	final static String ADMIN = "ADMIN";
	final static String AGENT = "AGENT";

	public static UserDTO synchRole(UserDTO userDto) {
		switch (userDto.getUserType()) {
		case OWNER:
			userDto.setUserType(Role.OWNER.getRole());
			break;
		case ADMIN:
			userDto.setUserType(Role.ADMIN.getRole());
			break;
		case AGENT:
			userDto.setUserType(Role.AGENT.getRole());
			break;
		}
		return userDto;
	}

}
