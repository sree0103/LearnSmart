package com.demo.learnsmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.learnsmart.entity.User;
import com.demo.learnsmart.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	
	@Autowired
	UserService user_serv;
	
	@PostMapping("/register")
	public String adduser(@ModelAttribute User user) {
		boolean status = user_serv.getEmail(user.getEmail());
		if(status == false) {
			user_serv.addUser(user);
			System.out.println("user added");
			
		}
		else {
			System.out.println("user exists");
		}
		return "login";
	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email, @RequestParam("password") String password, Model model ) {
		if(user_serv.validateUser(email, password) == true){
			String role = user_serv.getRole(email);
			if(role.equals("admin")) {
				return "adminhome";
			}
			else {
				return "learnerhome";
			}
		}
		else {
		return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
}
