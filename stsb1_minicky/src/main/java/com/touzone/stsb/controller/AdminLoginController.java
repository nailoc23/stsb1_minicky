package com.touzone.stsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.touzone.stsb.service.ManagerService;
import com.touzone.stsb.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {
	
	@Autowired
	ManagerService managerService;
	
	@GetMapping("/admlogin")
	public String admlogin() {
		return "admlogin";
	}
	
	@PostMapping("/admlogin")
	public String admloginpro(@RequestParam("id") String id, 
			   @RequestParam("pw") String pw,
			   HttpSession session) throws Exception {
		
		boolean rst = managerService.authManager(id, pw);
		if(rst) {
			session.setAttribute("loginAdmin", id);
			return "redirect:/admprdlist";
		}else {
			return "redirect:/admlogin";
		}
	}
	
	@GetMapping("/admlogout")
	public String admlogout(HttpSession session) {
		
		session.invalidate(); // 세션 삭제
		return "redirect:/admlogin";
	}

}
