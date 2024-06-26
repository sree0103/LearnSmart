package com.demo.learnsmart.service;

import com.demo.learnsmart.entity.User;

public interface UserService {

	boolean getEmail(String email);

	String addUser(User user);

	boolean validateUser(String email, String password);

	String getRole(String email);

}
