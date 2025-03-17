package com.touzone.stsb.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.touzone.stsb.service.MemberService;
import com.touzone.stsb.service.OrderService;
import com.touzone.stsb.vo.OrderDetailVo;
import com.touzone.stsb.vo.OrderVo;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private OrderService orderService;
	
	// 아이디-중복체크
	@PostMapping("/membercheck")
    public ResponseEntity<Map<String, Boolean>> checkDuplicate(@RequestBody Map<String, String> request) throws Exception {
        String userId = request.get("userid");
        System.out.println("userId="+userId);
        boolean exists = memberService.checkUserIdExists(userId);
        // boolean exists = false; // true 면 아이디가 존재함
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);

        return ResponseEntity.ok(response);
    }
	
	// 세션체크
	@GetMapping("/chksession")
    public ResponseEntity<Map<String, Boolean>> checkSession(HttpSession session) {
        Map<String, Boolean> response = new HashMap<>();
        
        // 로그인 세션 확인 (예: "user" 속성 존재 여부)
        response.put("loggedIn", session.getAttribute("loginUser") != null);
        
        return ResponseEntity.ok(response);
    }
	
	// 주문
	@PostMapping("/order")
	public ResponseEntity<Map<String, Object>> placeOrder(@RequestBody Map<String, Object> request, HttpSession session) {
		try {
            List<Map<String, Object>> items = (List<Map<String, Object>>) request.get("items");

            int totalprice = 0;
            
            //주문번호
            int order_id = orderService.getNewOrderId();
            
            //상품상세정보
            List<OrderDetailVo> orderDetailList = new ArrayList<OrderDetailVo>();
            
            for (Map<String, Object> item : items) {
                System.out.println(item);
                int product_id = (int) item.get("product_id"); // 가격 가져오기
                int price = (int) item.get("price"); // 가격 가져오기
                int stock = (int) item.get("stock"); // 수량 가져오기
                int subtotal = price * stock;
                
                totalprice += subtotal; // 가격 * 수량을 더함
                
                OrderDetailVo orderDetailVo = new OrderDetailVo(order_id, product_id, stock, price, subtotal);
                orderDetailList.add(orderDetailVo);
            }
            //System.out.println("총 주문 금액: " + totalprice);
            
           
            
            String memid = (String) session.getAttribute("loginUser");
    		//System.out.println("로그인한 Id: "+memid);
    		
            String order_status = "PENDING";
            
    		// 주문저장함수 호출
            OrderVo orderVo = new OrderVo(order_id, memid, totalprice, order_status);
            
    		int rstOrder = orderService.saveOrder(orderVo, orderDetailList);
    		
            Map<String, Object> response = new HashMap<>();
            if(rstOrder!=0) {
            	response.put("success", true);
            }else {
            	response.put("success", false);
            }
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
	}
}
