package com.tp.todoapp2.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import com.tp.todoapp2.beans.Todo;

public class TodoDaoJdbc extends Jbdc implements TodoDao {

	@Override
	public Todo save(Todo todo) {
		String sql = "INSERT INTO  todo (`title`, `completed`) VALUES('" + todo.getTitle() + "', " + todo.isCompleted()
				+ ")";
		try {
			Statement stmt = getConn().createStatement();
			stmt.executeUpdate(sql);

			return todo;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Todo get(String id) {
		String sql = "SELECT * FROM `todo` where id=" + id + ";";
		Todo todo;
		try {
			PreparedStatement pstmt = getConn().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);

			int idtodo = rs.getInt("id");
			String title = rs.getString("title");
			Boolean completed = rs.getBoolean("completed");
			System.out.println("id: " + id + " title: " + title + " completed: " + completed + "\n");
			todo = new Todo(title, completed);
			return todo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Todo> getAll() {

		String sql = "SELECT * FROM `todo`";
		List<Todo> todos = new Vector<Todo>();
		try {
			PreparedStatement pstmt = getConn().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery(sql);
			while (rs.next()) {

				int id = rs.getInt("id");
				String title = rs.getString("title");
				Boolean completed = rs.getBoolean("completed");
				System.out.println("id: " + id + " title: " + title + " completed: " + completed + "\n");
				todos.add(new Todo(title, completed));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return todos;

	}

	@Override
	public Todo update(Todo todo) {
		String sql = "UPDATE todo SET title =" + todo.getTitle() + "completed = " + todo.isCompleted() + " ;";
		try {
			Statement stmt = getConn().createStatement();
			stmt.executeUpdate(sql);
			return todo;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Todo delete(Todo todo) {
		String sql = "delete from todo where title ='" + todo.getTitle() + "'and completed =" + todo.isCompleted()
				+ ";";
		try {
			Statement stmt = getConn().createStatement();
			stmt.executeUpdate(sql);
			return todo;
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	}

}
