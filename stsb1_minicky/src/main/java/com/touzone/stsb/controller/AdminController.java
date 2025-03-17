package com.touzone.stsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.touzone.stsb.service.ProductService;
import com.touzone.stsb.vo.ProductVo;

@Controller
public class AdminController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/admprdlist")
	public String admprdlist(Model model) throws Exception{
		List<ProductVo> prdlist = productService.getProductList();
		
		model.addAttribute("products", prdlist);
		
		return "admprdlist";
	}
	
	// 관리자 상품 등록 화면
	@GetMapping("/admprdreg")
	public String admprdreg() throws Exception{
		
		return "admprdreg";
	}
	
	// 관리자 상품 수정 화면
	@GetMapping("/admprdmod")
	public String admprdmod(@RequestParam("pid") int productId,  Model model) throws Exception{
		
		System.out.println("상품 ID: " + productId);
		
		ProductVo product = productService.getProduct(productId);
		
		model.addAttribute("product", product);
		
		return "admprdmod";
	}

}
