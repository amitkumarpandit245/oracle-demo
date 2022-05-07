package com.oracle.demo.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Response {
	private String message;
	private LocalDateTime timeStamp;
}
