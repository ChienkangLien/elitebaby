package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.dao.impl.VisitDAO;
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.service.impl.VisitRoomServiceImpl;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitRoomUpdate
 */
@WebServlet("/visit/update")
public class VisitRoomUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.setContentType("text/html;charset=utf-8");

		
		Gson gson = new Gson();
		VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);	
		Date duedate = Date.valueOf(visitVO.getStrDueDate());
		Date visittime = Date.valueOf(visitVO.getStrVisitTime());
		visitVO.setDueDate(duedate);
		visitVO.setVisitTime(visittime);		
		VisitRoomService service = new VisitRoomServiceImpl();
		String resultStr = service.oneVisitUpdate(visitVO);
		
		JsonObject respbody = new JsonObject();
		respbody.addProperty("successful", resultStr.equals("更新成功"));
		respbody.addProperty("message", resultStr);
		response.getWriter().append(respbody.toString());

	}

}
