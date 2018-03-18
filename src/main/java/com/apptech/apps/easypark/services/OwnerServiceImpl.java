package com.apptech.apps.easypark.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apptech.apps.easypark.constants.ReturnCode;
import com.apptech.apps.easypark.controllers.vo.ResponseDTO;
import com.apptech.apps.easypark.controllers.vo.UserDTO;
import com.apptech.apps.easypark.dao.infc.OwnerRepo;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.security.config.Settings;
import com.apptech.apps.easypark.security.config.TKNClaims;
import com.apptech.apps.easypark.security.factory.SecurityUtil;
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
	public ResponseDTO getAgents(String token) {
		String id = (String) SecurityUtil.readFieldFromClaim(TKNClaims.ID, token, jwt);
		ResponseDTO rDTO = null;
		try {
			List<UserDTO> agents = UserDTO.buildResUserDTOs(ownerRepo.listAgents(Long.parseLong(id)));

			rDTO = ResponseUtil.createResponseDTO(ReturnCode.SUCCESS, agents, true);
		} catch (NumberFormatException e) {
			log.error("Number format error occured" + e);
			rDTO = ResponseUtil.createResponseDTO(ReturnCode.APPLICATION_ERROR, null, true);
			rDTO.createError();
			rDTO.getError().setErrorCode(ReturnCode.APPLICATION_ERROR.message());
			rDTO.getError().setErrorDescription(e.getMessage());
		} catch (ApplicationException e) {
			log.error("ERROR Occured during agent listing" + e);
			rDTO = ResponseUtil.createResponseDTO(ReturnCode.EMPTY_RECORD, null, true);
			rDTO.createError();
			rDTO.getError().setErrorCode(ReturnCode.EMPTY_RECORD.message());
			rDTO.getError().setErrorDescription(e.getMessage());
		}
		return rDTO;
	}

}
