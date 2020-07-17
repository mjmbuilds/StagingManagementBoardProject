package com.staging.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.staging.model.Board;
import com.staging.model.Card;
import com.staging.model.Category;
import com.staging.model.User;

// Temporary mock DB using a List
@Repository("fakeDao")
public class FakeDataImpl implements UserDao, BoardDao, CategoryDao, CardDao {
	
	private static List<User> DB = new ArrayList<>();
	
	//-------------------------------------------- Sample Data setup
	public FakeDataImpl() {
		User sampleUser = new User("Joe", "Smith", "jsmith", "j123");
		List<Board> sampleBoards = new ArrayList<Board>();
		Board sampleBoard = new Board("Board 1");
		List<Category> sampleCategories = new ArrayList<Category>();
		Category catToDo = new Category("To Do");
		List<Card> cards1 = new ArrayList<Card>();
		cards1.add(new Card("Task 1", "description of task 1"));
		cards1.add(new Card("Task 2", "description of task 2"));
		catToDo.setCards(cards1);
		Category catDoing = new Category("Doing");
		List<Card> cards2 = new ArrayList<Card>();
		cards2.add(new Card("Task 3", "description of task 3"));
		cards2.add(new Card("Task 4", "description of task 4"));
		catDoing.setCards(cards2);
		Category catDone = new Category("Done");
		List<Card> cards3 = new ArrayList<Card>();
		cards3.add(new Card("Task 5", "description of task 5"));
		cards3.add(new Card("Task 6", "description of task 6"));
		catDone.setCards(cards3);
		sampleCategories.add(catToDo);
		sampleCategories.add(catDoing);
		sampleCategories.add(catDone);
		sampleBoard.setCategories(sampleCategories);
		sampleBoards.add(sampleBoard);
		sampleUser.setBoards(sampleBoards);
		DB.add(sampleUser);
	}
	
	//-------------------------------------------- User DAO
	@Override
	public int addUser(User user) {
		DB.add(user);
		System.out.println("adding user: " + user.getUsername()); //log
		return 0; // always success
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		System.out.println("Searching for username: " + username + ", password: " + password); //log
		for (User dbUser : DB) {
			if ( username.equals(dbUser.getUsername()) && password.equals(dbUser.getPassword()) ) {
				System.out.println("user found"); //log
				System.out.println(dbUser); //log
				return dbUser;
			}
		}
		System.out.println("user NOT found"); //log
		return null;
	}
	
	@Override
	public List<User> getAllUsers() {
		System.out.println("getting all users"); //log
		return DB;
	}

	//-------------------------------------------- Board DAO
	@Override
	public int addBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateBoard(Board board) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBoard(UUID id) {
		// TODO Auto-generated method stub
		
	}
	
	//-------------------------------------------- Category DAO
	
	@Override
	public int addCategory(Category category) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategory(UUID id) {
		// TODO Auto-generated method stub
		
	}
	//-------------------------------------------- Card DAO
	@Override
	public int addCard(Card card) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCard(Card card) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCard(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
