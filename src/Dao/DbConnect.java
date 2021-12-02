package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnect {

// Code database URL
	static final String DB_URL = "jdbc:mysql://www.papademas.net:3307/510fp?autoReconnect=true&useSSL=false";
// Database credentials
	static final String USER = "fp510", PASS = "510";

	public static Connection connect() throws SQLException {

		return DriverManager.getConnection(DB_URL, USER, PASS);

	}

	public PreparedStatement prepareStatement(String sqlQuery) throws SQLException {
		return connect().prepareStatement(sqlQuery);
	}
	
	public PreparedStatement prepareStatement(String sqlQuery, int stmt) throws SQLException {
		return connect().prepareStatement(sqlQuery, stmt);
	}	
}
