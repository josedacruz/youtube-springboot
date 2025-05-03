package com.josedacruz.learning.spring.backend_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.josedacruz.learning.spring.others", "com.josedacruz.learning.spring.backend_server"})
public class BackendServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackendServerApplication.class, args);
	}

}
