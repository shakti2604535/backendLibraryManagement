package com.library.manage.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LibrarianRequest {

	

	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
    private String  name;
	
	
	private String  emailId;
	private String  password;
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
