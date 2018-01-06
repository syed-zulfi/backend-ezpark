package com.apptech.apps.easypark.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.apptech.apps.easypark.constants.Fields;
import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.dao.infc.UserDAO;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.exceptions.UserExistException;
import com.apptech.apps.easypark.util.HibernateUtil;

@Component
public class UserDAOImp implements UserDAO {

	@Override
	public User createNewUser(User user) throws ApplicationException {
		try {
			Session session = HibernateUtil.getHSessionFactory().openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			return user;
		} catch (Exception e) {
			throw new ApplicationException(ApplicationException.REG_FAIL, e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public User loadUserByUserId(String userid) {
		Session session = HibernateUtil.getHSessionFactory().openSession();
		Query query = session.createQuery("from User u where u.userid='"+ userid + "'");
		List<User> users = query.list();
		return users.get(0);
	}

	@Override
	public User loadUserByEmail(String emailid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> loadUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void emailExist(String email) throws UserExistException {
		Session session = HibernateUtil.getHSessionFactory().openSession();
		Query query = session
				.createQuery("select count(*) from User u where u.email='"
						+ email + "'");
		List<Long> result = query.list();
		if (result.get(0) > 0L) {
			result = null;
			session.clear();
			session.close();
			throw new UserExistException(UserExistException.EMAIL_EXIST, Fields.EMAIL);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void userIdExists(String userid) throws UserExistException {
		Session session = HibernateUtil.getHSessionFactory().openSession();
		Query query = session
				.createQuery("select count(*) from User u where u.userid='"
						+ userid + "'");
		List<Long> result = query.list();
		if (result.get(0) > 0L) {
			result = null;
			session.clear();
			session.close();
			throw new UserExistException(UserExistException.USERID_EXIST, Fields.USERID);
		}
	}

}
