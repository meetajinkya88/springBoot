package com.practice.project.todo.vo;

public class HelloWorldBean {

	protected String message = "Hi ";
	
	public HelloWorldBean(String name) 
	{
		this.message += name;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
