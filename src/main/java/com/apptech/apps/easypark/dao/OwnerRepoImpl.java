package com.apptech.apps.easypark.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.apptech.apps.easypark.constants.Fields;
import com.apptech.apps.easypark.constants.Status;
import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.dao.infc.OwnerRepo;
import com.apptech.apps.easypark.dao.infc.UserRepo;
import com.apptech.apps.easypark.exceptions.ApplicationException;
import com.apptech.apps.easypark.exceptions.UserExistException;

@Repository
@Transactional
public class OwnerRepoImpl implements  OwnerRepo{

	@PersistenceContext
	EntityManager eManager;



	public User loadUserByUserId(String userid) throws ApplicationException {
		String status = Status.ACTIVE.toString();
		List<?> resultList = eManager
				.createQuery("SELECT u FROM User u where u.username=? and u.status=?")
				.setParameter(1, userid).setParameter(2, status).getResultList();
		if (!resultList.isEmpty()) {
			return (User) resultList.get(0);
		} else {
			throw new ApplicationException(
					"User not found with provided user id", null);
		}

	}

	@Override
	public Set<User> listAgents(long ownerId) throws ApplicationException {
		User owner= eManager.find(User.class, ownerId) ;
		if (null!=owner) {
			return owner.getAgents();
		} else {
			throw new ApplicationException(
					"Owner not loaded or not found", null);
		}

	}

	@Override
	public boolean removeAgent(long agentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getAgentById(long agentId) throws ApplicationException {
		String status = Status.ACTIVE.toString();
		List<?> resultList = eManager
				.createQuery("SELECT u FROM User u where u.username=? and u.status=?")
				.setParameter(1, agentId).setParameter(2, status).getResultList();
		if (!resultList.isEmpty()) {
			return (User) resultList.get(0);
		} else {
			throw new ApplicationException(
					"User not found with provided user id", null);
		}
	}

	@Override
	public User loadOwnerByRecId(long id) throws ApplicationException {
		String status = Status.ACTIVE.toString();
		List<?> resultList = eManager
				.createQuery("SELECT u FROM User u where u.id=? and u.status=?")
				.setParameter(1, id).setParameter(2, status).getResultList();
		if (!resultList.isEmpty()) {
			return (User) resultList.get(0);
		} else {
			throw new ApplicationException(
					"User not found with provided id or user is not yet enabled", null);
		}
	}

}
