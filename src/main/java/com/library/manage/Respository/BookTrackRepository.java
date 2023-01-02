package com.library.manage.Respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.manage.entity.BookTrack;

public interface BookTrackRepository extends JpaRepository<BookTrack,Long>{

	
	BookTrack findByPersonIdAndBookId(long bookId,long personId);
	List<BookTrack> findByBookIdBookId(long bookId);
	List<BookTrack>findByPersonIdID(long personId);   
	List<BookTrack>findByActualReturnDateIsNull();  
}
    