package com.touzone.stsb.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.touzone.stsb.vo.ProductVo;

@Repository
public class ProductDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public ProductVo selectProductById(int product_id) throws Exception {
		
		ProductVo rst = sqlSession.selectOne("ProductDao.selectProductById", product_id);
		System.out.println(rst.getName());
		return rst;
	}

	public List<ProductVo> selectProductList() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList("ProductDao.selectProductList");
	}

	public int insertProductOne(ProductVo productVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("ProductDao.insertProductOne", productVo);
	}

	public int getMaxProductId() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("ProductDao.getMaxProductId");
	}

	public int updateProductOne(ProductVo productVo) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert("ProductDao.updateProductOne", productVo);
	}

}
