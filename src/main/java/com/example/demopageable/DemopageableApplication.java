package com.example.demopageable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemopageableApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemopageableApplication.class, args);
	}

}
