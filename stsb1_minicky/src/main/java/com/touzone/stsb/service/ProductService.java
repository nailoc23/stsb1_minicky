package com.touzone.stsb.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.touzone.stsb.vo.ProductVo;

public interface ProductService {
	
	public ProductVo getProduct(int product_id) throws Exception;
	public List<ProductVo> getProductList() throws Exception;
	public int regProduct(ProductVo productVo, MultipartFile file)throws Exception;
	public String makeDir();
	public int modifyProduct(ProductVo productVo, String deleteFile, MultipartFile file) throws Exception;

}
