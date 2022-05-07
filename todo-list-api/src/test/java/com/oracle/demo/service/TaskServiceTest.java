package com.oracle.demo.service;

import static org.mockito.Mockito.times;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.demo.model.Task;
import com.oracle.demo.repository.TaskRepository;

/**
 * Service Test class to test all the methods on Service Layer
 * 
 * @author Amit Kumar
 *
 */
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	@Mock
	TaskRepository taskRepository;
	@InjectMocks
	TaskService taskService;
	Task requestAddTask;

	/**
	 * Preparation of Task object by reading from JSON file
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
	 * Method to test add Task functionality
	 */
	@Test
	void test_addTask_validScenario() {
		Mockito.when(taskRepository.save(requestAddTask)).thenReturn(requestAddTask);
		Task response = taskService.add(requestAddTask);
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Morning Walk", response.getName());
	}

	/**
	 * Method to test search task functionality
	 */
	@Test
	void test_searchTask_validScenario() {
		Optional<Task> task = Optional.of(requestAddTask);
		Mockito.when(taskRepository.findById(1l)).thenReturn(task);
		Task response = taskService.search(1l);
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Morning Walk", response.getName());
	}

	/**
	 * Method to test update task functionality
	 */
	@Test
	void test_updateTask_validScenario() {
		requestAddTask.setDescription("Meeting");
		Mockito.when(taskRepository.getById(requestAddTask.getId())).thenReturn(requestAddTask);
		Mockito.when(taskRepository.save(requestAddTask)).thenReturn(requestAddTask);
		Task response = taskService.update(1l, requestAddTask);
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Meeting", response.getDescription());
	}

	/**
	 * Method to test delete task functionality
	 */
	@Test
	void test_deleteTask_validScenario() {
		Mockito.doNothing().when(taskRepository).deleteById(1l);
		taskService.delete(1l);
		Mockito.verify(taskRepository, times(1)).deleteById(1l);
	}

	/**
	 * Method to test get all tasks functionality
	 */
	@Test
	void test_getTasks_validScenario() {
		List<Task> tasks = new ArrayList<>();
		tasks.add(requestAddTask);
		Mockito.when(taskRepository.findAll()).thenReturn(tasks);
		List<Task> response = taskService.getTasks();
		Assertions.assertNotNull(response.get(0));
		Assertions.assertEquals(response.size(), 1);
	}
}
