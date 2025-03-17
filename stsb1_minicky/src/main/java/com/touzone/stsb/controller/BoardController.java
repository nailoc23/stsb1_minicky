package com.touzone.stsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.touzone.stsb.service.BoardService;
import com.touzone.stsb.service.PagingService;
import com.touzone.stsb.vo.BoardVo;
import com.touzone.stsb.vo.Paging;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private PagingService pagingService;
	
	@GetMapping("/boardmod")
	public String boardmod(@RequestParam("num") int num, Model model) throws Exception {
		
		BoardVo board = boardService.getBoard(num);
		model.addAttribute("board", board);
		return "boardmod";
	}
	
	@PostMapping("/boardmod")
	public String boardmodpro(BoardVo board) throws Exception {
				
		//System.out.println("입력정보="+board.getNum());
		int nMod = boardService.modifyBoard(board);
		
		return "redirect:/boardlist";
	}
	
	@GetMapping("/boardremove")
	public String boardremovepro(@RequestParam("num") int num) throws Exception {
				
		//System.out.println("삭제번호="+num);
		int nMod = boardService.delBoardByNum(num);
		
		return "redirect:/boardlist";
	}
	
	@GetMapping("/boardview")
	public String boardview(@RequestParam("num") int num, Model model) throws Exception {
		
		BoardVo board = boardService.getBoard(num);
		model.addAttribute("board", board);
		return "boardview";
	}
	
	@GetMapping("/boardreg")
	public String boardreg() {
		
		return "boardreg";
	}
	
	@PostMapping("/boardreg")
	public String boardregpro(BoardVo board) throws Exception {
		//System.out.println("입력정보="+board.getName());
		int nReg = boardService.regBoard(board);
		return "redirect:/boardlist";
	}
	
	@GetMapping("/boardlist")
	public String boardlist(Model model, @RequestParam(name="page", defaultValue = "1") int page) throws Exception {
		
		// 링크에 붙은 현재 page 값을 가져오기
		//page = 1;
		System.out.println("페이지번호="+ page);
		
		int total = boardService.getBoardTotalCnt();
		System.out.println("전체게시물수="+ total);
		
		Paging paging = pagingService.calculatePaging(total, page);
		System.out.println("페이지수="+ paging.getPagecount() );
		System.out.println("시작페이지="+ paging.getStartpage() );
		System.out.println("끝페이지="+ paging.getEndpage() );
		
		List<BoardVo> boardlist = boardService.getBoardList(page);
		model.addAttribute("total", total);
		model.addAttribute("boards", boardlist);
		model.addAttribute("paging", paging);
		return "boardlist";
	}

}
