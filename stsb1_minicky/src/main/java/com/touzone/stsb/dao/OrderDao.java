package com.touzone.stsb.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.touzone.stsb.vo.OrderVo;

@Repository
public class OrderDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<OrderVo> selectOrderList(String memid) throws Exception {
		return sqlSession.selectList("OrderDao.selectOrderList", memid);
	}

	public int insertOrder(OrderVo orderVo) {
		return sqlSession.insert("OrderDao.insertOrder", orderVo);
	}

	public int selectNewOrderId() {
		return sqlSession.selectOne("OrderDao.selectNewOrderId");
	}

}
