package com.library.manage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.library.manage.Respository.BooksRepository;
import com.library.manage.entity.Author;
import com.library.manage.entity.AuthorAllBooks;
import com.library.manage.entity.AuthorDetails;
import com.library.manage.entity.Books;
import com.library.manage.service.AuthorService;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/author")
public class Authorcontroller {

	@Autowired
	private AuthorService as;
	
	@GetMapping("/author/{Id}")
	public Author getauthorbyId(@PathVariable(value="Id" )long id){
	 return as.getauthorbyId(id);

		
	}
	@GetMapping("/author/{BId}/{AId}")
	public AuthorAllBooks getauthorbyIds(@PathVariable(value="BId" )long BId,@PathVariable(value="AId" )long AId){
		
         return as.getauthorbyIds(BId, AId);

	}
	@PutMapping("/authorbyid/{Id}")
	public boolean updateauthorbyid(@PathVariable(value="Id" )long id, @RequestBody @Valid Author auth) {
	
		return as.updateauthorbyid(id, auth);
	}
	
	
	
	@PutMapping("/authors/{Id}")
	public boolean updateauthor(@PathVariable(value="Id" )long id, @RequestBody Books book) {
		
          return as.updateauthor(id, book);
		
	}
	@GetMapping("/authors")
	public List<Author> getallauthor(){
		return as.getallauthor();
		
	}
	
	@GetMapping("/authorse")
	public List<AuthorAllBooks> getallauthors(){
        return as.getallauthors();
		
	}
	
	
	@GetMapping("authorwith")
	public List<AuthorAllBooks>authornamecontain(@RequestParam(value="val") String val)
	{
		
	return as.authornamecontain(val);
		
	}
	
	
	
	
	
	
	@PostMapping("/addauthor")
	public Author createauthor( @RequestBody  @Valid Author author) {
		 return as.createauthor(author);
	}
	@PutMapping("{bId}/book/{aId}")
	public boolean assignbookToauthor( @PathVariable(value="bId") Long bId,   @PathVariable(value="aId")Long aId) {
		
		
	return as.assignbookToauthor(bId, aId);
		
	}
	@GetMapping("/authorsbybook/{id}")
	public List<Author> getallauthorbyid(@PathVariable(value="id")long id ){
		return as.getallauthorbyid(id);
		
	}
	@GetMapping("/authorsdetails")
	public List<AuthorDetails> getallauthorDetail(){
	return as.getallauthorDetail();   
		
			
		
}
	
	
}
