package records;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ModelFactory {

	private static Map<Class, Model> map = new HashMap<Class, Model>();

	public static Model getInstance(Class c) throws SQLException {
		if (!map.containsKey(c)) {
			Model m = new Model(c);
			map.put(c, m);
		}
		return map.get(c);
	}

}
