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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tibame.web.service.RoomOrderService;
import com.tibame.web.service.impl.RoomOrderServiceImpl;
import com.tibame.web.vo.RoomOrderVO;

@WebServlet("/admin/room/RoomOrderController")
public class RoomOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RoomOrderService service;

	public RoomOrderController() {
		service = new RoomOrderServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();

		String status = request.getParameter("status");
		
		if (status != null && status.equals("orderIdSearch")) {
			Integer orderId = Integer.parseInt(request.getParameter("orderId"));
			if (orderId != null) {
				final RoomOrderVO roomOrder = service.getByOrderId(orderId);
				if (roomOrder == null) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				} else {
					Writer writer = response.getWriter();
					writer.write(gson.toJson(roomOrder));
				}
			}
		} else if (status != null && status.equals("userSearch")) {
//			Integer userId = (Integer)request.getSession().getAttribute("UserId"));
//			屆時登入功能完成要使用
			Integer userId = 1;
			if (userId != null) {
				final List<RoomOrderVO> list = service.getOrdersByUser(userId);
				if (list.size() == 0 || list == null) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				} else {
					Writer writer = response.getWriter();
					writer.write(gson.toJson(list));
				}
			}
		} else if (status != null && status.equals("calendar")) {
			Integer roomId = Integer.parseInt(request.getParameter("roomId"));
			if (roomId != null) {
				final List<RoomOrderVO> list = service.getOrdersForCalendar(roomId);
				if (list.size() == 0 || list == null) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				} else {
					Writer writer = response.getWriter();
					writer.write(gson.toJson(list));
				}
			}
		} else if (status != null && !status.equals("calendar")) {
			Integer limit = Integer.parseInt(request.getParameter("limit"));
			if (limit != null) {
				final List<RoomOrderVO> list = service.getAllOrders(status, limit);
				if (list.size() == 0 || list == null) {
					response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				} else {
					Writer writer = response.getWriter();
					writer.write(gson.toJson(list));
				}
			}
		} else {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		RoomOrderVO roomOrder = gson.fromJson(req.getReader(), RoomOrderVO.class);
		String resultStr = null;
		
		String action = req.getParameter("action");
		if(action!=null&&action.equals("status")) {
			if (roomOrder == null) {
				resultStr = "狀態內容有誤";
			} else {
				resultStr = service.changeStatus(roomOrder);
			}
			
		}else if (action!=null&&action.equals("edit")) {
			if (roomOrder == null) {
				resultStr = "編輯內容有誤";
			} else {
				resultStr = service.editOrder(roomOrder);
			}
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		resp.getWriter().append(jsonObject.toString());
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();
		RoomOrderVO roomOrder = gson.fromJson(request.getReader(), RoomOrderVO.class);
		String resultStr = null;

		if (roomOrder == null) {
			resultStr = "刪除內容有誤";
		} else {
			resultStr = service.remove(roomOrder);
		}

		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		response.getWriter().append(jsonObject.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

//		Gson gson = new Gson();

		RoomOrderVO roomOrder = gson.fromJson(request.getReader(), RoomOrderVO.class);
		String resultStr = null;

		if (roomOrder.getOrderResident() == null) {
//			roomOrder.setAdminId((Integer)request.getSession().getAttribute("adminId"));
//			屆時登入功能完成要使用
			roomOrder.setAdminId(1);
			resultStr = service.addOrder(roomOrder);
		} else if (roomOrder.getOrderResident() != null) {
//			roomOrder.setUserId((Integer)request.getSession().getAttribute("userId"));
//			屆時登入功能完成要使用
			roomOrder.setUserId(1);
			resultStr = service.addOrder(roomOrder);
		} else {
			resultStr = "操作有誤";
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("message", resultStr);

		response.getWriter().append(jsonObject.toString());

	}
}
