package com.touzone.stsb.service;

import com.touzone.stsb.vo.Paging;

public interface PagingService {
	
	public Paging calculatePaging(int total, int currpage); 

}
