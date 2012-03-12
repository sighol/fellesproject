package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.j256.ormlite.jdbc.JdbcConnectionSource;

public class DBConnection {

	private static String jdbcDriver = "com.mysql.jdbc.Driver";
	
	private static String username = "calendar";
	private static String password = "ryTmLAyhzYPe4X78";
	private static String databaseName = "calendar";

	private static String dbAddress = "jdbc:mysql://localhost/" + databaseName;
	
	private Connection conn;
	
	private static DBConnection instance;
	
	public static DBConnection getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	private DBConnection() throws ClassNotFoundException, SQLException {
		Class.forName(jdbcDriver);
		Properties props = getDatabaseProperties();
		conn = DriverManager.getConnection(dbAddress, props);
	}

	private Properties getDatabaseProperties() {
		Properties props = new Properties();
		props.setProperty("user", username);
		props.setProperty("password", password);
		return props;
	}

	public ResultSet makeQuery(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return rs;
	}
	
	public static void closeResultSet(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
	
	public int makeUpdate(String query) throws SQLException {
		Statement stmt = conn.createStatement();
		int res = stmt.executeUpdate(query);
		return res;
	}
	
	public void close() throws SQLException {
		conn.close();
	}
	
	public static JdbcConnectionSource getJdbcConnectionSource() throws SQLException {
		return new JdbcConnectionSource(dbAddress, username, password);
	}

}
