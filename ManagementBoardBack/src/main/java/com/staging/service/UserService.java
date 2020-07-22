package com.staging.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.dao.UserDao;
import com.staging.model.User;

@Service
public class UserService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final UserDao userDao;
	
	@Autowired
	public UserService(@Qualifier("fakeDao") UserDao userDao) {
		this.userDao = userDao;
	}
	
	// for adding a new user when they sign up
	public int addUser(User user) {
		log.trace("addUser()");
		return userDao.addUser(user);
	}
	
	// for getting a user by user-name and password
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("getUserByUsernameAndPassword()");
		return userDao.getUserByUsernameAndPassword(username, password);
	}
	
	// DEBUG method for getting all users
	// not intended for public API
	public List<User> getAllUsers() {
		log.trace("getAllUsers()");
		return userDao.getAllUsers();
	}
}
