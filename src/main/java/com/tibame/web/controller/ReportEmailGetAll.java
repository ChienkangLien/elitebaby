package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.google.gson.Gson;
import com.tibame.web.service.ReportEmailService;
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.service.impl.ReportEmailServiceImpl;
import com.tibame.web.service.impl.VisitRoomServiceImpl;
import com.tibame.web.util.HibernateUtil;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class ReportEmailGetAll
 */
@WebServlet("/email/getAll")
public class ReportEmailGetAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");

		final String start = request.getParameter("action");

		if ("GETALL_EMAIL".equals(start)) {

			ReportEmailService service = new ReportEmailServiceImpl();
			List<EmailVO> list = service.getAllInfo();
			Gson gson = new Gson();
			Writer writer = response.getWriter();
			writer.write(gson.toJson(list));

		}
		
	}

}
