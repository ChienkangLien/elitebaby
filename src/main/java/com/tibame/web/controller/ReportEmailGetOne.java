package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
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

	}

}
