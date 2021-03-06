package com.student.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.student.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	//List<Student> findByName(String name);

	Optional<Student> findByLoginIdAndPassword(String loginId, String password);

	//JPQL
	@Query("Select s From Student s where s.name = :name")
	List<Student> getByName(String name);

	@Modifying
	@Query("update Student s set s.name=?2 where s.studentId=?1")
	void updateStudentName(Integer studentId, String studentName);
	
	// SQL
	// @Query(value = "Select * From student_table where student_name = :name",
	// nativeQuery = true)
	// List<Student> getByName(String name);

}
