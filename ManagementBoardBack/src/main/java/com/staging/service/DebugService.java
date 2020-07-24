package com.staging.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.staging.data.DebugDao;
import com.staging.model.User;

@Service
public class DebugService extends GenericService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final DebugDao debugDao;
	
	@Autowired // daoQualifier is specified in GenericService
	public DebugService(@Qualifier(daoQualifier) DebugDao debugDao) {
		this.debugDao = debugDao;

	}

	// DEBUG method for dropping and recreating tables
	// not intended for public API
	public void resetDB() {
		log.trace("resetDB()");
		debugDao.resetDB();
	}
	
	// DEBUG method for initializing sample user
	// not intended for public API
	public void initSampleUser() {
		log.trace("initSampleUser()");
		debugDao.initSampleUser();
	}
	
	// DEBUG method for getting all users
	// not intended for public API
	public List<User> getAllUsers() {
		log.trace("getAllUsers()");
		return debugDao.getAllUsers();
	}
	
}
