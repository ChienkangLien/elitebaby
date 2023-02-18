package com.tibame.web.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.service.RoomService;
import com.tibame.web.service.impl.RoomServiceImpl;
import com.tibame.web.vo.RoomPhotoVO;
import com.tibame.web.vo.RoomVO;

@WebServlet("/admin/room/RoomSearch")
public class RoomSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomService service;
       
    public RoomSearch() {
    	service = new RoomServiceImpl();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		RoomVO room = gson.fromJson(request.getReader(), RoomVO.class);
		
		if(room!=null && room.getRoomTypeId() != null) {
			List<RoomVO> list = service.getRoomsByType(room);
			
			if (list.size() == 0) {
			    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
			    response.getWriter().write(gson.toJson(list));
			}
		}
	}
}
