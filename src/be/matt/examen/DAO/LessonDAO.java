package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			
			String request = "INSERT INTO Lesson (duringMorning, studentAmount, instructorID, lessonTypeID, minBookings, maxBookings) VALUES ('" + obj.getMorning() + "','" + obj.getAmountStudent() + "','" + iID + "','" + ltID + "','" + obj.getMinBookings() + "','" + obj.getMaxBookings() + "')";
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
		return null;
	}

	@Override
	public ArrayList<Lesson> getAll() {
		return null;
	}

}
