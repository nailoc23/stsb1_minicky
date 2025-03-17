package com.touzone.stsb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.touzone.stsb.service.ProductService;
import com.touzone.stsb.vo.ProductVo;

@RestController
public class AdminApiController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/uploadProduct_ok")
	@ResponseBody //Response가 붙으면 return의 값이 요청이 온곳으로 반환
	//public String uploadProductOk(@RequestParam("name")String name, @RequestParam("category")String category, 
		//	@RequestParam("price")int prices, @RequestParam("stock")int stock) {
	public String uploadProductOk(@ModelAttribute ProductVo productVo,
			@RequestParam("file")MultipartFile file) throws Exception{
		// productVo 객체에 name, category, price, stock 값이 자동으로 매핑됨
		System.out.println(productVo.getName());
		System.out.println(productVo.getCategory());
		System.out.println(productVo.getPrice());
		System.out.println(productVo.getStock());
		
		try {
			productService.regProduct(productVo, file);
			
	        return "success";
	    } catch (Exception e) {
	        return "fail: " + e.getMessage();
	    }

	}
	
	// 상품 정보 수정
	@PostMapping("/uploadPrdMod_ok")
	@ResponseBody //Response가 붙으면 return의 값이 요청이 온곳으로 반환
	public String uploadPrdModifyOk(@ModelAttribute ProductVo productVo,
			 @RequestParam(value = "deleteFile", required = false) String deleteFile,
			 @RequestParam("file")MultipartFile file ) throws Exception
	{
		// productVo 객체에 name, category, price, stock 값이 자동으로 매핑됨
		System.out.println(productVo.getName());
		System.out.println(productVo.getCategory());
		System.out.println(productVo.getPrice());
		System.out.println(productVo.getStock());
		
		System.out.println("삭제여부: "+ deleteFile);
		
		try {
			productService.modifyProduct(productVo, deleteFile, file);
			
	        return "success";
	    } catch (Exception e) {
	        return "fail: " + e.getMessage();
	    }
	}
}
