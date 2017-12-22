package com.apptech.apps.easypark.dao.infc;

import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.exceptions.UserExistException;

public interface UserRepo {

	void createNewUser(User user) throws ApplicationException;

	User loadUserByUserId(String userid) throws ApplicationException;

	void emailExist(String email) throws UserExistException;

	void userIdExists(String userid) throws UserExistException;;
}
