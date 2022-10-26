package com.library.manage.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.manage.entity.Author;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Long>  {

	
	List<Author>findByBooksBookId(long id);
}
 