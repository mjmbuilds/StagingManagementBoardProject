package com.staging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staging.model.User;
import com.staging.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
	
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	
	@RequestMapping("login")
	public User getUserByUsernameAndPassword(@RequestBody User user) {
		return userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	/*
	@RequestMapping("login")
	public User getUserByUsernameAndPassword(@RequestBody String user) {
		
		System.out.println("--------------------------------------------");
		System.out.println(user);
		
		return null;
	}
	*/
}
