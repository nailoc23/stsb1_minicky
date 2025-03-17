package com.touzone.stsb.vo;

public class ProductImageVo {
	
	
	private int image_id;
	private int  product_id;
	private String orgin_name;
	private String uuid_name;
	private String file_path;
	private String upload_date;
	
	
	public int getImage_id() {
		return image_id;
	}
	public void setImage_id(int image_id) {
		this.image_id = image_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getOrgin_name() {
		return orgin_name;
	}
	public void setOrgin_name(String orgin_name) {
		this.orgin_name = orgin_name;
	}
	public String getUuid_name() {
		return uuid_name;
	}
	public void setUuid_name(String uuid_name) {
		this.uuid_name = uuid_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	
	// 기본 생성자 (필수) : 없으면 @ModelAttribute 를 통해 생성자를 만들지 못함
	public ProductImageVo() { }
	
	public ProductImageVo(int image_id, int product_id, String orgin_name, String uuid_name, String file_path,
			String upload_date) {
		this.image_id = image_id;
		this.product_id = product_id;
		this.orgin_name = orgin_name;
		this.uuid_name = uuid_name;
		this.file_path = file_path;
		this.upload_date = upload_date;
	}
	
	
	

}
