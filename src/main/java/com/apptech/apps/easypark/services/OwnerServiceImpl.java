package com.apptech.apps.easypark.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apptech.apps.easypark.constants.ReturnCode;
import com.apptech.apps.easypark.controllers.vo.ResponseDTO;
import com.apptech.apps.easypark.dao.infc.OwnerRepo;
import com.apptech.apps.easypark.security.config.Settings;
import com.apptech.apps.easypark.services.infc.OwnerServices;
import com.apptech.apps.easypark.util.ResponseUtil;

@Component
public class OwnerServiceImpl implements OwnerServices {

	private static final Logger log = LoggerFactory.getLogger(OwnerServiceImpl.class);
	private Settings jwt;

	@Autowired
	public void setJwt(Settings jwt) {
		this.jwt = jwt;
	}
	private OwnerRepo ownerRepo;

	@Autowired
	public void setOwnerRepo(OwnerRepo ownerRepo) {
		this.ownerRepo = ownerRepo;
	}
	@Override
	public ResponseDTO listAgents() {
		ResponseDTO rDTO = ResponseUtil.createResponseDTO(ReturnCode.SUCCESS, null, false);
		return rDTO;
	}

	

}
