package com.tibame.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.service.MealService;
import com.tibame.web.service.impl.MealServiceImpl;
import com.tibame.web.vo.MealVO;


/**
 * Servlet implementation class MealSearch
 */
@WebServlet("/MealSearch")
public class MealSearch extends HttpServlet {
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
		Integer id = jsonObject.getMealId();
//		Integer id = gson.fromJson(request.getReader(), Integer.class);
		System.out.println(id);
//		response.setContentType("application/json");
//	    response.getWriter().write(gson.toJson(list));
		if(id!=null) {
//			RoomPhotoService service = new RoomPhotoServiceImpl();
			MealService service = new MealServiceImpl();
			List<MealVO> list = service.getAllMeal();
			System.out.println(list.size());
			
			if (list.size() == 0) {
			    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
			    response.setContentType("application/json");
			    response.getWriter().write(gson.toJson(list));
			}
		}
	}
    
    public MealSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

}
