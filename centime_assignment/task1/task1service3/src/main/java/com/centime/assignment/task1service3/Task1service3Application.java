package com.centime.assignment.task1service3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.centime"})
@EntityScan(basePackages="com.centime.assignment.task1service3.entity")
public class Task1service3Application {

	public static void main(String[] args) {
		SpringApplication.run(Task1service3Application.class, args);
	}

}
