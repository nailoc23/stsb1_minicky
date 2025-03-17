package com.touzone.stsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.touzone.stsb.service.BoardService;
import com.touzone.stsb.service.ProductService;
import com.touzone.stsb.vo.ProductVo;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/prddetail")
	//public String getProductDetail() throws Exception{
    public String getProductDetail(@RequestParam("pid") int productId,  Model model) throws Exception{
        //System.out.println("상품 ID: " + productId);	
		        
		ProductVo product = productService.getProduct(productId);
		model.addAttribute("product", product);
		
		//System.out.println("상품 이미지경로: " + product.getFile_path());
		
		return "prddetail";
	}
	
	/*
	 * 장바구니
	 */
	@GetMapping("/prdcart")
	public String prdcart() {
		return "prdcart";
	}

	
}
