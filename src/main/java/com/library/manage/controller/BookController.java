package com.library.manage.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.library.manage.service.BookService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bs;
	@GetMapping("/book/{Id}")
	public Books getbookbyId(@PathVariable(value="Id" )long id){
		 return bs.getbookbyId(id);
		
	}
	@GetMapping("/books")
	public List<BookDetails> getbookbyId(){
		return bs.getbookbyId();
			
		
	}
	
	@GetMapping("onlybooks")
	public List<Books> createBook( ) {
		 return bs.createBook();
	}
	//////////////////////
	
	@GetMapping("bookscount/{os}/{ps}")
	public ApiResponse<Page<Books>>countbooks(@PathVariable(value="os") int os,@PathVariable(value="ps") int ps){
		
		return bs.countbooks(os, ps);
		
	}
	
	@GetMapping("booksbyname/{os}/{ps}/{bn}")
	public ApiResponse<Page<Books>>bookbynamepage(@PathVariable(value="os") int os,@PathVariable(value="ps") int ps,@PathVariable(value="bn") String bn){
		
return bs.bookbynamepage(os, ps, bn);
	}
	/////////////////////////
	
	
	
	
	
	
	@PostMapping("/addbook")
	public boolean createBook( @RequestBody Books book) {
//		System.out.println(book.getPageCount());
		return bs.createBook(book);
	}
	
	
         @PutMapping("/updatebook/{id}")
         public boolean updatebook(@PathVariable(value="id")long id,@RequestBody Books book)
         {
        	return bs.updatebook(id, book);
         }
         
         
         
//         public Pagination<List<BookDetails>>

}
