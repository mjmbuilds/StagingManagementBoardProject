package com.staging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.data.UserDao;
import com.staging.model.CodeMessage;
import com.staging.model.User;

@Service
public class UserService extends GenericService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final UserDao userDao;
	
	@Autowired // daoQualifier is specified in GenericService
	public UserService(@Qualifier(daoQualifier) UserDao userDao) {
		this.userDao = userDao;
	}
	
	public CodeMessage addUser(User user) {
		log.trace("addUser()");
		return userDao.addUser(user);
	}
	
	public CodeMessage updateUserInfo(User user) {
		log.trace("updateUserInfo()");
		return userDao.updateUserInfo(user);
	}
	
	public CodeMessage updateUserPass(User user) {
		log.trace("updateUserPass()");
		return userDao.updateUserPass(user);
	}
	
	public CodeMessage deleteUser(String id) {
		log.trace("deleteUser()");
		return userDao.deleteUser(id);
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("getUserByUsernameAndPassword()");
		return userDao.getUserByUsernameAndPassword(username, password);
	}

}
