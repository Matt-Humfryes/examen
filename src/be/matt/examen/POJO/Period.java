package be.matt.examen.POJO;

import java.time.LocalDate;

import be.matt.examen.DAO.AbstractDAOFactory;
import be.matt.examen.DAO.DAO;

public class Period {
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean isVacation;
	
	public Period(LocalDate begin, LocalDate end) throws Exception
	{
		startDate = begin;
		endDate = end;
		
		if(begin.isAfter(LocalDate.of(2024, 12, 21)) && begin.isBefore(LocalDate.of(2025, 1, 4)))
		{
			isVacation = true;
		}
		
		if(begin.isAfter(LocalDate.of(2025, 3, 1)) && begin.isBefore(LocalDate.of(2025, 3, 8)))
		{
			isVacation = true;
		}
		
		if(begin.isAfter(LocalDate.of(2025, 4, 12)) && begin.isBefore(LocalDate.of(2025, 4, 26)))
		{
			isVacation = true;
		}
		
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		DAO<Period> dao = adf.getPeriodDAO();
		dao.create(this);
	}
	
	public LocalDate getStartDate()
	{
		return startDate;
	}
	public LocalDate getEndDate()
	{
		return endDate;
	}
	public boolean getVacation()
	{
		return isVacation;
	}
	
	public void setStartDate(LocalDate val)
	{
		startDate = val;
	}
	public void setEndDate(LocalDate val)
	{
		endDate = val;
	}
	public void setVacation(boolean val)
	{
		isVacation = val;
	}
}
