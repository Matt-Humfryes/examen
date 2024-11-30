package be.matt.examen.DAO;

import java.sql.Connection;
import java.util.ArrayList;

import be.matt.examen.POJO.Period;

public class PeriodDAO extends DAO<Period> {

	public PeriodDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Period obj) {
		return false;
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
