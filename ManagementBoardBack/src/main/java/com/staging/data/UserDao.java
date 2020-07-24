package com.staging.data;

import java.util.UUID;

import com.staging.model.User;

public interface UserDao {
	
	int addUser(User user);
	
	int updateUser(User user);
	
	int deleteUser(UUID id);
	
	User getUserByUsernameAndPassword(String username, String password);
	
}
