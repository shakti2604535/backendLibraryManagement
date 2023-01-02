package com.library.manage.service;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.library.manage.exception.ResourceNotFoundException;

@Service
public class BookTrackService {
	
	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private BookTrackRepository bookTrackRepository;
	
	public List<BookTrack>getbooktrack(){
		
		
		return bookTrackRepository.findAll();
		
		
	}
	public List<BookTrackDetails>getbooktrackwithname(){
		
		
		 List<BookTrack> bt =  bookTrackRepository.findAll();
		 List<BookTrackDetails>btd = new ArrayList<>();
//		 System.out.println(bt.get(0).getBookId()+"hi");
		 
		 for(int i = 0;i<bt.size();i++)   
		 {
			 
			 BookTrackDetails bookTrackDetails = new BookTrackDetails();
			 bookTrackDetails.setBooktrack(bt.get(i));
//			 Books b = booksRepository.findById(bt.get(i).getBookId()).get();
//			
//
//			 PersonData p = personRepository.findById(bt.get(i).getPersonId()).get();
			
			 bookTrackDetails.setTitle(bt.get(i).getBookId().getTitle());
			 bookTrackDetails.setPersonName(bt.get(i).getPersonId().getName());
			 btd.add(bookTrackDetails);
		 }
		 return btd;
		
		
	}
//	
//
	public List<BookTrackDetails>getrentedbookbyid( long bookid){
		
		List<BookTrack>bt = bookTrackRepository.findByBookIdBookId(bookid);
		List<BookTrackDetails>bk = new ArrayList<>();
		if(bt.isEmpty())
		{
			throw new ResourceNotFoundException("Book","BookId",bookid);
//			return  null ;
		}
		for(int i = 0;i<bt.size();i++)
		{
			
//			 bookTrackDetails.setBooktrack(bt.get(i));
			if(bt.get(i).getActualReturnDate()  == null)
			{
				 BookTrackDetails bookTrackDetails = new BookTrackDetails();
				 bookTrackDetails.setBooktrack(bt.get(i));
//				 Books b = booksRepository.findById(bt.get(i).getBookId()).get();
//					
//
//				 PersonData p = personRepository.findById(bt.get(i).getPersonId()).get();
				
				 bookTrackDetails.setTitle(bt.get(i).getBookId().getTitle());
				 bookTrackDetails.setPersonName(bt.get(i).getPersonId().getName());
				bk.add(bookTrackDetails);
			}
		}
			
			return bk;
			
//		}
		
		
	}
//
	public Boolean  Assignbooktoperson(  BookTrackset bookTrack) {
		BookTrack bookTrack1 =  new BookTrack();
	bookTrack1.setActualReturnDate(bookTrack.getActualReturnDate());
	Books book = booksRepository.findById(bookTrack.getBookId()).get();
	PersonData person = personRepository.findById(bookTrack.getPersonId()).get();
//	bookTrack1.setBookId (book);
	bookTrack1.setExpectedReturnDate(bookTrack.getExpectedReturnDate());
	bookTrack1.setStartDate(bookTrack.getStartDate());
	bookTrack1.setPersonId(person);
		try {
//		Books book = booksRepository.findById(bookTrack.getBookId()).get();
//		PersonData person = personRepository.findById(bookTrack.getPersonId()).get();
		if(book!= null && person!=null && book.getAvailableStock()>0)
		{  
			
			if(bookTrack.getStartDate().before(bookTrack.getExpectedReturnDate()))
			{        
				book.setAvailableStock(book.getAvailableStock()-1);
//				booksRepository.save(book);
				bookTrack1.setBookId (book);
				System.out.println("i am checking");
			 bookTrackRepository.save(bookTrack1);
			 return true;
			}
			  return  false; 
		}
		return false;
	 }
		catch(Exception e)
		{
			return false;
		}
		
	}
//	
//	
//	
	public Boolean  Returnbooktoperson(   Long btId,  BookTrackput bookTrack1)
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
//				    
			   Books book = booktrack.getBookId();
					   //booksRepository.findById(booktrack.getBookId()).get();
			   
			   book.setAvailableStock(book.getAvailableStock()+1);
//			   booksRepository.save(book);
			   booktrack.setBookId(book);
			   booktrack.setActualReturnDate(bookTrack1.getActualReturnDate());
			     bookTrackRepository.save(booktrack);
			   return true;
			   
		   }
			   return false;
		
	}
		   return false;

	}
	
}