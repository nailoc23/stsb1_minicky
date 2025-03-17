package com.touzone.stsb.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.touzone.stsb.dao.MemberDao;
import com.touzone.stsb.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean checkUserIdExists(String id) throws Exception {
		
		MemberVo membervo = memberDao.selectMemberById(id);
		if(membervo != null) {
			if(membervo.getMemid().equals(id))
				return true;
			else {
				return false;
			}
		}
		else {		
			return false;
		}
	}
	
	@Override
	public MemberVo getMemberById(String id) throws Exception {
		return memberDao.selectMemberById(id);
	}

	@Override
	public int regMember(MemberVo membervo) throws Exception {
		//System.out.println(membervo.getMemid());
		return memberDao.insertMember(membervo);
	}

	@Override
	public int modMember(MemberVo membervo) throws Exception {
		return 0;
	}

	@Override
	public int leaveMember(String id) throws Exception {
		return 0;
	}

	@Override
	public boolean authMember(String memid, String password) throws Exception {
		
		Map<String,String> IdPw = new HashMap<String, String>();
		IdPw.put("memid", memid);
		IdPw.put("password", password);
		
		int rst = memberDao.selectMemberByIdPw(IdPw);
		System.out.println("로그인인증결과="+rst);
		
		if(rst == 1) {
			return true;
		}else {
			return false;
		}
		
	}


}
