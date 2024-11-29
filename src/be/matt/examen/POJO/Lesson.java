package be.matt.examen.POJO;

public class Lesson {
	private int minBookings;
	private int maxBookings;
	private boolean isMorning;
	private int amountStudent;
	
	private Instructor instructor;
	private LessonType lessonType;

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
}
