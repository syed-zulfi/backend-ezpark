package com.apptech.apps.easypark.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.apptech.apps.easypark.constants.Fields;
import com.apptech.apps.easypark.constants.Status;
import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.dao.infc.UserRepo;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.exceptions.UserExistException;

@Repository
@Transactional
public class UserRepoImpl implements UserRepo {

	@PersistenceContext
	EntityManager eManager;

	@Override
	public void createNewUser(User user) throws ApplicationException {
		try{
		eManager.persist(user);
		}catch(Exception e){
			throw new ApplicationException(ApplicationException.REG_FAIL, e);
		}
	}

	@Override
	public User loadUserByUserId(String userid) throws ApplicationException {
		String status = Status.ACTIVE.toString();
		System.out.println("ISer idddddddd" + userid);
		List<?> resultList = eManager
				.createQuery("SELECT u FROM User u where u.username=? and u.status=?")
				.setParameter(1, userid).setParameter(2, status).getResultList();
		if (!resultList.isEmpty()) {
			System.out.println("USer object"+resultList.get(0).toString());
			return (User) resultList.get(0);
		} else {
			throw new ApplicationException(
					"User not found with provided user id", null);
		}

	}

	@Override
	public void emailExist(String email) throws UserExistException {
		@SuppressWarnings("unchecked")
		List<Long> resultList = eManager
				.createQuery("SELECT COUNT(*) FROM User u where u.email=?")
				.setParameter(1, email).getResultList();
		if (resultList.get(0) > 0L) {
			throw new UserExistException(UserExistException.EMAIL_EXIST,
					Fields.EMAIL);
		}
	}

	@Override
	public void userIdExists(String userid) throws UserExistException {
		@SuppressWarnings("unchecked")
		List<Long> resultList = eManager
				.createQuery("SELECT COUNT(*) FROM User u where u.username=?")
				.setParameter(1, userid).getResultList();
		if (resultList.get(0) > 0L) {
			throw new UserExistException(UserExistException.USERID_EXIST,
					Fields.USERID);
		}
	}

}
