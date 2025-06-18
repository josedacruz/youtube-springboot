package com.josedacruz.learning.spring.backend_server;

import com.josedacruz.learning.spring.backend_server.configurations.MyBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(nameGenerator = MyBeanNameGenerator.class)
public class BackendServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackendServerApplication.class, args);
	}

}
