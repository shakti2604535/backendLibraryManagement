package com.library.manage.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.library.manage.entity.Author;
import com.library.manage.entity.BookTrack;
import com.library.manage.entity.Books;
import com.library.manage.entity.PersonData;
import com.library.manage.entity.PersonDetails;
import com.library.manage.service.PersonService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/get")
public class PersonController {
	
	
	@Autowired
	private PersonService ps;
//@Autowired
//private PersonRepository personRepository;
//@Autowired
//private BooksRepository booksRepository;
//@Autowired
//private BookTrackRepository bookTrackRepository;
	
	@GetMapping("/person/{Id}")
	public PersonData getauthorbyId(@PathVariable(value="Id" )long id){
	return ps.getauthorbyId(id);
		
	
	}
	@GetMapping("/persons")
	public List<PersonData> getallpersons(){
		  
		
		
			return ps.getallpersons();
		
	}
	@GetMapping("/personsdetail")
	public List<PersonDetails> getallpersondetails(){
	return ps.getallpersondetails();
			
		
	}
	@PostMapping("/addperson")
	public PersonData createauthor( @RequestBody @Valid PersonData person) {
		 return ps.createauthor(person);
	}
	@PutMapping("/updateperson/{Id}")
	public boolean updateauthor(@PathVariable(value="Id" )long id, @RequestBody @Valid PersonData personData) {
		return ps.updateauthor(id, personData);
		
	}

}
