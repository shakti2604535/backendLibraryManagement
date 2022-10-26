package com.library.manage.entity;

import java.util.Date;

public class PersonDetails {
	
	private long personId;
	private String personName;
	private Date personDob;
	private String personAddress;
	private String personRentedBook;
	private String PersonOverdueBook;
	public PersonDetails() {
		super();
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Date getPersonDob() {
		return personDob;
	}
	public void setPersonDob(Date personDob) {
		this.personDob = personDob;
	}
	public String getPersonAddress() {
		return personAddress;
	}
	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}
	public String getPersonRentedBook() {
		return personRentedBook;
	}
	public void setPersonRentedBook(String personRentedBook) {
		this.personRentedBook = personRentedBook;
	}
	public String getPersonOverdueBook() {
		return PersonOverdueBook;
	}
	public void setPersonOverdueBook(String personOverdueBook) {
		PersonOverdueBook = personOverdueBook;
	}
	

}
