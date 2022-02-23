package com.student.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer collegeId;
	private String collegeName;
	private String unversity;

	@OneToMany(mappedBy = "college")
	@JsonIgnore
	private List<Student> student;

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getUnversity() {
		return unversity;
	}

	public void setUnversity(String unversity) {
		this.unversity = unversity;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

}
