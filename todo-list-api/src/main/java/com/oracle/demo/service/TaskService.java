package com.oracle.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.demo.model.Task;
import com.oracle.demo.repository.TaskRepository;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;

	public Task add(Task task) {
		task.setTimeStamp(LocalDateTime.now());
		return taskRepository.save(task);
	}

	public Task search(Long taskId) {
		return taskRepository.findById(taskId).get();
	}

	public Task update(Long id, Task task) {
		task.setId(id);
		task.setTimeStamp(LocalDateTime.now());
		return taskRepository.save(task);
	}

	public void delete(Long taskId) {
		taskRepository.deleteById(taskId);
	}

	public List<Task> getTasks() {
		return taskRepository.findAll().stream().sorted((a, b) -> b.getTimeStamp().compareTo(a.getTimeStamp()))
				.collect(Collectors.toList());
	}
}
