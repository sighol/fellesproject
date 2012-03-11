package records;

import java.sql.SQLException;

import com.j256.ormlite.field.DatabaseField;

public class User extends Record {

	@DatabaseField(columnName = "user_id", generatedId = true)
	public int id;

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
	public int phoneNumber;

	public static Model<User> model;

	public static Model<User> model() throws SQLException {
		initModel();
		return model;
	}

	private static void initModel() throws SQLException {
		if (model == null) {
			model = ModelFactory.getInstance(User.class);
		}
	}

	@Override
	public void insert() throws SQLException {
		initModel();
		model.save(this);
	}

	@Override
	public void update() throws SQLException {
		initModel();
		model.update(this);
	}

	@Override
	public String toString() {
		return username + ": " + firstName + " " + lastName;
	}

}
