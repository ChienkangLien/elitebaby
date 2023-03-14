package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tibame.web.service.EmployeeService;
import com.tibame.web.service.impl.EmployeeServiceImpl;
import com.tibame.web.vo.EmployeeVO;

@WebServlet("/admin/member/update")
public class EmployeeUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		EmployeeVO employee = gson.fromJson(req.getReader(), EmployeeVO.class);
		resp.setContentType("application/json;charset=UTF-8");
		JsonObject jsonObj = new JsonObject();
		Integer empid;
		if(req.getParameter("id") == null) {
			EmployeeVO employeeLogin = (EmployeeVO) req.getSession().getAttribute("employeeVO");
			empid = employeeLogin.getEmpid();
		}else {
			empid = Integer.parseInt(req.getParameter("id"));
		}
		
		
		if (empid == 0) {
			jsonObj.addProperty("message", "修改失敗");
		} else {
			EmployeeService service = new EmployeeServiceImpl();
			employee.setEmpid(empid);
			service.update(employee);
			jsonObj.addProperty("message", "修改成功");
		}
		resp.getWriter().append(jsonObj.toString());
	}

}
