package com.touzone.stsb.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.touzone.stsb.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int selectBoardCnt() throws Exception {
		return sqlSession.selectOne("BoardDao.selectBoardCnt");
	}
	
	public List<BoardVo> selectBoardList(int page) throws Exception {
		
		int offset = (page - 1) * 10;
	    Map<String, Object> params = new HashMap<>();
	    params.put("offset", offset);
	    
		return sqlSession.selectList("BoardDao.selectBoardList", params);			
	}

	public BoardVo selectBoardByNum(int num) throws Exception {
		return sqlSession.selectOne("BoardDao.selectBoardByNum", num);
	}

	public int updateBoard(BoardVo board) throws Exception {
		return sqlSession.update("BoardDao.updateBoard", board);
	}

	public int deleteBoardByNum(int num) throws Exception {
		return sqlSession.delete("BoardDao.deleteBoardByNum", num);
	}

	public int insertBoard(BoardVo board) throws Exception {
		return sqlSession.insert("BoardDao.insertBoard", board);
	}

}
