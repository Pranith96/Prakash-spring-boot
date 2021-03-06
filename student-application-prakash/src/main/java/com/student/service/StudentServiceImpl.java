package com.student.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.student.dto.StudentDto;
import com.student.entity.Student;
import com.student.exceptions.StudentNotFoundException;
import com.student.respository.StudentRepository;

@Service
@Transactional
@Profile(value = { "dev", "prod", "qa", "local" })
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public String saveStudent(Student student) {
		student.setStatus("ACTIVE");
		student.getAddress().setStudent(student);
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
			throw new StudentNotFoundException("Student Details Not Exists in database for" + studentId);
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
			throw new StudentNotFoundException("Data is not exists");
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
			throw new StudentNotFoundException("Data is not found");
		}
		return response;
	}

	@Override
	public Student getStudentLogin(String loginId, String password) {
		Optional<Student> response = studentRepository.findByLoginIdAndPassword(loginId, password);
		if (!response.isPresent()) {
			throw new StudentNotFoundException("Data doesnot match to fetch result");
		}
		return response.get();
	}

	@Override
	public String deleteByStudentId(Integer studentId) {
		Optional<Student> response = studentRepository.findById(studentId);
		if (!response.isPresent()) {
			throw new StudentNotFoundException("Student Details Not Exists in database for" + studentId);
		}
		studentRepository.deleteById(studentId);
		return "Successfully deleted";
	}

	@Override
	public String updateStudent(Student student) {
		Optional<Student> response = studentRepository.findById(student.getStudentId());
		if (!response.isPresent()) {
			throw new StudentNotFoundException("Student Details Not Exists in database");
		}

		if (student.getName() != null) {
			response.get().setName(student.getName());
		}
		if (student.getMobileNumber() != null) {
			response.get().setMobileNumber(student.getMobileNumber());
		}
		if (student.getEmail() != null) {
			response.get().setEmail(student.getEmail());
		}
		if (student.getLoginId() != null) {
			response.get().setLoginId(student.getLoginId());
		}
		if (student.getPassword() != null) {
			response.get().setPassword(student.getPassword());
		}

		studentRepository.save(response.get());

		return "Updated successfully";
	}

	@Override
	@Transactional
	public String updateStudentNameById(Integer studentId, String studentName) {
		studentRepository.updateStudentName(studentId, studentName);
		return "Updated successfully";
	}

}
