package com.touzone.stsb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("/boardmod")
	public String boardmod() {
		return "boardmod";
	}
	
	@GetMapping("/boardview")
	public String boardview() {
		return "boardview";
	}
	
	@GetMapping("/boardreg")
	public String boardreg() {
		return "boardreg";
	}
	
	@GetMapping("/boardlist")
	public String boardlist() {
		return "boardlist";
	}

}
