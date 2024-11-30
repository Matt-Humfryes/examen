package be.matt.examen.POJO;

import be.matt.examen.GetAllCourses;
import be.matt.examen.DAO.AbstractDAOFactory;
import be.matt.examen.DAO.DAO;

public abstract class Person {
	private String name;
	private String firstname;
	private String password;
	private int age;
	private String username;
	
	public Person(String name, String firstname, String password, int age, String username)
	{
		this.name = name;
		this.firstname = firstname;
		this.password = password;
		this.age = age;
		this.username = username;
	}
	
	public abstract boolean addToDB();
	
	public String getName()
	{
		return name;
	}
	public String getFirstname()
	{
		return firstname;
	}
	public String getPassword()
	{
		return password;
	}
	public int getAge()
	{
		return age;
	}
	public String getUsername()
	{
		return username;
	}
	
	public void setName(String val)
	{
		name = val;
	}
	public void setFirstname(String val)
	{
		firstname = val;
	}
	public void setPassword(String val)
	{
		password = val;
	}
	public void setAge(int val)
	{
		age = val;
	}
	public void setUsername(String val)
	{
		username = val;
	}
	
	public static String checkLog(String uname, String psswrd)
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Skier> daoS = adf.getSkierDAO();
		DAO<Instructor> daoI = adf.getInstructorDAO();
		
		for(Skier i : daoS.getAll())
		{
			if(uname.equals(i.getUsername()) && psswrd.equals(i.getPassword()))
			{
				Skier skier = new Skier(i.getName(), i.getFirstname(), psswrd, i.getAge(), uname);
				
				GetAllCourses.askCourses(skier);
				
				return "skier";
			}
		}
		
		for(Instructor i: daoI.getAll())
		{
			if(uname.equals(i.getUsername()) && psswrd.equals(i.getPassword()))
			{
				return "instructor";
			}
		}
		
		return "none";
	}
}
