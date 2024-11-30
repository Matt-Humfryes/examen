package be.matt.examen.POJO;

import java.util.ArrayList;

import be.matt.examen.SchedulePage;
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
	
	public Instructor(String name, String firstname, int age, String username, String sport, String level, boolean child, int price, Lesson l){
		super(name, firstname, "", age, username);
		
		this.setLessonType(sport, level, child, price, l);
	}
	
	public Instructor(String name, String firstname, String password, int age, String username) {
		super(name, firstname, password, age, username);
		
		accreditations = new ArrayList<Accreditation>();
		lessons = new ArrayList<Lesson>();
		
		this.getCourseFromDB();
	}
	
	private void setLessonType(String sport, String level, boolean child, int price, Lesson l)
	{
		lessons = new ArrayList<Lesson>();
		lessons.add(l);
		accreditations = new ArrayList<Accreditation>();
		
		Accreditation accreditation = new Accreditation(sport, level, child, price);
		accreditations.add(accreditation);
		
		LessonType lt = accreditations.get(0).getLessonType(0);
		lessons.getLast().setLessonType(lt);
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
	
	public static ArrayList<Lesson> getSchedule(String uname, String psswrd)
	{
		ArrayList<Lesson> list = new ArrayList<Lesson>();
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Instructor> daoI = adf.getInstructorDAO();
		
		for(Instructor i: daoI.getAll())
		{
			if(uname.equals(i.getUsername()) && psswrd.equals(i.getPassword()))
			{
				String name = i.getName();
				String fname = i.getFirstname();
				int age = i.getAge();
				
				Instructor instructor = new Instructor(name, fname, psswrd, age, uname);
				
				list = instructor.getLessons();
			}
		}
		
		return list;
	}
	
	public void getCourseFromDB()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Lesson> dao = adf.getLessonDAO();
		
		for(Lesson l : dao.getAll())
		{
			if(l.getInstructor().getUsername().equals(this.getUsername()))
			{
				String sport = l.getLessonType().getSportName();
				String level = l.getLessonType().getLevel();
				boolean child = l.getLessonType().getChildCourse();
				int price = l.getLessonType().getPrice();
				
				int min = l.getMinBookings();
				int max = l.getMaxBookings();
				boolean time = l.getMorning();
				int students = l.getAmountStudent();
				
				boolean already = false;
				
				String firstHalf = sport;
				String secondHalf;
				
				if(child)
				{
					secondHalf = "Child";
				}
				else
				{
					secondHalf = "Adult";
				}
				
				String nameA = firstHalf + secondHalf;
				
				Accreditation a = new Accreditation(sport, level, child, price);
				
				for(Accreditation aCheck : accreditations)
				{
					if(aCheck.getName().equals(nameA))
					{
						already = true;
						
						LessonType lt = a.getLessonType(0);
						
						aCheck.addLessonType(lt);
						
						Lesson lesson = new Lesson(this, lt, min, max, time, students);
						lessons.add(lesson);
					}
				}
				
				if(!already)
				{
					accreditations.add(a);
					
					LessonType lt = accreditations.getLast().getLessonType(0);
					
					Lesson lesson = new Lesson(this, lt, min, max, time, students);
					lessons.add(lesson);
				}
			}
		}
	}
	
	public static boolean newCourse(String sport, String level, boolean child, boolean time, Instructor i)
	{
		if(sport.length() > 0 && level.length() > 0 && (!i.isAccreditate(sport, child)))
		{
			try
			{
				return i.addCourse(sport, level, child, time);
			}
			catch(Exception ex)
			{
				return false;
			}
		}
		
		return false;
	}
	
	public boolean isAccreditate(String sport, boolean child)
	{
		boolean result = false;
		
		for(Accreditation a : accreditations)
		{
			for(LessonType lt : a.getLessonTypes())
			{
				if(lt.getChildCourse() == child && lt.getSportName().equals(sport))
				{
					result = true;
				}
			}
		}
		
		return result;
	}
	
	public boolean addCourse(String sport, String level, boolean child, boolean time) throws Exception
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
		
		Lesson l = new Lesson(this, sport, level, child, price, min, max, time);
		
		lessons.add(l);
		
		LessonType lt = lessons.getLast().getLessonType();
		Accreditation a = new Accreditation(lt);
		
		accreditations.add(a);
		
		return lessons.getLast().addToDB();
	}
}
