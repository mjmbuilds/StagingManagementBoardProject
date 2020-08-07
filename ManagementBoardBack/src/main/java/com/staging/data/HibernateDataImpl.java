package com.staging.data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.staging.model.Board;
import com.staging.model.Card;
import com.staging.model.Category;
import com.staging.model.CodeMessage;
import com.staging.model.IndexList;
import com.staging.model.User;
import com.staging.util.HibernateUtil;

@Repository("hibernateDao")
public class HibernateDataImpl implements UserDao, BoardDao, CategoryDao, CardDao, DebugDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//-------------------------------------------- User DAO
	
	@Override
	public CodeMessage addUser(User user) {
		log.trace("addUser()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			session.save(user);
			
			transaction.commit();
			log.info("Successfully added user: " + user.getUsername());
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
			log.warn("Failed to add user: " + user.getUsername());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}
	
	@Override
	public CodeMessage updateUserInfo(User user) {
		log.trace("updateUserInfo()");
		log.info("Updating user: " + user.getUsername());
		if (user.getFirstName().isEmpty()
			|| user.getLastName().isEmpty()
			|| user.getUsername().isEmpty() ) {
			log.info("Incomplete information submitted, canceling update");
			return new CodeMessage(-1);
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
			log.info("Successfully updated user: " + user.getUsername());
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update user: " + user.getUsername());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}
	
	@Override
	public CodeMessage updateUserPass(User user) {
		log.trace("updateUserPass()");
		log.info("Updating user with id: " + user.getId());
		if (user.getPassword().isEmpty()) {
			log.info("Incomplete information submitted, canceling update");
			return new CodeMessage(-1);
		}
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			User pUser = (User) session.load(User.class, user.getId());
			pUser.setPassword(user.getPassword());
			
			transaction.commit();
			log.info("Successfully updated password for user id: " + user.getId());
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update password for user id: " + user.getId());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}

	@Override
	public CodeMessage deleteUser(String id) {
		log.trace("deleteUser()");
		log.info("Deleting user with ID: " + id);
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			User pUser = (User) session.load(User.class, id);
			session.delete(pUser);
			
			transaction.commit();
			log.info("Successfully deleted user with ID: " + id);
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to delete user with ID: " + id);
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("getUserByUsernameAndPassword()");
		log.info("Searching for user: " + username);
		User user = new User();
		user.setId(null); // front end to check for null id as no match found
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
			log.info("User found");
		} catch (Exception e) {
			log.warn("User not found");
			//e.printStackTrace();
		}
		log.debug("User data:\n" + user);
		
		// return only the names of the boards, not the categories or cards
		for (Board board : user.getBoards()) {
			board.setCategories(null);
		}
		
		return user;
	}

	//-------------------------------------------- Board DAO
	
	@Override
	public CodeMessage addBoard(Board board) {
		log.trace("addBoard()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			User owningUser = new User();
			owningUser.setId(board.getOwningUserId());
			board.setUser(owningUser);
			session.save(board);
			
			transaction.commit();
			log.info("Successfully added board: " + board.getTitle());
			return new CodeMessage(board.getId());
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to add board: " + board.getTitle());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}

	@Override
	public Board getBoard(String id) {
		log.trace("getBoard()");
		log.info("Getting board with id: " +id);
		Board board = new Board();
		board.setId(null); // front end to check for null id as no match found
		try {
			Session session = HibernateUtil.openSession();
			board = (Board) session.get(Board.class, id);
			if (board == null) {
				log.info("No board found with id: " +id);
			} else {
				log.info("Found board: " + board.getTitle());
			}
		} catch (Exception e) {
			log.warn("Failed to get board");
			e.printStackTrace();
		}
		return board;
	}
	
	@Override
	public CodeMessage updateBoard(Board board) {
		log.trace("updateBoard()");
		log.info("Updating board: " + board.getTitle());
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Board pBoard = (Board) session.load(Board.class, board.getId());
			pBoard.setTitle(board.getTitle());
			
			transaction.commit();
			log.info("Successfully updated board: " + board.getTitle());
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update board: " + board.getTitle());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}

	@Override
	public CodeMessage deleteBoard(String id) {
		log.trace("deleteBoard()");
		log.info("Deleting board with ID: " + id);
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Board pBoard = (Board) session.get(Board.class, id);
			if (pBoard == null) {
				transaction.rollback();
				log.info("No board found with ID: " + id);
				return new CodeMessage(0);
			}
			session.delete(pBoard);
			transaction.commit();
			log.info("Successfully deleted board with ID: " + id);
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to delete board with ID: " + id);
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}
	
	//-------------------------------------------- Category DAO
	
	@Override
	public CodeMessage updateCategoryIndexList(IndexList indexList) {
		log.trace("updateCategoryIndexList()");
		Transaction transaction = null;
		if (indexList == null || indexList.getIdList() == null
				|| indexList.getIdList().size() < 1) {
			log.info("IndexList data was not valid");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(indexList);
			return new CodeMessage(-1);
		}
		try {
			Session session = HibernateUtil.openSession();
			
			List<String> idList = indexList.getIdList();
			for (int index = 0; index < idList.size(); index++) {
				transaction = session.beginTransaction();
				Category pCategory = (Category) session.load(Category.class, idList.get(index));
				pCategory.setIndex(index);
				log.debug("Updating Category: " + pCategory.getTitle() 
					+ " : with id : " + pCategory.getIndex());
				transaction.commit();
			}
			
			log.info("Successfully updated category indexes");
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update category indexes");
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}
	
	@Override
	public CodeMessage addCategory(Category category) {
		log.trace("addCategory()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Board owningBoard = new Board();
			owningBoard.setId(category.getOwningBoardId());
			category.setBoard(owningBoard);
			session.save(category);
			
			transaction.commit();
			log.info("Successfully added category: " + category.getTitle());
			return new CodeMessage(category.getId());
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to add category: " + category.getTitle());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}

	@Override
	public Category getCategory(String id) {
		log.trace("getCategory()");
		log.info("Getting category with id: " +id);
		Category category = new Category();
		category.setId(null); // front end to check for null id as no match found
		try {
			Session session = HibernateUtil.openSession();
			category = (Category) session.get(Category.class, id);
			if (category == null) {
				log.info("No category found with id: " + id);
			} else {
				log.info("Found category: " + category.getTitle());
			}
		} catch (Exception e) {
			log.warn("Failed to get category");
			e.printStackTrace();
		}
		return category;
	}
	
	@Override
	public CodeMessage updateCategory(Category category) {
		log.trace("updateCategory()");
		log.info("Updating category: " + category.getTitle());
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Category pCategory = (Category) session.load(Category.class, category.getId());
			pCategory.setTitle(category.getTitle());
			
			transaction.commit();
			log.info("Successfully updated category: " + category.getTitle());
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update category: " + category.getTitle());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}

	@Override
	public CodeMessage deleteCategory(String id) {
		log.trace("deleteCategory()");
		log.info("Deleting category with ID: " + id);
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Category pCategory = (Category) session.get(Category.class, id);
			session.delete(pCategory);
			if (pCategory == null) {
				transaction.rollback();
				log.info("No board category with ID: " + id);
				return new CodeMessage(0);
			}
			transaction.commit();
			log.info("Successfully deleted category with ID: " + id);
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to delete category with ID: " + id);
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}
	
	//-------------------------------------------- Card DAO
	
	@Override
	public CodeMessage updateCardIndexList(IndexList indexList) {
		log.trace("updateCardIndexList()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			List<String> idList = indexList.getIdList();
			for (int index = 0; index < idList.size(); index++) {
				Card pCard = (Card) session.load(Card.class, idList.get(index));
				pCard.setIndex(index);
			}
			
			transaction.commit();
			log.info("Successfully updated card indexes");
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update card indexes");
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}
	
	@Override
	public CodeMessage addCard(Card card) {
		log.trace("addCard()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Category owningCategory = new Category();
			owningCategory.setId(card.getOwningCategoryId());
			card.setCategory(owningCategory);
			session.save(card);
			
			transaction.commit();
			log.info("Successfully added card: " + card.getTitle());
			return new CodeMessage(card.getId());
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to add card: " + card.getTitle());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}

	@Override
	public Card getCard(String id) {
		log.trace("getCard()");
		log.info("Getting card with id: " +id);
		Card card = new Card();
		card.setId(null); // front end to check for null id as no match found
		try {
			Session session = HibernateUtil.openSession();
			card = (Card) session.get(Card.class, id);
			if (card == null) {
				log.info("No card found with id: " + id);
			} else {
				log.info("Found card: " + card.getTitle());
			}
		} catch (Exception e) {
			log.warn("Failed to get card");
			e.printStackTrace();
		}
		return card;
	}
	
	@Override
	public CodeMessage updateCard(Card card) {
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
			log.info("Successfully updated card: " + card.getTitle());
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to update card: " + card.getTitle());
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}

	@Override
	public CodeMessage deleteCard(String id) {
		log.trace("deleteCard()");
		log.info("Deleting card with ID: " + id);
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.openSession();
			transaction = session.beginTransaction();
			
			Card pCard = (Card) session.get(Card.class, id);
			session.delete(pCard);
			if (pCard == null) {
				transaction.rollback();
				log.info("No card found with ID: " + id);
				return new CodeMessage(0);
			}
			transaction.commit();
			log.info("Successfully deleted card with ID: " + id);
			return new CodeMessage(0);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.warn("Failed to delete card with ID: " + id);
			e.printStackTrace();
			return new CodeMessage(-1);
		}
	}
	
	//-------------------------------------------- Debug DAO
	
	@Override
	public int resetDB() {
		log.warn("!!!!!!!!!! DELETE ALL USERS - RESET DATABASE !!!!!!!!!!");
		try {
			List<User> users = getAllUsers();
			for (User user : users) {
				deleteUser(user.getId());
			}
			return 0;
		} catch(Exception e) {
			return -1;
		}
		
	}
	
	@Override
	public int initSampleUser() {
		log.trace("initSampleUser()");
		try {
			int numBoards = 3;
			int numCategories = 4;
			int numCards = 5;
			User sampleUser = new User("Joe", "Smith", "jsmith", "j123");
			sampleUser.setId("8fa417b5-b30c-4e16-b6b4-9edfafc91a57");
			addUser(sampleUser);
			for (int i = 1; i <= numBoards; i++) {
				Board board  = new Board("Board " + i, sampleUser);
				if (i == 1) {
					board.setId("f9c91b9c-e95f-4f67-98a0-614f052d59d5");
				}
				addBoard(board);
				for (int j = 1; j <= numCategories; j++) {
					Category category = new Category("Category " + i + "-" + j, board);
					if (i == 1 && j == 1) {
						category.setId("0ceedc63-6453-4ccb-8208-656e51be22d1");
					}
					category.setIndex(j - 1);
					addCategory(category);
					for (int k = 1; k <= numCards; k++) {
						Card card = new Card("Task " + k, "Description of task " + i + j + k, category);
						if (i == 1 && j == 1 && k == 1) {
							card.setId("27d7122a-9e4b-4917-9600-a7a49b3e5c2b");
						}
						card.setIndex(k - 1);
						addCard(card);
					}
				}
			}
			return 0;
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public List<User> getAllUsers() {
		log.trace("getAllUsers()");
		List<User> userList = null;
		try {
			Session session = HibernateUtil.openSession();
			String hql = "FROM User";
			userList = session.createQuery(hql, User.class).getResultList();
			log.info("Found " + userList.size() + " users.");
		} catch (Exception e) {
            e.printStackTrace();
		}
		return userList;
	}

}
