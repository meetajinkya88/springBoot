package com.centime.assignment.task1service1.entity;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	@JsonProperty("Name")
	@NotBlank(message = "Name is mandatory")
	protected String name;
	
	@JsonProperty("Surname")
	@NotBlank(message = "Surname is mandatory")
	protected String surName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}
}
