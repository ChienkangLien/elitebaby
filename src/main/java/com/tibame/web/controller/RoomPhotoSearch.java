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
import com.tibame.web.service.RoomPhotoService;
import com.tibame.web.service.impl.RoomPhotoServiceImpl;
import com.tibame.web.vo.RoomPhotoVO;

@WebServlet("/admin/room/RoomPhotoSearch")
public class RoomPhotoSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomPhotoService service;

	public RoomPhotoSearch() {
		service = new RoomPhotoServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		JsonObject jsonObject = gson.fromJson(request.getReader(), JsonObject.class);
		Integer id = jsonObject.get("id").getAsInt();

		if (id != null) {
			List<RoomPhotoVO> list = service.getAllPhotos(id);

			if (list.size() == 0 || list == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.getWriter().write(gson.toJson(list));
			}
		}

	}

}
