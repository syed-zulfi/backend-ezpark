package com.apptech.apps.easypark.services.infc;

import com.apptech.apps.easypark.controllers.vo.ResponseDTO;
import com.apptech.apps.easypark.controllers.vo.UserDTO;

public interface UserService {

	ResponseDTO loginUser(String uname, String pwd);
	ResponseDTO editUserDetails(UserDTO user);
	ResponseDTO deleteUser(UserDTO user);
	ResponseDTO validateFiled(String filed, String type);
	ResponseDTO register(UserDTO user);
	ResponseDTO login(String uid, String pwd);
}
