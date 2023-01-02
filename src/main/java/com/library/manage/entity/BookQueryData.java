package com.library.manage.entity;

public interface BookQueryData   {

	// book_id,rented,overdue,available_stock,authorname,title
	Long getBookId();
	String getTitle();
	String getAuthorname();
	Integer getAvailableStock();
	Integer getRented();
	Integer getOverdue();
	
	
	
}
