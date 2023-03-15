package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.EmployeeVO;
import com.tibame.web.vo.MemberVO;

public interface EmployeeDao {
	
	int insert(EmployeeVO employee);
	
//	update ADMINISTRATOR set xxx where empname = ?
	int updateById(EmployeeVO employee);
//	search
	EmployeeVO find(EmployeeVO employee);
	
	EmployeeVO selectById(int id);
	
	List<EmployeeVO> selectAll();
	
}
