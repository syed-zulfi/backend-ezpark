package com.apptech.apps.easypark.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.apptech.apps.easypark.security.factory.FilterFactory;
import com.apptech.apps.easypark.security.factory.FilterFactory.Filters;
import com.apptech.apps.easypark.security.service.LoginUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private LoginUserService loginUserService;

	@Autowired
	@Qualifier("loginUserService")
	public void setLoginUserService(LoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}

	private Settings jwt;

	@Autowired
	public void setJwt(Settings jwt) {
		this.jwt = jwt;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/public/*").permitAll();
		http.authorizeRequests().antMatchers("/owner/**").hasAuthority(Role.ROLE_OWNER.name());
		http.authorizeRequests().antMatchers("/admin/**").hasAuthority(Role.ROLE_ADMIN.name());
		http.authorizeRequests().antMatchers("/agent/**").hasAuthority(Role.ROLE_AGENT.name()).and()
				.addFilter(FilterFactory.createFilter(Filters.AUTHENTICATE, authenticationManager(), jwt,
						loginUserService))
				.addFilter(FilterFactory.createFilter(Filters.AUTHORISE, authenticationManager(), jwt, null));
		http.csrf().disable();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
