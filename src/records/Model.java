package records;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import db.DBConnection;

public class Model<T> {

	public Model(Class<T> c) throws SQLException {
		if (dao == null) {
			dao = DaoManager.createDao(
					DBConnection.getJdbcConnectionSource(), c);
		}
	}
	private Dao<T, String> dao;

	public void save(Record record) throws SQLException {
		if (record.isNewRecord) {
			insert(record);
		} else {
			update(record);
		}
	}

	public void insert(Record record) throws SQLException {
		dao.create((T) record);
		record.isNewRecord = false;
	}

	public void update(Record record) throws SQLException {
		dao.update((T)record);
	}

	public void delete(Record record) throws SQLException {
		dao.delete((T)record);
	}

	public QueryBuilder<T, String> builder() throws SQLException {
		return dao.queryBuilder();
	}

	public List<T> findAll(PreparedQuery<T> query) throws SQLException {
		List<T> models = dao.query(query);
		for (T record : models) {
			((Record)record).isNewRecord = false;
		}
		return models;
	}

	public List<T> findAll() throws SQLException {
		PreparedQuery<T> pq = dao.queryBuilder().prepare();
		return findAll(pq);
	}

	public T find(PreparedQuery<T> query) throws SQLException {
		List<T> users = findAll(query);
		if (users.size() >= 1) {
			return users.get(0);
		}
		return null;
	}

	public long count() throws SQLException {
		return dao.countOf();
	}
}
