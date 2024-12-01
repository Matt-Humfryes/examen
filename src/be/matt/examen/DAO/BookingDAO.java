package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import be.matt.examen.POJO.Booking;
import be.matt.examen.POJO.LessonType;

public class BookingDAO extends DAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Booking obj) {
		Statement stat = null;
		int res = 0;
		
		try
		{
			ResultSet resSet = null;
			int iID = 0;
			int lID = 0;
			int sID = 0;
			int pID = 0;
			int checkMax = 0;
			
			String getIDs = "SELECT ID FROM Instructor WHERE Username = '" + obj.getInstructor().getUsername() + "'";
			stat = connect.createStatement();
			resSet = stat.executeQuery(getIDs);
			
			while(resSet.next())
			{
				iID = resSet.getInt(1);
			}
			
			getIDs = "SELECT ID FROM Skier WHERE Username = '" + obj.getSkier().getUsername() + "'";
			stat = connect.createStatement();
			resSet = stat.executeQuery(getIDs);
			
			while(resSet.next())
			{
				sID = resSet.getInt(1);
			}
			
			LessonType lt = obj.getLesson().getLessonType();
			
			getIDs = "SELECT L.ID, L.maxBookings FROM Lesson L INNER JOIN LessonType LT ON L.LessonTypeID = LT.ID WHERE InstructorID = " + iID + " AND LT.sport = '" + lt.getSportName() + "' AND LT.level = '" + lt.getLevel() + "' AND LT.forChildren = " + lt.getChildCourse();
			stat = connect.createStatement();
			resSet = stat.executeQuery(getIDs);
			
			while(resSet.next())
			{
				lID = resSet.getInt(1);
				checkMax = resSet.getInt(2);
			}
			
			getIDs = "SELECT ID FROM Period WHERE begin = '" + obj.getPeriod().getStartDate() + "' AND end = '" + obj.getPeriod().getEndDate() + "'";
			stat = connect.createStatement();
			resSet = stat.executeQuery(getIDs);
			
			while(resSet.next())
			{
				pID = resSet.getInt(1);
			}
			
			boolean possible = true;
			
			String check = "SELECT ID FROM Booking WHERE SkierID = " + sID + " AND InstructorID = " + iID + " AND PeriodID = " + pID + " AND LessonID = " + lID;
			stat = connect.createStatement();
			resSet = stat.executeQuery(check);
			
			if(resSet.next())
			{
				possible = false;
			}
			
			String amount = "SELECT B.LessonID, P.end FROM Booking B INNER JOIN Period P ON B.PeriodID = P.ID WHERE LessonID = " + lID;
			int students = 0;
			
			stat = connect.createStatement();
			
			resSet = stat.executeQuery(amount);
			
			while(resSet.next())
			{
				LocalDate date = resSet.getDate(2).toLocalDate();
				
				if(date.isAfter(LocalDate.now()))
				{
					checkMax--;
				}
			}
			
			if(possible && checkMax > 0)
			{
				String request = "INSERT INTO Booking (SkierID, InstructorID, PeriodID, LessonID) VALUES (" + sID + "," + iID + "," + pID + "," + lID +")";
				stat = connect.createStatement();
				
				res = stat.executeUpdate(request);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return res > 0;
	}

	@Override
	public boolean delete(Booking obj) {
		return false;
	}

	@Override
	public boolean update(Booking obj) {
		return false;
	}

	@Override
	public Booking find(int id) {
		return null;
	}

	@Override
	public ArrayList<Booking> getAll() {
		return null;
	}

}
