package db;

import java.sql.SQLException;


import org.junit.Test;

import records.User;
import static org.junit.Assert.*;


public class UserTest {

	private User getNewUser() throws SQLException {
		User user = new User();
		user.firstName = "Sigurd";
		user.lastName = "Holsen";
		user.address = "Steinan";
		user.email = "sigurhol@stud.ntnu.no";
		user.username = "sigurhol" +  User.model().count();
		return user;
	}
	
	private User getUser() throws SQLException {
		User user = getNewUser();
		user.save();
		return user;
	}
	
	@Test
	public void test_isNewRecord() throws SQLException {
		User user = getNewUser();
		assertTrue(user.isNewRecord());
		user.save();
		assertFalse(user.isNewRecord());
	}

	@Test
	public void test_save() throws SQLException {
		long count = User.model().count();
		getUser();
		assertFalse(count == User.model().count());
	}
	
	@Test
	public void test_save_idIsUpdated() throws SQLException {
		User user= getNewUser();
		long count = User.model().count();
		assertNull(user.id);
		user.save();
		assertNotNull(user.id);
	}
	
}