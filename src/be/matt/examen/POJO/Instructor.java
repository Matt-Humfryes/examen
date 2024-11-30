package be.matt.examen.POJO;

import java.util.ArrayList;

import be.matt.examen.DAO.AbstractDAOFactory;
import be.matt.examen.DAO.DAO;

public class Instructor extends Person {
	private ArrayList<Accreditation> accreditations;
	private ArrayList<Lesson> lessons;
	
	public Instructor(String name, String firstname, String password, int age, String username, String sport, String level, boolean child, boolean time) throws Exception {
		super(name, firstname, password, age, username);
		
		accreditations = new ArrayList<Accreditation>();
		lessons = new ArrayList<Lesson>();
		
		this.signInCourse(sport, level, child, time);
	}
	
	private void signInCourse(String sport, String level, boolean child, boolean time) throws Exception
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<LessonType> dao = adf.getLessonTypeDAO();
		int price = 0;
		
		for(LessonType i : dao.getAll())
		{
			if(i.getSportName().equals(sport) && i.getLevel().equals(level) && i.getChildCourse() == child)
			{
				price = i.getPrice();
			}
		}
		if(price == 0)
		{
			throw new Exception("Aucun type de lesson ne correspond.");
		}
		
		Accreditation accreditation = new Accreditation(sport, level, child, price);
		accreditations.add(accreditation);
		
		LessonType lessonType = accreditations.get(0).getLessonType(0);
		
		int min = 0;
		int max = 0;
		
		if(child || sport.equals("snowboard") || level.equals("hors-piste") || level.equals("competition"))
		{
			min = 5;
			max = 8;
		}
		else
		{
			min = 6;
			max = 10;
		}
		
		Lesson lesson = new Lesson(this, lessonType, min, max, time);
		lessons.add(lesson);
	}
	
	public ArrayList<Accreditation> getAccreditations()
	{
		return accreditations;
	}
	public ArrayList<Lesson> getLessons()
	{
		return lessons;
	}
	
	public void setAccreditations(ArrayList<Accreditation> val)
	{
		accreditations = val;
	}
	public void setLessons(ArrayList<Lesson> val)
	{
		lessons = val;
	}
	
	public void addAccreditation(Accreditation a)
	{
		if(!accreditations.contains(a))
		{
			accreditations.add(a);
		}
	}
	public void addLesson(Lesson l)
	{
		if(!lessons.contains(l))
		{
			lessons.add(l);
		}
	}
	public void removeAccreditation(Accreditation a)
	{
		if(accreditations.contains(a))
		{
			accreditations.remove(a);
		}
	}
	public void removeLesson(Lesson l)
	{
		if(lessons.contains(l))
		{
			lessons.remove(l);
		}
	}
	
	public static boolean newInstructor(String name, String fname, String uname, int age, String psswrd, String sport, String level, boolean child, boolean time)
	{
		try
		{
			if(name.length() > 0 && fname.length() > 0 && uname.length() > 0 && age >= 18 && age <= 100 && psswrd.length() > 0 && sport.length() > 0 && level.length() > 0)
			{
				Instructor instructor = new Instructor(name, fname, psswrd, age, uname, sport, level, child, time);
				
				return instructor.addToDB();
			}
		}
		catch(Exception ex)
		{
			return false;
		}
		
		return false;
	}

	@Override
	public boolean addToDB() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Instructor> dao = adf.getInstructorDAO();
		
		boolean success = dao.create(this);
		
		if(success)
		{
			return lessons.getLast().addToDB();
		}
		
		return false;
	}
}
