package com.touzone.stsb.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touzone.stsb.dao.ManagerDao;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerDao managerDao;
	
	@Override
	public boolean authManager(String id, String pw) throws Exception {
		
		Map<String,String> idPw = new HashMap<String, String>();
		idPw.put("id", id);
		idPw.put("pw", pw);
		
		int rst = managerDao.selectManagerByIdPw(idPw);
		System.out.println("로그인인증결과="+rst);
		
		return true;
	}

}
