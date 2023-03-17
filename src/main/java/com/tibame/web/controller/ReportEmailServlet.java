package com.tibame.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

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
import com.tibame.web.util.EmailJedis;
import com.tibame.web.util.GetAuthCode;
import com.tibame.web.vo.AnswerImageVO;
import com.tibame.web.vo.EmailBellBean;
import com.tibame.web.vo.EmailDTO;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.EmployeeVO;
import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.ReportImageVO;

/**
 * Servlet implementation class ReportEmailServlet
 */
@WebServlet("/report/emailservlet")
public class ReportEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		resp.setContentType("application/json");
		final String action = req.getParameter("action");

		if (action.equals("INSERT_FRONT")) {

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

		if (action.equals("INSERT_BACK")) {

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
				resultStr = service.insertEamilFromBack(emailVO);

				respbody.addProperty("successful", resultStr.equals("文字新增成功"));
				respbody.addProperty("message", resultStr);
				resp.getWriter().append(respbody.toString());

			} catch (Exception e) {
				e.printStackTrace();
				resultStr = "請輸入內容";
				respbody.addProperty("successful", resultStr.equals("文字新增成功"));
				respbody.addProperty("message", resultStr);
				resp.getWriter().append(respbody.toString());
			}

		}

		if (action.equals("GET_MEMBER")) {

			ReportEmailService service = new ReportEmailServiceImpl();
			List<MemberVO> list = service.getAllMemberInfo();
			Gson gson = new Gson();
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(list));

		}

		if (action.equals("GET_ADMIN")) {

			HttpSession session = req.getSession();
			EmployeeVO empVO = (EmployeeVO) session.getAttribute("employeeVO");
			Gson gson = new Gson();
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(empVO));

		}

		if ("GETALL_EMAIL".equals(action)) {

			Integer offset = Integer.valueOf(req.getParameter("offset"));
			ReportEmailService service = new ReportEmailServiceImpl();
			List<EmailVO> list = service.getAllInfo(offset);
			Gson gson = new Gson();
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(list));

		}

		if (action.equals("get_byUserId_admin")) {

			Gson gson = new Gson();
			HttpSession session = req.getSession();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			ReportEmailService service = new ReportEmailServiceImpl();
			List<EmailVO> getOneList = service.getAllByUserId(memberVO.getId());
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(getOneList));

		}

		if (action.equals("get_byUserId_member")) {

			HttpSession session = req.getSession();
			Gson gson = new Gson();
			MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
			ReportEmailService service = new ReportEmailServiceImpl();
			List<EmailVO> getOneList = service.getAllByUserIdMember(memberVO.getId());
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(getOneList));

		}

		if ("GETALL_EMAIL_COUNT".equals(action)) {

			ReportEmailService service = new ReportEmailServiceImpl();
			List<EmailVO> list = service.getAllCount();
			Gson gson = new Gson();
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(list));

		}

		if ("GETALL_EMAIL_ADMIN".equals(action)) {

			Integer offset = Integer.valueOf(req.getParameter("offset"));
			ReportEmailService service = new ReportEmailServiceImpl();
			List<EmailVO> list = service.getAllInfoByAdmin(offset);
			Gson gson = new Gson();
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(list));

		}

		if (action.equals("get_back_oneall")) {

			final String authCode = req.getParameter("authCode");
			final Integer mailId = Integer.valueOf(req.getParameter("mailId"));

			if (mailId != null && authCode != null) {
				HttpSession session = req.getSession();
				ReportEmailService emailService = new ReportEmailServiceImpl();

				EmailVO emailVO = emailService.getOneEmail(mailId);
				session.setAttribute("emailVO", emailVO);

				ReportImageVO reportImageVO = emailService.getOneAllPhoto(authCode);
				if (reportImageVO != null) {
					session.setAttribute("reportImageVO", reportImageVO);
				}
				resp.sendRedirect("/elitebaby/admin/visit/getone_email.html");

			}
		}

		if (action.equals("get_front_oneall")) {

			final String authCode = req.getParameter("authCode");
			final Integer mailId = Integer.valueOf(req.getParameter("mailId"));

			if (mailId != null && authCode != null) {
				HttpSession session = req.getSession();
				ReportEmailService emailService = new ReportEmailServiceImpl();

				EmailVO emailVO = emailService.getOneEmail(mailId);
				session.setAttribute("emailVO", emailVO);

				ReportImageVO reportImageVO = emailService.getOneAllPhoto(authCode);
				if (reportImageVO != null) {
					session.setAttribute("reportImageVO", reportImageVO);
				}
				resp.sendRedirect("/elitebaby/visit/ReportEmailFrontGetOne.html");

			}
		}

		if (action.equals("getEmail")) {

			HttpSession session = req.getSession();
			EmailVO emailVO = (EmailVO) session.getAttribute("emailVO");
			Gson gson = new Gson();
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(emailVO));

		}

		if (action.equals("getPhoto")) {

			HttpSession session = req.getSession();
			Object reportImageVO = session.getAttribute("reportImageVO");
			Gson gson = new Gson();
			Writer writer = resp.getWriter();
			writer.write(gson.toJson(reportImageVO));
		}

		if (action.equals("get_one_answer")) {

			Gson gson = new Gson();
			EmailVO emailVO = gson.fromJson(req.getReader(), EmailVO.class);
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			String strDate = sdFormat.format(date);
			Timestamp timestampt = java.sql.Timestamp.valueOf(strDate);
			emailVO.setAnsertCreateTime(timestampt);
			ReportEmailService service = new ReportEmailServiceImpl();
			String resultStr = service.getOneAnswer(emailVO);

			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("回覆成功"));
			respbody.addProperty("message", resultStr);
			resp.getWriter().append(respbody.toString());

		}

		if (action.equals("get_answerphoto")) {

			Gson gson = new Gson();
			AnswerImageVO answerImageVO = gson.fromJson(req.getReader(), AnswerImageVO.class);
			ReportEmailService service = new ReportEmailServiceImpl();
			String authCode = answerImageVO.getAuthCode();
			if (authCode != null) {
				answerImageVO = service.getAllAnswerPhoto(authCode);
				Writer writer = resp.getWriter();
				writer.write(gson.toJson(answerImageVO));
			}
		}

		if (action.equals("get_one_user_answer")) {

			Gson gson = new Gson();
			EmailVO emailVO = gson.fromJson(req.getReader(), EmailVO.class);
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = new Date();
			String strDate = sdFormat.format(date);
			Timestamp timestampt = java.sql.Timestamp.valueOf(strDate);
			emailVO.setAnsertCreateTime(timestampt);
			ReportEmailService service = new ReportEmailServiceImpl();
			String resultStr = service.getOneUserAnswer(emailVO);

			JsonObject respbody = new JsonObject();
			respbody.addProperty("successful", resultStr.equals("回覆成功"));
			respbody.addProperty("message", resultStr);
			resp.getWriter().append(respbody.toString());

		}

		if (action.equals("insert_reportphoto")) {
			String resultStr = null;
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

			String resultStr = null;
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

		if (action.equals("serch_by_info_member")) {

			EmailDTO dto = new EmailDTO();
			String category = req.getParameter("category");
			String likesome = req.getParameter("likesome");
			Integer userId = 0;
			if (req.getParameter("userId") != null) {
				userId = Integer.valueOf(req.getParameter("userId"));
			}
			if (category != null && !category.isEmpty()) {
				dto.setCategory(category);
			}
			if (likesome != null && !likesome.isEmpty()) {
				dto.setLikesome(likesome);
			}
			if (userId != null && userId != 0) {
				dto.setUserId(userId);
			}
			System.out.println(dto);
			ReportEmailService service = new ReportEmailServiceImpl();
			List<EmailVO> serchList = service.serchInfoMember(dto);
			if (serchList != null) {
				Gson gson = new Gson();
				Writer writer = resp.getWriter();
				writer.write(gson.toJson(serchList));
			}

		}

		if (action.equals("serch_by_info_admin")) {

			EmailDTO dto = new EmailDTO();
			String category = req.getParameter("category");
			String likesome = req.getParameter("likesome");
			Integer userId = 0;
			if (req.getParameter("userId") != null) {
				userId = Integer.valueOf(req.getParameter("userId"));
			}
			if (category != null && !category.isEmpty()) {
				dto.setCategory(category);
			}
			if (likesome != null && !likesome.isEmpty()) {
				dto.setLikesome(likesome);
			}
			if (userId != null && userId != 0) {
				dto.setUserId(userId);
			}

			ReportEmailService service = new ReportEmailServiceImpl();
			List<EmailVO> serchList = service.serchInfoAdmin(dto);
			if (serchList != null) {
				Gson gson = new Gson();
				Writer writer = resp.getWriter();
				writer.write(gson.toJson(serchList));
			}

		}

		if (action.equals("get_email_redis")) {
			Gson gson = new Gson();
			String userId = req.getParameter("userId");
			if (userId != null) {

				List<EmailBellBean> emailBellData = EmailJedis.getBellData(userId);

				if (emailBellData != null) {
					Writer writer = resp.getWriter();
					writer.write(gson.toJson(emailBellData));
				}

			}
		}

		if (action.equals("get_email_chang_status")) {

			Gson gson = new Gson();
			String userId = req.getParameter("userId");
			if (userId != null) {

				List<EmailBellBean> emailBellData = EmailJedis.getBellData(userId);

				if (emailBellData != null) {
					EmailJedis.changeStatus(userId);
					Writer writer = resp.getWriter();
					writer.write(gson.toJson(emailBellData));
				}

			}

		}

		if (action.equals("delete_email")) {

			Gson gson = new Gson();
			JsonObject json = new JsonObject();
			EmailVO emailVO = gson.fromJson(req.getReader(), EmailVO.class);
			String authCode = emailVO.getAuthCode();
			Integer mailId = emailVO.getMailId();
			System.out.println(authCode + mailId);
			String result = "請確認信件是否存在";
			if (authCode != null && !authCode.isEmpty() && mailId != null && mailId != 0) {
				ReportEmailService service = new ReportEmailServiceImpl();
				result = service.deleteAllEmailData(mailId, authCode);

				json.addProperty("successful", result.equals("刪除成功"));
				json.addProperty("message", result);
				resp.getWriter().append(json.toString());

			} else {

				json.addProperty("successful", result.equals("刪除成功"));
				json.addProperty("message", result);
				resp.getWriter().append(json.toString());
			}

		}

	}

}
