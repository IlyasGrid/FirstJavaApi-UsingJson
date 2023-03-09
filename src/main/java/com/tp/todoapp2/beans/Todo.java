package com.tp.todoapp2.beans;

public class Todo {

	private String id;
	private String title;
	private boolean completed;

	public Todo() {

	}

	public Todo( String title, boolean completed) {
		super();
		this.title = title;
		this.completed = completed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
