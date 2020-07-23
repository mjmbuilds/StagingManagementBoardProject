package com.staging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ManagementBoardBack {

	private static final Logger log = LoggerFactory.getLogger(ManagementBoardBack.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ManagementBoardBack.class, args);
		log.trace("\n");
		log.trace("* * * * * * * * * * * * * * * * * * * * * * * *");
		log.trace("* * * * * * * * * * STARTUP * * * * * * * * * *");
	}

	@RequestMapping("/")
	@CrossOrigin
	public String index() {
		log.trace("index()");
		log.info("Request to '/' ");
	    return "Management Board backend is running!";
	}

}

