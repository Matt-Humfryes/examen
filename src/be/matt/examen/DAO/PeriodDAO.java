package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import be.matt.examen.POJO.Period;

public class PeriodDAO extends DAO<Period> {

	public PeriodDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Period obj) {
		Statement stat = null;
		int res = 0;
		
		try
		{
			boolean possible = true;
			ResultSet resSet = null;
			
			String check = "SELECT begin, end FROM Period";
			stat = connect.createStatement();
			
			resSet = stat.executeQuery(check);
			
			while(resSet.next())
			{
				LocalDate begin = resSet.getDate(1).toLocalDate();
				LocalDate end = resSet.getDate(2).toLocalDate();
				
				if(obj.getStartDate().isEqual(begin) && obj.getEndDate().isEqual(end))
				{
					possible = false;
				}
			}
			
			if(obj.getStartDate().isEqual(LocalDate.of(2025, 5, 3)) || obj.getEndDate().isEqual(LocalDate.of(2025, 5, 3)))
			{
				possible = false;
			}
			
			if(possible)
			{
				String request = "INSERT INTO Period (begin, end, isVacation) VALUES ('" + obj.getStartDate() + "','" + obj.getEndDate() + "','" + obj.getVacation() + "')";
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
	public boolean delete(Period obj) {
		return false;
	}

	@Override
	public boolean update(Period obj) {
		return false;
	}

	@Override
	public Period find(int id) {
		return null;
	}

	@Override
	public ArrayList<Period> getAll() {
		return null;
	}

}
