package com.apptech.apps.easypark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.apptech.apps.easypark.controllers.vo.ResponseDTO;
import com.apptech.apps.easypark.controllers.vo.UserDTO;
import com.apptech.apps.easypark.services.infc.UserService;
import com.apptech.apps.easypark.util.RequestUtil;
import com.apptech.apps.easypark.util.ResponseUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/public")
public class UserRegisterControler {

	private UserService userService;

	@Autowired
	@Qualifier("userServiceImp")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> create(
			@RequestBody UserDTO user, UriComponentsBuilder builder) {
		ResponseDTO rdto = userService.register(RequestUtil.compileRole(user,
				user.getUserType()));
		return ResponseUtil.buildResponse(rdto);
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> login(@RequestParam String userId,
			@RequestParam String passCode, UriComponentsBuilder builder) {
		System.out.println(userId + " " + passCode);

		return null;
	}

	@RequestMapping(value = "/validate/{field}", method = RequestMethod.GET)
	public ResponseEntity<?> validate(@PathVariable String field,
			@RequestParam String entry, UriComponentsBuilder builder) {
		ResponseDTO rdto = userService.validateFiled(entry, field);
		return ResponseUtil.buildResponse(rdto);

	}
}
