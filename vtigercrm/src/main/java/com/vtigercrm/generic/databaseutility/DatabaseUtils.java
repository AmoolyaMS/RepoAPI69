package com.vtigercrm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtils {
	Connection connection;

	public void getConnection(String url,String username, String password) throws SQLException
	{ try {

		Driver drive=new Driver();
		DriverManager.registerDriver(drive);
		connection = DriverManager.getConnection(url, username, password);
	}
	catch (Exception e) { }//if url is having any exception
	}
	public void getConnection() throws SQLException
	{ try {

		Driver drive=new Driver();
		DriverManager.registerDriver(drive);
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
	}
	catch (Exception e) { }//if url is having any exception
	}

	public void closeConnection() throws SQLException {
		try {
			connection.close();
		} catch (Exception e) {
		}
	}
	public ResultSet executeSelectQuery(String query) throws SQLException {
		ResultSet resultset=null;
		try {
			Statement statement = connection.createStatement();
			resultset = statement.executeQuery(query);

		} catch (Exception e) {

		}
		return resultset;
	}
	public int executenonselectQuery(String query) throws SQLException {
		int result=0;
		try { 
			Statement statement = connection.createStatement();
			result = statement.executeUpdate(query);}
		catch (Exception e) {
		}
		return result;//returns +/-1 then pass if 0 its fail of data insertion
	}
	
}
