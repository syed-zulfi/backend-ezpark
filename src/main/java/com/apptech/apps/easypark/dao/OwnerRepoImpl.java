package com.apptech.apps.easypark.dao;

import java.util.List;

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
	public List<User> listAgents(long ownerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAgent(long agentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getAgentById(long agentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
