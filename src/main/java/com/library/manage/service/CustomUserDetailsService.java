package com.library.manage.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.manage.Respository.LibrarianRespository;
import com.library.manage.entity.Librarian;




@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private LibrarianRespository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    	System.out.println("fffffffffffffffffffffff");
        Librarian user = repository.findByEmailId(username);
        System.out.println(user.getPassword()+"//////////////////////////////");
        return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), new ArrayList<>());
    }
}