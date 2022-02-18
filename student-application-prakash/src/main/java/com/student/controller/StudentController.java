package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.dto.StudentDto;
import com.student.entity.Student;
import com.student.exceptions.StudentNotFoundException;
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
	public ResponseEntity<StudentDto> getStudent(@PathVariable("studentId") Integer studentId) {
		StudentDto response = studentService.getStudentDetails(studentId);
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
	public ResponseEntity<StudentDto> getStudentData(@RequestParam("studentId") Integer studentId) {
		StudentDto response = studentService.getStudentDetails(studentId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/delete/{studentId}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("studentId") Integer studentId) {
		String response = studentService.deleteByStudentId(studentId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		String response = studentService.updateStudent(student);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PutMapping("/update/{studentId}/{studentName}")
	public ResponseEntity<String> updateStudentNameById(@PathVariable("studentId") Integer studentId,
			@PathVariable("studentName") String studentName) {
		String response = studentService.updateStudentNameById(studentId, studentName);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

}
