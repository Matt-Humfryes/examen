package be.matt.examen.DAO;

import java.sql.Connection;

import be.matt.examen.POJO.Instructor;

public class InstructorDAO extends DAO<Instructor> {

	public InstructorDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Instructor obj) {
		return false;
	}

	@Override
	public boolean delete(Instructor obj) {
		return false;
	}

	@Override
	public boolean update(Instructor obj) {
		return false;
	}

	@Override
	public Instructor find(int id) {
		return null;
	}

}
