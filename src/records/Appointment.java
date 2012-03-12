package records;

import java.sql.SQLException;

import com.j256.ormlite.field.DatabaseField;

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
	
	public static void main(String[] args) throws SQLException {
		User user = new User();
		user.address = "heisann";
		user.email = "oant";
		user.firstName = "Sigurd";
		user.lastName = "HOlsen";
		user.username = "sigurhol" + User.model().count();
		user.save();
		Room room = new Room();
		room.seats = 100;
		room.name = "Sigurd" + Room.model().count() + 1000;
		room.save();
		Appointment ap = new Appointment();
		ap.room = room;
		ap.subject = "heisann";
		ap.start = "2010.05.05 11:21";
		ap.end = ap.start;
		ap.meetingLeader = user;
		ap.save();
	}

}
