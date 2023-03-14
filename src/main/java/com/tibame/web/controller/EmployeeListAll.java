package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tibame.web.service.EmployeeService;
import com.tibame.web.service.impl.EmployeeServiceImpl;
import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.EmployeeVO;
import com.tibame.web.vo.MemberVO;

@WebServlet("/admin/member/listall")
public class EmployeeListAll extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		EmployeeService service = new EmployeeServiceImpl(); 
		List<EmployeeVO> employee = service.findAll();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Writer writer = resp.getWriter();
		writer.write(gson.toJson(employee));
	}
}
