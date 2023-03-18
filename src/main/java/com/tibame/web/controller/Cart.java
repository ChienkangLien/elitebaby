package com.tibame.web.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.CartService;
import com.tibame.web.service.MealOrderService;
import com.tibame.web.service.impl.CartServiceImpl;
import com.tibame.web.service.impl.MealOrderServiceImpl;
import com.tibame.web.util.GetAuthCode;
import com.tibame.web.vo.CartVO;
import com.tibame.web.vo.MealOrderDetailVO;
import com.tibame.web.vo.MealOrderVO;
import com.tibame.web.vo.MealVO;
import com.tibame.web.vo.MemberVO;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String str = request.getParameter("name");
		if (str.equals("addmeal")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			Jedis jedis = new Jedis("localhost", 6379);
			MemberVO membervo = ((MemberVO) request.getSession().getAttribute("memberVO"));
			JsonObject respbody = new JsonObject();
			if (membervo != null) {
				CartVO cartObject = gson.fromJson(request.getReader(), CartVO.class);
				Integer userId = ((MemberVO) request.getSession().getAttribute("memberVO")).getId();
				cartObject.setUserId(userId);
				CartService carts = new CartServiceImpl();
				int udm = carts.insertMeal(cartObject);
				int cartCount = carts.findByPrimaryKey(cartObject.getUserId()).size();
				if (udm > 0 && cartCount > 0) {
					respbody.addProperty("msg", "success");
					respbody.addProperty("cartcount", cartCount);
					response.getWriter().write(respbody.toString());
				} else {
					respbody.addProperty("msg", "fail");
					response.getWriter().write(respbody.toString());
				}
			} else {
				respbody.addProperty("msg", "notlogin");
				response.getWriter().write(respbody.toString());
			}
			jedis.close();
		}

		if (str.equals("getcart")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			JsonObject respbody = new JsonObject();
//			CartVO cartObject = gson.fromJson(request.getReader(), CartVO.class);
			MemberVO membervo = ((MemberVO) request.getSession().getAttribute("memberVO"));
			if (membervo != null) {
				Integer userId = ((MemberVO) request.getSession().getAttribute("memberVO")).getId();
//			Integer userId = cartObject.getUserId();
//			System.out.println(userId);
				CartService carts = new CartServiceImpl();
				Set<String> set = carts.findByPrimaryKey(userId);
				int count = set.size();
				respbody.addProperty("msg", "為已登入狀態");
				respbody.addProperty("cartcount", count);
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "為未登入狀態");
				response.getWriter().write(respbody.toString());
			}

		}

		HttpSession session = request.getSession();
		if (str.equals("tocart")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealOrderDetailVO meal = gson.fromJson(request.getReader(), MealOrderDetailVO.class);
			Integer userId = ((MemberVO) request.getSession().getAttribute("memberVO")).getId();
//			Integer userId = meal.getUserId();
//			session.setAttribute("userId", userId);
			meal.setUserId(userId);
//			System.out.println(session.getAttribute("userId"));
			JsonObject respbody = new JsonObject();
			if (session.getAttribute("userId") != null) {
				respbody.addProperty("msg", "為已登入狀態");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "為未登入狀態");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("getall")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
//			CartVO cartObject = gson.fromJson(request.getReader(), CartVO.class);
			if ((MemberVO) request.getSession().getAttribute("memberVO") == null) {
				response.getWriter().write(gson.toJson(null));
			} else {
				System.out.println("123");
				Integer userId = ((MemberVO) request.getSession().getAttribute("memberVO")).getId();
//			Integer userId = cartObject.getUserId();
				CartService carts = new CartServiceImpl();
				List<MealVO> mealList = carts.getAllMeal(userId);
				if (mealList != null) {
					response.getWriter().write(gson.toJson(mealList));
				}
			}
		}

		if (str.equals("update")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			CartVO cartObject = gson.fromJson(request.getReader(), CartVO.class);
			Integer userId = ((MemberVO) request.getSession().getAttribute("memberVO")).getId();
			cartObject.setUserId(userId);
			CartService carts = new CartServiceImpl();
			int udm = carts.updateMeal(cartObject);
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "修改成功");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "修改失敗");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("delete")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			CartVO cartObject = gson.fromJson(request.getReader(), CartVO.class);
			Integer userId = ((MemberVO) request.getSession().getAttribute("memberVO")).getId();
			cartObject.setUserId(userId);
			CartService carts = new CartServiceImpl();
			int udm = carts.deleteMeal(cartObject);
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("checkout")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealOrderVO cartObject = gson.fromJson(request.getReader(), MealOrderVO.class);
			Integer userId = ((MemberVO) request.getSession().getAttribute("memberVO")).getId();
			cartObject.setUserId(userId);
//			System.out.println(cartObject);
			Integer payment = cartObject.getOrderPayment();
//			Integer userId = cartObject.getUserId();
			if (payment > 0 && userId > 0 && payment != null && userId != null) {
				MealOrderService mealorder = new MealOrderServiceImpl();
				cartObject.setAuthCode(GetAuthCode.genAuthCode());
				int udm = mealorder.insertMeal(cartObject);
				JsonObject respbody = new JsonObject();
				if (udm > 0) {
					respbody.addProperty("msg", "success");
					response.getWriter().write(respbody.toString());
				} else {
					respbody.addProperty("msg", "fail");
					response.getWriter().write(respbody.toString());
				}
			}
		}
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

}
