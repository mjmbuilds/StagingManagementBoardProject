package com.staging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staging.model.User;
import com.staging.service.UserService;

@RestController
@RequestMapping("/api/debug")
@CrossOrigin
public class DebugController {
	
	private final UserService userService;

	@Autowired
	public DebugController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("all-users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	
}
