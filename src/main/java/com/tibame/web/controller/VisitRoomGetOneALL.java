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
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.service.impl.VisitRoomServiceImpl;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitRoomGetOneALL
 */
@WebServlet("/visit/getOneALL")
public class VisitRoomGetOneALL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
		Integer userId = Integer.valueOf(visitVO.getUserId());
		VisitRoomService service = new VisitRoomServiceImpl();
		List<VisitVO> list = service.getOneAll(userId);
		Writer writer = response.getWriter();
		writer.write(gson.toJson(list));

	}

}
