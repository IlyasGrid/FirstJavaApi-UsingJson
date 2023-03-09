package com.tp.todoapp2.dao;

import java.util.List;

import com.tp.todoapp2.beans.Todo;

public interface TodoDao {
	Todo save(Todo todo);

	Todo get(String id);

	List<Todo> getAll();

	Todo update(Todo todo);

	Todo delete(Todo todo);
}
