package com.library.manage.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.manage.Respository.BookTrackRepository;
import com.library.manage.Respository.BooksRepository;
import com.library.manage.Respository.PersonRepository;
import com.library.manage.entity.BookTrack;
import com.library.manage.entity.BookTrackDetails;
import com.library.manage.entity.BookTrackput;
import com.library.manage.entity.BookTrackset;
import com.library.manage.entity.Books;
import com.library.manage.entity.PersonData;
import com.library.manage.service.BookTrackService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/booktrack")
public class BookTrackController {
	
	@Autowired
	private BookTrackService bt;
	@GetMapping("/detail")
	public List<BookTrack>getbooktrack(){
		
		
		return bt.getbooktrack();
		
		
	}
	@GetMapping("/detailed")
	public List<BookTrackDetails>getbooktrackwithname(){
		
		
		return bt.getbooktrackwithname();
		
	}
	
	@GetMapping("/book/{id}")
	public List<BookTrackDetails>getrentedbookbyid(@PathVariable(value="id") long bookid){
	return bt.getrentedbookbyid(bookid);
		
		
	}
	@PostMapping("/assignbook")
	public Boolean  Assignbooktoperson( @RequestBody @Valid BookTrackset bookTrack) {
   return bt.Assignbooktoperson(bookTrack);
		
	}
	
	
	@PutMapping("/returnbook/{btId}")
	public Boolean  Returnbooktoperson(  @PathVariable(value="btId") Long btId, @RequestBody @Valid BookTrackput bookTrack1)
	{
		return bt.Returnbooktoperson(btId, bookTrack1);
	}
	
}
