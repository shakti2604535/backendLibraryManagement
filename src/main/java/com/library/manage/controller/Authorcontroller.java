package com.library.manage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.library.manage.entity.AuthorAllBooks;
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
	@GetMapping("/author/{BId}/{AId}")
	public AuthorAllBooks getauthorbyIds(@PathVariable(value="BId" )long BId,@PathVariable(value="AId" )long AId){
		
			AuthorAllBooks al = new AuthorAllBooks();
		Optional<Author> author = authorRepository.findById(AId);
		
	 Books b = new Books();
	 Author a = new Author();
		if(!author.isPresent())
		{
			return null;
		}
		else
		{
		 a = author.get();
		}
		
		Optional<Books> book = booksRepository.findById(BId);
		if(!book.isPresent())
		{
			return null;
		}
		else
		{
			 b = book.get();
		}
	
		 al.setBookId(b.getBookId());
		 al.setAvailableStock(b.getAvailableStock());
		 al.setDescription(b.getDescription());
		 al.setPageCount(b.getPageCount());
		 al.setPublishDate(b.getPublishDate());
		 al.setTitle(b.getTitle());
		
			al.setFirstname(a.getFirstName());
			al.setLastname(a.getLastname());
			al.setId(AId);

		return al;

	}
	@PutMapping("/authors/{Id}")
	public boolean updateauthor(@PathVariable(value="Id" )long id, @RequestBody Books book) {
		
//		  System.out.println(book.getAvailableStock());
//		return true;
//		Author author1 = authorRepository.findById(id).get();
////		author1.setFirstName(author.getFirstName());
////		author1.setLastname(author.getLastname());
//		author1.setBooks(author.getBooks());
//		authorRepository.save(author1);
//		
//		return true;
		Books book2 = booksRepository.findByTitle(book.getTitle());
		if(book2 != null)
		{
			return false;
		}
		long bookid = booksRepository.save(book).getBookId();
		System.out.println(bookid);
		long authorid = id;
		 Author author = authorRepository.findById(authorid).get();
		
		List<Author>auth =     authorRepository.findByBooksBookId(bookid);
	       if(auth.contains(author))
	       {
	    	   return false;
	       }
	        if(book != null && author != null) {
	          List<Books> a = author.getBooks();
//	        projectSet =  employee.getAssignedProjects();
//	        projectSet.add(project);\
	          a.add(book);
//	        employee.setAssignedProjects(projectSet);
	          author.setBooks(a);
	         authorRepository.save(author);
	         return true;
	        }
	        return false;
		
		
		
	}
	@GetMapping("/authors")
	public List<Author> getallauthor(){
		   List<Author> author = authorRepository.findAll();
		
		
			return author;
		
	}
	
	@GetMapping("/authorse")
	public List<AuthorAllBooks> getallauthors(){
		   List<Author> author = authorRepository.findAll();
		   List<AuthorAllBooks>authors = new ArrayList<>();
		  // Author at = new Author();
		  
		 for(int i = 0;i<author.size();i++)
		 {  
			 for(int j = 0;j<author.get(i).getBooks().size();j++)
			 {
				 AuthorAllBooks at = new AuthorAllBooks();
				 at.setId(author.get(i).getAuthorId());
				 at.setFirstname(author.get(i).getFirstName());
				 at.setLastname(author.get(i).getLastname());
				 Books b = author.get(i).getBooks().get(j);
				 at.setBookId(b.getBookId());
				 at.setAvailableStock(b.getAvailableStock());
				 at.setDescription(b.getDescription());
				 at.setPageCount(b.getPageCount());
				 at.setPublishDate(b.getPublishDate());
				 at.setTitle(b.getTitle());
				
				 
				 
//				 System.out.println(author.get(i).getBooks().get(j).getTitle());
//				 System.out.println(at.getBook().getBookId());
				 authors.add(at);
			 }
		 }
		
		
			return authors;
		
	}
	
	
	@GetMapping("authorwith/{val}")
	public List<AuthorAllBooks>authornamecontain(@PathVariable(value="val") String val)
	{
		
		   List<Author> author = authorRepository.findByFirstNameContaining(val);
		   List<AuthorAllBooks>authors = new ArrayList<>();
		   if(author == null)
		   {
//		   {   AuthorAllBooks at = new AuthorAllBooks();
			   return authors;
		   }
//		   List<AuthorAllBooks>authors = new ArrayList<>();
		  // Author at = new Author();
		  
		 for(int i = 0;i<author.size();i++)
		 {  
			 for(int j = 0;j<author.get(i).getBooks().size();j++)
			 {
				 AuthorAllBooks at = new AuthorAllBooks();
				 at.setId(author.get(i).getAuthorId());
				 at.setFirstname(author.get(i).getFirstName());
				 at.setLastname(author.get(i).getLastname());
				 Books b = author.get(i).getBooks().get(j);
				 at.setBookId(b.getBookId());
				 at.setAvailableStock(b.getAvailableStock());
				 at.setDescription(b.getDescription());
				 at.setPageCount(b.getPageCount());
				 at.setPublishDate(b.getPublishDate());
				 at.setTitle(b.getTitle());
				
				 
				 
//				 System.out.println(author.get(i).getBooks().get(j).getTitle());
//				 System.out.println(at.getBook().getBookId());
				 authors.add(at);
			 }
		 }
		
		
			return authors;
		
	}
	
	
	
	
	
	
	@PostMapping("/addauthor")
	public Author createauthor( @RequestBody Author author) {
		 return authorRepository.save(author);
	}
	@PutMapping("{bId}/book/{aId}")
	public boolean assignbookToauthor( @PathVariable(value="bId") Long bId,   @PathVariable(value="aId")Long aId) {
		
		
		Books book = booksRepository.findById(bId).get();
      //  System.out.println(coursejgjhghjhj);
        Author author = authorRepository.findById(aId).get();
       List<Author>auth =     authorRepository.findByBooksBookId(bId);
       if(auth.contains(author))
       {
    	   return false;
       }
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
