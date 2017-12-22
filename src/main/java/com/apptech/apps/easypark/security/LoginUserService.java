package com.apptech.apps.easypark.security;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apptech.apps.easypark.constants.JWTClaimField;
import com.apptech.apps.easypark.controllers.vo.UserDTO;
import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.dao.infc.UserRepo;
import com.apptech.apps.easypark.exceptions.ApplicationException;

@Service("loginUserService")
public class LoginUserService implements UserDetailsService {

	private UserRepo userRepo;
	private User user;

	@Autowired
	@Qualifier("userRepoImpl")
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String userid)
			throws UsernameNotFoundException {
		try {
			System.out.println("User id"+userid);
			user = userRepo.loadUserByUserId(userid);

		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user.createUserSecurityDetails();
	}
	
	/**
	 * 
	 * @return
	 */

	public Map<String, String> getPublicDetails() {
		Map<String, String> userDetails = new HashMap<String, String>();
		userDetails.put(JWTClaimField.ID.val(), user.getId().toString());
		userDetails.put(JWTClaimField.NAME.val(), user.getFullName());
		userDetails.put(JWTClaimField.EMAIL.val(), user.getEmail());
		userDetails.put(JWTClaimField.ROLE.val(), user.getRole());
		userDetails.put(JWTClaimField.USERNAME.val(), user.getUsername());
		user=null;
		return userDetails;

	}

}
