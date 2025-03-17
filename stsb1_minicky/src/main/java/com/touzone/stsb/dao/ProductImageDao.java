package com.touzone.stsb.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.touzone.stsb.vo.ProductImageVo;

@Repository
public class ProductImageDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insertProductImage(ProductImageVo productImageVo) throws Exception {
		return sqlSession.insert("ProductImageDao.insertProductImage", productImageVo);
	}

	public int deleteProductImg(int product_id) throws Exception {
		return sqlSession.delete("ProductImageDao.deleteProductImg", product_id);
		
	}

}
