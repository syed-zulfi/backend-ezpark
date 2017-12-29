package com.apptech.apps.easypark.security.factory;

import javax.servlet.Filter;

import org.springframework.security.authentication.AuthenticationManager;

import com.apptech.apps.easypark.security.config.Settings;
import com.apptech.apps.easypark.security.filters.AuthenticateFilter;
import com.apptech.apps.easypark.security.filters.AuthoriseFilter;
import com.apptech.apps.easypark.security.service.LoginUserService;

public final class FilterFactory {

	public static Filter createFilter(Filters type, AuthenticationManager authManager, Settings settings,
			LoginUserService loginUserService) {
		Filter filter = null;

		switch (type) {
		case AUTHENTICATE:
			filter = new AuthenticateFilter(authManager, settings, loginUserService);
			break;
		case AUTHORISE:
			filter = new AuthoriseFilter(authManager, settings);
			break;
		}
		return filter;
	}
	public enum Filters {
		AUTHORISE, 
		AUTHENTICATE;
	}

}
