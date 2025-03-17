package com.touzone.stsb.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ManagerDao {
	
	@Autowired
	private SqlSession sqlSession;

	public int selectManagerByIdPw(Map<String, String> idPw) throws Exception {
		return sqlSession.selectOne("ManagerDao.selectManagerByIdPw", idPw);
	}

}
