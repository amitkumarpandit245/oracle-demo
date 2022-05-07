package com.oracle.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.demo.model.Response;
import com.oracle.demo.model.Task;
import com.oracle.demo.service.TaskService;

/**
 * Controller Test Class to test all the End-point methods
 * 
 * @author Amit Kumar
 *
 */
@ExtendWith(MockitoExtension.class)
public class ToDoControllerTest {
	@Mock
	TaskService taskService;
	@InjectMocks
	ToDoController toDoController;
	Task requestAddTask;
	@Mock
	Response response;

	/**
	 * Preparation of Task object for testing by reading from JSON file
	 * 
	 * @throws IOException If file is not found
	 */
	@BeforeEach
	public void setUp() throws IOException {
		requestAddTask = new ObjectMapper().readValue(
				new String(Files.readAllBytes(new ClassPathResource("data/requestAddTask.json").getFile().toPath())),
				Task.class);
	}

	/**
	 * Test case to test test add task method
	 */
	@Test
	void test_addTask_validScenario() {
		Mockito.when(taskService.add(requestAddTask)).thenReturn(requestAddTask);
		ResponseEntity<Response> response = toDoController.addTask(requestAddTask);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	/**
	 * Test case to test find task method
	 */
	@Test
	void test_findTask_validScenario() {
		Mockito.when(taskService.search(1l)).thenReturn(requestAddTask);
		ResponseEntity<Task> response = toDoController.findTask(1l);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals("Morning Walk", response.getBody().getName());
	}

	/**
	 * Test case to test update task method
	 */
	@Test
	void test_updateTask_validScenario() {
		requestAddTask.setName("Meeting");
		Mockito.when(taskService.update(1l, requestAddTask)).thenReturn(requestAddTask);
		ResponseEntity<Response> response = toDoController.updateTask(1l, requestAddTask);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * Test case to test delete method
	 */
	@Test
	void test_deleteTask_validScenario() {
		Mockito.doNothing().when(taskService).delete(1l);
		ResponseEntity<Response> response = toDoController.deleteTask(1l);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	/**
	 * Test Case to test get all tasks from database
	 */
	@Test
	void test_getAllTasks_validScenario() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(requestAddTask);
		Mockito.when(taskService.getTasks()).thenReturn(tasks);
		ResponseEntity<List<Task>> response = toDoController.getAllTasks();
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals(response.getBody().size(), 1);
	}
}
