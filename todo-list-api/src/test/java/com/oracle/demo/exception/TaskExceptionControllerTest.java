package com.oracle.demo.exception;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oracle.demo.model.Response;

/**
 * 
 * Test class to handle Global Exceptions
 * 
 * @author Amit Kumar
 *
 */
@ExtendWith(MockitoExtension.class)
public class TaskExceptionControllerTest {
	@Mock
	Response response;
	@InjectMocks
	TaskExceptionController taskExceptionController;

	@Test
	void test_taskNotFoundException_validScenario() {
		response.setMessage("Task with provided ID not Found in the database");
		response.setTimeStamp(LocalDateTime.now());
		taskExceptionController.taskNotFoundExcepton(new TaskNotFoundException());
	}

	@Test
	void test_exception_validScenario() {
		response.setMessage("Task with provided ID not Found in the database");
		response.setTimeStamp(LocalDateTime.now());
		taskExceptionController.excepton(new Exception());
	}

}
