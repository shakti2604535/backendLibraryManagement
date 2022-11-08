package com.library.manage.Respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.manage.entity.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books,Long> 
 { 
       Books findByTitle(String title);
     Page<Books> findByTitleContaining(String title, Pageable pageable);
}
