package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MemberVO;

public interface MemberDao {
	
int insert(MemberVO member);
	
//	update member set xxx where username = ?
	int updateById(MemberVO member);
//	search
	MemberVO find(MemberVO member);
	
	MemberVO selectById(int id);
//	select * from MEMBER where username = ? and password = ?

}
