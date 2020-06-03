package com.example.crud.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Configuration {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/product-manager";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "";

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

	}

	public static void close(ResultSet rs) {
		try {

			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void close(PreparedStatement stmt) {
		try {
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void close(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
