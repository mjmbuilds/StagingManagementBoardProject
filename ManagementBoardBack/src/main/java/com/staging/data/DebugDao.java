package com.staging.data;

import java.util.List;

import com.staging.model.User;

public interface DebugDao {
	
	int resetDB();
	
	int initSampleUser();
	
	List<User> getAllUsers();
	
}
