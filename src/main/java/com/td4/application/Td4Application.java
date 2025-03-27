package com.td4.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"com.td4.controller",
		"com.td4.DAO",
		"com.td4.service",
		"com.td4.model",
		"com.td4.Mapper"
})
public class Td4Application {

	public static void main(String[] args) {
		SpringApplication.run(Td4Application.class, args);
	}

}
