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
 * Servlet implementation class VisitInsertController
 */
@WebServlet("/VisitInsertController")
public class VisitInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		try {
			Gson gson = new Gson();
			VisitVO visitVO = gson.fromJson(req.getReader(), VisitVO.class);
			Date duedate = Date.valueOf(visitVO.getStrDueDate());
			Date visittime = Date.valueOf(visitVO.getStrVisitTime());
			visitVO.setDueDate(duedate);
			visitVO.setVisitTime(visittime);
			VisitDAO visitDAO = new VisitDAO();
			System.out.print(visittime);
			if(visittime!=null ) {
				visitDAO.insert(visitVO);
			}


		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
	}

}
