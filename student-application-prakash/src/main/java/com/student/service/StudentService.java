package com.student.service;

import java.util.List;

import com.student.dto.StudentDto;
import com.student.entity.Student;

public interface StudentService {

	String saveStudent(Student student);

	StudentDto getStudentDetails(Integer studentId);

	List<Student> getAllStudentDetails();

	List<Student> getStudentDetailsByName(String name);

	Student getStudentLogin(String loginId, String password);

	String deleteByStudentId(Integer studentId);

	String updateStudent(Student student);

	String updateStudentNameById(Integer studentId, String studentName);

}
