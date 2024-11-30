package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.matt.examen.POJO.Instructor;
import be.matt.examen.POJO.Skier;

public class InstructorDAO extends DAO<Instructor> {

	public InstructorDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Instructor obj) {
		Statement stat = null;
		int res = 0;
		
		try
		{
			boolean possible = true;
			ResultSet resSet = null;
			
			String check = "SELECT Username FROM Skier WHERE Username = '" + obj.getUsername() + "'";
			stat = connect.createStatement();
			
			resSet = stat.executeQuery(check);
			
			if(resSet.next())
			{
				possible = false;
			}
			
			check = "SELECT Username FROM Instructor WHERE Username = '" + obj.getUsername() + "'";
			stat = connect.createStatement();
			
			resSet = stat.executeQuery(check);
			
			if(resSet.next())
			{
				possible = false;
			}
			
			if(possible)
			{
				String request = "INSERT INTO Instructor (Lastname, Firstname, Username, Password, Age) VALUES ('" + obj.getName() + "','" + obj.getFirstname() + "','" + obj.getUsername() + "','" + obj.getPassword() + "','" + obj.getAge() +"')";
				stat = connect.createStatement();
				
				res = stat.executeUpdate(request);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return (res > 0);
	}

	@Override
	public boolean delete(Instructor obj) {
		return false;
	}

	@Override
	public boolean update(Instructor obj) {
		return false;
	}

	@Override
	public Instructor find(int id) {
		Statement stat = null;
		ResultSet res = null;
		Instructor i = null;
		
		try
		{
			
			String check = "SELECT I.Lastname, I.Firstname, I.Username, I.Age, I.Password, LT.sport, LT.level, LT.forChildren, L.duringMorning FROM Instructor I INNER JOIN Lesson L ON I.ID = L.InstructorID INNER JOIN LessonType LT ON LT.ID = L.LessonTypeID WHERE I.ID = " + id;
			stat = connect.createStatement();
			
			res = stat.executeQuery(check);
			
			while(res.next())
			{
				String name = res.getString(1);
				String firstname = res.getString(2);
				String username = res.getString(3);
				int age = res.getInt(4);
				String password = res.getString(5);
				
				String sport = res.getString(6);
				String level = res.getString(7);
				boolean child = res.getBoolean(8);
				boolean time = res.getBoolean(9);
				
				i = new Instructor(name, firstname, password, age, username, sport, level, child, time);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public ArrayList<Instructor> getAll() {
		Statement stat = null;
		ResultSet res = null;
		ArrayList<Instructor> list = new ArrayList<Instructor>();
		
		try
		{
			
			String check = "SELECT I.Lastname, I.Firstname, I.Username, I.Age, I.Password, LT.sport, LT.level, LT.forChildren, L.duringMorning FROM Instructor I INNER JOIN Lesson L ON I.ID = L.InstructorID INNER JOIN LessonType LT ON LT.ID = L.LessonTypeID";
			stat = connect.createStatement();
			
			res = stat.executeQuery(check);
			
			while(res.next())
			{
				String name = res.getString(1);
				String firstname = res.getString(2);
				String username = res.getString(3);
				int age = res.getInt(4);
				String password = res.getString(5);
				
				String sport = res.getString(6);
				String level = res.getString(7);
				boolean child = res.getBoolean(8);
				boolean time = res.getBoolean(9);
				
				Instructor instructor = new Instructor(name, firstname, password, age, username, sport, level, child, time);
				list.add(instructor);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return list;
	}

}
