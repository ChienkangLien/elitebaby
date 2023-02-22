package com.tibame.web.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.web.service.LatestNewsService;
import com.tibame.web.vo.LatestNewsVO;

@WebServlet("/Latestnews.do")
public class LatestNewsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("newsId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入最新消息編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/selectLatestNews.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer newsId = null;
			try {
				newsId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("最新消息編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/selectLatestNews.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			LatestNewsService newsSvc = new LatestNewsService();
			LatestNewsVO latestNewsVO = newsSvc.getOneLatestNews(newsId);
			if (latestNewsVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/selectLatestNews.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("latestNewsVO", latestNewsVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/news/listOneLatestNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer newsId = Integer.valueOf(req.getParameter("newsId"));

			/*************************** 2.開始查詢資料 ****************************************/
			LatestNewsService newsSvc = new LatestNewsService();
			LatestNewsVO latestNewsVO = newsSvc.getOneLatestNews(newsId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("latestNewsVO", latestNewsVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/news/updateLatestNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer newsId = Integer.valueOf(req.getParameter("newsId").trim());
			Integer sortId = Integer.valueOf(req.getParameter("sortId").trim());
			Integer adminId = Integer.valueOf(req.getParameter("adminId").trim());

			String newsIntro = req.getParameter("newsIntro");
			String newsIntroReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (newsIntro == null || newsIntro.trim().length() == 0) {
				errorMsgs.add("消息內容: 請勿空白");
			}

			java.sql.Date publishedTime = null;
			try {
				publishedTime = java.sql.Date.valueOf(req.getParameter("publishedTime").trim());
			} catch (Exception e) {
				publishedTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期時間!");
			}

			java.sql.Date onNews = null;
			try {
				onNews = java.sql.Date.valueOf(req.getParameter("onNews").trim());
			} catch (IllegalArgumentException e) {
				onNews = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期時間!");
			}

			java.sql.Date offNews= null;
			try {
				offNews = java.sql.Date.valueOf(req.getParameter("offNews").trim());
			} catch (IllegalArgumentException e) {
				offNews = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期時間!");
			}

			String postTitle = req.getParameter("postTitle").trim();
			if (postTitle == null || postTitle.trim().length() == 0) {
				errorMsgs.add("標題名稱請勿空白");
			}

			LatestNewsVO latestNewsVO = new LatestNewsVO();
			latestNewsVO.setNewsId(newsId);
			latestNewsVO.setSortId(sortId);
			latestNewsVO.setAdminId(adminId);
			latestNewsVO.setNewsIntro(newsIntro);
			latestNewsVO.setPublishedTime(publishedTime);
			latestNewsVO.setOnNews(onNews);
			latestNewsVO.setOffNews(offNews);
			latestNewsVO.setPostTitle(postTitle);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("latestNewsVO", latestNewsVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/updateLatestNews.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			LatestNewsService newsSvc = new LatestNewsService();
			latestNewsVO = newsSvc.updateEmp(newsId, sortId, adminId, newsIntro, publishedTime, onNews, offNews,
					postTitle);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("latestNewsVO", latestNewsVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/admin/news/listOneLatestNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer sortId = Integer.valueOf(req.getParameter("sortId").trim());
			Integer adminId = Integer.valueOf(req.getParameter("adminId").trim());

			String newsIntro = req.getParameter("newsIntro");
			String newsIntroReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (newsIntro == null || newsIntro.trim().length() == 0) {
				errorMsgs.add("消息內容: 請勿空白");
			}

			java.sql.Date publishedTime = null;
			try {
				publishedTime = java.sql.Date.valueOf(req.getParameter("publishedTime").trim());
			} catch (IllegalArgumentException e) {
				publishedTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期時間!");
			}

			java.sql.Date onNews = null;
			try {
				onNews = java.sql.Date.valueOf(req.getParameter("onNews").trim());
			} catch (IllegalArgumentException e) {
				onNews = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期時間!");
			}

			java.sql.Date offNews = null;
			try {
				offNews = java.sql.Date.valueOf(req.getParameter("offNews").trim());
			} catch (IllegalArgumentException e) {
				offNews = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期時間!");
			}

			String postTitle = req.getParameter("postTitle").trim();
			if (postTitle == null || postTitle.trim().length() == 0) {
				errorMsgs.add("標題名稱請勿空白");
			}

			LatestNewsVO latestNewsVO = new LatestNewsVO();
			latestNewsVO.setSortId(sortId);
			latestNewsVO.setAdminId(adminId);
			latestNewsVO.setNewsIntro(newsIntro);
			latestNewsVO.setPublishedTime(publishedTime);
			latestNewsVO.setOnNews(onNews);
			latestNewsVO.setOffNews(offNews);
			latestNewsVO.setPostTitle(postTitle);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("latestNewsVO", latestNewsVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/addLatestNews.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			LatestNewsService newsSvc = new LatestNewsService();
			latestNewsVO = newsSvc.addEmp(sortId,  adminId, newsIntro, publishedTime, onNews, offNews, postTitle);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/admin/news/listAllLatestNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer newsId = Integer.valueOf(req.getParameter("newsId"));

			/*************************** 2.開始刪除資料 ***************************************/
			LatestNewsService newsSvc = new LatestNewsService();
			newsSvc.deleteEmp(newsId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/admin/news/listAllLatestNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}
