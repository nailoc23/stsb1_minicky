package com.touzone.stsb.service;

import org.springframework.stereotype.Service;

import com.touzone.stsb.vo.Paging;

@Service
public class PagingServiceImpl implements PagingService {

	@Override
	public Paging calculatePaging(int total, int currpage) {
		// TODO Auto-generated method stub
		// 기본값 페이지당게시물갯수 10, 페이지블록 5
		int itemPerPage = 10;
		int pagePerBlock = 5;
		
		int page_count = (int) Math.ceil((double)total / itemPerPage);
		// total / itemPerPage 를 정수가 아닌 소수점으로 변경하기 위해 캐스팅
		
		Paging paging = new Paging();
		paging.setPagecount( page_count );
		int start_page = ((currpage - 1) / pagePerBlock) * pagePerBlock + 1;
		paging.setStartpage( start_page );
		int end_page  = Math.min( start_page + pagePerBlock - 1, page_count);
		paging.setEndpage( end_page );
		paging.setCurpage(currpage);
		return paging;
	}

}
