package com.staging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ManagementBoardBack {

	public static void main(String[] args) {
		SpringApplication.run(ManagementBoardBack.class, args);
	}

	@RequestMapping("/")
	@CrossOrigin
	public String index() {
	    return "Management Board backend is running!";
	}

}

