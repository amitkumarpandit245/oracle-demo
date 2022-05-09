package com.oracle.demo.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oracle.demo.model.Response;

/**
 * Exception controller class to handle exceptions globally
 * 
 * @author Amit Kumar
 *
 */
@ControllerAdvice
public class TaskExceptionController {
	@Autowired
	Response response;

	/**
	 * 
	 * @param exception Exception handler to handle TaskNotFound Exception
	 * @return Response object with proper exception message
	 */
	@ExceptionHandler(value = TaskNotFoundException.class)
	public ResponseEntity<Response> taskNotFoundExcepton(TaskNotFoundException exception) {
		response.setMessage("Task with provided ID not Found in the database");
		response.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * @param exception Exception handler to handle all other Exceptions
	 * @return Response object with proper exception message
	 */
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Response> excepton(Exception exception) {
		response.setMessage("Exception occurred: " + exception.getMessage());
		response.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
}
