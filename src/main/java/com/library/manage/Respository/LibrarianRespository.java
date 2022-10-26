package com.library.manage.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
    
import com.library.manage.entity.Librarian;
@Repository
public interface LibrarianRespository  extends JpaRepository<Librarian,Long> {

		public Librarian findByEmailId(String emailId);
}
