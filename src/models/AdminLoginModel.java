package models;

import java.sql.*;
import utility.HashPassword;

import Dao.DbConnect;


public class AdminLoginModel {
	Connection connection;
	public AdminLoginModel() throws SQLException
	{
		connection = DbConnect.connect();
		if(connection == null)
		{
			System.exit(1);
		}
	}
	
	public boolean isDBConnected()
	{
		try 
		{
			return !connection.isClosed();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isLogin(String user, String pass) throws SQLException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String query ="select * from 510fp.shivani_shah_adminlogin where username = ?";
		try 
		{
			stmt = connection.prepareStatement(query);
			stmt.setString(1,user);
			//stmt.setString(2,pass);
			
			rs = stmt.executeQuery();
			if(rs.next())
			{
				String hashedPwd = rs.getString("password");
				
				return HashPassword.CheckIfPasswordCorrect(pass, hashedPwd);
				
				//return true;
			}
			else
			{
				return false;
			}
			
			
		}catch(Exception e)
		{
			return false;
		}finally
		{
			stmt.close();
			rs.close();
		}
	}
	
	
}