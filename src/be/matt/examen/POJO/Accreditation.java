package be.matt.examen.POJO;

import java.util.ArrayList;

public class Accreditation {
	private String name;
	
	private ArrayList<LessonType> lessonTypes;
	
	public Accreditation(String sport, String level, boolean child, int price)
	{
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
		
		this.name = firstHalf + secondHalf;
		
		LessonType lessonType = new LessonType(sport, level, child, price);
		lessonTypes = new ArrayList<LessonType>();
		
		lessonTypes.add(lessonType);
	}
	
	public Accreditation(LessonType lt)
	{
		String firstHalf = lt.getSportName();
		String secondHalf;
		
		if(lt.getChildCourse())
		{
			secondHalf = "Child";
		}
		else
		{
			secondHalf = "Adult";
		}
		
		this.name = firstHalf + secondHalf;
		
		lessonTypes = new ArrayList<LessonType>();
		lessonTypes.add(lt);
	}
	
	public String getName()
	{
		return name;
	}
	public ArrayList<LessonType> getLessonTypes()
	{
		return lessonTypes;
	}
	
	public void setName(String val)
	{
		name = val;
	}
	public void setLessonTypes(ArrayList<LessonType> val)
	{
		lessonTypes = val;
	}
	
	public void addLessonType(LessonType lt)
	{
		if(!lessonTypes.contains(lt))
		{
			lessonTypes.add(lt);
		}
	}
	public void removeLessonType(LessonType lt)
	{
		if(lessonTypes.contains(lt))
		{
			lessonTypes.remove(lt);
		}
	}
	
	public LessonType getLessonType(int target)
	{
		return lessonTypes.get(target);
	}
}
