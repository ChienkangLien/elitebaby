package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.EmployeeService;
import com.tibame.web.service.impl.EmployeeServiceImpl;
import com.tibame.web.vo.EmployeeVO;

@WebServlet("/member/emplogin")
public class EmployeeLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		EmployeeVO employee = gson.fromJson(req.getReader(), EmployeeVO.class);

		EmployeeService service = new EmployeeServiceImpl();
		employee = service.login(employee);
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;charset=UTF-8");

		JsonObject jsonObj = new JsonObject();
		if (employee == null) {
			jsonObj.addProperty("message", "登入失敗");

		} else {
			if (req.getSession(false) != null) {
				req.changeSessionId();
			} // ←產生新的Session ID}
			jsonObj.addProperty("message", "登入成功");
			req.getSession().setAttribute("employeeVO", employee);
			System.out.println(req.getSession().getAttribute("employeeVO"));
		}
		resp.getWriter().write(jsonObj.toString());

	}
}
