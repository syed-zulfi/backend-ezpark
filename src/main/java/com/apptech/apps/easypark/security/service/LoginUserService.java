package com.apptech.apps.easypark.security.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.dao.infc.UserRepo;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.security.config.TKNClaims;

@Service("loginUserService")
public class LoginUserService implements UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(LoginUserService.class);

	private UserRepo userRepo;
	private User user;

	@Autowired
	@Qualifier("userRepoImpl")
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
		try {
			user = userRepo.loadUserByUserId(userid);
		} catch (ApplicationException e) {
			throw new UsernameNotFoundException("User not found");
		}
		return user.createUserSecurityDetails();
	}

	/**
	 * 
	 * @return
	 */

	public Map<String, String> getPublicDetails() {
		Map<String, String> userDetails = new HashMap<String, String>();
		userDetails.put(TKNClaims.ID.val(), user.getId().toString());
		userDetails.put(TKNClaims.NAME.val(), user.getFullName());
		userDetails.put(TKNClaims.EMAIL.val(), user.getEmail());
		userDetails.put(TKNClaims.ROLE.val(), user.getRole());
		userDetails.put(TKNClaims.USERNAME.val(), user.getUsername());
		user = null;
		return userDetails;

	}

}
