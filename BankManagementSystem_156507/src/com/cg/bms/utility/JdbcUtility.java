package com.cg.bms.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.bms.exceptions.BMSException;

public class JdbcUtility {

	private static Connection connection = null;

	public static Connection getConnection() throws BMSException {

		File file = new File("resources/jdbc.properties");
		FileInputStream inputStream = null;
		Properties properties = null;

		try {
			inputStream = new FileInputStream(file);
			properties = new Properties();

			properties.load(inputStream);

			String driver = properties.getProperty("db.driver");
			String url = properties.getProperty("db.url");
			String username = properties.getProperty("db.username");
			String password = properties.getProperty("db.password");

			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);

		} catch (FileNotFoundException e) {
			throw new BMSException("file not present");
		} catch (IOException e) {
			throw new BMSException("unable to read the data from the file");
		} catch (ClassNotFoundException e) {
			throw new BMSException("class not loaded");
		} catch (SQLException e) {
			throw new BMSException("problem with the connection");
		}
		return connection;
	}
}
