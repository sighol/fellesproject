package records;

import java.sql.SQLException;

public abstract class Record {

	Class subtype;

	public Record(Class c) {
		subtype = c;
	}
	protected boolean isNewRecord;

	public void save() throws SQLException {
		if (isNewRecord) {
			insert();
		} else {
			update();
		}
	}
	private static Model<?> model;

	public static Model<?> model(Class c) throws SQLException {
		return ModelFactory.getInstance(c);
	}

	public void insert() throws SQLException {
		model(subtype).insert(this);
	}

	public void update() throws SQLException {
		model(subtype).update(this);
	}

	public void delete() throws SQLException {
		model(subtype).delete(this);
	}
	
}