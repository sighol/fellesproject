package records;

import java.sql.SQLException;

import com.j256.ormlite.field.DatabaseField;

import db.orm.Model;
import db.orm.Record;

public class Appointment extends Record{
	
	public Appointment() {
		super(Appointment.class);
	}

	@DatabaseField(columnName = "appointment_id", generatedId = true)
	public Integer id;
	
	@DatabaseField
	public String subject;
	
	@DatabaseField
	public String start;
	
	@DatabaseField
	public String end;
	
	@DatabaseField(foreign = true)
	public Room room;
	
	@DatabaseField(foreign = true, columnName="meeting_leader_id")
	public User meetingLeader;
	
	public static Model<Appointment> model() throws SQLException {
		return (Model<Appointment>) Record.model(Appointment.class);
	}

}
