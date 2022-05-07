package com.oracle.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main method to start spring boot application
 * 
 * @author Amit Kumar
 */
@SpringBootApplication
@EnableSwagger2
public class TodoListApiApplication {
/**
 * Main method for spring boot application
 * @param args accepts array of string as arguments
 */
	public static void main(String[] args) {
		SpringApplication.run(TodoListApiApplication.class, args);
	}

}
