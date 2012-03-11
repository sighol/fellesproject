package records;

import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import db.DBConnection;

public class Model<T extends Record> {
	
	public Model(Class<T> c) throws SQLException {
		if (dao == null) {
			dao = DaoManager.createDao(DBConnection.getJdbcConnectionSource(),
					c);
		}
	}
	
	private Dao<T, String> dao;

	public void save(T t) throws SQLException {
		if (t.isNewRecord) {
			insert(t);
		} else {
			update(t);
		}
	}

	public void insert(T t) throws SQLException {
		dao.create(t);
		t.isNewRecord = false;
		
	}

	public void update(T t) throws SQLException {
		dao.update(t);
	}
	
	public QueryBuilder<T, String> prepare() throws SQLException {
		return dao.queryBuilder();
	}
	
	public List<T> findAll(PreparedQuery<T> query) throws SQLException {
		List<T> models = dao.query(query); 
		for (T t : models) {
			t.isNewRecord = false;
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
