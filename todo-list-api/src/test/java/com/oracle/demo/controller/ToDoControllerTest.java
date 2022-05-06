package com.oracle.demo.controller;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.demo.model.Task;
import com.oracle.demo.service.TaskService;


@ExtendWith(MockitoExtension.class)
public class ToDoControllerTest {
	@Mock
	TaskService taskService;
	@InjectMocks
	ToDoController toDoController;
	Task requestAddTask;

	@BeforeEach
	public void setUp() throws IOException {
		requestAddTask = new ObjectMapper().readValue(
				new String(Files.readAllBytes(new ClassPathResource("data/requestAddTask.json").getFile().toPath())),
				Task.class);
	}

	@Test
	void test_addTask_validScenario() {
		Mockito.when(taskService.add(requestAddTask)).thenReturn(requestAddTask);
		ResponseEntity<String> response = toDoController.addTask(requestAddTask);
		Assertions.assertNotNull(response.getBody());
		Assertions.assertEquals("Task Added Successfully", response.getBody());
	}

}
