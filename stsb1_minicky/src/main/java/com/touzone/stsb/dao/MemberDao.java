package com.touzone.stsb.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.touzone.stsb.vo.MemberVo;

@Repository
public class MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public MemberVo selectMemberById(String id) throws Exception {
		return sqlSession.selectOne("MemberDao.selectMemberById", id);
	}

	public int insertMember(MemberVo membervo) throws Exception {
		return sqlSession.insert("MemberDao.insertMember", membervo);
	}

	public int selectMemberByIdPw(Map<String, String> idPw) throws Exception  {
		return sqlSession.selectOne("MemberDao.selectMemberByIdPw", idPw);
				
	}



}
