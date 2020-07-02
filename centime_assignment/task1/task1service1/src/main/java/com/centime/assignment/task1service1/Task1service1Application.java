package com.centime.assignment.task1service1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;



@SpringBootApplication(scanBasePackages = {"com.centime"})
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.centime"})
@EntityScan(basePackages="com.centime.assignment.task1service1.entity")
public class Task1service1Application {

	public static void main(String[] args) {
		SpringApplication.run(Task1service1Application.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("Centime Assignment Task 1 Application API") String appDesciption, @Value("1.0.0") String appVersion) {
		return new OpenAPI()
				.info(new Info()
				.title("Centime Assignment Application API")
				.version(appVersion)
				.description(appDesciption)
				.contact(new Contact().email("meetajinkya88@gmail.com"))
				.termsOfService("http://meetajinkya88.com/terms/")
				.license(new License().name("Liscence2.0").url("http://meetajinkya88.com")));
	}
}
