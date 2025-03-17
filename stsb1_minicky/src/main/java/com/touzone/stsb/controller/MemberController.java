package com.touzone.stsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.touzone.stsb.service.MemberService;
import com.touzone.stsb.vo.MemberVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class MemberController {
	
	@Autowired
	MemberService memberService;
	/*
	 * withdraw
	 */
	@GetMapping("/withdraw")
	public String withdraw() {
		return "withdraw";
	}
	
	/*
	 * memupform
	 */
	@GetMapping("/memupdate")
	public String memupdate() {
		return "memupdate";
	}
	
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
	public String loginpro(@RequestParam("memid") String memid, 
						   @RequestParam("password") String password,
						   HttpSession session) throws Exception {
		System.out.println("memid="+memid);
		System.out.println("password="+password);
		boolean rst = memberService.authMember(memid, password);
		if(rst) {
			// 로그인 성공 시 세션에 사용자 정보 저장
	        session.setAttribute("loginUser", memid);
			return "redirect:/";
		}else {
			return "redirect:/login";
		}		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
	    session.invalidate(); // 세션 삭제
	    return "redirect:/";
	}
	
	@GetMapping("/reg")
	public String reg() {
		return "reg";
	}
	
	@PostMapping("/reg")
	public String regro(MemberVo membervo) throws Exception {
		//System.out.println(membervo.getMemid());
		
		int nMemReg = memberService.regMember(membervo); 
		if(nMemReg != 0) {
			return "redirect:/login"; 
		} else { 
			return "redirect:/reg"; 
		}
		
	}

}
