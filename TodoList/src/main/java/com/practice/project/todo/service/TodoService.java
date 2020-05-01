package com.practice.project.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.practice.project.todo.vo.Todo;

@Service
public class TodoService 
{
	List<Todo> todoList = new ArrayList<>();
	protected AtomicInteger counter = new AtomicInteger(0);
	
	
	{
		todoList.add(new Todo(counter.incrementAndGet(), "ajinkyab", "sample_desc", new Date(), false));
		todoList.add(new Todo(counter.incrementAndGet(), "raj", "sample_desc2", new Date(), false));
		todoList.add(new Todo(counter.incrementAndGet(), "varsha", "sample_desc3", new Date(), true));

	}
	public List<Todo> getAllTodos()
	{
		return this.todoList;
	}
	public Todo removeTodoItem(int id)
	{
		Todo todo = findById(id);
		if(todo == null) return null;
		
		return this.todoList.remove(todo) ? todo : null;
	}
	private Todo findById(int id) 
	{
		for(Todo todo: this.todoList)
			if(todo.getId() == id)
				return todo;
		return null;
	}

}
