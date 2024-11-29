package be.matt.examen.POJO;

import java.time.LocalDate;

public class Period {
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean isVacation;
	
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
