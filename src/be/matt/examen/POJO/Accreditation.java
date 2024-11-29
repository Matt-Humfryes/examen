package be.matt.examen.POJO;

import java.util.ArrayList;

public class Accreditation {
	private String name;
	
	private ArrayList<LessonType> lessonTypes;
	
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
}
