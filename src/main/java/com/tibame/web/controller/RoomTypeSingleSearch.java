package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.service.RoomTypeService;
import com.tibame.web.service.impl.RoomTypeServiceImpl;
import com.tibame.web.vo.RoomTypeVO;

@WebServlet("/admin/room/RoomTypeSingleSearch")
public class RoomTypeSingleSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomTypeService service;

	public RoomTypeSingleSearch() {
		service = new RoomTypeServiceImpl();
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		RoomTypeVO roomType = gson.fromJson(request.getReader(), RoomTypeVO.class);
		
		if(roomType!=null) {
			Writer writer = response.getWriter();
			writer.write(gson.toJson(service.getRoomType(roomType)));
		}else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}

}
