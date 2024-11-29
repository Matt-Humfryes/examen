package be.matt.examen.DAO;

import java.sql.Connection;

import be.matt.examen.POJO.Skier;

public class SkierDAO extends DAO<Skier> {

	public SkierDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Skier obj) {
		return false;
	}

	@Override
	public boolean delete(Skier obj) {
		return false;
	}

	@Override
	public boolean update(Skier obj) {
		return false;
	}

	@Override
	public Skier find(int id) {
		return null;
	}

}
