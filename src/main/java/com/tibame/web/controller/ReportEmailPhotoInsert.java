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
import com.tibame.web.vo.AnswerImageVO;
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

		final String action = req.getParameter("action");
		String resultStr = null;

		if (action.equals("insert_reportphoto")) {
			HttpSession session = req.getSession();
			String authCode = (String) session.getAttribute("authCode");
			Gson gson = new Gson();
			ReportImageVO reportImage = gson.fromJson(req.getReader(), ReportImageVO.class);
			reportImage.setAuthCode(authCode);
			ArrayList arryBase64 = reportImage.getArryBase64();

			for (int i = 0; i < arryBase64.size(); i++) {

				String str = (String) arryBase64.get(i);
				reportImage.setAuthCode(authCode);
				byte[] base64Byte = Base64.getMimeDecoder().decode(str);
				reportImage.setReportImage(base64Byte);
				ReportEmailService service = new ReportEmailServiceImpl();
				resultStr = service.insertPhoto(reportImage);

			}
			if (resultStr != null) {
				JsonObject respbody = new JsonObject();
				respbody.addProperty("successful", resultStr.equals("信件全部新增成功"));
				respbody.addProperty("message", resultStr);
				resp.getWriter().append(respbody.toString());
			}

		}

		if (action.equals("insert_answerphoto")) {

			Gson gson = new Gson();
			AnswerImageVO answerImageVO = gson.fromJson(req.getReader(), AnswerImageVO.class);
			ArrayList arryBase64 = answerImageVO.getArryBase64();

			for (int i = 0; i < arryBase64.size(); i++) {

				String str = (String) arryBase64.get(i);
				byte[] base64Byte = Base64.getMimeDecoder().decode(str);
				answerImageVO.setAnswerImage(base64Byte);
				ReportEmailService service = new ReportEmailServiceImpl();
				resultStr = service.insertAnswerPhoto(answerImageVO);

			}

			if (resultStr != null) {
				JsonObject respbody = new JsonObject();
				respbody.addProperty("successful", resultStr.equals("回覆全部新增成功"));
				respbody.addProperty("message", resultStr);
				resp.getWriter().append(respbody.toString());

			}
		}

	}

}
