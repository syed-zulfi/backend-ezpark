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
			userDto.setUserType(Role.ROLE_OWNER.name());
			break;
		case ADMIN:
			userDto.setUserType(Role.ROLE_ADMIN.name());
			break;
		case AGENT:
			userDto.setUserType(Role.ROLE_AGENT.name());
			break;
		}
		return userDto;
	}

}
