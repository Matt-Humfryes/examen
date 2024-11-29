package be.matt.examen.POJO;

public class Booking {
	private int minBookings;
	private int maxBookings;
	
	private Skier skier;
	private Instructor instructor;
	private Period period;
	private Lesson lesson;
	
	public int getMinBookings()
	{
		return minBookings;
	}
	public int getMaxBookings()
	{
		return maxBookings;
	}
	public Skier getSkier()
	{
		return skier;
	}
	public Instructor getInstructor()
	{
		return instructor;
	}
	public Period getPeriod()
	{
		return period;
	}
	public Lesson getLesson()
	{
		return lesson;
	}
	
	public void setMinBookings(int val)
	{
		minBookings = val;
	}
	public void setMaxBookings(int val)
	{
		maxBookings = val;
	}
	public void setSkier(Skier val)
	{
		skier = val;
	}
	public void setInstructor(Instructor val)
	{
		instructor = val;
	}
	public void setPeriod(Period val)
	{
		period = val;
	}
	public void setLesson(Lesson val)
	{
		lesson = val;
	}
}
