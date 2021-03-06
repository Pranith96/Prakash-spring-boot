package com.student.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class StudentExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ExceptionResponse> handleStudentNotFoundException(StudentNotFoundException ex,
			WebRequest request) {
		ExceptionResponse response = new ExceptionResponse();
		response.setDetails("Data Not exists");
		response.setMessage(ex.getMessage());
		response.setDateTme(LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
}
