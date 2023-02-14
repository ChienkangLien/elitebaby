package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
 * Servlet implementation class VisitGetOneUpdate
 */
@WebServlet("/visit/getOneUpdate")
public class VisitGetOneUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");

//============================取得儲存在session的物件==============================================	

		HttpSession session = request.getSession();
		VisitVO visitVO = (VisitVO) session.getAttribute("visitVO");

//==================================日期轉換格式================================================

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String strDueDate = dateFormat.format(visitVO.getDueDate());
		String strVisitTime = dateFormat.format(visitVO.getVisitTime());
		visitVO.setStrDueDate(strDueDate);
		visitVO.setStrVisitTime(strVisitTime);

//==========================================================================================		

		Gson gson = new Gson();
		Writer writer = response.getWriter();
		writer.write(gson.toJson(visitVO));

	}

}
