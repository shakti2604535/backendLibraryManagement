package com.library.manage.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.library.manage.Respository.AuthorRepository;
import com.library.manage.Respository.BookTrackRepository;
import com.library.manage.Respository.BooksRepository;
import com.library.manage.entity.ApiResponse;
import com.library.manage.entity.Author;
import com.library.manage.entity.BookDetails;
import com.library.manage.entity.BookTrack;
import com.library.manage.entity.Books;

@Service
public class BookService {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private BookTrackRepository bookTrackRepository;

	public Books getbookbyId(long id){
		   Books books = booksRepository.findById(id).get();
		
		if(books != null)
		{
			return books;
		}
		Books book = new Books();
		
		return book;
		
	}
	
	public List<BookDetails> getbookbyId(){
		   List<Books> books = booksRepository.findAll();
		List<BookDetails>BookDetails = new ArrayList<>();
		List<BookDetails>BookDetails2 = new ArrayList<>();
		
		for(int i = 0;i<books.size();i++)
		{ 
			
			long bookid =  books.get(i).getBookId();
		
			BookDetails Bookdetail  = new BookDetails() ;
			   Bookdetail.setBookId(bookid);
			   Bookdetail.setBookName(books.get(i).getTitle());
			   Bookdetail.setAvailableStock(books.get(i).getAvailableStock());
			List<Author>authordetails =    authorRepository.findByBooksBookId(bookid);
			String authorname = "";
			for(Author auth:authordetails)
			{
				authorname = authorname+auth.getFirstName()+auth.getLastname()+"     ";
			}
			Bookdetail.setAuthorName(authorname);
		List<BookTrack>booktrack = 	bookTrackRepository.findByBookId(bookid);
		
		int rente = 0;
		 Date date = new Date();
//		   ZonedDateTime zdt = date.toInstant().atZone(ZoneId.systemDefault());
		   
		int overdue = 0;
		    for(BookTrack bt: booktrack) {
//		    	ZonedDateTime zdt1 = bt.getExpectedReturnDate().toInstant().atZone(ZoneId.systemDefault());
		    	if(bt.getActualReturnDate() == null )
		    	{
		    		rente = rente+1;
		    	}
		    	if(bt.getExpectedReturnDate().before(date) && bt.getActualReturnDate() == null)
		    	{
		    		overdue = overdue+1;
		    	}
		    	
		    }
		    Bookdetail.setRentedBook(rente);
		    Bookdetail.setOverdueBook(overdue);
			
		    BookDetails.add(Bookdetail) ;
		}
		for(int i = 0;i<BookDetails.size();i++)
		{
			if(BookDetails.get(i).getRentedBook() != 0)
			{
				BookDetails2.add(BookDetails.get(i));
			}
		}
			return BookDetails2;
		
	}
	
	
	public List<Books> createBook( ) {
		 return booksRepository.findAll();
	}
	//////////////////////
	
	
	public ApiResponse<Page<Books>>countbooks( int os,int ps){
		
		Page<Books>books = booksRepository.findAll(PageRequest.of(os, ps));
		return new ApiResponse<>(books.getSize(),books);
		
	}
	
	
	public ApiResponse<Page<Books>>bookbynamepage( int os,int ps,String bn){
		
		Page<Books>books = booksRepository.findByTitleContaining(bn, PageRequest.of(os, ps));
		if(books != null)
		{
		return new ApiResponse<>(books.getSize(),books);
		}
		return null;
		
	}
	/////////////////////////
	
	
	
	
	

	public boolean createBook( Books book) {
//		System.out.println(book.getPageCount());
		Books books = booksRepository.findByTitle(book.getTitle());
		if(books!=null)
		{
			return false;
		}
		  booksRepository.save(book);
		  return true;
	}
	

         public boolean updatebook(long id,Books book)
         {
        	 Books books = booksRepository.findById(id).get();
        	  if(books!=null) {
        		  books.setTitle(book.getTitle());
     	           books.setDescription(book.getDescription());
     	           books.setAvailableStock(book.getAvailableStock());
     	           books.setPageCount(book.getPageCount());
     	           books.setPublishDate(book.getPublishDate());
     	          booksRepository.save(books);
     	          return true;
        	  }
        	  return false;
         }
         
         
         
//         public Pagination<List<BookDetails>>

}