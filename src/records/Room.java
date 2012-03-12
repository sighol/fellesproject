package records;

import java.sql.SQLException;

import com.j256.ormlite.field.DatabaseField;

public class Room extends Record{

	public Room() {
		super(Room.class);
	}
	
	@DatabaseField(columnName = "room_id", generatedId = true)
	public Integer id;
	
	@DatabaseField
	public String name;
	
	@DatabaseField
	public Integer seats;
	
	public static Model<Room> model() throws SQLException {
		return (Model<Room>) Record.model(Room.class);
	}

}
