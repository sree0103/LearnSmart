package com.demo.learnsmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.learnsmart.entity.User;

@Repository
public interface UserRepo  extends JpaRepository<User, String>{

	User findByEmail(String email);



}
