package com.library.manage.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
//import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.manage.validation.PublishDate;




public class Book {
	
	
	@NotNull( message="can not empty or null")
	 @Length(min = 4,max = 15,message = "title  must be min 4  max 15 letter")
	private String 	title;
	@NotNull( message="can not empty or null")
	 @Length(min = 4,max = 25,message = "description  must be min 4  max 25 letter")
	private String	description;
	@NotNull( message="can not empty or null")

	@Min(value=100 ,message=" Pagecount min 50 required")

	private int	pageCount;
	@PublishDate
	private Date	publishDate;  
	@NotNull( message=" availableStockcan not empty or null")
	 @Min(value=2,message=" availableStock min 2 required")
	private	int availableStock;
//	
	
	public Book() {
		super();
	}
	
	



	public Book(String title, String description, int pageCount, Date publishDate, int availableStock) {
		super();
		this.title = title;
		this.description = description;
		this.pageCount = pageCount;
		this.publishDate = publishDate;
		this.availableStock = availableStock;
	}

//	public void setBookId(Long bookId) {
//		this.bookId = bookId;
//	}

	public String getTitle() {
		return title;
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
