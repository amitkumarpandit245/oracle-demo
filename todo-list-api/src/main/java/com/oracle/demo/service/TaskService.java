package com.oracle.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.demo.exception.TaskNotFoundException;
import com.oracle.demo.model.Task;
import com.oracle.demo.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * Service class containing all the business logic for interacting with Database
 * 
 * @author Amit Kumar
 * 
 */
@Service
@Slf4j
public class TaskService {
	@Autowired
	TaskRepository taskRepository;

	/**
	 * Method to add Task
	 * 
	 * @param task Task object that needs to be added
	 * @return Added task in the database
	 */
	public Task add(Task task) {
		log.info("Inside Add Task Service Method -> ");
		task.setTimeStamp(LocalDateTime.now());
		return taskRepository.save(task);
	}

	/**
	 * Method to search Task
	 * 
	 * @param taskId Task Id that needs to be searched on databse
	 * @return Task Object with the provided task ID
	 */
	public Task search(Long taskId) {
		log.info("Inside search Task Service Method -> ");
		Optional<Task> task = taskRepository.findById(taskId);
		if (!task.isPresent()) {
			throw new TaskNotFoundException();
		} else {
			return taskRepository.findById(taskId).get();
		}
	}

	/**
	 * Method to update Task
	 * 
	 * @param id   Task Id that needs to be updated
	 * @param task task object that is updated
	 * @return Updated task object
	 */
	public Task update(Long id, Task task) {
		log.info("Inside update Task Service Method -> ");
		if (taskRepository.getById(id) == null) {
			throw new TaskNotFoundException();
		}
		task.setId(id);
		task.setTimeStamp(LocalDateTime.now());
		return taskRepository.save(task);
	}

	/**
	 * Method to delete Task
	 * 
	 * @param taskId Task Id that needs to be deleted from database
	 */
	public void delete(Long taskId) {
		log.info("Inside delete Task Service Method -> ");
		taskRepository.deleteById(taskId);
	}

	/**
	 * Method to List all tasks in the database
	 * 
	 * @return All the tasks stored in the database
	 */
	public List<Task> getTasks() {
		log.info("Inside List Tasks Service Method -> ");
		return taskRepository.findAll().stream().sorted((a, b) -> b.getTimeStamp().compareTo(a.getTimeStamp()))
				.collect(Collectors.toList());
	}
}
