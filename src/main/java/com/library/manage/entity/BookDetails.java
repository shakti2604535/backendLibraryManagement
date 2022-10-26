package com.library.manage.entity;

public class BookDetails {

	private long bookId;
	private String bookName;
	private String authorName;
	private int availableStock;
	private int rentedBook;
	private int overdueBook;
	
	public BookDetails() {
		super();
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getAvailableStock() {
		return availableStock;
	}
	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}
	public int getRentedBook() {
		return rentedBook;
	}
	public void setRentedBook(int rentedBook) {
		this.rentedBook = rentedBook;
	}
	public int getOverdueBook() {
		return overdueBook;
	}
	public void setOverdueBook(int overdueBook) {
		this.overdueBook = overdueBook;
	}
}
