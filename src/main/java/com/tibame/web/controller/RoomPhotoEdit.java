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

@WebServlet("/admin/room/RoomPhotoEdit")
public class RoomPhotoEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RoomPhotoService service;
	
	public RoomPhotoEdit() {
		service = new RoomPhotoServiceImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
//		Type listType = new TypeToken<List<RoomPhotoVO>>() {}.getType();
//		List<RoomPhotoVO> roomPhotoVOList = gson.fromJson(request.getReader(), listType);
//
//		JsonObject jsonObject = new JsonObject();
//		String resultStr;
//		if(roomPhotoVOList.size()==0) {
//			jsonObject.addProperty("message", "上傳照片失敗");
//			response.getWriter().append(jsonObject.toString());
//		}else {
//			resultStr = service.editRoomTypePhoto(roomPhotoVOList);
//			jsonObject.addProperty("message", resultStr);
//			response.getWriter().append(jsonObject.toString());
//		}
		
		Type listType = new TypeToken<List<List<Map<String, Object>>>>(){}.getType();
		List<List<Map<String, Object>>> allData  = gson.fromJson(request.getReader(), listType);
		System.out.println(allData);
		List<Map<String, Object>> data1 = allData.get(0);
		List<Map<String, Object>> data2 = allData.get(1);

		List<RoomPhotoVO> newPhotoList = new ArrayList<>();
		for (Map<String, Object> map : data1) {
			RoomPhotoVO photo = new RoomPhotoVO();
			photo.setRoomPhoto((String) map.get("roomPhoto"));
			photo.setRoomTypeId(Integer.parseInt((String)map.get("roomTypeId")));
			newPhotoList.add(photo);
		}

		List<RoomPhotoVO> removePhotoList = new ArrayList<>();
		for (Map<String, Object> map : data2) {
			RoomPhotoVO photo = new RoomPhotoVO();
			photo.setRoomPhotoId(Integer.parseInt((String)map.get("roomPhotoId")));
		  removePhotoList.add(photo);
		}

		//		List<List<Object>> data = gson.fromJson(request.getReader(), List.class);

		
//		for (Object obj : data.get(0)) {
//		    Map<String, Object> photoData = (Map<String, Object>) obj;
////		    UserVO user = new UserVO();
//		    photo.setRoomPhoto((String) photoData.get("roomPhoto"));
//		    photo.setRoomTypeId((int) (double) photoData.get("roomTypeId"));
//		    newPhotoList.add(photo);
//		}
		
//		for (Map<String, Object> photoData : data.get(0)) {
//			photo.setRoomPhoto((String) photoData.get("roomPhoto"));
//			photo.setRoomTypeId((int) (double) photoData.get("roomTypeId"));
//			newPhotoList.add(photo);
//		}

//		for (Object obj : data.get(1)) {
//		    Map<String, Object> photoData = (Map<String, Object>) obj;
////		    ContactVO contact = new ContactVO();
//		    photo.setRoomPhotoId((int) (double) photoData.get("roomPhotoId"));
//		    removePhotoList.add(photo);
//		}
		
//		for (Map<String, Object> photoData : data.get(1)) {
//			photo.setRoomPhotoId((int) (double) photoData.get("roomPhotoId"));
//			removePhotoList.add(photo);
//		}
		
		String resultStr = service.editRoomTypePhoto(newPhotoList, removePhotoList);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);
		
		response.getWriter().append(jsonObject.toString());
		
	}

}
