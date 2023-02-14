package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.service.impl.VisitRoomServiceImpl;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitRoomDelete
 */
@WebServlet("/visit/delete")
public class VisitRoomDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");
		
		Integer visitid = Integer.valueOf(request.getParameter("visitid"));
		System.out.print(visitid);
		VisitRoomService service = new VisitRoomServiceImpl();
		String resultStr = service.deleteOneVisit(visitid);

		JsonObject respbody = new JsonObject();
		respbody.addProperty("successful", resultStr.equals("刪除成功"));
		respbody.addProperty("message", resultStr);
		response.getWriter().append(respbody.toString());

	}

}
