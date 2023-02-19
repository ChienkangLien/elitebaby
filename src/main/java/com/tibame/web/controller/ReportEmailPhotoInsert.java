package com.tibame.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.ReportEmailService;
import com.tibame.web.service.impl.ReportEmailServiceImpl;

import com.tibame.web.vo.ReportImageVO;

/**
 * Servlet implementation class ReporTEmailPhotoInsert
 */
@WebServlet("/report/emailPhotoInsert")
public class ReportEmailPhotoInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("application/json");

		HttpSession session = req.getSession();
		String authCode = (String) session.getAttribute("authCode");
		Gson gson = new Gson();
		ReportImageVO reportImage = gson.fromJson(req.getReader(), ReportImageVO.class);
		reportImage.setAuthCode(authCode);
		String[] arryBase64 = reportImage.getArryBase64();
		String resultStr = null;

		for (int i = 0; i < arryBase64.length; i++) {

			reportImage.setAuthCode(authCode);
			byte[] base64Byte = Base64.getMimeDecoder().decode(arryBase64[i]);
			reportImage.setReportImage(base64Byte);
			ReportEmailService service = new ReportEmailServiceImpl();
			resultStr = service.insertPhoto(reportImage);

		}

		JsonObject respbody = new JsonObject();
		respbody.addProperty("successful", resultStr.equals("信件全部新增成功"));
		respbody.addProperty("message", resultStr);
		resp.getWriter().append(respbody.toString());

	}

}
