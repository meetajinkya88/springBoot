package com.practice.project.todo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.practice.project.todo.service.TodoService;
import com.practice.project.todo.vo.HelloWorldBean;
import com.practice.project.todo.vo.Todo;


@RestController
//by default Sboot prevent request from other port apart from 8080. So, we need to use @CrossOrigin to allow request 
//from other ports. Also, We can selectively define the origin.
@CrossOrigin(origins = "http://localhost:4200")
public class TodoRestResource 
{
	@Autowired
	TodoService todoService;
	
	@GetMapping(path = "/users/{name}/todo")
	public List<Todo> getAllTodos(@PathVariable String name) 
	{
		return todoService.getAllTodos();
	}
	
	

	@DeleteMapping(path = "/users/{name}/todos/{id}")
	public ResponseEntity<Void> getAllTodos(@PathVariable String name,@PathVariable int id) 
	{
		Todo todo = todoService.removeTodoItem(id);
		
		if(todo != null)
			return ResponseEntity.noContent().build();
		
		return ResponseEntity.notFound().build();
	}
}