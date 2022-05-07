package com.oracle.demo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity class to map to database table
 * 
 * @author Amit Kumar
 * 
 */
@Entity
@Setter
@Getter
@ToString
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(length = 500)
	private String description;
	private LocalDateTime timeStamp;

}
