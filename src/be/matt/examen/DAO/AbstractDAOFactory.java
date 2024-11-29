package be.matt.examen.DAO;

import be.matt.examen.POJO.Accreditation;
import be.matt.examen.POJO.Booking;
import be.matt.examen.POJO.Instructor;
import be.matt.examen.POJO.Lesson;
import be.matt.examen.POJO.LessonType;
import be.matt.examen.POJO.Period;
import be.matt.examen.POJO.Skier;

public abstract class AbstractDAOFactory {
	public static final int DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;
	
	public abstract DAO<Skier> getSkierDAO();
	public abstract DAO<Instructor> getInstructorDAO();
	public abstract DAO<Booking> getBookingDAO();
	public abstract DAO<Period> getPeriodDAO();
	public abstract DAO<Lesson> getLessonDAO();
	public abstract DAO<LessonType> getLessonTypeDAO();
	public abstract DAO<Accreditation> getAccreditationDAO();
	
	public static AbstractDAOFactory getFactory(int type)
	{
		switch(type)
		{
			case DAO_FACTORY:
				return new DAOFactory();
			default:
				return null;
		}
	}
}
