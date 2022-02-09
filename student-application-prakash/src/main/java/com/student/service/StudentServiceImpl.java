package com.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.student.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Override
	public Student saveStudent(Student student) {
		if (student != null) {
			return student;
		}
		return null;
	}

	@Override
	public Student getStudentDetails() {
		Student student = new Student();
		student.setEmail("qwerty@gmail.com");
		student.setStudentId(1);
		student.setLoginId("qwerty1234");
		student.setName("ABC");
		student.setMobileNumber("1234567890");
		student.setPassword("12345678");
		return student;
	}

	@Override
	public List<Student> getAllStudentDetails() {
		Student student = new Student();
		student.setEmail("qwerty@gmail.com");
		student.setStudentId(1);
		student.setLoginId("qwerty1234");
		student.setName("ABC");
		student.setMobileNumber("1234567890");
		student.setPassword("12345678");
		
		Student student1 = new Student();
		student1.setEmail("qwerty123@gmail.com");
		student1.setStudentId(2);
		student1.setLoginId("qwerty123");
		student1.setName("ABCDEF");
		student1.setMobileNumber("123456789");
		student1.setPassword("12345678");
		
		List<Student> list = new ArrayList<>();
		list.add(student1);
		list.add(student);
		
		return list;
	}

}
