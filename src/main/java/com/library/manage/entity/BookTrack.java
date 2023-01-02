package com.library.manage.entity;
import  java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.library.manage.validation.ExpectedDate;
import com.library.manage.validation.StartDate;


@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity



public class BookTrack {

	@Id
	@SequenceGenerator(
			name = "track_sequence", 
			sequenceName="track_sequence",
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	          generator="track_sequence")
	private Long trackId;
//    @NotEmpty(message = "It should not be Empty")
    
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BOOK_ID")
	private Books	bookId;

	private Date	startDate;
	private Date	expectedReturnDate;

	private Date	actualReturnDate;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERSON_ID")
	private PersonData	personId;
	
	
	public BookTrack() {
		super();
	}
	public Long getTrackId() {
		return trackId;
	}
//	public void setTrackId(Long trackId) {
//		this.trackId = trackId;
//	}
	
	public Date getStartDate() {
		return startDate;
	}
	public Books getBookId() {
		return bookId;
	}
	public void setBookId(Books bookId) {
		this.bookId = bookId;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getExpectedReturnDate() {
		return expectedReturnDate;
	}
	public void setExpectedReturnDate(Date expectedReturnDate) {
		this.expectedReturnDate = expectedReturnDate;
	}
	public Date getActualReturnDate() {
		return actualReturnDate;
	}
	public void setActualReturnDate(Date actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}
	public PersonData getPersonId() {
		return personId;
	}
	public void setPersonId(PersonData personId) {
		this.personId = personId;
	}


}
