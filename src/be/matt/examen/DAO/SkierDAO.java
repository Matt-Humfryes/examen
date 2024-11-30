package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.matt.examen.POJO.LessonType;
import be.matt.examen.POJO.Skier;

public class SkierDAO extends DAO<Skier> {

	public SkierDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Skier obj) {
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
				String request = "INSERT INTO Skier (Lastname, Firstname, Username, Password, Age) VALUES ('" + obj.getName() + "','" + obj.getFirstname() + "','" + obj.getUsername() + "','" + obj.getPassword() + "','" + obj.getAge() +"')";
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
	public boolean delete(Skier obj) {
		return false;
	}

	@Override
	public boolean update(Skier obj) {
		return false;
	}

	@Override
	public Skier find(int id) {
		return null;
	}

	@Override
	public ArrayList<Skier> getAll() {
		Statement stat = null;
		ResultSet res = null;
		ArrayList<Skier> list = new ArrayList<Skier>();
		
		try
		{
			
			String check = "SELECT Lastname, Firstname, Username, Age, Password FROM Skier";
			stat = connect.createStatement();
			
			res = stat.executeQuery(check);
			
			while(res.next())
			{
				String name = res.getString(1);
				String firstname = res.getString(2);
				String username = res.getString(3);
				int age = res.getInt(4);
				String password = res.getString(5);
				
				Skier skier = new Skier(name, firstname, password, age, username);
				list.add(skier);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}

}
