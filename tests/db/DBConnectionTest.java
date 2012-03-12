package db;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DBConnectionTest {

	@Test
	public void connection() throws ClassNotFoundException, SQLException {
		DBConnection conn = DBConnection.getInstance();
	}

}
