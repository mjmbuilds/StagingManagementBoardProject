package com.staging.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.data.UserDao;
import com.staging.model.User;

@Service
public class UserService extends GenericService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final UserDao userDao;
	
	@Autowired // daoQualifier is specified in GenericService
	public UserService(@Qualifier(daoQualifier) UserDao userDao) {
		this.userDao = userDao;
	}
	
	public int addUser(User user) {
		log.trace("addUser()");
		return userDao.addUser(user);
	}
	
	public int updateUser(User user) {
		log.trace("updateUser()");
		return userDao.updateUser(user);
	}
	
	public int deleteUser(UUID id) {
		log.trace("deleteUser()");
		return userDao.deleteUser(id);
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("getUserByUsernameAndPassword()");
		return userDao.getUserByUsernameAndPassword(username, password);
	}

}
