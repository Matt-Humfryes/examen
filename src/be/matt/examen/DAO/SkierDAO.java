package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

}
