package com.apptech.apps.easypark.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.apptech.apps.easypark.constants.Fields;
import com.apptech.apps.easypark.constants.ReturnCode;
import com.apptech.apps.easypark.controllers.vo.ResponseDTO;
import com.apptech.apps.easypark.controllers.vo.UserDTO;
import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.dao.infc.UserDAO;
import com.apptech.apps.easypark.dao.infc.UserRepo;
import com.apptech.apps.easypark.exceptions.AccessDeniedException;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.exceptions.ObjectCreationException;
import com.apptech.apps.easypark.exceptions.UserExistException;
import com.apptech.apps.easypark.services.infc.UserService;
import com.apptech.apps.easypark.util.RequestUtil;
import com.apptech.apps.easypark.util.ResponseUtil;

@Component
public class UserServiceImp implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);
	private UserDAO userDao;
	private UserRepo userRepo;

	@Autowired
	@Qualifier("userDAOImp")
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	@Autowired
	@Qualifier("userRepoImpl")
	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public ResponseDTO register(UserDTO user) {
		ResponseDTO _resDto = null;
		try {
			user = RequestUtil.synchRole(user);
			userRepo.userIdExists(user.getUsername());
			log.info("User not found in the records, registering the user");
			userRepo.createNewUser(User.buildDomainObject(user));
			_resDto = ResponseUtil.createResponseDTO(ReturnCode.REGESTERED, user, false);
			log.info("User registered completed");

		} catch (UserExistException e) {
			_resDto = ResponseUtil.createResponseDTO(ReturnCode.DECLINED, user, true);
			_resDto.createError();
			_resDto.getError().setErrorCode(ReturnCode.DECLINED.message());
			log.error("User already available in the records hence registration process declined for this user" + e);
		} catch (ObjectCreationException | ApplicationException e) {
			log.error("Some error occured during registratoin process" + e);
			_resDto = ResponseUtil.createResponseDTO(ReturnCode.APPLICATION_ERROR, user, true);
			_resDto.createError();
			_resDto.getError().setErrorCode(ReturnCode.APPLICATION_ERROR.message());
			_resDto.getError().setErrorDescription(e.getMessage());
		} catch (AccessDeniedException e) {
			log.error("Some error occured during registratoin process" + e);
			_resDto = ResponseUtil.createResponseDTO(ReturnCode.FORBID_ACCESS, user, true);
			_resDto.createError();
			_resDto.getError().setErrorCode(ReturnCode.FORBID_ACCESS.message());
			_resDto.getError().setErrorDescription(e.getMessage());
		}
		return _resDto;
	}

	/*
	 * @Override public ResponseDTO registerUser(UserDTO user) { ResponseDTO
	 * _resDto = null; try { userDao.userIdExists(user.getUsername()); log.info(
	 * "User not found in the records, registering the user");
	 * userDao.createNewUser(User.buildDomainObject(user));
	 * user.setPassword(""); _resDto =
	 * ResponseUtil.createResponseDTO(ReturnCode.REGESTERED, user); log.info(
	 * "User registered witht he applciiation"); } catch
	 * (ObjectCreationException | ApplicationException e) { log.error(
	 * "Some error occured during registratoin process" + e); _resDto =
	 * ResponseUtil.createResponseDTO( ReturnCode.APPLICATION_ERROR, user);
	 * _resDto.createError(); _resDto.getError().setErrorCode(
	 * ReturnCode.APPLICATION_ERROR.message()); } catch (UserExistException e) {
	 * _resDto = ResponseUtil.createResponseDTO(ReturnCode.DECLINED, user);
	 * log.error(
	 * "User already available in the records hence registration process declined for this user"
	 * + e); } return _resDto; }
	 */
	@Override
	public ResponseDTO loginUser(String uname, String pwd) {
		ResponseDTO _resDto = ResponseDTO.create();
		return _resDto;

	}

	@Override
	public ResponseDTO editUserDetails(UserDTO user) {
		ResponseDTO _resDto = ResponseDTO.create();
		return _resDto;
	}

	@Override
	public ResponseDTO deleteUser(UserDTO user) {
		ResponseDTO _resDto = ResponseDTO.create();
		return _resDto;
	}

	/**
	 * 
	 */
	@Override
	public ResponseDTO validateFiled(String field, String type) {
		ResponseDTO rdto = null;
		try {
			if (type.equals(Fields.EMAIL.getType())) {
				// userDao.emailExist(field);
				userRepo.emailExist(field);
				rdto = ResponseUtil.createResponseDTO(ReturnCode.EMAIL_AVAILABLE, field, true);
			} else if (type.equals(Fields.USERID.getType())) {
				// userDao.userIdExists(field);
				userRepo.userIdExists(field);
				rdto = ResponseUtil.createResponseDTO(ReturnCode.UID_AVAILABLE, field, true);
			} else {
				rdto = ResponseUtil.createResponseDTO(ReturnCode.BAD_INPUT, type, true);
			}
		} catch (UserExistException e) {
			if (e.getField().equals(Fields.EMAIL)) {
				rdto = ResponseUtil.createResponseDTO(ReturnCode.EMAIL_DUPLICATE, field, true);
			} else {
				rdto = ResponseUtil.createResponseDTO(ReturnCode.UID_UNAVAILABLE, field, true);
			}
			e.printStackTrace();
		}
		return rdto;
	}

	@Override
	public ResponseDTO login(String uid, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * @Override public UserDetails loadUserByUsername(String userid) throws
	 * UsernameNotFoundException {
	 * 
	 * User user = userDao.loadUserByUserId(userid); return (UserDetails) user;
	 * }
	 */

}
