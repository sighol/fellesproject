package db;

import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.*;

import records.User;

public class UserTest {

	@Test
	public void test_save() throws SQLException {
		User user = new User();
		user.firstName = "Sigurd";
		user.lastName = "Holsen";
		user.address = "Steinan";
		user.email = "sigurhol@stud.ntnu.no";
		user.username = "sigurhol";
		user.save();
		long count = User.model.count();
		User user2 = User.model().find(User.model.prepare().where().eq("user_id", count).prepare());
		
		assertNotNull(user2);
		
		assertFalse(user2.id == 0);
	}

}
