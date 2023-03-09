package com.tibame.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.vo.MealOrderDetailVO;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String str = request.getParameter("name");
		if (str.equals("addmeal")) {
			Gson gson = new Gson();
			Jedis jedis = new Jedis("localhost", 6379);
			MealOrderDetailVO meal = gson.fromJson(request.getReader(), MealOrderDetailVO.class);
			System.out.println(meal);
			String cartpk = "user" + meal.getUserId();
			Integer mealId = meal.getMealId();
			String mealpk = "meal" + mealId;
			HashMap<String, String> data = new HashMap<>();
			Set<String> keys = jedis.keys(cartpk + "*");
			Iterator<String> iterator = keys.iterator();
			if (keys == null || keys.isEmpty()) {
				System.out.println("近來ㄌ");
				data.put("mealId", String.valueOf(mealId));
				data.put("count", "1");
				jedis.hmset(cartpk + ":" + mealpk, data);
			} else {
				while (iterator.hasNext()) {
					String cart = iterator.next();
					System.out.println(cart);
					System.out.println(cartpk + ":meal" + mealId);
					if (cart.equals(cartpk + ":meal" + mealId)) {
						Integer count = Integer.valueOf(jedis.hget(cartpk + ":meal" + mealId, "count"));
						count += 1;
						data.put("mealId", jedis.hget(cartpk + ":meal" + mealId, "mealId"));
						data.put("count", String.valueOf(count));
						jedis.hmset(cartpk + ":meal" + mealId, data);
					} else {
						data.put("mealId", String.valueOf(mealId));
						data.put("count", "1");
						jedis.hmset(cartpk + ":meal" + mealId, data);
					}
				}
			}
			String cartCount = String.valueOf(jedis.keys(cartpk + "*").size());
			JsonObject respbody = new JsonObject();
			respbody.addProperty("msg", "success");
			respbody.addProperty("cartcount", cartCount);
			response.setContentType("application/json");
			response.getWriter().write(respbody.toString());
			jedis.close();
		}

		if (str.equals("getcart")) {
			Gson gson = new Gson();
			MealOrderDetailVO meal = gson.fromJson(request.getReader(), MealOrderDetailVO.class);
			Integer userId = meal.getUserId();
			System.out.println(userId);
			if (userId != null) {
				Jedis jedis = new Jedis("localhost", 6379);
				String cartCount = String.valueOf(jedis.keys("user" + userId + "*").size());
				System.out.println(cartCount);
				JsonObject respbody = new JsonObject();
				respbody.addProperty("msg", "為已登入狀態");
				respbody.addProperty("cartcount", cartCount);
				System.out.println(respbody);
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
				jedis.close();
			} else {
				JsonObject respbody = new JsonObject();
				respbody.addProperty("msg", "為未登入狀態");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			}
		}
		
		if(str.equals("tocart")) {
			
		}

	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

}
