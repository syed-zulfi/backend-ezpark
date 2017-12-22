package com.apptech.apps.easypark.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/owner")
public class OwnerOperationControler {
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ResponseEntity<?> login(@RequestParam String userId,
			@RequestParam String passCode, UriComponentsBuilder builder) {
		System.out.println("Welcome owner");
		return null;
	}
}
