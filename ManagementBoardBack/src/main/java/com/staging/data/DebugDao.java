package com.staging.data;

import java.util.List;

import com.staging.model.User;

public interface DebugDao {
	
	void resetDB();
	
	void initSampleUser();
	
	List<User> getAllUsers();
	
}
