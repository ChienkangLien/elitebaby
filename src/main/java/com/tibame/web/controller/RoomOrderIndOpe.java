package com.tibame.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.RoomOrderService;
import com.tibame.web.service.impl.RoomOrderServiceImpl;
import com.tibame.web.vo.RoomOrderVO;

@WebServlet("/admin/room/RoomOrderIndOpe")
public class RoomOrderIndOpe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomOrderService service;

	public RoomOrderIndOpe() {
		service = new RoomOrderServiceImpl();
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Gson gson = new Gson();
		RoomOrderVO roomOrder = gson.fromJson(req.getReader(), RoomOrderVO.class);
		String resultStr = null;

		if (roomOrder == null) {
			resultStr = "狀態內容有誤";
		} else {
			resultStr = service.changeStatus(roomOrder);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		resp.getWriter().append(jsonObject.toString());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Gson gson = new Gson();
		RoomOrderVO roomOrder = gson.fromJson(req.getReader(), RoomOrderVO.class);
		String resultStr = null;

		if (roomOrder == null) {
			resultStr = "刪除內容有誤";
		} else {
			resultStr = service.remove(roomOrder);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		resp.getWriter().append(jsonObject.toString());
	}

}
