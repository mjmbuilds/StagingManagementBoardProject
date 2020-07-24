package com.staging.data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import com.staging.model.User;
import com.staging.util.HibernateUtil;

@Repository("hibernateDao")
public class HibernateDataImpl implements UserDao, BoardDao, CategoryDao, CardDao, DebugDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//-------------------------------------------- Debug DAO
	@Override
	public void resetDB() {
		log.warn("!!!!!!!!!! DROP TABLES - RESET DATABASE !!!!!!!!!!");
		try {
			String sql = "DROP TABLE mb_user CASCADE CONSTRAINTS; "
					+ "DROP TABLE mb_board CASCADE CONSTRAINTS; "
					+ "DROP TABLE mb_category CASCADE CONSTRAINTS; "
					+ "DROP TABLE mb_card CASCADE CONSTRAINTS; "
					+ "CREATE TABLE mb_user ("
					+ "user_id VARCHAR2(36), "
					+ "user_firstname VARCHAR2(50) NOT NULL, "
					+ "user_lastname VARCHAR2(50) NOT NULL, "
					+ "user_username VARCHAR2(50) NOT NULL, "
					+ "user_password VARCHAR2(50) NOT NULL, "
					+ "CONSTRAINT pk_user PRIMARY KEY (user_id)); "
					+ "CREATE TABLE mb_board ("
					+ "board_id VARCHAR2(36), "
					+ "user_id VARCHAR2(36), "
					+ "board_title VARCHAR2(50) NOT NULL, "
					+ "CONSTRAINT pk_board PRIMARY KEY (board_id)); "
					+ "CREATE TABLE mb_category ("
					+ "category_id VARCHAR2(36), "
					+ "board_id VARCHAR2(36), "
					+ "category_title VARCHAR2(50) NOT NULL, "
					+ "CONSTRAINT pk_category PRIMARY KEY (category_id)); "
					+ "CREATE TABLE mb_card ("
					+ "card_id VARCHAR2(36), "
					+ "category_id VARCHAR2(36), "
					+ "card_title VARCHAR2(50) NOT NULL, "
					+ "card_description VARCHAR2(1000) NOT NULL, "
					+ "CONSTRAINT pk_card PRIMARY KEY (card_id));"
					+ "ALTER TABLE mb_board"
					+ "ADD CONSTRAINT fk_board_user FOREIGN KEY ( user_id )"
					+ "REFERENCES mb_user ( user_id );"
					+ "ALTER TABLE mb_category"
					+ "ADD CONSTRAINT fk_category_board FOREIGN KEY ( board_id )"
					+ "REFERENCES mb_board ( board_id );"
					+ "ALTER TABLE mb_card"
					+ "ADD CONSTRAINT fk_card_category FOREIGN KEY ( category_id )"
					+ "REFERENCES mb_category ( category_id );";
			Session session = HibernateUtil.getSession();
			session.beginTransaction();
			session.createSQLQuery(sql).executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initSampleUser() {
		log.trace("initSampleUser()");
		int numBoards = 3;
		int numCategories = 4;
		int numCards = 5;
		User sampleUser = new User("Joe", "Smith", "jsmith", "j123");
		addUser(sampleUser);
		for (int i = 1; i <= numBoards; i++) {
			Board board  = new Board("Board " + i, sampleUser.getId());
			addBoard(board);
			for (int j = 1; j <= numCategories; j++) {
				Category category = new Category("Category " + i + "-" + j, board.getId());
				addCategory(category);
				for (int k = 1; k <= numCards; k++) {
					addCard(new Card("Task " + k, "Description of task " + i + j + k, category.getId()));
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
			Session session = HibernateUtil.getSession();
			String hql = "FROM User";
			
			userList = session.createQuery(hql, User.class).list();
			
			//Query<User> query = session.createQuery(hql, User.class);
			//userList = query.getResultList();
			
		} catch (Exception e) {
            e.printStackTrace();
		}
		return userList;
	}
	
	//-------------------------------------------- User DAO
	@Override
	public int addUser(User user) {
		log.trace("addUser()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			log.info("Successfully added user: " + user.getUsername());
			return 0;
		} catch (Exception e) {
			if (transaction != null) {
                transaction.rollback();
            }
			log.info("Failed to add user: " + user.getUsername());
			e.printStackTrace();
			return -1;
		}
	}
	
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int deleteUser(UUID id) {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		log.trace("getUserByUsernameAndPassword()");
		log.info("Searching for user: " + username);
		User user = null;
		try {
			Session session = HibernateUtil.getSession();
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
			log.info("User not found");
			e.printStackTrace();
		}
		return user;
	}

	//-------------------------------------------- Board DAO
	@Override
	public int addBoard(Board board) {
		log.trace("addBoard()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(board);
			transaction.commit();
			log.info("Successfully added board: " + board.getTitle());
			return 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.info("Failed to add board: " + board.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateBoard(Board board) {
		log.trace("updateBoard()");
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int deleteBoard(UUID id) {
		log.trace("deleteBoard()");
		// TODO Auto-generated method stub
		return -1;
	}
	
	//-------------------------------------------- Category DAO
	
	@Override
	public int addCategory(Category category) {
		log.trace("addCategory()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(category);
			transaction.commit();
			log.info("Successfully added category: " + category.getTitle());
			return 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.info("Failed to add category: " + category.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateCategory(Category category) {
		log.trace("updateCategory()");
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int deleteCategory(UUID id) {
		log.trace("deleteCategory()");
		// TODO Auto-generated method stub
		return -1;
	}
	//-------------------------------------------- Card DAO
	@Override
	public int addCard(Card card) {
		log.trace("addCard()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();
			transaction = session.beginTransaction();
			session.save(card);
			transaction.commit();
			log.info("Successfully added card: " + card.getTitle());
			return 0;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			log.info("Failed to add card: " + card.getTitle());
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateCard(Card card) {
		log.trace("updateCard()");
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public int deleteCard(UUID id) {
		log.trace("deleteCard()");
		// TODO Auto-generated method stub
		return -1;
	}
}
