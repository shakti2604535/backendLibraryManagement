package com.library.manage.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.SequenceGenerator;
@Entity
public class Librarian {

	
	

	@Id
	@SequenceGenerator(
			name = "student_sequence", 
			sequenceName="student_sequence",
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	          generator="student_sequence")
	private Long Id;
	private String  name;
	
	
	private String  emailId;
	private String  password;
	public Long getId() {
		return Id;
	}
//	public void setId(Long id) {
//		Id = id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
