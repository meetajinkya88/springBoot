package com.centime.assignment.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.centime.assignment.entity.Person;
import com.centime.assignment.entity.StringConstants;
import com.centime.assignment.repo.PersonRepository;
import com.centime.assignment.view.BasePersonView;
import com.centime.assignment.view.handler.PersonViewController;

@Service
public class PersonService {
	private  Logger logger = org.slf4j.LoggerFactory.getLogger(PersonService.class);

	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	protected PersonViewController personViewController;
	
	public List<Person> saveAll(List<Person> persons) {		
		return personRepository.saveAll(persons);
	}

	public BasePersonView findPersonById(Long personId) {			
		Person person = findById(personId);
		return  getPersonView(person);		
	}

	private Person findById(Long personId) {
		Optional<Person> person = personRepository.findById(personId);
		
		if(person.isEmpty())
			throw new ResourceNotFoundException("Person not found for Id :: " + personId);

		return person.get();
	}
	
	private BasePersonView getPersonView(Person person) {
		// for now using default Person view. But representation
		//  might differ based on requirements and config
		return personViewController.getPersonView(StringConstants.PERSON_VIEW_TYPE,person);
	}

	private List<BasePersonView> getPersonsView(List<Person> persons) {
		// for now using default Person view. But representation
		//  might differ based on requirements and config
		return personViewController.getPersonsView(StringConstants.PERSON_VIEW_TYPE,persons);
	}
	
	public List<BasePersonView> getAllPersons() {
		List<Person> persons =  personRepository.findAll();

		if(persons == null || persons.isEmpty())
				throw new ResourceNotFoundException("Person List unavailable");
		logger.info("Person List:" + persons.size());
		return getPersonsView(persons);
	
	}

	public BasePersonView delete(Long personId) {

		Person person = findById(personId);
		if(person == null)
			throw new ResourceNotFoundException("Person not found for Id :: " + personId);
		
		personRepository.delete(person);
		return getPersonView(person);
	}


}
