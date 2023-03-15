package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.EmployeeVO;
import com.tibame.web.vo.MemberVO;

public interface EmployeeService {
	
	String register(EmployeeVO Employee);
	EmployeeVO login(EmployeeVO Employee);
	EmployeeVO findById(EmployeeVO Employee);
	EmployeeVO update(EmployeeVO Employee);
	EmployeeVO findById(Integer id);
	List<EmployeeVO> findAll();
}
