package com.staging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staging.model.Board;
import com.staging.model.Card;
import com.staging.model.Category;
import com.staging.model.CodeMessage;
import com.staging.model.IndexList;
import com.staging.model.User;
import com.staging.service.BoardService;
import com.staging.service.CardService;
import com.staging.service.CategoryService;
import com.staging.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200", allowCredentials="true")
public class MainController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private UserService userService;
	private BoardService boardService;
	private CategoryService categoryService;
	private CardService cardService;

	@Autowired
	public MainController(UserService us, BoardService bs, CategoryService cts, CardService cds) {
		this.userService = us;
		this.boardService = bs;
		this.categoryService = cts;
		this.cardService = cds;
	}

	//-------------------------------------------- Check Response
	@GetMapping("/hello")
	public String checkResponse() {
		return "Hello from Main Controller";
	}
	
	//-------------------------------------------- User 
	@PostMapping("user/signup")
	public CodeMessage addUser(@RequestBody User user) {
		log.trace("\n");
		log.trace("addUser()");
		log.info("POST Request to '/api/user/signup' ");
		return userService.addUser(user);
	}
	
	@PutMapping("user/update")
	public CodeMessage updateUserInfo(@RequestBody User user) {
		log.trace("\n");
		log.trace("updateUserInfo()");
		log.info("PUT Request to '/api/user/update' ");
		return userService.updateUserInfo(user);
	}
	
	@PutMapping("user/updatepass")
	public CodeMessage updateUserPass(@RequestBody User user) {
		log.trace("\n");
		log.trace("updateUserPass()");
		log.info("PUT Request to '/api/user/updatepass' ");
		return userService.updateUserPass(user);
	}
	
	@DeleteMapping("user/remove/{id}")
	public CodeMessage deleteUser(@PathVariable("id") String id) {
		log.trace("\n");
		log.trace("deleteUser()");
		log.info("DELETE Request to '/api/user/remove' ");
		return userService.deleteUser(id);
	}
	
	@PostMapping("user/login")
	public User getUserByUsernameAndPassword(@RequestBody User user) {
		log.trace("\n");
		log.trace("getUserByUsernameAndPassword()");
		log.info("POST Request to '/api/user/login' ");
		return userService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword());
	}

	//-------------------------------------------- Board
	@PostMapping("board/add")
	public CodeMessage addBoard(@RequestBody Board board) {
		log.trace("\n");
		log.trace("addBoard()");
		log.info("POST Request to '/api/board/add' ");
		return boardService.addBoard(board);
	}
	
	@GetMapping("board/{id}")
	public Board getBoard(@PathVariable("id") String id) {
		log.trace("\n");
		log.trace("getBoard()");
		log.info("GET Request to '/api/board' ");
		return boardService.getBoard(id);
	}
	
	@PutMapping("board/update")
	public CodeMessage updateBoard(@RequestBody Board board) {
		log.trace("\n");
		log.trace("updateBoard()");
		log.info("PUT Request to '/api/board/update' ");
		return boardService.updateBoard(board);
	}
	
	@DeleteMapping("board/remove/{id}")
	public CodeMessage deleteBoard(@PathVariable("id") String id) {
		log.trace("\n");
		log.trace("deleteBoard()");
		log.info("DELETE Request to '/api/board/remove' ");
		return boardService.deleteBoard(id);
	}
	
	//-------------------------------------------- Category
	
	/*
	@PutMapping("category/update-index-list")
	public CodeMessage updateCategoryIndexList(@RequestBody String data) {
		log.trace("\n");
		log.trace("updateCategoryIndexList()");
		log.info("PUT Request to '/api/category/update-index-list' ");
		log.trace("debug()");
		log.debug("Data from Request:\n" + data);
		return new CodeMessage();
	}*/
	
	@PutMapping("category/update-index-list")
	public CodeMessage updateCategoryIndexList(@RequestBody IndexList indexList) {
		log.trace("\n");
		log.trace("updateCategoryIndexList()");
		log.info("PUT Request to '/api/category/update-index-list' ");
		return categoryService.updateCategoryIndexList(indexList);
	}
	
	@PostMapping("category/add")
	public CodeMessage addCategory(@RequestBody Category category) {
		log.trace("\n");
		log.trace("addCategory()");
		log.info("POST Request to '/api/category/add' ");
		return categoryService.addCategory(category);
	}
	
	@GetMapping("category/{id}")
	public Category getCategory(@PathVariable("id") String id) {
		log.trace("\n");
		log.trace("getCategory()");
		log.info("GET Request to '/api/category' ");
		return categoryService.getCategory(id);
	}
	
	@PutMapping("category/update")
	public CodeMessage updateCategory(@RequestBody Category category) {
		log.trace("\n");
		log.trace("updateCategory()");
		log.info("PUT Request to '/api/category/update' ");
		return categoryService.updateCategoryd(category);
	}
	
	@DeleteMapping("category/remove/{id}")
	public CodeMessage deleteCategory(@PathVariable("id") String id) {
		log.trace("\n");
		log.trace("deleteCategory()");
		log.info("DELETE Request to '/api/category/remove' ");
		return categoryService.deleteCategory(id);
	}
	
	//-------------------------------------------- Card
	
	@PutMapping("card/update-index-list")
	public CodeMessage updateCardIndexList(@RequestBody IndexList indexList) {
		log.trace("\n");
		log.trace("updateCardIndexList()");
		log.info("PUT Request to '/api/category/update-index-list' ");
		return cardService.updateCardIndexList(indexList);
	}
	
	@PostMapping("card/add")
	public CodeMessage addCard(@RequestBody Card card) {
		log.trace("\n");
		log.trace("addCard()");
		log.info("POST Request to '/api/card/add' ");
		return cardService.addCard(card);
	}
	
	@GetMapping("card/{id}")
	public Card getCard(@PathVariable("id") String id) {
		log.trace("\n");
		log.trace("getCard()");
		log.info("GET Request to '/api/card' ");
		return cardService.getCard(id);
	}
	
	@PutMapping("card/update")
	public CodeMessage updateCard(@RequestBody Card card) {
		log.trace("\n");
		log.trace("updateCard()");
		log.info("PUT Request to '/api/card/update' ");
		return cardService.updateCard(card);
	}
	
	@DeleteMapping("card/remove/{id}")
	public CodeMessage deleteCard(@PathVariable("id") String id) {
		log.trace("\n");
		log.trace("deleteCard()");
		log.info("DELETE Request to '/api/card/remove' ");
		return cardService.deleteCard(id);
	}
	
}
