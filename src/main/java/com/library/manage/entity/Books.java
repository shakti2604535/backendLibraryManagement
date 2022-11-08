package com.library.manage.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Books {
	@Id
	@SequenceGenerator(
			name = "Book_sequence", 
			sequenceName="Book_sequence",
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	          generator="Book_sequence")
	private Long bookId;
	private String 	title;

	private String	description;
	private int	pageCount;
	private Date	publishDate;  
	
	private	int availableStock;
//	
	@JsonIgnore
    @ManyToMany(mappedBy = "books" )
	private List<Author>author;
	public Books() {
		super();
	}
	
	



	public Books(String title, String description, int pageCount, Date publishDate, int availableStock) {
		super();
		this.title = title;
		this.description = description;
		this.pageCount = pageCount;
		this.publishDate = publishDate;
		this.availableStock = availableStock;
	}
	public Long getBookId() {
		return bookId;
	}
//	public void setBookId(Long bookId) {
//		this.bookId = bookId;
//	}

	public String getTitle() {
		return title;
	}
	public List<Author> getAuthor() {
		return author;
	}


	public void setAuthor(List<Author> author) {
		this.author = author;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	public int getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;  
	}
	

}
