package com.staging;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(ManagementBoardBack.class)
class ManagementBoardBackTests {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testCheckForMessage() throws Exception {
		
		String expectedMessage = ManagementBoardBack.getMessage();
		
		mockMvc = MockMvcBuilders.standaloneSetup(new ManagementBoardBack()).build();
		
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(content().string(expectedMessage));
	}

}
