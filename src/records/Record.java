package records;

import java.sql.SQLException;

public abstract class Record {

	protected boolean isNewRecord;
	protected Model<?> model;

	public void save() throws SQLException {
		if (isNewRecord) {
			insert();
		} else {
			update();
		}
	}

	 public abstract void insert() throws SQLException;
	 public abstract void update() throws SQLException;


}
