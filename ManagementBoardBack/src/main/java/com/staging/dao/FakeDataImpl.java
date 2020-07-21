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
		int numBoards = 3;
		int numCategories = 4;
		int numCards = 5;
		User sampleUser = new User("Joe", "Smith", "jsmith", "j123");
		
		List<Board> boards = new ArrayList<Board>();
		for (int i = 1; i <= numBoards; i++) {
			Board board  = new Board("Board " + i);
			List<Category> categories = new ArrayList<Category>();
			for (int j = 1; j <= numCategories; j++) {
				Category category = new Category("Category " + i + "-" + j);
				List<Card> cards = new ArrayList<Card>();
				for (int k = 1; k <= numCards; k++) {
					cards.add(new Card("Task " + k, "Description of task " + i + j + k));
				}
				category.setCards(cards);
				categories.add(category);
			}
			board.setCategories(categories);
			boards.add(board);
		}
		sampleUser.setBoards(boards);
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
