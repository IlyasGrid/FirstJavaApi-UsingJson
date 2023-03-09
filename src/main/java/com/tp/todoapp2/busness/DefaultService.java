package com.tp.todoapp2.busness;

import java.util.List;

import com.tp.todoapp2.beans.Todo;
import com.tp.todoapp2.dao.TodoDao;
import com.tp.todoapp2.dao.TodoDaoMemory;



public class DefaultService implements Service{
	private static DefaultService instance = null;
	public static DefaultService getInstance() {
		if(instance==null)
			instance=new DefaultService(new TodoDaoMemory());
		return instance;
	}
	
	private TodoDao todoDao;
	private DefaultService(TodoDao todoDao) {
		this.todoDao=todoDao;
	}
	@Override
	public Todo addTodo(Todo todo) {
		return todoDao.save(todo);
	}

	@Override
	public Todo updateTodo(Todo todo) {
		return todoDao.update(todo);
	}

	@Override
	public Todo deleteTodo(Todo todo) {
		return todoDao.delete(todo);
	}

	@Override
	public Todo getTodo(String id) {
		return todoDao.get(id);
	}

	@Override
	public List<Todo> getAllTodos() {
		return todoDao.getAll();
	}

}
