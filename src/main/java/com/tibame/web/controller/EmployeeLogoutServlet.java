package com.tibame.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/member/emplogout")
public class EmployeeLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.getSession().invalidate();
			resp.sendRedirect(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
					+ req.getContextPath()  + "/admin/member/emplogin.html");
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
