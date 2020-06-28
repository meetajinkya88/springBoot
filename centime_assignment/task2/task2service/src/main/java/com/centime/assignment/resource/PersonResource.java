package com.centime.assignment.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.centime.assignment.entity.Person;
import com.centime.assignment.log.LogMethodParam;
import com.centime.assignment.service.PersonService;
import com.centime.assignment.view.BasePersonView;

@RestController
@RequestMapping("/task2/service/api/v1")
@EnableWebMvc
public class PersonResource {

	private  Logger logger = org.slf4j.LoggerFactory.getLogger(PersonResource.class);
	@Autowired
	private PersonService service;

	/**
	 * Get all persons list.
	 *
	 * @return the list
	 */
	@GetMapping("/person")
	@LogMethodParam(log = "method:getAllPersons()")
	public ResponseEntity<List<BasePersonView>> getAllPersons() throws ResourceNotFoundException {	
		logger.info("getAllPersons");
		return ResponseEntity.ok().body(service.getAllPersons());

	}
	/**
	 * Gets person by id.
	 *
	 * @param personId the user id
	 * @return the person by id
	 * @throws ResourceNotFoundException the resource not found exception
	 */
	@GetMapping("/person/{id}")
	@LogMethodParam( log = "method:getPersonById() with Id:[0]")
	public ResponseEntity<BasePersonView> getPersonById(@PathVariable(value = "id") Long personId)
			throws ResourceNotFoundException {

		return ResponseEntity.ok().body(service
				.findPersonById(personId));
	}
	/**
	 * Create Persons.
	 *
	 * @param Persons
	 * @return Persons
	 */
	@PostMapping("/person")
	@LogMethodParam( log = "createPersons() with Id:[0]")
	public ResponseEntity<String> createPersons(@Valid @RequestBody List<Person> persons) {
		List<Person> createdPersons = service.saveAll(persons);
		if(createdPersons != null && !createdPersons.isEmpty())
			return ResponseEntity.created(URI.create("/person")).build();
		return null;
	}

	/**
	 * Delete Person
	 *
	 * @param PersonId
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/person/{id}")
	@LogMethodParam(log = "deleteUser() with Id: [0]")
	public ResponseEntity<BasePersonView> deleteUser(@PathVariable(value = "id") Long personId) throws Exception {


		BasePersonView person = service.delete(personId);
		return ResponseEntity.accepted().body(person);
	}
}
