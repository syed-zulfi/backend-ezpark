package com.apptech.apps.easypark.util;

import com.apptech.apps.easypark.constants.Role;
import com.apptech.apps.easypark.controllers.vo.UserDTO;

public class RequestUtil {

	final static String OWNER = "OWNER";
	final static String ADMIN = "ADMIN";
	final static String AGENT = "AGENT";

	public static UserDTO compileRole(UserDTO userDto, String role) {
		switch (role) {
		case OWNER:
			userDto.setRole(Role.ROLE_OWNER.name());
			break;
		case ADMIN:
			userDto.setRole(Role.ROLE_ADMIN.name());
			break;
		case AGENT:
			userDto.setRole(Role.ROLE_AGENT.name());
			break;
		}
		return userDto;
	}

}
