package com.touzone.stsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	
	/*
	 * loginform
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/*
	 * loginpro
	 */
	@PostMapping("/login")
	public String loginpro() {
		return "redirect:/";
	}
	
	@GetMapping("/reg")
	public String reg() {
		return "reg";
	}

}
