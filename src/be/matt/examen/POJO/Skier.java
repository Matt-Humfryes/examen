package be.matt.examen.POJO;

import java.util.ArrayList;

import be.matt.examen.DAO.AbstractDAOFactory;
import be.matt.examen.DAO.DAO;

public class Skier extends Person {
	private ArrayList<Booking> bookings;
	
	public Skier(String name, String firstname, String password, int age, String username) {
		super(name, firstname, password, age, username);
		
		bookings = new ArrayList<Booking>();
	}
	
	public ArrayList<Booking> getBookings()
	{
		return bookings;
	}
	
	public void setBookings(ArrayList<Booking> val)
	{
		bookings = val;
	}
	
	public void addBooking(Booking b)
	{
		if(!bookings.contains(b))
		{
			bookings.add(b);
		}
	}
	public void removeBooking(Booking b)
	{
		if(bookings.contains(b))
		{
			bookings.remove(b);
		}
	}
	
	public static boolean newUser(String name, String fname, String uname, int age, String psswrd)
	{
		if(name.length() > 0 && fname.length() > 0 && uname.length() > 0 && age >= 4 && age <= 100 && psswrd.length() > 0)
		{
			Skier skier = new Skier(name, fname, psswrd, age, uname);
			return skier.addToDB();
		}
		
		return false;
	}
	
	@Override
	public boolean addToDB()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Skier> dao = adf.getSkierDAO();
		
		return dao.create(this);
	}
	
	public ArrayList<Lesson> getJoinedCourses()
	{
		this.resetBookingsList();
		ArrayList<Lesson> llb = new ArrayList<Lesson>();
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Booking> dao = adf.getBookingDAO();
		
		for(Booking b : dao.getAll())
		{
			if(b.getSkier().getUsername().equals(this.getUsername()))
			{
				bookings.add(b);
				
				Lesson l = bookings.getLast().getLesson();
				llb.add(l);
			}
		}
		
		return llb;
	}
	
	public void resetBookingsList()
	{
		bookings.clear();
	}
}
