package com.staging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staging.model.User;
import com.staging.service.UserService;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("user/signup")
	public void addUser(@RequestBody User user) {
		log.trace("\n");
		log.trace("addUser()");
		log.info("POST Request to '/api/v1/user/signup' ");
		userService.addUser(user);
	}
	
	@PostMapping("user/login")
	public User getUserByUsernameAndPassword(@RequestBody User user) {
		log.trace("\n");
		log.trace("getUserByUsernameAndPassword()");
		log.info("POST Request to '/api/v1/user/login' ");
		return userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	// TODO TEST using response entity
	/*
	@PostMapping("user/login2")
	public ResponseEntity<User> getUserByUsernameAndPassword2(@RequestBody User user) {
		log.trace("\n");
		log.trace("getUserByUsernameAndPassword()");
		log.info("POST Request to '/api/v1/user/login' ");
		User u = userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
		if (u == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(u);
	}
	*/

}
