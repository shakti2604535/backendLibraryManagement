package com.library.manage.entity;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
	 @Length(min = 4,max = 15,message = "username  must be min 4  max 15 letter")
    private String userName;
    
	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
	 @Length(min = 6,max = 15,message = "password must be min 6 and max 15 letter")
    private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}
