package com.centime.assignment.view.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.centime.assignment.entity.Person;
import com.centime.assignment.view.BasePersonView;

@Component
public class PersonViewController {

	@Autowired
	protected PersonViewHandlerFactory factory;
	
	public BasePersonView getPersonView(int viewType,Person person) {

		return factory.getPersonViewHandlerInstance(viewType).getPersonView(person);
	}

	public List<BasePersonView> getPersonsView(int viewType, List<Person> persons) {
		return factory.getPersonViewHandlerInstance(viewType).getPersonsView(persons);
	}
	

	
}
