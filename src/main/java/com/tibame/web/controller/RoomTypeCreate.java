package com.tibame.web.controller;

import java.io.IOException;
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

@WebServlet("/admin/room/RoomTypeCreate")
public class RoomTypeCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		JsonElement root = gson.fromJson(request.getReader(), JsonElement.class);

		JsonArray array = root.getAsJsonArray();
		RoomTypeVO roomType = gson.fromJson(array.get(0), RoomTypeVO.class);
		JsonArray photoArray = array.get(1).getAsJsonArray();

		
		RoomTypeService service = new RoomTypeServiceImpl();
		
		String resultStr;
		if(photoArray.size()==0) {
			resultStr = service.createRoomType(roomType);
		}else {
			List<RoomPhotoVO> roomPhotos = new ArrayList<>();
			for (JsonElement element : photoArray) {
				RoomPhotoVO roomPhotoVO = gson.fromJson(element, RoomPhotoVO.class);
				roomPhotos.add(roomPhotoVO);
			}
			resultStr = service.createRoomType(roomType,roomPhotos);
		}
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);
		
		response.getWriter().append(jsonObject.toString());
	}

}
