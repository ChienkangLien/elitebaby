package com.tibame.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.MealOrderService;
import com.tibame.web.service.impl.MealOrderServiceImpl;
import com.tibame.web.vo.MealOrderVO;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class Order
 */
@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String str = request.getParameter("name");
		if (str.equals("getorder")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealOrderVO mealOrderObject = gson.fromJson(request.getReader(), MealOrderVO.class);
			MealOrderService mealOrderService = new MealOrderServiceImpl();
			List<MealOrderVO> list = mealOrderService.findByPrimaryKey(mealOrderObject.getUserId());
			JsonObject respbody = new JsonObject();
//			int cartCount = carts.findByPrimaryKey(cartObject.getUserId()).size();
			if (list !=null) {
				response.getWriter().write(gson.toJson(list));
			} else {
				response.getWriter().write(gson.toJson(null));
			}
		}
	}

}
