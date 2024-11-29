package be.matt.examen.DAO;

import java.sql.Connection;

import be.matt.examen.POJO.Accreditation;

public class AccreditationDAO extends DAO<Accreditation> {

	public AccreditationDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Accreditation obj) {
		return false;
	}

	@Override
	public boolean delete(Accreditation obj) {
		return false;
	}

	@Override
	public boolean update(Accreditation obj) {
		return false;
	}

	@Override
	public Accreditation find(int id) {
		return null;
	}

}
