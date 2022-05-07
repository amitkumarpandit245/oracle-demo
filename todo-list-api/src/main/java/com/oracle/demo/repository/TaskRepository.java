package com.oracle.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oracle.demo.model.Task;

/**
 * Class for all the CRUD operation
 * 
 * @author Amit Kumar
 * 
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
