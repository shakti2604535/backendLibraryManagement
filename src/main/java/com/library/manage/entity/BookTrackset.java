package com.library.manage.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.library.manage.validation.ActualReturnDate;
import com.library.manage.validation.ExpectedDate;
import com.library.manage.validation.StartDate;
@ExpectedDate
public class BookTrackset {


		private Long trackId;
		@NotNull(message = "It should not be null")
//	    @NotEmpty(message = "It should not be Empty")
	     @Min(value=1)
		private long	bookId;
		@NotNull(message = "It should not be null")
		@StartDate
		private Date	startDate;
		@NotNull(message = "It should not be null")
		private Date	expectedReturnDate;
        
		
		private Date	actualReturnDate;
		@NotNull(message = "It should not be null")
//	    @NotEmpty(message = "It should not be Empty")
	     @Min(value=1)
		private long	personId;
		public Long getTrackId() {
			return trackId;
		}
		public void setTrackId(Long trackId) {
			this.trackId = trackId;
		}
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
