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
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitSetOneUpdate
 */
@WebServlet("/VisitSetOneUpdate")
public class VisitSetOneUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String start = request.getParameter("action"); // 收到的請求是什麼 ex:新增,修改,刪除
		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");

		if ("getOne_For_Update".equals(start)) {

			Integer visitid = Integer.valueOf(request.getParameter("visitid"));

			HttpSession session = request.getSession();
			VisitDAO dao = new VisitDAO();
			VisitVO visitVO = dao.findByPrimaryKey(visitid);
			session.setAttribute("visitVO", visitVO);

			response.sendRedirect("admin/visit/update_visit.html");
		}

	}

}
