package com.touzone.stsb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.touzone.stsb.service.OrderService;
import com.touzone.stsb.service.ProductService;
import com.touzone.stsb.vo.OrderVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/orderlist")
	public String orderlist(Model model, HttpSession session) throws Exception {
		
		String memid = (String) session.getAttribute("loginUser");
		System.out.println("로그인한 Id="+memid);
		
		List<OrderVo> orderlist = orderService.getOrderList(memid);
		
		//System.out.println("주문상세갯수="+orderlist.size());
		
		model.addAttribute("orders", orderlist);
		
		return "orderlist";
	}
}
