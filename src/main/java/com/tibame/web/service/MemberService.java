package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.MemberVO;

public interface MemberService {
	
	String register(MemberVO member);
	MemberVO login(MemberVO member);
	MemberVO findById(MemberVO member);
	MemberVO update(MemberVO member);
	MemberVO findById(Integer id);
	public List<MemberVO> findAll();
	MemberVO findByEmail(MemberVO member);

}
	