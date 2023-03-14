package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tibame.web.service.RoomTypeService;
import com.tibame.web.service.impl.RoomTypeServiceImpl;
import com.tibame.web.vo.RoomPhotoVO;
import com.tibame.web.vo.RoomTypeVO;

@WebServlet("/RoomTypeController")
public class RoomTypeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomTypeService service;

	public RoomTypeController() {
		service = new RoomTypeServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		// 定義Json類別來接請求內容，一筆房型加上多筆照片
		JsonElement root = gson.fromJson(request.getReader(), JsonElement.class);

		JsonArray array = root.getAsJsonArray();
		RoomTypeVO roomType = gson.fromJson(array.get(0), RoomTypeVO.class);
		JsonArray photoArray = array.get(1).getAsJsonArray();

		String resultStr;
		if (photoArray.size() == 0 || photoArray == null) {
			resultStr = service.createRoomType(roomType);
		} else {
			List<RoomPhotoVO> roomPhotos = new ArrayList<>();
			for (JsonElement element : photoArray) {
				RoomPhotoVO roomPhotoVO = gson.fromJson(element, RoomPhotoVO.class);
				roomPhotos.add(roomPhotoVO);
			}
			resultStr = service.createRoomType(roomType, roomPhotos);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		response.getWriter().append(jsonObject.toString());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		String task = request.getParameter("task");

		if (task != null && task.equals("getAll")) {
			final List<RoomTypeVO> list = service.getAllTypes();

			if (list.size() == 0 || list == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				Writer writer = response.getWriter();
				writer.write(gson.toJson(list));
			}
		} else if (task != null && task.equals("getSingle")) {
			RoomTypeVO roomType = new RoomTypeVO();
			roomType.setRoomTypeId(Integer.parseInt(request.getParameter("roomTypeId")));

			Writer writer = response.getWriter();
			writer.write(gson.toJson(service.getRoomType(roomType)));
		} else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		RoomTypeVO roomType = gson.fromJson(request.getReader(), RoomTypeVO.class);
		String resultStr = "編輯內容有誤";

		if (roomType != null) {
			resultStr = service.editRoomType(roomType);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		response.getWriter().append(jsonObject.toString());
	}

}
