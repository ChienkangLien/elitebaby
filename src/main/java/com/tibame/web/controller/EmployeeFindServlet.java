package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.service.impl.EmployeeServiceImpl;
import com.tibame.web.vo.EmployeeVO;
import com.tibame.web.vo.MemberVO;

@WebServlet("/admin/member/find")
public class EmployeeFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer empid;
		
		if(req.getParameter("id") == null) {
			EmployeeVO employeLogin = (EmployeeVO) req.getSession().getAttribute("employeeVO");
			empid = employeLogin.getEmpid();		
		}else {
			empid = Integer.parseInt(req.getParameter("id"));
		}
		try {
			EmployeeServiceImpl service = new EmployeeServiceImpl();
			EmployeeVO employee = service.findById(empid);

			Gson gson = new Gson();
			resp.setContentType("application/json;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			resp.getWriter().write(gson.toJson(employee));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}