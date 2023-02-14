package com.tibame.web.controller;

import java.io.IOException;
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
 * Servlet implementation class VisitInsertController
 */
@WebServlet("/visitInsert")
public class VisitInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("application/json");
		
		try {
			Gson gson = new Gson();
			VisitVO visitVO = gson.fromJson(req.getReader(), VisitVO.class);
			Date duedate = Date.valueOf(visitVO.getStrDueDate());
			Date visittime = Date.valueOf(visitVO.getStrVisitTime());
			visitVO.setDueDate(duedate);
			visitVO.setVisitTime(visittime);
			
			VisitRoomService service = new VisitRoomServiceImpl();
			final String resultStr = service.visitReserve(visitVO);
			
			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("預約成功"));
			respbody.addProperty("message", resultStr);
			resp.getWriter().append(respbody.toString());

			System.out.println(resultStr);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}

}
