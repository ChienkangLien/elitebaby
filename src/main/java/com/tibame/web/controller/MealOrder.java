package com.tibame.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.service.MealOrderService;
import com.tibame.web.service.impl.MealOrderServiceImpl;
import com.tibame.web.vo.MealOrderVO;

/**
 * Servlet implementation class MealOrder
 */
@WebServlet("/MealOrder")
public class MealOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String str = request.getParameter("name");
		if(str.equals("getall")) {
			Gson gson = new Gson();
			MealOrderService service = new MealOrderServiceImpl();
			List<MealOrderVO> list = service.getAllMeal();
			if (list.size() == 0) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.setContentType("application/json");
				response.getWriter().write(gson.toJson(list));
			}
		}
		
//		if() {
//			
//		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MealOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
}
