package com.touzone.stsb.service;

import com.touzone.stsb.vo.MemberVo;

public interface MemberService {
	
	public boolean checkUserIdExists(String id) throws Exception;
	public MemberVo getMemberById(String id) throws Exception;
	public int regMember(MemberVo membervo) throws Exception;
	public int modMember(MemberVo membervo) throws Exception;
	public int leaveMember(String id) throws Exception;
	public boolean authMember(String memid, String password) throws Exception;

}
