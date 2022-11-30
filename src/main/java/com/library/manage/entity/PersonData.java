package com.library.manage.entity;

import java.time.LocalDate;
import java.util.Date;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.library.manage.validation.Dateofbirth;
@Entity
public class PersonData {
	@Id
	@SequenceGenerator(
			name = "Person_sequence", 
			sequenceName="Person_sequence",
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	          generator="Person_sequence")
	private long	ID;

	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
	 @Length(min = 4,max = 15,message = "username  must be min 4  max 15 letter")
	private String	Name;
	
	
	@NotNull(message = "It should not be null")
//    @NotEmpty(message = "It should not be Empty")
	@Dateofbirth
	private Date DOB;
	@Lob

//	@NotNull(message = "It should not be null")
//    @NotEmpty(message = "It should not be Empty")
//	 @Length(min = 4,message = "Address  must be min 4   letter")
	private String	Address;
	
	
	public long getID() {
		return ID;
	}
//	public void setID(long iD) {
//		ID = iD;
//	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}

}
