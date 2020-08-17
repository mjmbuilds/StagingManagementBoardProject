package com.staging.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.staging.model.CodeMessage;
import com.staging.model.User;
import com.staging.service.BoardService;
import com.staging.service.CardService;
import com.staging.service.CategoryService;
import com.staging.service.UserService;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
class MainControllerTest{

	String urlBase = "http://localhost:8080/api";
	
	MainController mainController;
	MockMvc mockMvc;
	
	@Mock
	UserService userServMock;
	
	@Mock
	BoardService boardServMock;
	
	@Mock
	CategoryService categoryServMock;
	
	@Mock
	CardService cardServMock;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mainController = new MainController(userServMock, boardServMock, categoryServMock, cardServMock);
		mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
	}
	
	//-------------------------------------------- Check Response
	
	@Test
	void checkResponseGetTest() throws Exception {
		String url = urlBase + "/hello";
		mockMvc.perform(get(url))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello from Main Controller"));
	}
	
	//-------------------------------------------- User 
	
	@Test
	void addUserPostTest() throws Exception {
		
		when(userServMock.addUser(any(User.class))).thenReturn(new CodeMessage("12345"));
		
		String url = urlBase + "/user/signup";
		String body = "{\"firstName\": \"David\", \"lastName\": \"Smith\", \"username\": \"dsmith\", \"password\": \"d123\"}";
		mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(body))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.message").value("12345"));
	}
	
	//@Test
	void updateUserInfo_PutTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void updatepass_PutTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void deleteUser_DeleteTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void getUserByUsernameAndPassword_PostTest() {
		fail("Not yet implemented");
	}
	
	//-------------------------------------------- Board
	
	//@Test
	void addBoard_PostTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void getBoard_GetTest() throws Exception {
		/*
		String testID = "";
		
		mockMvc.perform(get("board/%s", testID))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
			//.andExpect(jsonPath("$.person.name").value("Jason"));
			 */
		fail("Not yet implemented");
	}
	
	//@Test
	void updateBoard_PutTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void deleteBoard_DeleteTest() {
		fail("Not yet implemented");
	}
	
	//-------------------------------------------- Category
	
	//@Test
	void updateCategoryIndexList_PutTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void addCategory_PostTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void getCategory_GetTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void updateCategory_PutTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void deleteCategory_DeleteTest() {
		fail("Not yet implemented");
	}
	
	//-------------------------------------------- Card
	
	//@Test
	void updateCardIndexList_PutTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void addCard_PostTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void getCard_GetTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void updateCard_PutTest() {
		fail("Not yet implemented");
	}
	
	//@Test
	void deleteCard_DeleteTest() {
		fail("Not yet implemented");
	}

	
}
