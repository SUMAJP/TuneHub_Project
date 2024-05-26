package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.serviceimpl.SongServiceImpl;
import com.kodnest.tunehub.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserServiceImpl serviceimpl;
	
	@Autowired
	SongServiceImpl songservice;
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user) {
		
		String email = user.getEmail();

		//checking if the email as entered in registration  form 
		// is present in DB or not.
		boolean status = serviceimpl.emailExists(email);

		if(status == false) {
			serviceimpl.addUser(user);
			System.out.println("User added");
			return "login";
		}
		else {
			System.out.println("User already exists");
		    return "login";
		}
	}
	
	
	
	
	//validating user login
	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password, HttpSession session, Model model) {

		if(serviceimpl.validateUser(email, password) == true){

			String role = serviceimpl.getRole(email);

			session.setAttribute("email", email);

			if(role.equals("admin")) {

				return "adminhome";
			}
			else {
				User user = serviceimpl.getUser(email);
				boolean userstatus = user.isPremium();
				List<Song> songList = songservice.fetchAllSongs();
				model.addAttribute("songs", songList);
				model.addAttribute("ispremium", userstatus);
				return "customerhome";
			}
		}	
		else {
			return "login";
		}
	}
	
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "login";
	}
}
