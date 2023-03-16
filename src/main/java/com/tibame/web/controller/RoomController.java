package com.tibame.web.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
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

@WebServlet("/RoomController")
public class RoomController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomService service;

	public RoomController() {
		service = new RoomServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		Gson gson = new Gson();
		String task = request.getParameter("task");

		if (task != null && task.equals("check")) { // 檢查欲變更的訂單日期是否為空房
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String roomId = request.getParameter("roomId");
			String orderId = request.getParameter("orderId");

			Map<String, String> map = new HashMap<String, String>();
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("roomId", roomId);
			map.put("orderId", orderId);

			if (map != null) {
				String resultStr = service.checkAva(map);

				if (resultStr == null) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				} else {
					JsonObject jsonObject = new JsonObject();
					jsonObject.addProperty("message", resultStr);

					response.getWriter().append(jsonObject.toString());
				}
			}
		} else if (task != null && task.equals("available")) { // 查詢日期間有多少空房
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String typeId = request.getParameter("typeId");

			Map<String, String> map = new HashMap<String, String>();
			map.put("startDate", startDate);
			map.put("endDate", endDate);
			map.put("typeId", typeId);

			if (map != null) {
				List<RoomVO> list = service.getRoomsByDate(map);

				if (list.size() == 0) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				} else {
					response.getWriter().write(gson.toJson(list));
				}
			}
		} else if (task != null && task.equals("search")) { // 搜尋該房型底下的房間
			RoomVO room = new RoomVO();
			room.setRoomTypeId(Integer.parseInt(request.getParameter("roomTypeId")));

			if (room != null && room.getRoomTypeId() != null) {
				List<RoomVO> list = service.getRoomsByType(room);

				if (list.size() == 0) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				} else {
					response.getWriter().write(gson.toJson(list));
				}
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}

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

		// 要新增的房間
		List<Map<String, Object>> data1 = allData.get(0);
		// 要變更名字的房間
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
