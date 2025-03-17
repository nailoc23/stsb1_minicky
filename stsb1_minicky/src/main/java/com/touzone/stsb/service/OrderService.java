package com.touzone.stsb.service;

import java.util.List;

import com.touzone.stsb.vo.OrderDetailVo;
import com.touzone.stsb.vo.OrderVo;

public interface OrderService {
	
	public List<OrderVo> getOrderList(String memid) throws Exception;

	public int saveOrder(OrderVo orderVo, List<OrderDetailVo> orderDetailList) throws Exception;

	public int getNewOrderId() throws Exception;

}
