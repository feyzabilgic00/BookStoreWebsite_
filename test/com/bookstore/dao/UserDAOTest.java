package com.bookstore.dao;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bookstore.entity.Users;

public class UserDAOTest {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static UserDAO userDAO;

	@BeforeClass
	public static void setUpClass() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	    userDAO = new UserDAO(entityManager);
	}

	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("tommy@gmail.com");
		user1.setFullName("Tommy Timothy");
		user1.setPassword("abcdefghj");
		
		user1 = userDAO.create(user1);

		assertTrue(user1.getUserId() > 0);
	}

	@Test(expected = PersistenceException.class)
	public void testCreateUsersFiledNotSet() {
		Users user1 = new Users();
		user1 = userDAO.create(user1);
	}
	
	@Test
	public void testUpdateUsers() {
		Users user=new Users();
		user.setUserId(1);
		user.setEmail("feyzabilgic00@gmail.com");
		user.setFullName("Feyza Bilgi�");
		user.setPassword("mysecret");
		user=userDAO.update(user);
		String expected="mysecret";
		String actual=user.getPassword();
		assertEquals(expected, actual);
	}
	@AfterClass
	public static void tearDownClass() {
		
		entityManager.close();
		entityManagerFactory.close();
	}

}
