package com.oracle.demo.service;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.demo.model.Task;
import com.oracle.demo.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
	@Mock
	TaskRepository taskRepository;
	@InjectMocks
	TaskService taskService;
	Task requestAddTask;

	@BeforeEach
	public void setUp() throws IOException {
		requestAddTask = new ObjectMapper().readValue(
				new String(Files.readAllBytes(new ClassPathResource("data/requestAddTask.json").getFile().toPath())),
				Task.class);
	}

	@Test
	void test_addTask_validScenario() {
		Mockito.when(taskRepository.save(requestAddTask)).thenReturn(requestAddTask);
		Task response = taskService.add(requestAddTask);
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Morning Walk", response.getName());
	}
}
