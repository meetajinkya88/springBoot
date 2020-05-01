package com.alacriti.poc.multipledbperf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.alacriti.poc.multipledbperf"})
public class MultipledbperfApplication 
{

	public static void main(String[] args) {
		SpringApplication.run(MultipledbperfApplication.class, args);
	}

}
