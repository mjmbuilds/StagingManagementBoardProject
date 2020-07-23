package com.staging.data;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.staging.model.Board;
import com.staging.model.Card;
import com.staging.model.Category;
import com.staging.model.User;
import com.staging.util.HibernateUtil;

@Repository("HibernateDao")
public class HibernateDataImpl implements UserDao, BoardDao, CategoryDao, CardDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private HibernateUtil conn = HibernateUtil.getHibernateUtil();
	
	//-------------------------------------------- User DAO
	@Override
	public int addUser(User user) {
		log.trace("addUser()");
		log.info("adding user: " + user.getUsername());
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("getUserByUsernameAndPassword()");
		log.info("Searching for username: " + username + ", password: " + password);
		// TODO Auto-generated method stub
		log.info("user NOT found");
		return null;
	}
	
	@Override
	public List<User> getAllUsers() {
		log.trace("getAllUsers()");
		// TODO Auto-generated method stub
		return null;
	}

	//-------------------------------------------- Board DAO
	@Override
	public int addBoard(Board board) {
		log.trace("addBoard()");
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateBoard(Board board) {
		log.trace("updateBoard()");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(UUID id) {
		log.trace("deleteBoard()");
		// TODO Auto-generated method stub
		
	}
	
	//-------------------------------------------- Category DAO
	
	@Override
	public int addCategory(Category category) {
		log.trace("addCategory()");
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCategory(Category category) {
		log.trace("updateCategory()");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategory(UUID id) {
		log.trace("deleteCategory()");
		// TODO Auto-generated method stub
		
	}
	//-------------------------------------------- Card DAO
	@Override
	public int addCard(Card card) {
		log.trace("addCard()");
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCard(Card card) {
		log.trace("updateCard()");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCard(UUID id) {
		log.trace("deleteCard()");
		// TODO Auto-generated method stub
		
	}
}
