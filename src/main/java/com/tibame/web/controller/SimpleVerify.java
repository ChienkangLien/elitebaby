package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/SimpleVerify")
public class SimpleVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		HttpSession session = req.getSession();
		// 從session判斷使用者是否登入過
		Object verifySession = session.getAttribute("memberVO");
		System.out.println("過濾器：" + verifySession);
		if (verifySession == null) {
			System.out.println("請登入");
			resp.sendRedirect("/elitebaby/member/login.html");
		} else {
			System.out.println("過濾器：" + verifySession);
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("message", "已登入");

			resp.getWriter().append(jsonObject.toString());
		}
	}
}
