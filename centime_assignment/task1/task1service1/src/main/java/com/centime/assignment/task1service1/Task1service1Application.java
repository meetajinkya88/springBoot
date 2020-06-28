package com.centime.assignment.task1service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = {"com.centime"})
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.centime"})
@EntityScan(basePackages="com.centime.assignment.entity")
public class Task1service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Task1service1Application.class, args);
	}

}
