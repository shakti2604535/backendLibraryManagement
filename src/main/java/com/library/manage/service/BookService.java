package com.library.manage.service;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.library.manage.entity.AuthorAllBooks;
import com.library.manage.entity.Book;
import com.library.manage.entity.BookDetails;
import com.library.manage.entity.BookQueryData;
import com.library.manage.entity.BookTrack;
import com.library.manage.entity.Books;
import com.library.manage.entity.CreateAuthorBook;
import com.library.manage.exception.ResourceNotFoundException;

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
	
public List<BookQueryData>getbook()
	
	{
	return booksRepository.getallbookTest();
//		return  booksRepository.getallbook();
		 
	}
	
public List<BookDetails> getallbooks(){
		   List<Books> books = booksRepository.findAll();
		List<BookDetails>BookDetails = new ArrayList<>();
		List<BookDetails>BookDetails2 = new ArrayList<>();
		
		for(int i = 0;i<books.size();i++)
	{  
			//if(books.get(i).getAuthor().size()>0)	 {
//			System.out.println(books.get(i).getAuthor().get(0).getFirstName());
//		           }
		
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
		List<BookTrack>booktrack = 	bookTrackRepository.findByBookIdBookId(bookid);
		
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
	
	
	public CreateAuthorBook countbooks( int os,int ps){
		CreateAuthorBook ca =  new CreateAuthorBook();
		Page<Books>books = booksRepository.findAll(PageRequest.of(os, ps));
		     ca.setPs( books.getPageable().getPageSize());
		     ca.setTe(books.getTotalElements());
		     List<AuthorAllBooks> al = new ArrayList<>();
		     List<Books> b = new ArrayList<>();
		     b = books.getContent();
		     for(int i = 0;i<b.size();i++)
		     {
		    	 AuthorAllBooks alb = new AuthorAllBooks();
		    	 alb.setBookId(b.get(i).getBookId());
		    	 alb.setAvailableStock(b.get(i).getAvailableStock());
		    	 alb.setFirstname(b.get(i).getAuthor().get(0).getFirstName());
		    	 alb.setLastname(b.get(i).getAuthor().get(0).getLastname());
		    	 alb.setDescription(b.get(i).getDescription());
		    	 alb.setPageCount(b.get(i).getPageCount());
		    	 alb.setId(b.get(i).getAuthor().get(0).getAuthorId());
		    	 alb.setPublishDate(b.get(i).getPublishDate());
		    	 alb.setTitle(b.get(i).getTitle());
		    	 al.add(alb);
		     }
		     ca.setAb(al);
		      
		return ca;
		
	}
	
	public boolean createbook(long id,  Book book) {
		
//		  System.out.println(book.getAvailableStock());
//		return true;
//		Author author1 = authorRepository.findById(id).get();
////		author1.setFirstName(author.getFirstName());
////		author1.setLastname(author.getLastname());
//		author1.setBooks(author.getBooks());
//		authorRepository.save(author1);
//		
//		return true;
		Books book2 = booksRepository.findByTitle(book.getTitle());
		long authorid = id;
		Author author = authorRepository.findById(authorid).get();
		if(book2 != null)
		{
			return false;
		}
//		long authorid = id;
		Books b =  new Books();
		b.setAvailableStock(book.getAvailableStock());
		b.setDescription(book.getDescription());
		b.setPageCount(book.getPageCount());
		b.setPublishDate(book.getPublishDate());
		b.setTitle(book.getTitle());
//		 Author author = authorRepository.findById(authorid).get();
		long bookid = booksRepository.save(b).getBookId();
		System.out.println(bookid);
//		long authorid = id;
	
		
//		List<Author>auth =     authorRepository.findByBooksBookId(bookid);
//	       if(auth.contains(author))
//	       {
//	    	   return false;
//	       }
	        if(book != null && author != null) {
	          List<Books> a = author.getBooks();
//	        projectSet =  employee.getAssignedProjects();
//	        projectSet.add(project);\
	          a.add(b);
//	        employee.setAssignedProjects(projectSet);
	          author.setBooks(a);
	         authorRepository.save(author);
	         return true;
	        }
	        return false;
		
		
		
	}
	
public AuthorAllBooks getauthorbyIds(long BId,long AId){
		
		AuthorAllBooks al = new AuthorAllBooks();
	Optional<Author> author = authorRepository.findById(AId);
	
 Books b = new Books();
 Author a = new Author();
	if(!author.isPresent())
	{
		throw new ResourceNotFoundException("Author","AuthorId",AId);
//		return  null ;
	}
	else
	{
	 a = author.get();
	}
	
	Optional<Books> book = booksRepository.findById(BId);
	if(!book.isPresent())
	{
		throw new ResourceNotFoundException("Book","BookId",BId);
//		return  null ;
	}
	else
	{
		 b = book.get();
	}

	 al.setBookId(b.getBookId());
	 al.setAvailableStock(b.getAvailableStock());
	 al.setDescription(b.getDescription());
	 al.setPageCount(b.getPageCount());
	 al.setPublishDate(b.getPublishDate());
	 al.setTitle(b.getTitle());
	
		al.setFirstname(a.getFirstName());
		al.setLastname(a.getLastname());
		al.setId(AId);

	return al;

}
	
	public CreateAuthorBook bookbynamepage( int os,int ps,String bn){
		
		CreateAuthorBook ca =  new CreateAuthorBook();
		Page<Books>books = booksRepository.findByAuthorFirstNameContaining(bn, PageRequest.of(os, ps));
		     ca.setPs( books.getPageable().getPageSize());
		     ca.setTe(books.getTotalElements());
		     List<AuthorAllBooks> al = new ArrayList<>();
		     List<Books> b = new ArrayList<>();
		     b = books.getContent();
		     for(int i = 0;i<b.size();i++)
		     {
		    	 AuthorAllBooks alb = new AuthorAllBooks();
		    	 alb.setBookId(b.get(i).getBookId());
		    	 alb.setAvailableStock(b.get(i).getAvailableStock());
		    	 alb.setFirstname(b.get(i).getAuthor().get(0).getFirstName());
		    	 alb.setLastname(b.get(i).getAuthor().get(0).getLastname());
		    	 alb.setDescription(b.get(i).getDescription());
		    	 alb.setPageCount(b.get(i).getPageCount());
		    	 alb.setId(b.get(i).getAuthor().get(0).getAuthorId());
		    	 alb.setPublishDate(b.get(i).getPublishDate());
		    	 alb.setTitle(b.get(i).getTitle());
		    	 al.add(alb);
		     }
		     ca.setAb(al);
		      
		return ca;
		
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