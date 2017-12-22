package com.apptech.apps.easypark.security;

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

import com.apptech.apps.easypark.constants.Role;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

	private LoginUserService loginUserService;

	@Autowired
	@Qualifier("loginUserService")
	public void setLoginUserService(LoginUserService loginUserService) {
		this.loginUserService = loginUserService;
	}

	@Autowired
	@Qualifier("appAuthenticationEntry")
	private AppAuthenticationEntry appAuthenticationEntry;

	private JWTAuthSettings jwt;

	@Autowired
	public void setJwt(JWTAuthSettings jwt) {
		this.jwt = jwt;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/public/*").permitAll();
		http.authorizeRequests().antMatchers("/owner/**")
				.hasAuthority(Role.ROLE_OWNER.name());
		http.authorizeRequests().antMatchers("/admin/**")
				.hasAuthority(Role.ROLE_ADMIN.name());
		http.authorizeRequests()
				.antMatchers("/agent/**")
				.hasAuthority(Role.ROLE_AGENT.name())
				.and()
				.addFilter(
						new JWTAuthenticateFilter(authenticationManager(), jwt,
								loginUserService))
				.addFilter(new JWTAuthoriseFilter(authenticationManager()));
		http.csrf().disable();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginUserService).passwordEncoder(
				new BCryptPasswordEncoder());
	}
}
