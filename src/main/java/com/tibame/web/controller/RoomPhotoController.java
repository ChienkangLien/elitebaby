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
import com.tibame.web.service.RoomPhotoService;
import com.tibame.web.service.impl.RoomPhotoServiceImpl;
import com.tibame.web.vo.RoomPhotoVO;

@WebServlet("/RoomPhotoController")
public class RoomPhotoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private RoomPhotoService service;

	public RoomPhotoController() {
		service = new RoomPhotoServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Gson gson = new Gson();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Integer id = Integer.parseInt(request.getParameter("id"));

		if (id != null) {
			List<RoomPhotoVO> list = service.getAllPhotos(id);

			if (list.size() == 0 || list == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.getWriter().write(gson.toJson(list));
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		Type listType = new TypeToken<List<List<Map<String, Object>>>>() {
		}.getType();
		String resultStr = null;

		List<List<Map<String, Object>>> allData = gson.fromJson(request.getReader(), listType);
		// 欲新增的照片
		List<Map<String, Object>> data1 = allData.get(0);
		// 欲刪除的照片
		List<Map<String, Object>> data2 = allData.get(1);

		if (data1 == null || data2 == null) {
			resultStr = "編輯內容有誤";
		} else {

			List<RoomPhotoVO> newPhotoList = new ArrayList<>();
			for (Map<String, Object> map : data1) {
				RoomPhotoVO photo = new RoomPhotoVO();
				photo.setRoomPhoto((String) map.get("roomPhoto"));
				photo.setRoomTypeId(Integer.parseInt((String) map.get("roomTypeId")));
				newPhotoList.add(photo);
			}

			List<RoomPhotoVO> removePhotoList = new ArrayList<>();
			for (Map<String, Object> map : data2) {
				RoomPhotoVO photo = new RoomPhotoVO();
				photo.setRoomPhotoId(Integer.parseInt((String) map.get("roomPhotoId")));
				removePhotoList.add(photo);
			}
			resultStr = service.editRoomTypePhoto(newPhotoList, removePhotoList);
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		response.getWriter().append(jsonObject.toString());
	}

}
