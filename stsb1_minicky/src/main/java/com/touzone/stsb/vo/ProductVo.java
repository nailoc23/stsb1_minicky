package com.touzone.stsb.vo;

import org.springframework.web.bind.annotation.ModelAttribute;

public class ProductVo {

	private int product_id;
	private String name;
	private String description;
	private String category;
	private double price;
	private int stock;
	private String regdate;
	
	private int image_id;
	private String origin_name;
	private String file_path;

	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}



	public int getImage_id() {
		return image_id;
	}



	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}

	public String getOrigin_name() {
		return origin_name;
	}

	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	// 기본 생성자 (필수) : 없으면 @ModelAttribute 를 통해 생성자를 만들지 못함
    public ProductVo() {}
    
    // 생성자: 모든 필드에 초기값을 설정
	public ProductVo(int product_id, String name, String description, String category, double price, int stock,
			String regdate, int image_id, String origin_name, String file_path) {
		super();
		this.product_id = product_id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.price = price;
		this.stock = stock;
		this.regdate = regdate;
		this.image_id = image_id;
		this.origin_name = origin_name;
		this.file_path = file_path;
	}


    
	
	
	
    
}
