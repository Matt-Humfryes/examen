package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import be.matt.examen.POJO.LessonType;

public class LessonTypeDAO extends DAO<LessonType> {

	public LessonTypeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(LessonType obj) {
		return false;
	}

	@Override
	public boolean delete(LessonType obj) {
		return false;
	}

	@Override
	public boolean update(LessonType obj) {
		return false;
	}

	@Override
	public LessonType find(int id) {
		return null;
	}

	@Override
	public ArrayList<LessonType> getAll() {
		Statement stat = null;
		ResultSet res = null;
		ArrayList<LessonType> list = new ArrayList<LessonType>();
		
		try
		{
			
			String check = "SELECT sport, level, forChildren, price FROM LessonType";
			stat = connect.createStatement();
			
			res = stat.executeQuery(check);
			
			while(res.next())
			{
				String sport = res.getString(1);
				String level = res.getString(2);
				boolean child = res.getBoolean(3);
				int price = res.getInt(4);
				
				LessonType found = new LessonType(sport, level, child, price);
				list.add(found);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}

}
