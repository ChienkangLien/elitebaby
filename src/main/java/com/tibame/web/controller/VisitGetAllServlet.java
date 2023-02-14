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
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.service.impl.VisitRoomServiceImpl;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitCJson
 */
@WebServlet("/visitGetAll")
public class VisitGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1123L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");

		final String start = request.getParameter("action");

		if ("GETALL_VISIT".equals(start)) {

			VisitRoomService service = new VisitRoomServiceImpl();
			List<VisitVO> list = service.getAllInfo();
			Gson gson = new Gson();
			Writer writer = response.getWriter();
			writer.write(gson.toJson(list));

		}

	}
}
