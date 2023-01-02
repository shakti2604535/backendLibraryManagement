package com.library.manage.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
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
import com.library.manage.service.BookService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bs;
	@GetMapping("/{Id}")
	public Books getbookbyId(@PathVariable(value="Id" )long id){
		 return bs.getbookbyId(id);
		
	}
//	@GetMapping("/books")
//	public List<BookDetails> getallbooks(){
//		return bs.getallbooks();
//			
//		
//	}
//	@Autowired
//	private BooksRepository br;

//	 List<Map<String, Object>
	@GetMapping("/rented")
	public List<BookQueryData>getbook()
	
	{
		return  bs.getbook();
		 
	}
//	@GetMapping("/bookss")
//	public List<BookDetails> getallbookss(){
//		return bs.getallbooks();
//			
//		
//	}
	
	@PostMapping("/create/{Id}")
	public boolean createbook(@PathVariable(value="Id" )long id,  @RequestBody  @Valid Book book) {
		
          return bs.createbook(id, book);
		
	}
	
	@GetMapping("/onlybooks")
	public List<Books> createBook( ) {
		 return bs.createBook();
	}
	//////////////////////
	
	@GetMapping("pagination/{os}/{ps}")
	public  CreateAuthorBook countbooks(@PathVariable(value="os") int os,@PathVariable(value="ps") int ps){
		
		return bs.countbooks(os, ps);
		
	}
	
	@GetMapping("namepagination")
	public CreateAuthorBook bookbynamepage(@RequestParam(value="os") int os,@RequestParam(value="ps") int ps,@RequestParam(value="val") String bn){
		
         return bs.bookbynamepage(os, ps, bn);
	}
	/////////////////////////
	
	
	
	
	
	
//	@PostMapping("/addbook")
//	public boolean createBook( @RequestBody Books book) {
////		System.out.println(book.getPageCount());
//		return bs.createBook(book);
//	}
	
	
         @PutMapping("/updatebook/{id}")
         public boolean updatebook(@PathVariable(value="id")long id,@RequestBody Books book)
         {
        	return bs.updatebook(id, book);
         }
         
         
     	@GetMapping("/author/{BId}/{AId}")
    	public AuthorAllBooks getauthorbyIds(@PathVariable(value="BId" )long BId,@PathVariable(value="AId" )long AId){
    		
             return bs.getauthorbyIds(BId, AId);

    	}
         
//         public Pagination<List<BookDetails>>

}
