package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;
	
	@PostMapping("/save")
	public ResponseEntity<Student> createStudent(@RequestBody Student student){
		Student response = studentService.saveStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/get")
	public ResponseEntity<Student> getStudent(){
		Student response = studentService.getStudentDetails();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/get/all")
	public ResponseEntity<List<Student>> getAllStudent(){
		List<Student> response = studentService.getAllStudentDetails();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
