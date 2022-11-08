package com.library.manage.entity;

public class BookTrackDetails {

	
	private String title;
	private String personName;
	private BookTrack booktrack;
	
	public BookTrackDetails() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public BookTrack getBooktrack() {
		return booktrack;
	}
	public void setBooktrack(BookTrack booktrack) {
		this.booktrack = booktrack;
	}
	
}
