package be.matt.examen.DAO;

import java.sql.Connection;

import be.matt.examen.POJO.Lesson;

public class LessonDAO extends DAO<Lesson> {

	public LessonDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Lesson obj) {
		return false;
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

}
