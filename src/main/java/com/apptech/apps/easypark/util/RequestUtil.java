package com.apptech.apps.easypark.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.apptech.apps.easypark.controllers.vo.UserDTO;
import com.apptech.apps.easypark.exceptions.AccessDeniedException;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.security.config.Role;
import com.apptech.apps.easypark.security.factory.SecurityUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RequestUtil {

	final static String OWNER = "OWNER";
	final static String ADMIN = "ADMIN";
	final static String AGENT = "AGENT";

	public static UserDTO synchRole(UserDTO userDto) throws ApplicationException, AccessDeniedException {
		switch (userDto.getUserType()) {
		case OWNER:
			userDto.setUserType(Role.OWNER.getRole());
			break;
		case ADMIN:
			userDto.setUserType(Role.ADMIN.getRole());
			break;
		case AGENT:
			SecurityContextHolder.getContext().getAuthentication().getAuthorities();
			List<String> auths = SecurityUtil
					.rolesToStringList(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
			if (auths.contains(Role.ADMIN.getRole()) || auths.contains(Role.OWNER.getRole())) {
				if (!StringUtils.isEmpty(userDto.getOwnerID()) || StringUtils.isNotBlank(userDto.getOwnerID())) {
					userDto.setUserType(Role.AGENT.getRole());
				} else {
					throw new ApplicationException("Owner Id not found for agent");
				}
			} else {
				throw new AccessDeniedException("Agents can only be registered by either owner or admin");
			}

			break;
	   default:
		   throw new ApplicationException("Invalid role identified.. \n Possible roles accepted [OWNER,AGENT,ADMIN] ");
		   
		}
		 
		return userDto;
	}

	public static String extractUserType(InputStream payload)
			throws JsonParseException, JsonMappingException, IOException {
		UserDTO user = new ObjectMapper().readValue(payload, UserDTO.class);
		return user.getUserType();
	}

}
