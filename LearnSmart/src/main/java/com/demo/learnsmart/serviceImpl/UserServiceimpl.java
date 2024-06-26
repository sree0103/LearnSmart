package com.demo.learnsmart.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.learnsmart.entity.User;
import com.demo.learnsmart.repository.UserRepo;
import com.demo.learnsmart.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepo user_repo;

	@Override
	public boolean getEmail(String email) {
		if (user_repo.findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String addUser(User user) {
		user_repo.save(user);
		return "user added";

	}

	@Override
	public boolean validateUser(String email, String password) {
		String pass = user_repo.findByEmail(email).getPassword();
		if (pass.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		String role = user_repo.findByEmail(email).getRole();
		return role;
	}

}
