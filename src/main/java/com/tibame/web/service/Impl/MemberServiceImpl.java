package com.tibame.web.service.impl;

import java.util.List;

import com.tibame.web.dao.MemberDao;
import com.tibame.web.dao.impl.MemberDaoImpl;
import com.tibame.web.service.MemberService;
import com.tibame.web.vo.MemberVO;

public class MemberServiceImpl implements MemberService{
	MemberDao dao = new MemberDaoImpl();

	@Override
	public String register(MemberVO member) {
		final String username = member.getUserName();
		if (username == null || username.length() < 1 || username.length() > 20) {
			return "使用者名稱不符合規則";
		}
		final String password = member.getPassword();
		if (password == null || password.length() < 8 || password.length() > 20) {
			return "密碼不符合規則";
		}
		final String email = member.getEmail();
		if (email == null ) {
			return "信箱不符合規則";
		}
		
		final String phonenumber = member.getPhoneNumber();
		if (phonenumber == null || phonenumber.length() < 10 || phonenumber.length() >12) {
			return "手機不符合規則";
		}
		
		final int resultCount = dao.insert(member);
		return resultCount > 0 ? "註冊成功" : "註冊失敗";

	}
	
	

	@Override
	public MemberVO login(MemberVO member) {
		final String email = member.getEmail();
		final String password = member.getPassword();
		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			return null;
		}else {
			return dao.find(member);
			
		}

	}
	
	

	@Override
	public MemberVO update(MemberVO member) {
		member.getId();
		member.getPassword();
		member.getAddress();
		member.getPhoneNumber();
		dao.updateById(member);
		return member;
	}


	@Override
	public MemberVO findById(Integer id) {
		return id != null ? dao.selectById(id) : null;
		
	}



	@Override
	public MemberVO findById(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<MemberVO> findAll() {
		return dao.selectAll();
	}
	
	
}
