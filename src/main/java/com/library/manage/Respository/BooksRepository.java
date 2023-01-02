package com.library.manage.Respository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.manage.entity.BookDetails;
import com.library.manage.entity.BookQueryData;
import com.library.manage.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books,Long> 
 { 
       Books findByTitle(String title);
     Page<Books> findByTitleContaining(String title, Pageable pageable);
     Page<Books>findByAuthorFirstNameContaining(String fn,Pageable pageable);
     
     
 @Query(value = "select   book_id ,rented ,overdue ,available_stock ,authorname ,title from "
 		+ "(SELECT b.book_id as book_id, b.title as title,"
 		+ "(  SELECT COUNT (*) AS rented FROM shakti1.book_track b1 "
 		+ "WHERE b1.actual_return_date IS NULL AND b1.book_id = b.book_id GROUP BY b1.book_id) rented ,"
 		+ "(  SELECT COUNT (*) AS overdue FROM shakti1.book_track b1 "
 		+ "WHERE    b1.actual_return_date IS NULL  AND b1.expected_return_date < SYSDATE  AND b1.book_id = b.book_id  "
 		+ "GROUP BY b1.book_id) overdue, b.available_stock,(at.first_name||+ ' ' || at.lastname) as authorname FROM shakti1.books b"
 		+ " inner join shakti1.author_book_map ab on ab.book_id = b.BOOK_ID "
 		+ "inner join shakti1.author at on at.author_id = ab.author_id) a where a.rented is not null",
 		 nativeQuery = true) 
 List<Map<String, Object>> getallbook();


 @Query(value = "select   book_id as bookId,rented ,overdue ,available_stock as availableStock,authorname ,title from "
 		+ "(SELECT b.book_id as book_id, b.title as title,"
 		+ "(  SELECT COUNT (*) AS rented FROM shakti1.book_track b1 "
 		+ "WHERE b1.actual_return_date IS NULL AND b1.book_id = b.book_id GROUP BY b1.book_id) rented ,"
 		+ "(  SELECT COUNT (*) AS overdue FROM shakti1.book_track b1 "
 		+ "WHERE    b1.actual_return_date IS NULL  AND b1.expected_return_date < SYSDATE  AND b1.book_id = b.book_id  "
 		+ "GROUP BY b1.book_id) overdue, b.available_stock,(at.first_name||+ ' ' || at.lastname) as authorname FROM shakti1.books b"
 		+ " inner join shakti1.author_book_map ab on ab.book_id = b.BOOK_ID "
 		+ "inner join shakti1.author at on at.author_id = ab.author_id) a where a.rented is not null",
 		 nativeQuery = true) 
 List<BookQueryData> getallbookTest();
 
 

     
}
