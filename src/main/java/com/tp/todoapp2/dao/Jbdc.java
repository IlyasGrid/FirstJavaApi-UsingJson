package com.tp.todoapp2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import com.tp.todoapp2.beans.Todo;
import com.tp.todoapp2.presentation.Main;

public class Jbdc {

	private Connection conn;

	public Jbdc() {
		connection();
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public void connection() {

		System.out.println("Connecting database...  ");

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/jeeintro", "root", "");
			System.out.println("Database connected!");
		} catch (SQLException e) {
			throw new IllegalStateException("Cannot connect the database!", e);
		}
	}

}
