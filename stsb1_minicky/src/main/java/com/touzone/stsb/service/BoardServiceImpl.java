package com.touzone.stsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touzone.stsb.dao.BoardDao;
import com.touzone.stsb.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;
	
	@Override
	public int getBoardTotalCnt() throws Exception {
		return boardDao.selectBoardCnt();
	}

	@Override
	public List<BoardVo> getBoardList(int page) throws Exception {
		List<BoardVo> boards = boardDao.selectBoardList(page);
		return boards;
	}

	@Override
	public BoardVo getBoard(int num) throws Exception {
		BoardVo board = boardDao.selectBoardByNum(num);
		return board;
	}

	@Override
	public int modifyBoard(BoardVo board) throws Exception {
		int nMod = boardDao.updateBoard(board);
		return nMod;
	}

	@Override
	public int delBoardByNum(int num) throws Exception {
		int nMod = boardDao.deleteBoardByNum(num);
		return nMod;
	}

	@Override
	public int regBoard(BoardVo board) throws Exception {
		int nReg = boardDao.insertBoard(board);
		return nReg;
	}

}
