package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class VisitCJson
 */
@WebServlet("/visitGetAll")
public class VisitGetAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1123L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");

		final String start = request.getParameter("action");

		if ("GETALL_VISIT".equals(start)) {

			VisitRoomService service = new VisitRoomServiceImpl();
			List<VisitVO> list = service.getAllInfo();
			Gson gson = new Gson();
			Writer writer = response.getWriter();
			writer.write(gson.toJson(list));

		}

		if ("check_visit".equals(start)) {
			Gson gson = new Gson();
			VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
			VisitRoomService service = new VisitRoomServiceImpl();
			String resultStr = service.checkVisitDate(java.sql.Date.valueOf(visitVO.getStrVisitTime()));
			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("當日預約已滿"));
			respbody.addProperty("message", resultStr);
			response.getWriter().append(respbody.toString());

		}

		if ("GETALL_VISIT_PAGE".equals(start)) {

			Integer offset = Integer.valueOf(request.getParameter("offset"));
			VisitRoomService service = new VisitRoomServiceImpl();
			List<VisitVO> list = service.getAllInfoPage(offset);
			try {

				System.out.print(list);
				if (list.isEmpty()) {

					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					
				} else {
					response.setStatus(HttpServletResponse.SC_OK);
					Gson gson = new Gson();
					Writer writer = response.getWriter();
					writer.write(gson.toJson(list));
				}

			} catch (Exception e) {

				response.setStatus(HttpServletResponse.SC_NO_CONTENT);

			}

		}
		
		
		if ("GETALL_VISIT_PAGE_HISTORY".equals(start)) {

			Integer offset = Integer.valueOf(request.getParameter("offset"));
			VisitRoomService service = new VisitRoomServiceImpl();
			List<VisitVO> list = service.getAllInfoPageHistory(offset);
			try {

				System.out.print(list);
				if (list.isEmpty()) {

					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
					
				} else {
					response.setStatus(HttpServletResponse.SC_OK);
					Gson gson = new Gson();
					Writer writer = response.getWriter();
					writer.write(gson.toJson(list));
				}

			} catch (Exception e) {

				response.setStatus(HttpServletResponse.SC_NO_CONTENT);

			}

		}


	}
}
