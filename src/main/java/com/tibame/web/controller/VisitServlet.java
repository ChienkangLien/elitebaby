package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.service.impl.VisitRoomServiceImpl;
import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitServlet
 */
@WebServlet("/visit/servlet")
public class VisitServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		response.setContentType("application/json");
		request.setCharacterEncoding("UTF-8");

		final String action = request.getParameter("action");

		if ("GETALL_VISIT".equals(action)) {

			VisitRoomService service = new VisitRoomServiceImpl();
			List<VisitVO> list = service.getAllInfo();
			Gson gson = new Gson();
			Writer writer = response.getWriter();
			writer.write(gson.toJson(list));

		}

		if ("check_visit".equals(action)) {
			Gson gson = new Gson();
			VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
			VisitRoomService service = new VisitRoomServiceImpl();
			String resultStr = service.checkVisitDate(java.sql.Date.valueOf(visitVO.getStrVisitTime()));
			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("當日預約已滿"));
			respbody.addProperty("message", resultStr);
			response.getWriter().append(respbody.toString());

		}

		if ("GETALL_VISIT_PAGE".equals(action)) {

			Integer offset = Integer.valueOf(request.getParameter("offset"));
			VisitRoomService service = new VisitRoomServiceImpl();
			List<VisitVO> list = service.getAllInfoPage(offset);
			try {

				if (list.isEmpty()) {

					response.setStatus(HttpServletResponse.SC_NO_CONTENT);

				} else {
					response.setStatus(HttpServletResponse.SC_OK);
					Gson gson = new Gson();
					Writer writer = response.getWriter();
					writer.write(gson.toJson(list));
				}

			} catch (Exception e) {

				response.setStatus(HttpServletResponse.SC_NO_CONTENT);

			}

		}

		if ("GETALL_VISIT_PAGE_HISTORY".equals(action)) {

			Integer offset = Integer.valueOf(request.getParameter("offset"));
			VisitRoomService service = new VisitRoomServiceImpl();
			List<VisitVO> list = service.getAllInfoPageHistory(offset);
			try {

				System.out.print(list);
				if (list.isEmpty()) {

					response.setStatus(HttpServletResponse.SC_NO_CONTENT);

				} else {
					response.setStatus(HttpServletResponse.SC_OK);
					Gson gson = new Gson();
					Writer writer = response.getWriter();
					writer.write(gson.toJson(list));
				}

			} catch (Exception e) {

				response.setStatus(HttpServletResponse.SC_NO_CONTENT);

			}

		}

		if (action.equals("MEMBER_INDERT_VISIT")) {

			String resultStr = null;
			Gson gson = new Gson();
			VisitRoomService service = new VisitRoomServiceImpl();
			VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
			Date duedate = Date.valueOf(visitVO.getStrDueDate());
			Date visittime = Date.valueOf(visitVO.getStrVisitTime());
			String suc = service.checkVisitDate(visittime);

			if (suc.equals("可以預約")) {
				visitVO.setDueDate(duedate);
				visitVO.setVisitTime(visittime);

				resultStr = service.visitReserve(visitVO);
				if (resultStr != null || resultStr != "") {
					JsonObject respbody = new JsonObject();
					respbody.addProperty("successful", resultStr.equals("預約成功"));
					respbody.addProperty("message", resultStr);
					response.getWriter().append(respbody.toString());
				}
			} else {

				if (suc != null || suc != "") {
					JsonObject respbody = new JsonObject();
					respbody.addProperty("successful", suc.equals("預約成功"));
					respbody.addProperty("message", suc);
					response.getWriter().append(respbody.toString());
				}
			}
		}

		if (action.equals("GET_MEMBER_INFO")) {

			Gson gson = new Gson();
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			if (memberVO != null) {
				Integer userId = memberVO.getId();
				if (userId > 0 && userId != null) {
					VisitRoomService service = new VisitRoomServiceImpl();
					memberVO = service.getOneMemberInfo(userId);
					Writer writer = response.getWriter();
					writer.write(gson.toJson(memberVO));
				}
			} else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		}

		if ("getOne_For_Update".equals(action)) {

			Integer visitid = Integer.valueOf(request.getParameter("visitid"));
			if (visitid != null) {

				HttpSession session = request.getSession();
				VisitRoomService service = new VisitRoomServiceImpl();
				VisitVO visitVO = service.getOneInfo(visitid);
				session.setAttribute("visitVO", visitVO);
				response.sendRedirect("/elitebaby/admin/visit/update_visit.html");
			}
		}

		if ("getOne_NO_Update".equals(action)) {

			Integer visitid = Integer.valueOf(request.getParameter("visitid"));
			if (visitid != null) {

				HttpSession session = request.getSession();
				VisitRoomService service = new VisitRoomServiceImpl();
				VisitVO visitVO = service.getOneInfo(visitid);
				session.setAttribute("visitVO", visitVO);
				response.sendRedirect("/elitebaby/visit/VisitRoomFrontViewOne.html");
			}
		}

		if ("UPDATE_VISIT".equals(action)) {
			Gson gson = new Gson();
			VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
			Date duedate = Date.valueOf(visitVO.getStrDueDate());
			Date visittime = Date.valueOf(visitVO.getStrVisitTime());
			visitVO.setDueDate(duedate);
			visitVO.setVisitTime(visittime);
			VisitRoomService service = new VisitRoomServiceImpl();
			String resultStr = service.oneVisitUpdate(visitVO);

			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("更新成功"));
			respbody.addProperty("message", resultStr);
			response.getWriter().append(respbody.toString());

		}

		if ("GET_ONE_MEMBER_VISIT".equals(action)) {

			Gson gson = new Gson();
			HttpSession session = request.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			if (memberVO != null) {
				Integer userId = memberVO.getId();

				if (userId > 0 && userId != null) {
					VisitRoomService service = new VisitRoomServiceImpl();
					List<VisitVO> list = service.getOneAll(userId);
					Writer writer = response.getWriter();
					writer.write(gson.toJson(list));
				}

			} else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		}

		if ("DELETE_ONE_VISIT".equals(action))

		{

			Gson gson = new Gson();
			VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
			Integer visitid = visitVO.getVisitId();

			VisitRoomService service = new VisitRoomServiceImpl();
			String resultStr = service.deleteOneVisit(visitid);

			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("刪除成功"));
			respbody.addProperty("message", resultStr);
			response.getWriter().append(respbody.toString());

		}

		if ("GET_ONE_VISIT_DATA".equals(action)) {

			HttpSession session = request.getSession();
			VisitVO visitVO = (VisitVO) session.getAttribute("visitVO");

			DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
			String strDueDate = dateFormat.format(visitVO.getDueDate());
			String strVisitTime = dateFormat.format(visitVO.getVisitTime());
			visitVO.setStrDueDate(strDueDate);
			visitVO.setStrVisitTime(strVisitTime);

			Gson gson = new Gson();
			Writer writer = response.getWriter();
			writer.write(gson.toJson(visitVO));

		}

		if ("CHECK_DOUBLE_VISIT".equals(action)) {

			Gson gson = new Gson();
			VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
			Integer userid = visitVO.getUserId();

			if (userid > 0 && userid != null) {

				VisitRoomService service = new VisitRoomServiceImpl();
				List<VisitVO> list = service.getOneAll(userid);
				Writer writer = response.getWriter();
				writer.write(gson.toJson(list));

			}
		}

		if ("SERCH_ONE_MEMBER_VISIT".equals(action)) {

			Gson gson = new Gson();
			VisitVO visitVO = gson.fromJson(request.getReader(), VisitVO.class);
			VisitRoomService service = new VisitRoomServiceImpl();
			List<VisitVO> list = service.getOneAll(visitVO.getUserId());
			Writer writer = response.getWriter();
			writer.write(gson.toJson(list));

		}

	}

}
