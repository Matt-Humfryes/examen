package be.matt.examen.POJO;

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
}
