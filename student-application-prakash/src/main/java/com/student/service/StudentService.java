package com.student.service;

import java.util.List;

import com.student.entity.Student;

public interface StudentService {

	String saveStudent(Student student);

	Student getStudentDetails(Integer studentId);

	List<Student> getAllStudentDetails();

	List<Student> getStudentDetailsByName(String name);

	Student getStudentLogin(String loginId, String password);

}
