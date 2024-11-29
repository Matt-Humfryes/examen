package be.matt.examen.POJO;

import java.util.ArrayList;

public class Instructor extends Person {
	private ArrayList<Accreditation> accreditations;
	private ArrayList<Lesson> lessons;
	
	public Instructor(String name, String firstname, String password, int age, String username) {
		super(name, firstname, password, age, username);
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
}
