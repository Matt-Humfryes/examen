package be.matt.examen.POJO;

import be.matt.examen.DAO.AbstractDAOFactory;
import be.matt.examen.DAO.DAO;

public class Lesson {
	private int minBookings;
	private int maxBookings;
	private boolean isMorning;
	private int amountStudent;
	
	private Instructor instructor;
	private LessonType lessonType;
	
	public Lesson(Instructor i, LessonType lt, int min, int max, boolean time)
	{
		instructor = i;
		lessonType = lt;
		minBookings = min;
		maxBookings = max;
		isMorning = time;
		
		amountStudent = 0;
	}

	public int getMinBookings()
	{
		return minBookings;
	}
	public int getMaxBookings()
	{
		return maxBookings;
	}
	public boolean getMorning()
	{
		return isMorning;
	}
	public int getAmountStudent()
	{
		return amountStudent;
	}
	public Instructor getInstructor()
	{
		return instructor;
	}
	public LessonType getLessonType()
	{
		return lessonType;
	}
	
	public void setMinBookings(int val)
	{
		minBookings = val;
	}
	public void setMaxBookings(int val)
	{
		maxBookings = val;
	}
	public void setMorning(boolean val)
	{
		isMorning = val;
	}
	public void setAmountStudent(int val)
	{
		amountStudent = val;
	}
	public void setInstructor(Instructor val)
	{
		instructor = val;
	}
	public void setLessonType(LessonType val)
	{
		lessonType = val;
	}
	
	public boolean addToDB()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Lesson> dao = adf.getLessonDAO();
		
		return dao.create(this);
	}
}
