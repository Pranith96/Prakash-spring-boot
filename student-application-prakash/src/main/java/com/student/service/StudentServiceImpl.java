package com.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.student.dto.StudentDto;
import com.student.entity.Student;
import com.student.respository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public String saveStudent(Student student) {
		Student response = studentRepository.save(student);
		if (response == null) {
			return "Student data not saved";
		}
		return "Student data is saved Successfully";
	}

	@Override
	public StudentDto getStudentDetails(Integer studentId) {
		Optional<Student> response = studentRepository.findById(studentId);
		if (!response.isPresent()) {
			throw new RuntimeException("Student Details Not Exists in database for" + studentId);
		}

		StudentDto studentDto = new StudentDto();
		studentDto.setName(response.get().getName());
		studentDto.setMobileNumber(response.get().getMobileNumber());
		studentDto.setEmail(response.get().getEmail());
		studentDto.setLoginId(response.get().getLoginId());
		return studentDto;
	}

	@Override
	public List<Student> getAllStudentDetails() {
		List<Student> studentResponse = studentRepository.findAll();
		if (null == studentResponse || studentResponse.isEmpty()) {
			throw new RuntimeException("Data is not exists");
		}
		return studentResponse;
	}

	@Override
	public List<Student> getStudentDetailsByName(String name) {
		// below is JPA query
		// List<Student> response = studentRepository.findByName(name);

		// Below is JPQL query method
		List<Student> response = studentRepository.getByName(name);
		if (response.isEmpty() || response == null) {
			throw new RuntimeException("Data is not found");
		}
		return response;
	}

	@Override
	public Student getStudentLogin(String loginId, String password) {
		Optional<Student> response = studentRepository.findByLoginIdAndPassword(loginId, password);
		if (!response.isPresent()) {
			throw new RuntimeException("Data doesnot match to fetch result");
		}
		return response.get();
	}

	@Override
	public String deleteByStudentId(Integer studentId) {
		Optional<Student> response = studentRepository.findById(studentId);
		if (!response.isPresent()) {
			throw new RuntimeException("Student Details Not Exists in database for" + studentId);
		}
		studentRepository.deleteById(studentId);
		return "Successfully deleted";
	}
}
