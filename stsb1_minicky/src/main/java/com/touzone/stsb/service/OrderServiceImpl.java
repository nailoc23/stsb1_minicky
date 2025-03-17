package com.touzone.stsb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.touzone.stsb.dao.OrderDao;
import com.touzone.stsb.dao.OrderDetailDao;
import com.touzone.stsb.dao.ProductDao;
import com.touzone.stsb.vo.OrderDetailVo;
import com.touzone.stsb.vo.OrderVo;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;

	@Override
	public List<OrderVo> getOrderList(String memid) throws Exception {
		return orderDao.selectOrderList(memid);
	}

	@Override
	public int getNewOrderId() throws Exception {
		return orderDao.selectNewOrderId();
	}

	@Override
	@Transactional // 트랜잭션 적용
	public int saveOrder(OrderVo orderVo, List<OrderDetailVo> orderDetailList) throws Exception {

		int rstOrder = orderDao.insertOrder(orderVo);
		
		int rstOrderDetail = 0;
		for (OrderDetailVo orderDetail : orderDetailList) {
			rstOrderDetail += orderDetailDao.insertOrderDatail(orderDetail);
		}

		if (rstOrder > 0 && rstOrderDetail > 0) {
	        return 1; // 성공
	    } else {
	        throw new Exception("주문 저장 중 오류 발생");
	    }
	}


	
}
