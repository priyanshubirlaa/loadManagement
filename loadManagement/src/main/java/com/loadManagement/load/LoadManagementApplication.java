package com.loadManagement.load;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
public class LoadManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoadManagementApplication.class, args);
	}

}
