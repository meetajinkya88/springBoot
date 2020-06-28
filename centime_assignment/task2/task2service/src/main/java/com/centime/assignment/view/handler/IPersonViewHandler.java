package com.centime.assignment.view.handler;

import java.util.List;

import com.centime.assignment.entity.Person;
import com.centime.assignment.view.BasePersonView;

public interface IPersonViewHandler {

	public BasePersonView getPersonView(Person person);
	public List<BasePersonView> getPersonsView(List<Person> persons);

}
