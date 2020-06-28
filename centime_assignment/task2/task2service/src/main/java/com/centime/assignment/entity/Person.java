package com.centime.assignment.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
public class Person {

	@Id
	protected long id;
	
	protected long parentId;
	
	@NotBlank(message = "Name is mandatory")
	protected String name;
	
	@NotBlank(message = "Color is mandatory")
	protected String color;
	
	
	public Person() {
		super();
	}
	public Person(long id, long parentId, String name, String color) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.color = color;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Person [Id=" + id + ", parentId=" + parentId + ", name=" + name + ", color=" + color + "]";
	}
	
	public static void main(String [] args)
	{
		List<Person> personList = new LinkedList<>();
		personList.add(new Person(1L,0L,"Warrior","Red"));
		personList.add(new Person(2L,0L,"Wizard","green"));
		personList.add(new Person(3L,0L,"Priest","white"));
		personList.add(new Person(4L,0L,"Rogue","yellow"));
		personList.add(new Person(5L,1L,"Fighter","blue"));
		personList.add(new Person(6L,1L,"Paladin","lightblue"));
		personList.add(new Person(7L,1L,"Ranger","lightgreen"));
		personList.add(new Person(8L,2L,"Mage","grey"));
		personList.add(new Person(9L,2L,"Specialist Wizard","lightgrey"));
		personList.add(new Person(10L,3L,"Cleric","Red"));
		personList.add(new Person(11L,3L,"Druid","green"));
		personList.add(new Person(12L,3L,"Priest of specific mythos","white"));
		personList.add(new Person(13L,4L,"Thief","yellow"));
		personList.add(new Person(14L,4L,"Bard","blue"));
		personList.add(new Person(15L,13L,"Assasin","lightblue"));
		
		 ObjectMapper Obj = new ObjectMapper(); 
		  
	        try { 
	  
	            // get Oraganisation object as a json string 
	            String jsonStr = Obj.writerWithDefaultPrettyPrinter().writeValueAsString(personList); 
	  
	            // Displaying JSON String 
	            System.out.println(jsonStr); 
	        } 
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	}
	
}
