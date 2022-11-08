package com.library.manage.controller;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

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
import com.library.manage.entity.Books;
import com.library.manage.entity.PersonData;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/booktrack")
public class BookTrackController {
	
	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private BookTrackRepository bookTrackRepository;
	@GetMapping("/detail")
	public List<BookTrack>getbooktrack(){
		
		
		return bookTrackRepository.findAll();
		
		
	}
	@GetMapping("/detailed")
	public List<BookTrackDetails>getbooktrackwithname(){
		
		
		 List<BookTrack> bt =  bookTrackRepository.findAll();
		 List<BookTrackDetails>btd = new ArrayList<>();
//		 System.out.println(bt.get(0).getBookId()+"hi");
		 
		 for(int i = 0;i<bt.size();i++)   
		 {
			 
			 BookTrackDetails bookTrackDetails = new BookTrackDetails();
			 bookTrackDetails.setBooktrack(bt.get(i));
			 Books b = booksRepository.findById(bt.get(i).getBookId()).get();
			

			 PersonData p = personRepository.findById(bt.get(i).getPersonId()).get();
			
			 bookTrackDetails.setTitle(b.getTitle());
			 bookTrackDetails.setPersonName(p.getName());
			 btd.add(bookTrackDetails);
		 }
		 return btd;
		
		
	}
	
	@GetMapping("/book/{id}")
	public List<BookTrackDetails>getrentedbookbyid(@PathVariable(value="id") long bookid){
		
		List<BookTrack>bt = bookTrackRepository.findByBookId(bookid);
		List<BookTrackDetails>bk = new ArrayList<>();
		if(bt.isEmpty())
		{
			return  null;
		}
		for(int i = 0;i<bt.size();i++)
		{
			
//			 bookTrackDetails.setBooktrack(bt.get(i));
			if(bt.get(i).getActualReturnDate()  == null)
			{
				 BookTrackDetails bookTrackDetails = new BookTrackDetails();
				 bookTrackDetails.setBooktrack(bt.get(i));
				 Books b = booksRepository.findById(bt.get(i).getBookId()).get();
					

				 PersonData p = personRepository.findById(bt.get(i).getPersonId()).get();
				
				 bookTrackDetails.setTitle(b.getTitle());
				 bookTrackDetails.setPersonName(p.getName());
				bk.add(bookTrackDetails);
			}
		}
			
			return bk;
			
//		}
		
		
	}
	@PostMapping("/assignbook")
	public Boolean  Assignbooktoperson( @RequestBody BookTrack bookTrack) {
		
		Books book = booksRepository.findById(bookTrack.getBookId()).get();
		PersonData person = personRepository.findById(bookTrack.getPersonId()).get();
		if(book!= null && person!=null && book.getAvailableStock()>0)
		{  
			
			if(bookTrack.getStartDate().before(bookTrack.getExpectedReturnDate()))
			{
				book.setAvailableStock(book.getAvailableStock()-1);
				booksRepository.save(book);
				
			 bookTrackRepository.save(bookTrack);
			 return true;
			}
			  return  false; 
		}
		return false; 
		
	}
	
	
	@PutMapping("/returnbook/{btId}")
	public Boolean  Returnbooktoperson(  @PathVariable(value="btId") Long btId, @RequestBody BookTrack bookTrack1)
	{
		BookTrack booktrack = bookTrackRepository.findById(btId).get();
//		   BookTrack booktrack =   bookTrackRepository.findByPersonIdAndBookId(pId, bId);  
		   if(booktrack!= null) {
			   Date date = new Date();
			   ZonedDateTime zdt = date.toInstant().atZone(ZoneId.systemDefault());
			   ZonedDateTime zdt1 = bookTrack1.getActualReturnDate().toInstant().atZone(ZoneId.systemDefault());
//			   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
//		       String str = formatter.format(date);
//		        System.out.print("Current date: "+str);
		 System.out.println(zdt.getDayOfMonth());
			   if(zdt1.getDayOfMonth() == zdt.getDayOfMonth() && zdt1.getMonth() == zdt.getMonth())
			   {
			   Books book = booksRepository.findById(booktrack.getBookId()).get();
			   book.setAvailableStock(book.getAvailableStock()+1);
			   booksRepository.save(book);
			   booktrack.setActualReturnDate(bookTrack1.getActualReturnDate());
			     bookTrackRepository.save(booktrack);
			   return true;
			   
		   }
			   return false;
		
	}
		   return false;

	}
	
}
