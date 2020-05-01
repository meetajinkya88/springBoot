package com.practice.project.todo.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.practice.project.todo.vo.HelloWorldBean;


@RestController
// by default Sboot prevent request from other port apart from 8080. So, we need to use @CrossOrigin to allow request 
// from other ports. Also, We can selectively define the origin.
@CrossOrigin(origins = "http://localhost:4200")
public class HelloWorldRestResource 
{
	@GetMapping(path = "/helloWorldBean/pathVaribale/{name}")
	public HelloWorldBean getHelloWorldBean(@PathVariable String name) 
	{
		return new HelloWorldBean(name);
	//	throw new RuntimeException("Some error occured !!!!");
	}
}
