package com.touzone.stsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.touzone.stsb.service.ProductService;
import com.touzone.stsb.vo.ProductVo;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String home(Model model) throws Exception{
		List<ProductVo> prdlist = productService.getProductList();
		
		model.addAttribute("products", prdlist);
		return "index";
	}
	
	/* 이미지 업로드 화면 
	 * 테스트
	 */
	@GetMapping("/uploadex")
	public String uploadex() {
		return "uploadex";
	}
	
	/* 단일 업로드 화면 
	 * 테스트
	 */
	@GetMapping("/uploadsingle")
	public String uploadsingle() {
		return "uploadsingle";
	}
	
	/* 다중 업로드 화면 
	 * 테스트
	 */
	@GetMapping("/uploadmulti")
	public String uploadmulti() {
		return "uploadmulti";
	}

}
