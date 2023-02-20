package com.tibame.web.controller;

import java.io.IOException;
import java.net.URLDecoder;
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

		String resultStr = null;
		final String authCode = GetAuthCode.genAuthCode();
		JsonObject respbody = new JsonObject();
		HttpSession session = req.getSession();
		session.setAttribute("authCode", authCode);
		Gson gson = new Gson();
		
		try {
			EmailVO emailVO = gson.fromJson(req.getReader(), EmailVO.class);

			final Integer userId = emailVO.getUserId();
			if (userId == null) {
				resultStr = "請先登入";
			}

			final Integer categoryId = emailVO.getCategoryId();
			if (categoryId == null || categoryId == 0) {
				resultStr = "請選擇類別";
			}

			final String reportTile = emailVO.getReportTile();
			if (reportTile == null || reportTile.isEmpty()) {
				resultStr = "請選擇類別";
			}

			final String reportContent = emailVO.getReportContent();
			if (reportContent == null || reportContent.isEmpty()) {
				resultStr = "請輸入回報內容";
			}

			emailVO.setAuthCode(authCode);

			ReportEmailService service = new ReportEmailServiceImpl();
			resultStr = service.insertEamil(emailVO);

			respbody.addProperty("successful", resultStr.equals("文字新增成功"));
			respbody.addProperty("message", resultStr);
			resp.getWriter().append(respbody.toString());
			
		} catch (Exception e) {
			resultStr = "請輸入內容";
			respbody.addProperty("successful", resultStr.equals("文字新增成功"));
			respbody.addProperty("message", resultStr);
			resp.getWriter().append(respbody.toString());
		}

	}

}
