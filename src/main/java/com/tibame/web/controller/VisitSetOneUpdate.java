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
import com.tibame.web.dao.impl.VisitDAO;
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.service.impl.VisitRoomServiceImpl;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitSetOneUpdate
 */
@WebServlet("/visit/setOneUpdate")
public class VisitSetOneUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");

		final String start = request.getParameter("action");

		if ("getOne_For_Update".equals(start)) {

			Integer visitid = Integer.valueOf(request.getParameter("visitid"));

			if (visitid != null) {
				HttpSession session = request.getSession();
				VisitRoomService service = new VisitRoomServiceImpl();
				VisitVO visitVO = service.getOneInfo(visitid);
				session.setAttribute("visitVO", visitVO);

				response.sendRedirect("/elitebaby/admin/visit/update_visit.html");

			}

		}

	}

}
