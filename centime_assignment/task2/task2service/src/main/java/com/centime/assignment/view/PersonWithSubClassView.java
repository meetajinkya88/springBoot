package com.centime.assignment.view;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PersonWithSubClassView extends BasePersonView{
	
	@JsonProperty("Name")
	protected String name;
	
	@JsonProperty("Sub Classes")
	protected List<BasePersonView> subClasses;
	
	
	public PersonWithSubClassView() {
		super();
	}
	
	public PersonWithSubClassView(String name) {
		this.name = name;
	}
	
	public PersonWithSubClassView(String name, List<BasePersonView> subClasses) {
		super();
		this.name = name;
		this.subClasses = subClasses;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BasePersonView> getSubClasses() {
		if(this.subClasses == null)
			this.subClasses = new LinkedList<>();
		
		return subClasses;
	}
	public void setSubClasses(List<BasePersonView> subClasses) {
		this.subClasses = subClasses;
	}
	@Override
	public String toString() {
		return "PersonWithSubClass [name=" + name + ", subClasses=" + subClasses + "]";
	}


	
	
}
