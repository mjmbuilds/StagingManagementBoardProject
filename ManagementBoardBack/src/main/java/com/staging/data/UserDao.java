package com.staging.data;

import com.staging.model.CodeMessage;
import com.staging.model.User;

public interface UserDao {
	
	CodeMessage addUser(User user);
	
	CodeMessage updateUserInfo(User user);
	
	CodeMessage updateUserPass(User user);
	
	CodeMessage deleteUser(String id);
	
	User getUserByUsernameAndPassword(String username, String password);
	
}
