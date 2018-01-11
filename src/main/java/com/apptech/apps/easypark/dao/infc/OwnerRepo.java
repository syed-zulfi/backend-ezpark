package com.apptech.apps.easypark.dao.infc;

import java.util.List;
import java.util.Set;

import com.apptech.apps.easypark.dao.entity.User;
import com.apptech.apps.easypark.exceptions.ApplicationException;

public interface OwnerRepo {
	User loadOwnerByRecId(long id) throws ApplicationException;
	Set<User> listAgents(long ownerId) throws ApplicationException;
	boolean removeAgent(long agentId);
	User getAgentById(long agentId) throws ApplicationException;

}
