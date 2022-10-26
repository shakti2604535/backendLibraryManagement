package com.library.manage.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.library.manage.entity.Author;
import com.library.manage.entity.BookDetails;
import com.library.manage.entity.BookTrack;
import com.library.manage.entity.Books;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private BookTrackRepository bookTrackRepository;
	@GetMapping("/book/{Id}")
	public Books getbookbyId(@PathVariable(value="Id" )long id){
		   Books books = booksRepository.findById(id).get();
		
		if(books != null)
		{
			return books;
		}
		return null;
		
	}
	@GetMapping("/books")
	public List<BookDetails> getbookbyId(){
		   List<Books> books = booksRepository.findAll();
		List<BookDetails>BookDetails = new ArrayList<>();
		
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
			return BookDetails;
		
	}
	
	@GetMapping("onlybooks")
	public List<Books> createBook( ) {
		 return booksRepository.findAll();
	}
	@PostMapping("/addbook")
	public Books createBook( @RequestBody Books book) {
//		System.out.println(book.getPageCount());
		 return booksRepository.save(book);
	}
	
	
         @PutMapping("/updatebook/{id}")
         public boolean updatebook(@PathVariable(value="id")long id,@RequestBody Books book)
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

}
