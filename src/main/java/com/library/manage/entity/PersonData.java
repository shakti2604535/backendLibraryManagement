package com.library.manage.entity;

import java.util.Date;


import javax.persistence.*;
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
	private String	Name;
	private Date	DOB;
	@Lob   
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
