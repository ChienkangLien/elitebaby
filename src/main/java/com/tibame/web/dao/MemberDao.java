package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MemberVO;

public interface MemberDao {
	
int insert(MemberVO member);
	
	int updateById(MemberVO member);
//	search
	MemberVO find(MemberVO member);
	
	MemberVO selectById(int id);
	List<MemberVO> selectAll();
	
	MemberVO selectByEmail(String email);

}
