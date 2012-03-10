package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

	private String jdbcDriver = "com.mysql.jdbc.Driver";
	private String dbAddress = "jdbc:mysql://localhost/calendar";
	
	private String username = "client";
	private String password = "ryTmLAyhzYPe4X78";

	private Connection conn;

	public DBConnection() throws ClassNotFoundException, SQLException {
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

}
