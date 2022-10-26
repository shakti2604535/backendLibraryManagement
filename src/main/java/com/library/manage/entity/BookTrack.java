package com.library.manage.entity;
import  java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

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
	private long	bookId;
	private Date	startDate;
	private Date	expectedReturnDate;
	private Date	actualReturnDate;
	private long	personId;
	
	
	public BookTrack() {
		super();
	}
	public Long getTrackId() {
		return trackId;
	}
//	public void setTrackId(Long trackId) {
//		this.trackId = trackId;
//	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public Date getStartDate() {
		return startDate;
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
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}

}
