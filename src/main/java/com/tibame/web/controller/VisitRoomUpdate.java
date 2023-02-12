package com.tibame.web.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.dao.impl.VisitDAO;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitRoomUpdate
 */
@WebServlet("/VisitRoomUpdate")
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
		
		Gson gson = new Gson();
		VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
		Date duedate = Date.valueOf(visitVO.getStrDueDate());
		Date visittime = Date.valueOf(visitVO.getStrVisitTime());
		visitVO.setDueDate(duedate);
		visitVO.setVisitTime(visittime);
		VisitDAO visitDAO = new VisitDAO();
		visitDAO.update(visitVO);
		response.sendRedirect("admin/visit/GetAllVisit.html");
	}

}
