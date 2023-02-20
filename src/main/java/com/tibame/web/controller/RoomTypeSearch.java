package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.service.RoomTypeService;
import com.tibame.web.service.impl.RoomTypeServiceImpl;
import com.tibame.web.vo.RoomTypeVO;

@WebServlet("/admin/room/RoomTypeSearch")
public class RoomTypeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomTypeService service;

	public RoomTypeSearch() {
		service = new RoomTypeServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		final List<RoomTypeVO> list = service.getAllTypes();

		response.setContentType("application/json");
		Gson gson = new Gson();
		
		if (list.size() == 0 || list == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		} else {
			Writer writer = response.getWriter();
			writer.write(gson.toJson(list));
		}
	}

}
