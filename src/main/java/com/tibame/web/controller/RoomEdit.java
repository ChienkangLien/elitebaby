package com.tibame.web.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tibame.web.service.RoomService;
import com.tibame.web.service.impl.RoomServiceImpl;
import com.tibame.web.vo.RoomVO;

@WebServlet("/admin/room/RoomEdit")
public class RoomEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomService service;

	public RoomEdit() {
		service = new RoomServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		String resultStr = null;

		Type listType = new TypeToken<List<List<Map<String, Object>>>>() {
		}.getType();
		List<List<Map<String, Object>>> allData = gson.fromJson(request.getReader(), listType);
		System.out.println(allData);
		List<Map<String, Object>> data1 = allData.get(0);
		List<Map<String, Object>> data2 = allData.get(1);

		if (data1 == null || data2 == null) {
			resultStr = "編輯內容有誤";
		} else {
			List<RoomVO> newRoomList = new ArrayList<>();
			for (Map<String, Object> map : data1) {
				RoomVO room = new RoomVO();
				room.setRoomName((String) map.get("roomName"));
				room.setRoomTypeId(Integer.parseInt((String) map.get("roomTypeId")));
				newRoomList.add(room);
			}

			List<RoomVO> updateRoomList = new ArrayList<>();
			for (Map<String, Object> map : data2) {
				RoomVO room = new RoomVO();
				room.setRoomName((String) map.get("roomName"));
				room.setRoomId(Integer.parseInt((String) map.get("roomId")));
				updateRoomList.add(room);
			}

			resultStr = service.editRoom(newRoomList, updateRoomList);
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		response.getWriter().append(jsonObject.toString());
	}

}
