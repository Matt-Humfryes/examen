package be.matt.examen.POJO;

public class LessonType {
	private String sportName;
	private String level;
	private int price;
	private boolean isChildCourse;
	
	public LessonType(String sport, String level, boolean child, int price)
	{
		this.sportName = sport;
		this.level = level;
		this.isChildCourse = child;
		this.price = price;
	}
	
	public String getSportName()
	{
		return sportName;
	}
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
	
	public void setSportName(String val)
	{
		sportName = val;
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
