package com.oracle.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main method to start spring boot application
 * 
 * @author Amit Kumar
 */
@SpringBootApplication
public class TodoListApiApplication {
/**
 * Main method for spring boot application
 * @param args accepts array of string as arguments
 */
	public static void main(String[] args) {
		SpringApplication.run(TodoListApiApplication.class, args);
	}

}
