package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.dao.impl.VisitDAO;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitCJson
 */
@WebServlet("/VisitCJson")
public class VisitCJson extends HttpServlet {
	private static final long serialVersionUID = 1123L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String start = request.getParameter("action"); // 收到的請求是什麼 ex:新增,修改,刪除

		

		if("JSON".equals(start)) {
			VisitDAO dao = new VisitDAO();
			List<VisitVO> list = dao.getAll();
			response.setContentType("text/html;charset=utf-8");
			response.setContentType("application/json");
			Gson gson = new Gson();				
			Writer writer = response.getWriter();
			writer.write(gson.toJson(list));

		}
		
		
		
	}
}
