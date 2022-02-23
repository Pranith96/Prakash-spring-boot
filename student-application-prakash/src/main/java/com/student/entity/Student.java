package com.student.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ramannpr
 *
 */
@Entity
@Table(name = "student_table")
@ApiModel(description = "Details About the Student Accounts")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	@ApiModelProperty(notes = "The Student id is primary key type auto incremented value")
	private Integer studentId;
	@Column(name = "student_name")
	@ApiModelProperty(notes = "Student name as a string")
	private String name;
	@Column(name = "mobile_number")
	@ApiModelProperty(notes = "student mobile number as a string")
	private String mobileNumber;
	@Column(name = "email_id")
	@ApiModelProperty(notes = "Student email as a String")
	private String email;
	@Column(name = "login_id", unique = true)
	@ApiModelProperty(notes = "Student login id is unique data as a String")
	private String loginId;
	@Column(name = "password")
	@ApiModelProperty(notes = "Student password as a string")
	private String password;
	@ApiModelProperty(notes = "Student status as a String")
	private String status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@ManyToOne(cascade = CascadeType.ALL, targetEntity = College.class)
	@JoinColumn(name = "college_id")
	private College college;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private List<Course> course;

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", mobileNumber=" + mobileNumber + ", email="
				+ email + ", loginId=" + loginId + ", password=" + password + "]";
	}

}
