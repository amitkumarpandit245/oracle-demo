package com.oracle.demo.exception;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.oracle.demo.model.Response;

@ControllerAdvice
public class TaskExceptionController {
	@Autowired
	Response response;

	@ExceptionHandler(value = TaskNotFoundException.class)
	public ResponseEntity<Response> taskNotFoundExcepton(TaskNotFoundException exception) {
		response.setMessage("Task with provided ID not Found in the database");
		response.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Response> excepton(Exception exception) {
		response.setMessage("Exception occurred: " + exception.getMessage());
		response.setTimeStamp(LocalDateTime.now());
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}
}
