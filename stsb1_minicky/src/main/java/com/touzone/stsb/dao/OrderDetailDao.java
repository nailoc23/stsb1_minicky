package com.touzone.stsb.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.touzone.stsb.vo.OrderDetailVo;

@Repository
public class OrderDetailDao {
	
	@Autowired
	private SqlSession sqlSession;

	public int insertOrderDatail(OrderDetailVo orderDetail) {
		return sqlSession.insert("OrderDetailDao.insertOrderDatail", orderDetail);
	}
	
}
