package com.centime.assignment.task1service2.resource;


import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import brave.sampler.Sampler;



@RestController
@RequestMapping("/task1/service2/api/v1")
@EnableWebMvc
public class GreetResource {

	protected Logger log = org.slf4j.LoggerFactory.getLogger(GreetResource.class);
	
	@Bean
	public Sampler defaultSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}
	@Bean
	public WebClient getWebclient()
	{
		return WebClient
		  .create();
	}
	
	@GetMapping("/greet")
	public ResponseEntity<String> greet() {
		
		log.info("Greet Method is called");
		return ResponseEntity.ok().body("Hello");

	}
}
