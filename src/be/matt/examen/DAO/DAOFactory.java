package be.matt.examen.DAO;

import java.sql.Connection;

import be.matt.examen.POJO.Accreditation;
import be.matt.examen.POJO.Booking;
import be.matt.examen.POJO.Instructor;
import be.matt.examen.POJO.Lesson;
import be.matt.examen.POJO.LessonType;
import be.matt.examen.POJO.Period;
import be.matt.examen.POJO.Skier;

public class DAOFactory {
	protected static final Connection conn = DBConnection.getInstance();
	
	public DAO<Skier> getSkierDAO()
	{
		return new SkierDAO(conn);
	}
	public DAO<Instructor> getInstructorDAO()
	{
		return new InstructorDAO(conn);
	}
	public DAO<Booking> getBookingDAO()
	{
		return new BookingDAO(conn);
	}
	public DAO<Period> getPeriodDAO()
	{
		return new PeriodDAO(conn);
	}
	public DAO<Lesson> getLessonDAO()
	{
		return new LessonDAO(conn);
	}
	public DAO<LessonType> getLessonTypeDAO()
	{
		return new LessonTypeDAO(conn);
	}
	public DAO<Accreditation> getAccreditationDAO()
	{
		return new AccreditationDAO(conn);
	}
}
