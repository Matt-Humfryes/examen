package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import be.matt.examen.POJO.Lesson;
import be.matt.examen.POJO.LessonType;

public class LessonDAO extends DAO<Lesson> {

	public LessonDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Lesson obj) {
		Statement stat = null;
		int resL = 0;
		int resA = 0;
		
		try
		{
			ResultSet resSet = null;
			int iID = 0;
			int ltID = 0;
			int aID = 0;
			
			String getIDs = "SELECT ID FROM Instructor WHERE Username = '" + obj.getInstructor().getUsername() + "'";
			stat = connect.createStatement();
			resSet = stat.executeQuery(getIDs);
			
			while(resSet.next())
			{
				iID = resSet.getInt(1);
			}
			
			LessonType lt = obj.getLessonType();
			
			getIDs = "SELECT ID, AccreditationID FROM LessonType WHERE sport = '" + lt.getSportName() + "' AND level = '" + lt.getLevel() + "' AND forChildren = '" + lt.getChildCourse() + "' AND price = '" + lt.getPrice() + "'";
			stat = connect.createStatement();
			resSet = stat.executeQuery(getIDs);
			
			while(resSet.next())
			{
				ltID = resSet.getInt(1);
				aID = resSet.getInt(2);
			}
			
			String request = "INSERT INTO Lesson (duringMorning, instructorID, lessonTypeID, minBookings, maxBookings) VALUES ('" + obj.getMorning() + "','" + iID + "','" + ltID + "','" + obj.getMinBookings() + "','" + obj.getMaxBookings() + "')";
			stat = connect.createStatement();
			
			resL = stat.executeUpdate(request);
			
			if(resL > 0)
			{
				request = "INSERT INTO InstructorAccreditation (InstructorID, AccreditationID) VALUES ('" + iID + "','" + aID +"')";
				stat = connect.createStatement();
				
				resA = stat.executeUpdate(request);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return (resL > 0) && (resA > 0);
	}

	@Override
	public boolean delete(Lesson obj) {
		return false;
	}

	@Override
	public boolean update(Lesson obj) {
		return false;
	}

	@Override
	public Lesson find(int id) {
		Statement stat = null;
		ResultSet res = null;
		Lesson l = null;
		
		try
		{
			
			String check = "SELECT L.minBookings, L.maxBookings, L.duringMorning, LT.sport, LT.level, LT.forChildren, LT.price, L.InstructorID, L.ID FROM Lesson L INNER JOIN LessonType LT ON L.LessonTypeID = LT.ID WHERE L.ID = " + id;
			stat = connect.createStatement();
			
			res = stat.executeQuery(check);
			
			while(res.next())
			{
				int min = res.getInt(1);
				int max = res.getInt(2);
				boolean time = res.getBoolean(3);
				
				String sport = res.getString(4);
				String level = res.getString(5);
				boolean child = res.getBoolean(6);
				int price = res.getInt(7);
				
				int instructorId = res.getInt(8);
				
				Statement stat2 = null;
				ResultSet res2 = null;
				
				String amount = "SELECT B.LessonID, P.end FROM Booking B INNER JOIN Period P ON B.PeriodID = P.ID WHERE LessonID = " + res.getInt(9);
				int students = 0;
				
				stat2 = connect.createStatement();
				
				res2 = stat2.executeQuery(amount);
				
				while(res2.next())
				{
					LocalDate date = res2.getDate(2).toLocalDate();
					
					if(date.isAfter(LocalDate.now()))
					{
						students++;
					}
				}
				
				l = new Lesson(min, max, time, sport, level, child, price, instructorId, students);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return l;
	}

	@Override
	public ArrayList<Lesson> getAll() {
		Statement stat = null;
		ResultSet res = null;
		ArrayList<Lesson> list = new ArrayList<Lesson>();
		
		try
		{
			
			String check = "SELECT L.minBookings, L.maxBookings, L.duringMorning, LT.sport, LT.level, LT.forChildren, LT.price, L.InstructorID, L.ID FROM Lesson L INNER JOIN LessonType LT ON L.LessonTypeID = LT.ID";
			stat = connect.createStatement();
			
			res = stat.executeQuery(check);
			
			while(res.next())
			{
				int min = res.getInt(1);
				int max = res.getInt(2);
				boolean time = res.getBoolean(3);
				
				String sport = res.getString(4);
				String level = res.getString(5);
				boolean child = res.getBoolean(6);
				int price = res.getInt(7);
				
				int instructorId = res.getInt(8);
				
				Statement stat2 = null;
				ResultSet res2 = null;
				
				String amount = "SELECT B.LessonID, P.end FROM Booking B INNER JOIN Period P ON B.PeriodID = P.ID WHERE LessonID = " + res.getInt(9);
				int students = 0;
				
				stat2 = connect.createStatement();
				
				res2 = stat2.executeQuery(amount);
				
				while(res2.next())
				{
					LocalDate date = res2.getDate(2).toLocalDate();
					
					if(date.isAfter(LocalDate.now()))
					{
						students++;
					}
				}
				
				Lesson l = new Lesson(min, max, time, sport, level, child, price, instructorId, students);
				list.add(l);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return list;
	}

}
