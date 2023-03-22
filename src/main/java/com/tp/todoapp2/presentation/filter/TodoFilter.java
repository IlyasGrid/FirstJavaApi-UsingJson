package com.tp.todoapp2.presentation.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.tp.todoapp2.beans.Todo;

@WebFilter("/Todo/*")
public class TodoFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (req.getMethod().toLowerCase().equals("post") || req.getMethod().toLowerCase().equals("put")) {

			// Get the JSON payload from the request body
			BufferedReader reader = request.getReader();

			// create a new instance of Todo with the parsed data
			Todo todo = new Gson().fromJson(reader, Todo.class);
			System.out.println(todo.toString());

			request.setAttribute("newTodo", todo);
			// pass the request along the filter chain
			chain.doFilter(req, res);

		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
