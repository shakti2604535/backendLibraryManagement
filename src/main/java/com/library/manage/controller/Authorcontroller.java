package com.library.manage.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.manage.Respository.AuthorRepository;
import com.library.manage.Respository.BooksRepository;
import com.library.manage.entity.Author;
import com.library.manage.entity.AuthorDetails;
import com.library.manage.entity.Books;


@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/author")
public class Authorcontroller {

	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BooksRepository booksRepository;
	
	@GetMapping("/author/{Id}")
	public Author getauthorbyId(@PathVariable(value="Id" )long id){
		Author author = authorRepository.findById(id).get();
		
		if(author != null)
		{
			return author;
		}
		return null;
		
	}
	@PutMapping("/authors/{Id}")
	public boolean updateauthor(@PathVariable(value="Id" )long id, @RequestBody Author author) {
		Author author1 = authorRepository.findById(id).get();
		author1.setFirstName(author.getFirstName());
		author1.setLastname(author.getLastname());
		authorRepository.save(author1);
		
		return true;
		
	}
	@GetMapping("/authors")
	public List<Author> getallauthor(){
		   List<Author> author = authorRepository.findAll();
		
		
			return author;
		
	}
	@PostMapping("/addauthor")
	public Author createauthor( @RequestBody Author author) {
		 return authorRepository.save(author);
	}
	@PutMapping("{bId}/book/{aId}")
	public boolean assignbookToauthor( @PathVariable(value="bId") Long bId,   @PathVariable(value="aId")Long aId) {
		
		
		Books book = booksRepository.findById(bId).get();
      //  System.out.println(course);
        Author author = authorRepository.findById(aId).get();
        if(book != null && author != null) {
          List<Books> a = author.getBooks();
//        projectSet =  employee.getAssignedProjects();
//        projectSet.add(project);\
          a.add(book);
//        employee.setAssignedProjects(projectSet);
          author.setBooks(a);
         authorRepository.save(author);
         return true;
        }
        return false;
		
	}
	@GetMapping("/authorsbybook/{id}")
	public List<Author> getallauthorbyid(@PathVariable(value="id")long id ){
		   List<Author> author = authorRepository.findByBooksBookId(id);
		
		
			return author;
		
	}
	@GetMapping("/authorsdetails")
	public List<AuthorDetails> getallauthorDetail(){
		   List<Author> author = authorRepository.findAll();
		   List<AuthorDetails> authorDetail = new ArrayList<>();
		  
		   
	for(int i = 0;i<author.size();i++)
		   {
		 AuthorDetails ad = new AuthorDetails();
		   ad.setAuthorid(author.get(i).getAuthorId());
		   ad.setAuthorName(author.get(i).getFirstName()+" "+author.get(i).getLastname());
		   List<Books> book = new ArrayList<>();
		   book = author.get(i).getBooks();
		   String allbooks = "";
		   String outofstock = "";
		   boolean coma = false;
		   for(Books bk:book)
		   {
			   allbooks += bk.getTitle();
			   if(bk.getAvailableStock() == 0)
			   {   
				   outofstock += bk.getTitle();
				   if(coma)
				   {
					   outofstock +=" , ";
					   
					    coma = true;
				   }
				   else {
					   coma = false;
				   }
						   
						
			      }
			   if(book.indexOf(bk) != book.size()-1)
			   {
				   allbooks +=" , ";
				   
				   
			   }
			   
		   }
		   ad.setAuthorBook(allbooks);
		   ad.setOutofStock(outofstock);
		  
		   authorDetail.add(ad);
		   
	 }
//		  System.out.println(authorDetail.get(1).getAuthorid());
	 return authorDetail;	   
		
			
		
}
	
	
}
