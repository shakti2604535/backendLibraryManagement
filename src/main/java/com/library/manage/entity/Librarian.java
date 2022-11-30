package com.library.manage.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import lombok.Data;


//import org.hibernate.validator.constraints.NotEmpty;

@Entity
//@Validated
@Data
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
	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
	 @Length(min = 4,max = 12,message = "name must be min 4 and max 12 letter")
	private String  name;
	
	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
	 @Length(min = 6,max = 15,message = "username or email must be min 6 and max 15 letter")
      private String  emailId;
	
	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
	 @Length(min = 6,max = 15,message = "password must be min 6 and max 15 letter")

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
