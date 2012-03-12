package records;

import com.j256.ormlite.field.DatabaseField;

import db.orm.Model;
import db.orm.Record;

import java.sql.SQLException;

public class User extends Record {

	public User() {
		super(User.class);
	}
	@DatabaseField(columnName = "user_id", generatedId = true)
	public Integer id;
	@DatabaseField(columnName = "username")
	public String username;
	@DatabaseField(columnName = "first_name")
	public String firstName;
	@DatabaseField(columnName = "last_name")
	public String lastName;
	@DatabaseField
	public String email;
	@DatabaseField
	public String address;
	@DatabaseField(columnName = "phone")
	public Integer phoneNumber;

	public static Model<User> model() throws SQLException {
		return (Model<User>) Record.model(User.class);
	}

	@Override
	public String toString() {
		return username + ": " + firstName + " " + lastName;
	}
}