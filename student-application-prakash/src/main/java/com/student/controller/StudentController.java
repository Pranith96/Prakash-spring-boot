package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Student;
import com.student.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@PostMapping("/save")
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		String response = studentService.saveStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/get/{studentId}")
	public ResponseEntity<Student> getStudent(@PathVariable("studentId") Integer studentId) {
		Student response = studentService.getStudentDetails(studentId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/get/all")
	public ResponseEntity<List<Student>> getAllStudent() {
		List<Student> response = studentService.getAllStudentDetails();
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Student>> getStudentDetailsByName(@PathVariable("name") String name) {
		List<Student> response = studentService.getStudentDetailsByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping("/login/{loginId}/{password}")
	public ResponseEntity<Student> getStudentLogin(@PathVariable("loginId") String loginId,
			@PathVariable("password") String password) {
		Student response = studentService.getStudentLogin(loginId, password);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/get/data")
	public ResponseEntity<Student> getStudentData(@RequestParam("studentId") Integer studentId) {
		Student response = studentService.getStudentDetails(studentId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}
