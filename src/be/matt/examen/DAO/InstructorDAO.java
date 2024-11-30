package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.matt.examen.POJO.Instructor;

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
		return null;
	}

	@Override
	public ArrayList<Instructor> getAll() {
		return null;
	}

}
