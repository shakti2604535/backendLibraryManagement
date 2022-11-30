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

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/get")
public class PersonController {
	
@Autowired
private PersonRepository personRepository;
@Autowired
private BooksRepository booksRepository;
@Autowired
private BookTrackRepository bookTrackRepository;
	
	@GetMapping("/person/{Id}")
	public PersonData getauthorbyId(@PathVariable(value="Id" )long id){
		try {
		PersonData person = personRepository.findById(id).get();
		
		if(person != null)
		{
			return person;
		}
		
		}
		catch(Exception e){
			PersonData person1 = new PersonData();
			person1.setName("");
			System.out.println("hiiiiiiiiiiiii");
			return person1;
		}
		return null;
		
	
	}
	@GetMapping("/persons")
	public List<PersonData> getallpersons(){
		   List<PersonData> person = personRepository.findAll();
		
		
			return person;
		
	}
	@GetMapping("/personsdetail")
	public List<PersonDetails> getallpersondetails(){
		   List<PersonData> person = personRepository.findAll();
		   List<PersonDetails>persondetail =  new ArrayList<>();
		   
		   for(int i = 0;i<person.size();i++) {
			   long personId = person.get(i).getID();
			   PersonDetails pd = new PersonDetails();
			   pd.setPersonId(personId);
			   pd.setPersonDob(person.get(i).getDOB());
			   pd.setPersonAddress(person.get(i).getAddress());
			   pd.setPersonName(person.get(i).getName());
			   
			   List<BookTrack> booktrack = bookTrackRepository.findByPersonId(personId);
			   String Rentedbyperson = "";
			   String OverdueBooks = "";
			   Date date = new Date();
			   for(BookTrack bt:booktrack)
			   {
				    Books book = booksRepository.findById(bt.getBookId()).get();
				    if(bt.getActualReturnDate() ==null) {
				    Rentedbyperson += book.getTitle()+" ,";
				    if(bt.getExpectedReturnDate().before(date))
				    {
				    	System.out.println(date);
				    	System.out.println(bt.getExpectedReturnDate().before(date));
				    	
				    	OverdueBooks += book.getTitle()+",";
				    	System.out.println(OverdueBooks);
				    	}
				    }
			   }
			   pd.setPersonRentedBook(Rentedbyperson);
			   pd.setPersonOverdueBook(OverdueBooks);
			   persondetail.add(pd);
		   }
		   return persondetail;
		
		
			
		
	}
	@PostMapping("/addperson")
	public PersonData createauthor( @RequestBody @Valid PersonData person) {
		 return personRepository.save(person);
	}
	@PutMapping("/updateperson/{Id}")
	public boolean updateauthor(@PathVariable(value="Id" )long id, @RequestBody PersonData personData) {
		PersonData personData1 = personRepository.findById(id).get();
		personData1.setName(personData.getName());
		personData1.setAddress(personData.getAddress());
		personData1.setDOB(personData.getDOB());
		personRepository.save(personData1);
		
		return true;
		
	}

}
