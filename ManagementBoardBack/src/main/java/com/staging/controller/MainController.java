package com.staging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staging.model.Board;
import com.staging.model.Card;
import com.staging.model.Category;
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
	private final UserService userService;
	private final BoardService boardService;
	private final CategoryService categoryService;
	private final CardService cardService;

	@Autowired
	public MainController(UserService us, BoardService bs, CategoryService cts, CardService cds) {
		this.userService = us;
		this.boardService = bs;
		this.categoryService = cts;
		this.cardService = cds;
	}

	//-------------------------------------------- User 
	@PostMapping("user/signup")
	public void addUser(@RequestBody User user) {
		log.trace("\n");
		log.trace("addUser()");
		log.info("POST Request to '/api/user/signup' ");
		userService.addUser(user);
	}
	
	@PutMapping("user/update")
	public void updateUser(@RequestBody User user) {
		log.trace("\n");
		log.trace("updateUser()");
		log.info("PUT Request to '/api/user/update' ");
		userService.updateUser(user);
	}
	
	@DeleteMapping("user/remove")
	public void deleteUser(@RequestBody String id) {
		log.trace("\n");
		log.trace("deleteUser()");
		log.info("DELETE Request to '/api/user/remove' ");
		userService.deleteUser(id);
	}
	
	@PostMapping("user/login")
	public User getUserByUsernameAndPassword(@RequestBody User user) {
		log.trace("\n");
		log.trace("getUserByUsernameAndPassword()");
		log.info("POST Request to '/api/user/login' ");
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

	//-------------------------------------------- Board
	@PostMapping("board/add")
	public void addBoard(@RequestBody Board board) {
		log.trace("\n");
		log.trace("addBoard()");
		log.info("POST Request to '/api/board/add' ");
		boardService.addBoard(board);
	}
	
	@PutMapping("board/update")
	public void updateBoard(@RequestBody Board board) {
		log.trace("\n");
		log.trace("updateBoard()");
		log.info("PUT Request to '/api/board/update' ");
		boardService.updateBoard(board);
	}
	
	@DeleteMapping("board/remove")
	public void deleteBoard(@RequestBody String id) {
		log.trace("\n");
		log.trace("deleteBoard()");
		log.info("DELETE Request to '/api/board/remove' ");
		boardService.deleteBoard(id);
	}
	
	//-------------------------------------------- Category
	@PostMapping("category/add")
	public void addCategory(@RequestBody Category category) {
		log.trace("\n");
		log.trace("addCategory()");
		log.info("POST Request to '/api/category/add' ");
		categoryService.addCategory(category);
	}
	
	@PutMapping("category/update")
	public void updateCategory(@RequestBody Category category) {
		log.trace("\n");
		log.trace("updateCategory()");
		log.info("PUT Request to '/api/category/update' ");
		categoryService.updateCategoryd(category);
	}
	
	@DeleteMapping("category/remove")
	public void deleteCategory(@RequestBody String id) {
		log.trace("\n");
		log.trace("deleteCategory()");
		log.info("DELETE Request to '/api/category/remove' ");
		categoryService.deleteCategory(id);
	}
	
	//-------------------------------------------- Card
	@PostMapping("card/add")
	public void addCard(@RequestBody Card card) {
		log.trace("\n");
		log.trace("addCard()");
		log.info("POST Request to '/api/card/add' ");
		cardService.addCard(card);
	}
	
	@PutMapping("card/update")
	public void updateCard(@RequestBody Card card) {
		log.trace("\n");
		log.trace("updateCard()");
		log.info("PUT Request to '/api/card/update' ");
		cardService.updateCard(card);
	}
	
	@DeleteMapping("card/remove")
	public void deleteCard(@RequestBody String id) {
		log.trace("\n");
		log.trace("deleteCard()");
		log.info("DELETE Request to '/api/card/remove' ");
		cardService.deleteCard(id);
	}
	
}
