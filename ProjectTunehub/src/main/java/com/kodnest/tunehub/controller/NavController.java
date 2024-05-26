package com.kodnest.tunehub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	
	@GetMapping("/login")
	public String login(){
		return "login";
	}

	@GetMapping("/registeration")
	public String registration(){
		return "registeration";
	}
	
	
	@GetMapping("/newsong")
	public String newsong() {
		return "newsong";
	}


}
