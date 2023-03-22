package com.tp.todoapp2.presentation.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;

import com.google.gson.Gson;
import com.tp.todoapp2.beans.Todo;
import com.tp.todoapp2.busness.DefaultService;

@WebServlet("/Todo")
public class ApiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Todo> todos = new ArrayList<>();

	public ApiServlet() {
		super();
		this.todos = DefaultService.getInstance().getAllTodos();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id") + "";
		Gson gson = new Gson();
		String json;
		
		// check if there is any id in the request url

		// no id found in the url
		if (id.equals("null")) {
			
			// parse all todos to json 
			json = gson.toJson(todos);


			//  Write the JSON string to the response with ok status 
			response.setStatus(200);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println(json);
		}
		// id found in url
		else {
			// search if this id exist in a todo in todos list
			for (Todo todo : todos) {
				if (todo.getId().equals(id)) {
					
					// get this todo with the same id as the url 
					json = gson.toJson(todo);
										
					// display the todo 
					response.setStatus(200);
					response.setHeader("Content-Type", "application/json");
					response.getOutputStream().println(json);
					return;
				}
			}
			// no todo with id same as url id
			response.setStatus(404);
			response.setHeader("Content-Type", "application/json");
			response.getOutputStream().println("id " + id + " not found");
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

		Todo todo =(Todo) req.getAttribute("newTodo");

		// add the todo to the todos list
		DefaultService.getInstance().addTodo(todo);

		// Set the response status code to 201 (added to todos)
		resp.setStatus(201);
		resp.setHeader("Content-Type", "application/json");
		resp.getOutputStream().println("data added successfuly");
	}

	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get the id from the request url
		String id = req.getParameter("id") + "";

		Gson gson = new Gson();
		
		// get the todo with the same id as the url id and parsed it to json
		String json = gson.toJson(DefaultService.getInstance().getTodo(id));
		
		// check if todo exist
		
		// todo doesn't exist
		if (json.equals("null")) {
			resp.setStatus(404);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(gson.toJson("Todo not found"));

		} // todo found
		else {
			// create new object using the parsed data 
			Todo todo = new Gson().fromJson(json, Todo.class);
			
			// delete todo from list todos
			DefaultService.getInstance().deleteTodo(todo);

			// display deleted  todo
			resp.setStatus(200);
			resp.setHeader("Content-Type", "application/json");
			resp.getOutputStream().println(json);
		}
	}

}
