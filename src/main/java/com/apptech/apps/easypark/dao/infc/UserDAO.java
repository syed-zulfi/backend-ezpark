package com.apptech.apps.easypark.dao.infc;

import java.util.List;

import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.exceptions.UserExistException;

public interface UserDAO {
	
   User createNewUser(User user) throws ApplicationException;
   User loadUserByUserId(String userid);
   User loadUserByEmail(String emailid);
   List<User> loadUsers();
   void emailExist(String email) throws UserExistException;
   void userIdExists(String userid) throws UserExistException;;

}
