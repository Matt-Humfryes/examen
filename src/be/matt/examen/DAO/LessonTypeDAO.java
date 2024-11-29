package be.matt.examen.DAO;

import java.sql.Connection;

import be.matt.examen.POJO.LessonType;

public class LessonTypeDAO extends DAO<LessonType> {

	public LessonTypeDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(LessonType obj) {
		return false;
	}

	@Override
	public boolean delete(LessonType obj) {
		return false;
	}

	@Override
	public boolean update(LessonType obj) {
		return false;
	}

	@Override
	public LessonType find(int id) {
		return null;
	}

}
