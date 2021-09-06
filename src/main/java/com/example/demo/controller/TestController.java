package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserInfo;
import com.example.demo.repository.RoleRespo;
import com.example.demo.repository.UserInfoRespo;
import com.example.demo.repository.UserRespo;

@Controller
public class TestController {
	@Autowired 
	UserRespo userRespo;
	
	@Autowired 
	RoleRespo roleRespo;
	
	@Autowired
	UserInfoRespo userInfoRespo;
	
	@RequestMapping("/")
	public String getindex(Principal principle) {
		if(principle.getName()!=null) {
			return "index";
		}else {
			return "loginpage";
		}
	}
	
	@GetMapping("/getloginpage")
	public String getLogin() {
		return "loginpage";
	}
	
	@GetMapping("/admin/home")
	public String jsfdag() {
		
		
		
		return "admin";
	}
	@GetMapping("/user/home")
	public String userF() {
		return "user";
	}
	
	@GetMapping("/logout")
	public String logOut() {
		
		return "loginpage";
		
	}
	
	@GetMapping("/access")
	public String errorH() {
		return "error";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		
		
		
		return "registerpage";
	}
		
	@PostMapping("/registerUser")
	public String cu(@ModelAttribute User user) {
		
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		
		userRespo.save(user);
		
		
		return "loginpage";
	}
	
	
	@GetMapping("/getAll")
	@ResponseBody
	public List<UserInfo> getDetails() {
		
		
		List<UserInfo> user = userInfoRespo.findAll();
		
		return user;
	}
	
	public void setRole() {
		
		Role role = new Role();
	
		role.setRole("ROLE_ADMIN");
		
	
		
	}

}
