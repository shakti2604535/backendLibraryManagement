package com.library.manage.entity;

public class AuthorDetails {

	
	private long authorid;
	private String authorName;
	private String authorBook;
	private String outofStock;
	
	public AuthorDetails() {
		super();
	}
	public long getAuthorid() {
		return authorid;
	}
	public void setAuthorid(long authorid) {
		this.authorid = authorid;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorBook() {
		return authorBook;
	}
	public void setAuthorBook(String authorBook) {
		this.authorBook = authorBook;
	}
	public String getOutofStock() {
		return outofStock;
	}
	public void setOutofStock(String outofStock) {
		this.outofStock = outofStock;
	}
	
}
