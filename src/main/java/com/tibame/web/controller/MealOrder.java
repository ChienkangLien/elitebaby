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
import com.tibame.web.service.MealOrderDetailService;
import com.tibame.web.service.MealOrderService;
import com.tibame.web.service.impl.MealOrderDetailServiceImpl;
import com.tibame.web.service.impl.MealOrderServiceImpl;
import com.tibame.web.vo.MealOrderDetailVO;
import com.tibame.web.vo.MealOrderVO;
import com.tibame.web.vo.MemberVO;

/**
 * Servlet implementation class MealOrder
 */
@WebServlet("/MealOrder")
public class MealOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		String str = request.getParameter("name");
		if (str.equals("getorder")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
//			MealOrderVO mealOrderObject = gson.fromJson(request.getReader(), MealOrderVO.class);
//			MemberVO membervo = ((MemberVO)request.getSession().getAttribute("memberVO"));
			Integer userId = ((MemberVO)request.getSession().getAttribute("memberVO")).getId();
//			mealOrderObject.setUserId(userId);
			MealOrderService mealOrderService = new MealOrderServiceImpl();
			List<MealOrderVO> list = mealOrderService.findByPrimaryKey(userId);
//			int cartCount = carts.findByPrimaryKey(cartObject.getUserId()).size();
			if (list != null) {
				response.getWriter().write(gson.toJson(list));
			} else {
				response.getWriter().write(gson.toJson(null));
			}
		}

		if (str.equals("getall")) {
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

		if (str.equals("update")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealOrderVO MealOrderObject = gson.fromJson(request.getReader(), MealOrderVO.class);
//			System.out.println(MealOrderObject.getStrstatus());
			MealOrderService service = new MealOrderServiceImpl();
			int udm = service.updateMeal(MealOrderObject);
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.getWriter().write(respbody.toString());
			}
		}
		
		if (str.equals("updateMealWithAddress")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealOrderVO MealOrderObject = gson.fromJson(request.getReader(), MealOrderVO.class);
//			System.out.println(MealOrderObject.getStrstatus());
			MealOrderService service = new MealOrderServiceImpl();
			int udm = service.updateMealWithAddress(MealOrderObject);
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("getorderdetail")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealOrderDetailVO MealOrderDetailObject = gson.fromJson(request.getReader(), MealOrderDetailVO.class);
//			Integer userId = ((MemberVO)request.getSession().getAttribute("memberVO")).getId();
//			System.out.println(userId);
//			MealOrderDetailObject.setUserId(userId);
			MealOrderService moService = new MealOrderServiceImpl();
			MealOrderDetailService modsService = new MealOrderDetailServiceImpl();
			List<MealOrderVO> list1 = moService.findByMealOrder(MealOrderDetailObject.getMealOrderId());
			List<MealOrderDetailVO> list2 = modsService.getOrderDetail(list1.get(0).getAuthCode());
			response.getWriter().write(gson.toJson(list2));
//			service.getOrderDetail(str);
		}

		if (str.equals("getbyorderid")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealOrderDetailVO MealOrderDetailObject = gson.fromJson(request.getReader(), MealOrderDetailVO.class);
			MealOrderService moService = new MealOrderServiceImpl();
			List<MealOrderVO> list = moService.findByMealOrder(MealOrderDetailObject.getMealOrderId());
//			System.out.println(list);
			response.getWriter().write(gson.toJson(list));
		}
		
		if(str.equals("getuserorderbyorderid")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealOrderVO mealOrderObject = gson.fromJson(request.getReader(), MealOrderVO.class);
			Integer userId = ((MemberVO)request.getSession().getAttribute("memberVO")).getId();
			mealOrderObject.setUserId(userId);
			MealOrderService moService = new MealOrderServiceImpl();
			List<MealOrderVO> list = moService.findByMealOrderwithuser(mealOrderObject.getUserId(), mealOrderObject.getMealOrderId());
			response.getWriter().write(gson.toJson(list));
		}
	}

	public MealOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
}
