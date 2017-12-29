package com.apptech.apps.easypark.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apptech.apps.easypark.constants.ReturnCode;
import com.apptech.apps.easypark.controllers.vo.ResponseDTO;
import com.apptech.apps.easypark.dao.infc.UserRepo;
import com.apptech.apps.easypark.services.infc.OwnerService;
import com.apptech.apps.easypark.util.ResponseUtil;

@Component
public class OwnerServiceImpl implements OwnerService {

	private static final Logger log = LoggerFactory.getLogger(OwnerServiceImpl.class);

	private UserRepo userRepo;

	@Autowired
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public ResponseDTO listAgents() {
		ResponseDTO rDTO = ResponseUtil.createResponseDTO(ReturnCode.SUCCESS, null, false);
		return rDTO;
	}

}
