package com.staging.data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.staging.model.Board;
import com.staging.model.Card;
import com.staging.model.Category;
import com.staging.model.User;
import com.staging.util.HibernateUtil;

@Repository("hibernateDao")
public class HibernateDataImpl implements UserDao, BoardDao, CategoryDao, CardDao, DebugDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//-------------------------------------------- User DAO
	@Override
	public int addUser(User user) {
		log.trace("addUser()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			session.save(user);
			
			transaction.commit();
			//session.close();
			log.info("Successfully added user: " + user.getUsername());
			return 0;
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
			log.warn("Failed to add user: " + user.getUsername());
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public int updateUser(User user) {
		log.trace("updateUser()");
		log.info("Updating user: " + user.getUsername());
		if (user.getFirstName().isEmpty()
			|| user.getLastName().isEmpty()
			|| user.getUsername().isEmpty() ) {
			log.info("Incomplete information submitted, canceling update");
			return -1;
		}
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			User pUser = (User) session.load(User.class, user.getId());
			pUser.setFirstName(user.getFirstName());
			pUser.setLastName(user.getLastName());
			pUser.setUsername(user.getUsername());
			
			transaction.commit();
			//session.close();
			log.info("Successfully updated user: " + user.getUsername());
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update user: " + user.getUsername());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteUser(String id) {
		log.trace("deleteUser()");
		log.info("Deleting user with ID: " + id);
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			User pUser = (User) session.load(User.class, id);
			session.delete(pUser);
			
			transaction.commit();
			//session.close();
			log.info("Successfully deleted user with ID: " + id);
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to delete user with ID: " + id);
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("getUserByUsernameAndPassword()");
		log.info("Searching for user: " + username);
		User user = null;
		try {
			Session session = HibernateUtil.openSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<User> cQuery = cb.createQuery(User.class);
			Root<User> root = cQuery.from(User.class);
			Predicate predicateForUsername = cb.equal(root.get("username"), username);
			Predicate predicateForPassword = cb.equal(root.get("password"), password);
			Predicate predicateForUsernamePassword = cb.and(predicateForUsername, predicateForPassword);
			cQuery.select(root).where(predicateForUsernamePassword);
			user = session.createQuery(cQuery).getSingleResult();
			//session.close();
			log.info("User found");
		} catch (Exception e) {
			log.warn("User not found");
			//e.printStackTrace();
		}
		log.debug("User data:\n" + user);
		return user;
	}

	//-------------------------------------------- Board DAO
	@Override
	public int addBoard(Board board) {
		log.trace("addBoard()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			session.save(board);
			
			transaction.commit();
			//session.close();
			log.info("Successfully added board: " + board.getTitle());
			return 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to add board: " + board.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateBoard(Board board) {
		log.trace("updateBoard()");
		log.info("Updating board: " + board.getTitle());
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Board pBoard = (Board) session.load(Board.class, board.getId());
			pBoard.setTitle(board.getTitle());
			
			transaction.commit();
			//session.close();
			log.info("Successfully updated board: " + board.getTitle());
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update board: " + board.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteBoard(String id) {
		log.trace("deleteBoard()");
		log.info("Deleting board with ID: " + id);
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Board pBoard = (Board) session.load(Board.class, id);
			session.delete(pBoard);
			
			transaction.commit();
			//session.close();
			log.info("Successfully deleted board with ID: " + id);
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to delete board with ID: " + id);
			e.printStackTrace();
			return -1;
		}
	}
	
	//-------------------------------------------- Category DAO
	
	@Override
	public int addCategory(Category category) {
		log.trace("addCategory()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			session.save(category);
			
			transaction.commit();
			//session.close();
			log.info("Successfully added category: " + category.getTitle());
			return 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to add category: " + category.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateCategory(Category category) {
		log.trace("updateCategory()");
		log.info("Updating category: " + category.getTitle());
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Category pCategory = (Category) session.load(Category.class, category.getId());
			pCategory.setTitle(category.getTitle());
			
			transaction.commit();
			//session.close();
			log.info("Successfully updated category: " + category.getTitle());
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update category: " + category.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteCategory(String id) {
		log.trace("deleteCategory()");
		log.info("Deleting category with ID: " + id);
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Category pCategory = (Category) session.load(Category.class, id);
			session.delete(pCategory);
			
			transaction.commit();
			//session.close();
			log.info("Successfully deleted category with ID: " + id);
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to delete category with ID: " + id);
			e.printStackTrace();
			return -1;
		}
	}
	//-------------------------------------------- Card DAO
	@Override
	public int addCard(Card card) {
		log.trace("addCard()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			session.save(card);
			
			transaction.commit();
			//session.close();
			log.info("Successfully added card: " + card.getTitle());
			return 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to add card: " + card.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateCard(Card card) {
		log.trace("updateCard()");
		log.info("Updating card: " + card.getTitle());
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Card pCard = (Card) session.load(Card.class, card.getId());
			pCard.setTitle(card.getTitle());
			pCard.setDescription(card.getDescription());
			
			transaction.commit();
			//session.close();
			log.info("Successfully updated card: " + card.getTitle());
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update card: " + card.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteCard(String id) {
		log.trace("deleteCard()");
		log.info("Deleting card with ID: " + id);
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Card pCard = (Card) session.load(Card.class, id);
			session.delete(pCard);
			
			transaction.commit();
			//session.close();
			log.info("Successfully deleted card with ID: " + id);
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to delete card with ID: " + id);
			e.printStackTrace();
			return -1;
		}
	}
	
	//-------------------------------------------- Debug DAO
	@Override
	public void resetDB() {
		log.warn("!!!!!!!!!! DELETE ALL USERS - RESET DATABASE !!!!!!!!!!");
		List<User> users = getAllUsers();
		for (User user : users) {
			deleteUser(user.getId());
		}
	}
	
	@Override
	public void initSampleUser() {
		log.trace("initSampleUser()");
		int numBoards = 3;
		int numCategories = 4;
		int numCards = 5;
		User sampleUser = new User("Joe", "Smith", "jsmith", "j123");
		sampleUser.setId("8fa417b5-b30c-4e16-b6b4-9edfafc91a57");
		addUser(sampleUser);
		for (int i = 1; i <= numBoards; i++) {
			Board board  = new Board("Board " + i, sampleUser);
			addBoard(board);
			for (int j = 1; j <= numCategories; j++) {
				Category category = new Category("Category " + i + "-" + j, board);
				addCategory(category);
				for (int k = 1; k <= numCards; k++) {
					addCard(new Card("Task " + k, "Description of task " + i + j + k, category));
				}
			}
		}
		/*
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
		addUser(sampleUser);
		*/
	}
	
	@Override
	public List<User> getAllUsers() {
		log.trace("getAllUsers()");
		List<User> userList = null;
		try {
			Session session = HibernateUtil.openSession();
			String hql = "FROM User";
			userList = session.createQuery(hql, User.class).getResultList();
			// DO NOT CLOSE SESSION HERE
			log.info("Found " + userList.size() + " users.");
		} catch (Exception e) {
            e.printStackTrace();
		}
		return userList;
	}

}
