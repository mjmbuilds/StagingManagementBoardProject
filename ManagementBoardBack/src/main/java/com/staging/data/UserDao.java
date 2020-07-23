package com.staging.data;

import java.util.List;

import com.staging.model.User;

public interface UserDao {
	
	/** 
	 * Adds a user.
	 * @param user The user to be added.
	 * @return 0 if success. -1 if add failed.
	 */
	int addUser(User user);
	
	User getUserByUsernameAndPassword(String username, String password);
	
	List<User> getAllUsers();
	
}
