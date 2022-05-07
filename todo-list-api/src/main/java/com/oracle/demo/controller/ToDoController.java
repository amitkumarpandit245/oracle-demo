package com.oracle.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.demo.model.Task;
import com.oracle.demo.service.TaskService;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller Class to expose all the end-points to the client application
 * 
 * @author Amit Kumar
 * 
 */
@RestController
@CrossOrigin("*")
@Slf4j
public class ToDoController {
	@Autowired
	TaskService taskService;

	/**
	 * API to search a task based on the Task ID
	 * 
	 * @param id accepts Task ID to find task.
	 * @return Task object with the task ID provided
	 */
	@GetMapping("/api/v1/task/{id}")
	public ResponseEntity<Task> findTask(@PathVariable("id") Long id) {
		log.info("Inside Search Task Endpoint->");
		log.info("Task Id -> " + id);
		Task task = taskService.search(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	/**
	 * API to update Task based on the Task ID and Task object
	 * 
	 * @param id   accepts task ID to update task
	 * @param task updated Task object
	 * @return Update Successful message
	 */
	@PutMapping("/api/v1/task/{id}")
	public ResponseEntity<String> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
		log.info("Inside update Task Endpoint->");
		log.info("Request Body -> " + task + " Task Id ->" + id);
		taskService.update(id, task);
		return new ResponseEntity<String>("Task Updated Successfully", HttpStatus.OK);
	}

	/**
	 * API to add task provided by the client application
	 * 
	 * @param task Task object that needs to be added
	 * @return Update Successful message
	 */
	@PostMapping("/api/v1/task")
	public ResponseEntity<String> addTask(@RequestBody Task task) {
		log.info("Inside add Task Endpoint->");
		log.info("Request Body -> " + task);
		taskService.add(task);
		return new ResponseEntity<String>("Task Added Successfully", HttpStatus.CREATED);
	}

	/**
	 * API to delete a task based on the Task ID
	 * 
	 * @param id Task ID that needs to be deleted
	 * @return Delete Successful message
	 */
	@DeleteMapping("/api/v1/task/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
		log.info("Inside delete Task Endpoint");
		log.info("Task Id -> " + id);
		taskService.delete(id);
		return new ResponseEntity<String>("Task Deleted Successfully", HttpStatus.OK);
	}

	/**
	 * API to List all tasks present in the database
	 * 
	 * @return All the tasks present in the database
	 */
	@GetMapping("/api/v1/tasks")
	public ResponseEntity<List<Task>> getAllTasks() {
		log.info("Inside Find All Task Endpoint->");
		List<Task> result = taskService.getTasks();
		return new ResponseEntity<List<Task>>(result, HttpStatus.OK);
	}
}
