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

@RestController
@CrossOrigin("*")
public class ToDoController {
	@Autowired
	TaskService taskService;

	@GetMapping("/api/v1/task/{id}")
	public ResponseEntity<Task> findTask(@PathVariable("id") Long id) {
		Task task = taskService.search(id);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@PutMapping("/api/v1/task/{id}")
	public ResponseEntity<String> updateTask(@PathVariable("id") Long id, @RequestBody Task task) {
		taskService.update(id, task);
		return new ResponseEntity<String>("Task Updated Successfully", HttpStatus.OK);
	}

	@PostMapping("/api/v1/task")
	public ResponseEntity<String> addTask(@RequestBody Task task) {
		taskService.add(task);
		return new ResponseEntity<String>("Task Added Successfully", HttpStatus.CREATED);
	}

	@DeleteMapping("/api/v1/task/{id}")
	public ResponseEntity<String> addTask(@PathVariable("id") Long id) {
		taskService.delete(id);
		return new ResponseEntity<String>("Task Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/api/v1/tasks")
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> result = taskService.getTasks();
		return new ResponseEntity<List<Task>>(result, HttpStatus.OK);
	}
}
