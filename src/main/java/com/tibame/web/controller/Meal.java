package com.tibame.web.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.MealService;
import com.tibame.web.service.impl.MealServiceImpl;
import com.tibame.web.vo.MealVO;

import redis.clients.jedis.Jedis;

/**
 * Servlet implementation class MealSearch
 */
@WebServlet("/Meal")
public class Meal extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		String str = request.getParameter("name");
		if (str.equals("getall")) {
			Gson gson = new Gson();
			MealService service = new MealServiceImpl();
			List<MealVO> list = service.getAllMeal();
//			System.out.println(list);
			if (list.size() == 0) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.setContentType("application/json");
				response.getWriter().write(gson.toJson(list));
			}
		}

		if (str.equals("insert")) {
			Gson gson = new Gson();
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			byte[] mealpic = Base64.getDecoder().decode(jsonObject.getBase64());
			jsonObject.setMealPic(mealpic);
			MealService service = new MealServiceImpl();
			int udm = service.insertMeal(jsonObject);
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("update")) {
			Gson gson = new Gson();
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			System.out.println(jsonObject.getBase64());
			if (jsonObject.getBase64() == "" || jsonObject.getBase64() == null) {
				jsonObject.setMealPic(null);
			} else {
				byte[] mealpic = Base64.getMimeDecoder().decode(jsonObject.getBase64());
				jsonObject.setMealPic(mealpic);
			}
			MealService service = new MealServiceImpl();
			int udm = service.updateMeal(jsonObject);
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("findByPrimaryKey")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			System.out.println(jsonObject.getMealId());
			MealService service = new MealServiceImpl();
			MealVO meal = service.findByPrimaryKey(jsonObject.getMealId());
			response.getWriter().write(gson.toJson(meal));
		}

		if (str.equals("delete")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			System.out.println(jsonObject.getMealId());
			MealService service = new MealServiceImpl();
			int udm = service.deleteMeal(jsonObject.getMealId());
			JsonObject respbody = new JsonObject();
			if (udm > 0) {
				respbody.addProperty("msg", "success");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			} else {
				respbody.addProperty("msg", "fail");
				response.setContentType("application/json");
				response.getWriter().write(respbody.toString());
			}
		}

		if (str.equals("getonemeal")) {
			Gson gson = new Gson();
			response.setContentType("application/json");
			MealVO jsonObject = gson.fromJson(request.getReader(), MealVO.class);
			System.out.println(jsonObject);
			MealService service = new MealServiceImpl();
			MealVO meal = service.findByPrimaryKey(jsonObject.getMealId());
			response.getWriter().write(gson.toJson(meal));
		}
	}

	public Meal() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
