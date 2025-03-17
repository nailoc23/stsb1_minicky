package com.touzone.stsb.service;

import java.util.List;

import com.touzone.stsb.vo.BoardVo;

public interface BoardService {
	
	public int getBoardTotalCnt() throws Exception;
	public List<BoardVo> getBoardList(int page) throws Exception;
	public BoardVo getBoard(int num) throws Exception;
	public int modifyBoard(BoardVo board) throws Exception;
	public int delBoardByNum(int num) throws Exception;
	public int regBoard(BoardVo board) throws Exception;

}
