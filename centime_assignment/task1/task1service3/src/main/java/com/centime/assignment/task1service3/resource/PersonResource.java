package com.centime.assignment.task1service3.resource;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.centime.assignment.task1service3.entity.Person;

import brave.sampler.Sampler;


@RestController
@RequestMapping("/task1/service3/api/v1")
@EnableWebMvc
public class PersonResource {
	protected Logger log = org.slf4j.LoggerFactory.getLogger(PersonResource.class);

	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	@PostMapping("/person")
	public ResponseEntity<String> greetPerson(@Valid @RequestBody Person person) {	
		log.info("greetPerson() Method is called");
		return ResponseEntity.ok(person.getName() + " " + person.getSurName());
	}
}
