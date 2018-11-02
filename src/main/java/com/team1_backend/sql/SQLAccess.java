package com.team1_backend.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLAccess {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public String readDataBase(String table, String id) throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Setup the connection with the DB
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/OURDATABASE?" + "user=sqluser&password=sqluserpw");

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			
			// PreparedStatements can use variables and are more efficient
            preparedStatement = connect
                    .prepareStatement("SELECT * from ? WHERE auth0-id = ?");
            preparedStatement.setString(1, table);
            preparedStatement.setString(2, id);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()){
            	
            }
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
		return null;
	}

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}

}
