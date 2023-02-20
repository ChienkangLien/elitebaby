package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.RoomTypeService;
import com.tibame.web.service.impl.RoomTypeServiceImpl;
import com.tibame.web.vo.RoomTypeVO;

@WebServlet("/admin/room/RoomTypeEdit")
public class RoomTypeEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomTypeService service;

	public RoomTypeEdit() {
		service = new RoomTypeServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		RoomTypeVO roomType = gson.fromJson(request.getReader(), RoomTypeVO.class);
		String resultStr = "編輯內容有誤";
		
		if(roomType!=null) {
			resultStr = service.editRoomType(roomType);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);
		
		response.getWriter().append(jsonObject.toString());
	}

}
