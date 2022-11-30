package com.library.manage.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.manage.Respository.LibrarianRespository;
import com.library.manage.entity.AuthRequest;
import com.library.manage.entity.Librarian;
import com.library.manage.entity.LibrarianRequest;
import com.library.manage.entity.loginResponse;
import com.library.manage.filter.JwtUtil;
@CrossOrigin(origins="*")
@RestController
@Validated
//@RequestMapping("/get")
public class LibrarianController {

	
	 @Autowired
	    private JwtUtil jwtUtil;
	    @Autowired
	    private AuthenticationManager authenticationManager;
	@Autowired
	 private LibrarianRespository librarianRespository;
	
	@PostMapping("/newlibr")
	public boolean createnewLib( @Valid @RequestBody   Librarian librarian){
		
		Librarian lib = librarianRespository.findByEmailId(librarian.getEmailId());
		if(lib != null)
		{
			return  false;
		}
		else {
		//	librarianRespository.save(librarian);
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
	
	//
    @PostMapping("/authenticate")
    
    public loginResponse generateToken(@RequestBody @Valid AuthRequest authRequest) throws Exception {
    	  loginResponse lr = new loginResponse();
    	try {
//        	  loginResponse lr = new loginResponse();
        	System.out.println("hhhhhhhhhhhhhhh");
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
        	System.out.println(ex.getMessage());
        	 lr.setStatuscode(500) ;
        	 return lr;
//            throw new Exception("inavalid username/password");
        }
        System.out.println(jwtUtil.generateToken(authRequest.getUserName()));
//        loginResponse lr = new loginResponse();
        		
        	lr.setToken(jwtUtil.generateToken(authRequest.getUserName()));
        	lr.setStatuscode(200);
        	return lr;
    }
}
