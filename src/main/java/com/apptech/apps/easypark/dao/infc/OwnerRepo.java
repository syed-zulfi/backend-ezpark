package com.apptech.apps.easypark.dao.infc;

import java.util.List;

import com.apptech.apps.easypark.dao.entity.User;

public interface OwnerRepo {
	List<User> listAgents(long ownerId);
	boolean removeAgent(long agentId);
	User getAgentById(long agentId);

}
