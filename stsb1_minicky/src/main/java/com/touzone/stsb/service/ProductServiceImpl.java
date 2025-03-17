package com.touzone.stsb.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.touzone.stsb.dao.ProductDao;
import com.touzone.stsb.dao.ProductImageDao;
import com.touzone.stsb.vo.ProductImageVo;
import com.touzone.stsb.vo.ProductVo;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductImageDao productImageDao;
	
	private String uploadPath = "D:/upload";
	
	@Override
	public ProductVo getProduct(int product_id) throws Exception {
		// TODO Auto-generated method stub
		// ProductVo product = new ProductVo(product_id, "test", "test text", "전자제품", 10000.00, 10, "2025-02-20");
		ProductVo product = productDao.selectProductById(product_id);
		
		//System.out.println("test="+ product.getName());
		
		return product;
	}

	@Override
	public List<ProductVo> getProductList() throws Exception {
		// TODO Auto-generated method stub
		List<ProductVo> prdlist = productDao.selectProductList();
		
		//System.out.println("list count="+prdlist.size());
		
		return prdlist;
	}

	@Override
	@Transactional  // 트랜잭션 적용
	public int regProduct(ProductVo productVo, MultipartFile file) throws Exception {
		
		// TODO Auto-generated method stub
		// 상품을 등록하기 전에 product_id 을 최대값을 가져옴
		int product_id = productDao.getMaxProductId();
		productVo.setProduct_id(product_id);
		
		// 상품 등록
		int regRst = productDao.insertProductOne(productVo);
		
		// 파일업로드 처리
		//파일명
		String origin = file.getOriginalFilename(); 
		//브라우저별로 경로가 포함되서 올라오는 경우가 있기에 간단한 처리.
		String filename=origin.substring(origin.lastIndexOf("\\")+1); 
		//폴더생성
		String madedir=makeDir();
		//중복파일의 처리
		String uuid=UUID.randomUUID().toString();
		//중복파일처리 + 원본파일명 (디비저장)
		String uuid_filename = uuid+"_"+filename;
		//임시저장경로
		String temppath=madedir+"\\"+uuid_filename;
		 // 드라이브 문자 제거 (첫 번째 `:` 이후 부분만 사용)
        String filepath = temppath.substring(temppath.indexOf(":") + 1);
        // 역슬래시(`\`)를 슬래시(`/`)로 변경
        filepath = filepath.replace("\\", "/");
		
		System.out.println(origin);
		System.out.println(filename);
		System.out.println(uuid_filename);
		System.out.println(filepath);
		
		try {
			File save = new File(temppath); //세이브경로
			file.transferTo(save);
		} catch (Exception e) {
			e.printStackTrace();
			return regRst;
		}
		
		// 상품이미지 등록
		ProductImageVo productImageVo = new ProductImageVo();
		productImageVo.setProduct_id(product_id);
		productImageVo.setOrgin_name(origin);
		productImageVo.setUuid_name(uuid_filename);
		productImageVo.setFile_path(filepath);
		
		int regImgRst = productImageDao.insertProductImage(productImageVo);
				
		return regRst;
	}
	
	// 날짜별로 폴더생성
	@Override
    public String makeDir() {
    	Date date=new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
    	String now=sdf.format(date);

    	String path=uploadPath + "\\" +now; //경로
    	File file = new File(path);

    	if(file.exists()==false) {//파일이 존재하면 true
    		file.mkdir(); //폴더생성
    	}

    	return path;
    }

	@Override
	@Transactional  // 트랜잭션 적용
	public int modifyProduct(ProductVo productVo, String deleteFile, MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("상품_ID:"+productVo.getProduct_id());
		// 상품 수정
		int regRst = productDao.updateProductOne(productVo);
		
		// 파일업로드 처리
		//파일명
		String origin = file.getOriginalFilename(); 
		//브라우저별로 경로가 포함되서 올라오는 경우가 있기에 간단한 처리.
		String filename=origin.substring(origin.lastIndexOf("\\")+1); 
		//폴더생성
		String madedir=makeDir();
		//중복파일의 처리
		String uuid=UUID.randomUUID().toString();
		//중복파일처리 + 원본파일명 (디비저장)
		String uuid_filename = uuid+"_"+filename;
		//임시저장경로
		String temppath=madedir+"\\"+uuid_filename;
		 // 드라이브 문자 제거 (첫 번째 `:` 이후 부분만 사용)
        String filepath = temppath.substring(temppath.indexOf(":") + 1);
        // 역슬래시(`\`)를 슬래시(`/`)로 변경
        filepath = filepath.replace("\\", "/");
		
		System.out.println(origin);
		System.out.println(filename);
		System.out.println(uuid_filename);
		System.out.println(filepath);
		
		// 기존 파일 삭제 로직
		// && post.getFilePath() != null
	    if ("yes".equals(deleteFile) ) {
	    	
	    	//System.out.println("첨부파일삭제기능");
	    	System.out.println("첨부파일이름: "+productVo.getFile_path());
	        File oldFile = new File("D:/" + productVo.getFile_path() );
	        if (oldFile.exists()) {
	        	System.out.println("첨부파일이 존재함");
	            oldFile.delete();
	        }
	        
	        // 삭체 체크된 파일의 정보를 디비에서 삭제
	        int product_id = productVo.getProduct_id();
	        System.out.println("삭제할 이미지파일의 상품Id: " + product_id);
	        productImageDao.deleteProductImg(product_id);
	    }
		
		return regRst;
		
	}


}
