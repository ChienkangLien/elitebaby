package com.tibame.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.ReportEmailService;
import com.tibame.web.service.impl.ReportEmailServiceImpl;
import com.tibame.web.util.GetAuthCode;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.ReportImageVO;


/**
 * Servlet implementation class ReportEmailInsert
 */
@WebServlet("/report/emailInsert")
public class ReportEmailInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("application/json");

		ReportImageVO reportImage = new ReportImageVO();
		final String authCode = GetAuthCode.genAuthCode();
		System.out.println(authCode);
		Gson gson = new Gson();
		EmailVO emailVO = gson.fromJson(req.getReader(), EmailVO.class);
		emailVO.setAuthCode(authCode);
		reportImage.setAuthCode(authCode);
		String base64 = emailVO.getStrBase64();
		System.out.println(base64);
		byte[] base64Byte = Base64.getMimeDecoder().decode(base64);
		
		reportImage.setReportImage(base64Byte);
		
		ReportEmailService service = new ReportEmailServiceImpl();
		final int resultCount= service.insertEamil(emailVO);

		if(resultCount>=1) {
			
			final String resultStr = service.insertPhoto(reportImage);
			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("信件新增成功"));
			respbody.addProperty("message", resultStr);
			resp.getWriter().append(respbody.toString());		
		}

	}

}
