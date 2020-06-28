package com.centime.assignment.view.handler;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.centime.assignment.entity.Person;
import com.centime.assignment.repo.PersonRepository;
import com.centime.assignment.view.BasePersonView;
import com.centime.assignment.view.PersonWithSubClassView;

@Component("PersonViewHandler-" + 1)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PersonWithSubClassListHandler implements IPersonViewHandler{

	
	@Autowired
	private PersonRepository personRepository;
	
	
	@Override
	public BasePersonView getPersonView(Person person) {
		return new PersonWithSubClassView(person.getName(),getParentPersonWithSubClassList(person.getId()));
	}
	@Override
	public List<BasePersonView> getPersonsView(List<Person> persons) {
		List<BasePersonView> list = new LinkedList<>();
		for(Person person: persons)
		{
			if(!isParentPerson(person))
				continue;

			list.add(getPersonView(person));
		}

		return list;
	}
	
	private boolean isParentPerson(Person person) {
		return person != null && person.getParentId() <=0;
	}

	private List<BasePersonView> getParentPersonWithSubClassList(Long id) {

		if(id <= 0)
			return null;

		List<BasePersonView> personWithSubClassList = null; 
		List<Person> childPersons = personRepository.findByparentId(id);

		if(childPersons != null && !childPersons.isEmpty())
		{	
			personWithSubClassList  = new LinkedList<>();
			for(Person childPerson: childPersons)
				personWithSubClassList.add(getPersonView(childPerson));
		}		
		return personWithSubClassList;
	}
	
}
