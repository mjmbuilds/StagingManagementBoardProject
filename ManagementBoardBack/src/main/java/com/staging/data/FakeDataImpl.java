package com.staging.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.staging.model.Board;
import com.staging.model.Card;
import com.staging.model.Category;
import com.staging.model.CodeMessage;
import com.staging.model.User;

// Temporary mock DB using a List
@Repository("fakeDao")
public class FakeDataImpl implements UserDao, BoardDao, CategoryDao, CardDao, DebugDao {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private static List<User> DB = new ArrayList<>();
	
	//-------------------------------------------- Debug DAO
	@Override
	public int resetDB() {
		DB = new ArrayList<>();
		return 0;
	}
	
	@Override
	public int initSampleUser() {
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
		log.info("Sample user data has been set up");
		return 0;
	}
	
	@Override
	public List<User> getAllUsers() {
		log.trace("getAllUsers()");
		return DB;
	}
	
	//-------------------------------------------- User DAO
	@Override
	public CodeMessage addUser(User user) {
		log.trace("addUser()");
		DB.add(user);
		log.info("adding user: " + user.getUsername());
		return null; // always success
	}

	@Override
	public CodeMessage updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeMessage deleteUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("getUserByUsernameAndPassword()");
		log.info("Searching for username: " + username + ", password: " + password);
		for (User dbUser : DB) {
			if ( username.equals(dbUser.getUsername()) && password.equals(dbUser.getPassword()) ) {
				log.info("User found: \n" + dbUser);
				return dbUser;
			}
		}
		log.info("user NOT found");
		return null;
	}

	//-------------------------------------------- Board DAO
	@Override
	public CodeMessage addBoard(Board board) {
		log.trace("addBoard()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Board getBoard(String id) {
		//TODO
		return null;
	}
	
	@Override
	public CodeMessage updateBoard(Board board) {
		log.trace("updateBoard()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeMessage deleteBoard(String id) {
		log.trace("deleteBoard()");
		// TODO Auto-generated method stub
		return null;
	}
	
	//-------------------------------------------- Category DAO
	
	@Override
	public CodeMessage addCategory(Category category) {
		log.trace("addCategory()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategory(String id) {
		//TODO
		return null;
	}
	
	@Override
	public CodeMessage updateCategory(Category category) {
		log.trace("updateCategory()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeMessage deleteCategory(String id) {
		log.trace("deleteCategory()");
		// TODO Auto-generated method stub
		return null;
	}
	//-------------------------------------------- Card DAO
	@Override
	public CodeMessage addCard(Card card) {
		log.trace("addCard()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card getCard(String id) {
		//TODO
		return null;
	}
	
	@Override
	public CodeMessage updateCard(Card card) {
		log.trace("updateCard()");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CodeMessage deleteCard(String id) {
		log.trace("deleteCard()");
		// TODO Auto-generated method stub
		return null;
	}

}
