package com.tp.todoapp2.dao;

import java.util.List;
import java.util.Vector;

import com.tp.todoapp2.beans.Todo;

public class TodoDaoMemory implements TodoDao {

	private List<Todo> todos;
	private int lastIndex = 0;

	public TodoDaoMemory() {
		todos = new Vector<Todo>();
		save(new Todo("rdv dentiste", false));
		save(new Todo("rdv docteur", true));
	}

	@Override
	public Todo save(Todo todo) {
		lastIndex++;
		todo.setId(lastIndex + "");
		todos.add(todo);
		return todo;
	}

	@Override
	public Todo get(String id) {

		for (Todo element : todos)
			if (element.getId().equals(id))
				return element;
		return null;
	}

	@Override
	public List<Todo> getAll() {
		return todos;
	}

	@Override
	public Todo update(Todo todo) {
		Todo u = get(todo.getId());
		if (u == null)
			return null;

		u.setCompleted(todo.isCompleted());
		u.setTitle(todo.getTitle());
		return u;
	}

	@Override
	public Todo delete(Todo todo) {
		Todo element = get(todo.getId());
		if (element == null)
			return null;

		todos.remove(element);
		return element;
	}

}
