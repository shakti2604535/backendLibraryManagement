package com.library.manage.Respository;

import org.springframework.data.jpa.repository.JpaRepository;



import com.library.manage.entity.PersonData;

public interface PersonRepository extends JpaRepository<PersonData,Long> {

}
