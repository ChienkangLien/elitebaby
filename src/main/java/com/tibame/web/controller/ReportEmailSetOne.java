package com.tibame.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.tibame.web.service.ReportEmailService;
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.service.impl.ReportEmailServiceImpl;
import com.tibame.web.service.impl.VisitRoomServiceImpl;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.ReportImageVO;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class ReportEmailGetOne
 */
@WebServlet("/report/emailSetOne")
public class ReportEmailSetOne extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");

		final String authCode = request.getParameter("authCode");
		final Integer mailId = Integer.valueOf(request.getParameter("mailId"));
		final String action = request.getParameter("action");
		if (mailId != null && authCode != null) {

			if (action.equals("get_back_oneall")) {

				HttpSession session = request.getSession();
				ReportEmailService emailService = new ReportEmailServiceImpl();

				EmailVO emailVO = emailService.getOneEmail(mailId);
				session.setAttribute("emailVO", emailVO);

				ReportImageVO reportImageVO = emailService.getOneAllPhoto(authCode);
				if (reportImageVO != null) {
					session.setAttribute("reportImageVO", reportImageVO);
				}
				response.sendRedirect("/elitebaby/admin/visit/getone_email.html");

			}

			if (action.equals("get_front_oneall")) {


				HttpSession session = request.getSession();
				ReportEmailService emailService = new ReportEmailServiceImpl();

				EmailVO emailVO = emailService.getOneEmail(mailId);
				session.setAttribute("emailVO", emailVO);

				ReportImageVO reportImageVO = emailService.getOneAllPhoto(authCode);
				if (reportImageVO != null) {
					session.setAttribute("reportImageVO", reportImageVO);
				}
				response.sendRedirect("/elitebaby/visit/ReportEmailFrontGetOne.html");
				
			}
		}

	}

}
