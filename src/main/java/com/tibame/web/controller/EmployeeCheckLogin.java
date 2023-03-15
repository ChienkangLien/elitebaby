package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.vo.EmployeeVO;

@WebServlet("/employee/check")
public class EmployeeCheckLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		EmployeeVO employeeLogin = (EmployeeVO) req.getSession().getAttribute("employeeVO");
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		resp.setContentType("application/json;charset=UTF-8");
		if (employeeLogin == null) {
//			resp.sendRedirect("emplogin.html");
			jsonObject.addProperty("message", "未登入");
		} else {
//			resp.getWriter().append(gson.toJson(memberLogin));
			jsonObject.addProperty("message", "已登入");
		}
		System.out.println(jsonObject.toString());
		resp.getWriter().append(jsonObject.toString());

	}

}
