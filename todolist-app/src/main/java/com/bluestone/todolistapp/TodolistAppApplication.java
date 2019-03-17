package com.bluestone.todolistapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistAppApplication {
	private static final Logger logger = LogManager.getLogger(TodolistAppApplication.class);
	public static void main(String[] args) {
		logger.info("Starting Spring Boot App");
		SpringApplication.run(TodolistAppApplication.class, args);
	}

}
