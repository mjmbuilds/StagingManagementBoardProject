package com.staging.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staging.model.User;
import com.staging.service.DebugService;

@RestController
@RequestMapping("/api/debug")
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
public class DebugController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final DebugService debugService;

	@Autowired
	public DebugController(DebugService debugService) {
		this.debugService = debugService;
	}
	
	// prints the request data to the log
	@RequestMapping()
	public void debug(@RequestBody String data) {
		log.trace("\n");
		log.trace("debug()");
		log.info("Request to '/api/debug' ");
		log.debug("Data from Request:\n" + data);
	}
	
	// drop and recreate tables
	@RequestMapping("reset-db")
	public void resetDB() {
		log.trace("\n");
		log.trace("resetDB()");
		log.info("Request to '/api/debug/reset-db' ");
		debugService.resetDB();
	}
	
	// initializes sample user
	@RequestMapping("init-sample-user")
	public void initSampleUser() {
		log.trace("\n");
		log.trace("initSampleUser()");
		log.info("Request to '/api/debug/init-sample-user' ");
		debugService.initSampleUser();
	}
	
	// returns data for all users
	@GetMapping("all-users")
	public List<User> getAllUsers() {
		log.trace("\n");
		log.trace("getAllUsers()");
		log.info("GET Request to '/api/debug/all-users' ");
		return debugService.getAllUsers();
	}
	
}
