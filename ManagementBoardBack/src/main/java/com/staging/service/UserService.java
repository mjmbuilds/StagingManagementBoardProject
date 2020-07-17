package com.staging.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.dao.UserDao;
import com.staging.model.User;

@Service
public class UserService {
	
	private final UserDao userDao;
	
	@Autowired
	public UserService(@Qualifier("fakeDao") UserDao userDao) {
		this.userDao = userDao;
	}
	
	// for adding a new user when they sign up
	public int addUser(User user) {
		return userDao.addUser(user);
	}
	
	// for getting a user by user-name and password
	public User getUserByUsernameAndPassword(String username, String password) {
		return userDao.getUserByUsernameAndPassword(username, password);
	}
	
	// DEBUG method for getting all users
	// not intended for public API
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}
}
