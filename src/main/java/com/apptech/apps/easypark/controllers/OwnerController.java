package com.apptech.apps.easypark.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.apptech.apps.easypark.controllers.vo.ResponseDTO;
import com.apptech.apps.easypark.services.infc.OwnerServices;
import com.apptech.apps.easypark.util.ResponseUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("secure/owner")
public class OwnerController {
	private OwnerServices ownerService;

	@Autowired
	@Qualifier("ownerServiceImpl")
	public void setOwnerService(OwnerServices ownerService) {
		this.ownerService = ownerService;
	}

	@RequestMapping(value = "/list-agents", method = RequestMethod.GET)
	public ResponseEntity<?> login(@RequestParam String ownerId, UriComponentsBuilder builder) {
		ResponseDTO rDTO = ownerService.listAgents();
		return ResponseUtil.buildResponse(rDTO);
	}
}
