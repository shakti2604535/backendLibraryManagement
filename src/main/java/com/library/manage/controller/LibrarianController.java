package com.library.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.manage.Respository.LibrarianRespository;
import com.library.manage.entity.Librarian;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/get")
public class LibrarianController {

	
	
	@Autowired
	 private LibrarianRespository librarianRespository;
	
	@PostMapping("/newlibr")
	public boolean createnewLib(@RequestBody Librarian librarian){
		
		Librarian lib = librarianRespository.findByEmailId(librarian.getEmailId());
		if(lib != null)
		{
			return  false;
		}
		else {
			librarianRespository.save(librarian);
			return true;
			
		}
	
		
	}
	
	@GetMapping("/librarian/{emailId}")
	public Librarian getByEmailId(@PathVariable(value="emailId" ) String emailId){
		
		 Librarian librarian = librarianRespository.findByEmailId( emailId);
		 if(librarian != null)
		 {
			  return librarian;
		 }  
//		    String e = 'eeeeeeeeeeee';
			 Librarian li = new Librarian();
//			 li.setEmailId("");
//			 li.setName("");
//			 li.setPassword("");
			 return li;
			 
		 
//		 return null;
	}
}
