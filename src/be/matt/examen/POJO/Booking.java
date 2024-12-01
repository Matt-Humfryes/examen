package be.matt.examen.POJO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import be.matt.examen.DAO.AbstractDAOFactory;
import be.matt.examen.DAO.DAO;

public class Booking {
	private Skier skier;
	private Instructor instructor;
	private Period period;
	private Lesson lesson;
	
	public Booking(Lesson l, Skier s, Instructor i) throws Exception
	{
		skier = s;
		instructor = i;
		lesson = l;
		
		LocalDate begin = LocalDate.now();
		if(begin.isBefore(LocalDate.of(2024, 12, 6)))
		{
			begin = LocalDate.of(2024, 12, 6);
		}
		begin = begin.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		
		LocalDate end = begin;
		end = end.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
		
		Period p = new Period(begin, end);
		period = p;
		
		s.addBooking(this);
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
	
	public static int newBooking(Lesson l, Skier s)
	{
		boolean full = l.isFull();
		int age = s.getAge();
		boolean child = l.getLessonType().getChildCourse();
		
		if((!full) && (!child && age > 12) || (child && age <= 12))
		{
			try
			{
				Instructor i = l.getInstructor();
				
				Booking b = new Booking(l, s, i);
				
				boolean result = b.addToDB();
				System.out.println("ok");
				if(result)
				{
					return b.calculatePrice();
				}
			}
			catch(Exception ex)
			{
				return 0;
			}
		}
		
		return 0;
	}
	
	public boolean addToDB()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Booking> dao = adf.getBookingDAO();
		
		return dao.create(this);
	}
	
	public int calculatePrice()
	{
		return this.lesson.getLessonPrice();
	}
}
