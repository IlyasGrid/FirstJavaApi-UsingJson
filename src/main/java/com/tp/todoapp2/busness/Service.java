package com.tp.todoapp2.busness;

import java.util.List;

import com.tp.todoapp2.beans.Todo;

public interface Service {

	public Todo addTodo(Todo todo);
	public Todo updateTodo(Todo todo);
	public Todo deleteTodo(Todo todo);
	public Todo getTodo(String id);
	public List<Todo> getAllTodos();
}
