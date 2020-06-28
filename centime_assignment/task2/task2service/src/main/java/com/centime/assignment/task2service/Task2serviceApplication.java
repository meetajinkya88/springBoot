package com.centime.assignment.task2service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.centime"})
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.centime"})
@EnableJpaRepositories(basePackages="com.centime.assignment.repo")
@EntityScan(basePackages="com.centime.assignment.entity")
public class Task2serviceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(Task2serviceApplication.class, args);
	}

}
