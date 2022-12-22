package com.library.manage.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Author {

	
	@Id
	@SequenceGenerator(
			name = "Author_sequence", 
			sequenceName="Author_sequence",
			allocationSize = 1
			)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	          generator="Author_sequence")
	private Long authorId;
	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
	private String firstName;
	@NotNull(message = "It should not be null")
    @NotEmpty(message = "It should not be Empty")
	private String Lastname;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "author_book_map", joinColumns = @JoinColumn(name = "author_id", referencedColumnName = "authorId"), inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookId"))
    private List<Books> books;
	  
	public Author() {
		super();
	}

	public Author(String firstName, String lastname) {
		super();
		this.firstName = firstName;
		this.Lastname = lastname;
	}

	public Long getAuthorId() {
		return authorId;
	}
	
//	public void setAuthorId(Long authorId) {
//		this.authorId = authorId;
//	}
	public String getFirstName() {
		return firstName;
	}
	public List<Books> getBooks() {
		return books;
	}
	public void setBooks(List<Books> books) {
		this.books = books;
	}
	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	
	
}
