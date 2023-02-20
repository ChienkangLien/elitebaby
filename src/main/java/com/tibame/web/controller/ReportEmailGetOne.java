package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.ReportEmailService;
import com.tibame.web.service.impl.ReportEmailServiceImpl;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.ReportImageVO;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class ReportEmailSetOne
 */
@WebServlet("/report/emailGetOne")
public class ReportEmailGetOne extends HttpServlet {
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
		
		final String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if (action.equals("getEmail")) {

			EmailVO emailVO = (EmailVO) session.getAttribute("emailVO");
			Gson gson = new Gson();
			Writer writer = response.getWriter();
			writer.write(gson.toJson(emailVO));

		}

		if (action.equals("getPhoto")) {

			Object reportImageVO = session.getAttribute("reportImageVO");
			Gson gson = new Gson();
			Writer writer = response.getWriter();
			writer.write(gson.toJson(reportImageVO));
		}
		
		
		if(action.equals("get_one_answer")) {
			
			Gson gson = new Gson();
			EmailVO emailVO = gson.fromJson(request.getReader(), EmailVO.class);
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			String strDate = sdFormat.format(date);
			Timestamp timestampt =  java.sql.Timestamp.valueOf(strDate);
			emailVO.setAnsertCreateTime(timestampt);
			ReportEmailService service = new ReportEmailServiceImpl();
			String resultStr = service.getOneAnswer(emailVO);
			
			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("回覆成功"));
			respbody.addProperty("message", resultStr);
			response.getWriter().append(respbody.toString());
			
		}
	}

}
