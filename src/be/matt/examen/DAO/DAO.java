package be.matt.examen.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class DAO<T> {
	protected Connection connect = null;
	
	public DAO(Connection conn)
	{
		this.connect = conn;
	}
	
	public abstract boolean create(T obj);
	public abstract boolean delete(T obj);
	public abstract boolean update(T obj);
	public abstract T find(int id);
	public abstract ArrayList<T> getAll();
}
