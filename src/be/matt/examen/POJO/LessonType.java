package be.matt.examen.POJO;

public class LessonType {
	private String level;
	private int price;
	private boolean isChildCourse;
	
	public String getLevel()
	{
		return level;
	}
	public int getPrice()
	{
		return price;
	}
	public boolean getChildCourse()
	{
		return isChildCourse;
	}
	
	public void setLevel(String val)
	{
		level = val;
	}
	public void setPrice(int val)
	{
		price = val;
	}
	public void setChildCourse(boolean val)
	{
		isChildCourse = val;
	}
}
