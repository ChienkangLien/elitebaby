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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(request.getReader(), JsonObject.class);
		Integer id = jsonObject.get("id").getAsInt();
//		Integer id = gson.fromJson(request.getReader(), Integer.class);
		System.out.println(id);
		
		if(id!=null) {
			RoomPhotoService service = new RoomPhotoServiceImpl();
			List<RoomPhotoVO> list = service.getAllPhotos(id);
			System.out.println(list.size());
			
			if (list.size() == 0) {
			    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
			    response.setContentType("application/json");
			    response.getWriter().write(gson.toJson(list));
			}
		}
	
	}


}
