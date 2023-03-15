package com.tibame.web.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.web.dao.LatestNewsDAO;
import com.tibame.web.service.LatestNewsService;
import com.tibame.web.vo.LatestNewsVO;


@MultipartConfig
@WebServlet(urlPatterns = { "/LatestNewsServlet", "/Latestnews.do" })

public class LatestNewsServlet extends HttpServlet {
	
	public static byte[] getPictureByteArray(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        return buffer;
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		Integer newsId = Integer.parseInt(req.getParameter("newsId"));
		LatestNewsDAO latestNewsDAO = new LatestNewsDAO();

		try {
		    LatestNewsVO latestNewsVO = latestNewsDAO.getnewsById(newsId);
		    res.setContentType("image/png");
		    OutputStream out = res.getOutputStream();
		    out.write(latestNewsVO.getNewsPhoto());
		    out.close();
		} catch (SQLException | ClassNotFoundException e) {
		    e.printStackTrace();
		}
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



			java.sql.Date scheduledTime = null;
			try {
				scheduledTime = java.sql.Date.valueOf(req.getParameter("scheduledTime").trim());
			} catch (IllegalArgumentException e) {
				scheduledTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("時間格式不正確,請重新輸入!");
			}

//			java.sql.Date offShelfTime= null;
//			try {
//				offShelfTime = java.sql.Date.valueOf(req.getParameter("offShelfTime").trim());
//			} catch (IllegalArgumentException e) {
//				offShelfTime = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期時間!");
//			}

			String postTitle = req.getParameter("postTitle").trim();
			if (postTitle == null || postTitle.trim().length() == 0) {
				errorMsgs.add("標題名稱請勿空白");
			}
			
			String savepath = req.getServletContext().getRealPath("/DB-image");
            File imgfolderPath = new File(savepath);
            if (!imgfolderPath.exists()) {
                imgfolderPath.mkdirs();
            }
			 javax.servlet.http.Part part = req.getPart("newsPhoto");
	            String filename = part.getSubmittedFileName();
	            byte[] newsPhoto = null;
	            
	            
	            if (filename != null && !filename.isEmpty()) {
	            	String imgPath = imgfolderPath + "/" + filename;
	            	part.write(imgPath);
	            	newsPhoto = getPictureByteArray(imgPath);
	            	}
	            	else {

	            	   errorMsgs.add("圖片未上傳");
	            	}
//	            if (filename != "") {
//	                String imgPath = imgfolderPath + "/" + filename;
//	                part.write(imgPath);
//	                newsPhoto = getPictureByteArray(imgPath);
//	            }

			LatestNewsVO latestNewsVO = new LatestNewsVO();
			latestNewsVO.setNewsId(newsId);
			latestNewsVO.setSortId(sortId);
			latestNewsVO.setAdminId(adminId);
			latestNewsVO.setNewsIntro(newsIntro);
			latestNewsVO.setScheduledTime(scheduledTime);
//			latestNewsVO.setOffShelfTime(offShelfTime);
			latestNewsVO.setPostTitle(postTitle);
			latestNewsVO.setNewsPhoto(newsPhoto);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("latestNewsVO", latestNewsVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/updateLatestNews.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			LatestNewsService newsSvc = new LatestNewsService();
//			latestNewsVO = newsSvc.addLatestNewsEmp(sortId,  adminId, newsIntro, scheduledTime, offShelfTime, postTitle, newsPhoto);
			latestNewsVO = newsSvc.addLatestNewsEmp(sortId,  adminId, newsIntro, scheduledTime, postTitle, newsPhoto);

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
			
			
			java.sql.Date scheduledTime = null;
			try {
				scheduledTime = java.sql.Date.valueOf(req.getParameter("scheduledTime").trim());
			} catch (IllegalArgumentException e) {
				scheduledTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("時間格式不正確,請重新輸入!");
			}
			
//			java.sql.Date offShelfTime = null;
//			try {
//				offShelfTime = java.sql.Date.valueOf(req.getParameter("offShelfTime").trim());
//			} catch (IllegalArgumentException e) {
//				offShelfTime = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入日期時間!");
//			}

			String postTitle = req.getParameter("postTitle").trim();
			if (postTitle == null || postTitle.trim().length() == 0) {
				errorMsgs.add("標題名稱請勿空白");
			}
			
//	圖片
			String savepath = req.getServletContext().getRealPath("/DB-image");
            File imgfolderPath = new File(savepath);
            if (!imgfolderPath.exists()) {
                imgfolderPath.mkdirs();
            }
			 javax.servlet.http.Part part = req.getPart("newsPhoto");
	            String filename = part.getSubmittedFileName();
	            byte[] newsPhoto = null;
	            
	            
	            if (filename != null && !filename.isEmpty()) {
	            	String imgPath = imgfolderPath + "/" + filename;
	            	part.write(imgPath);
	            	newsPhoto = getPictureByteArray(imgPath);
	            	}
	            	else {

	            	   errorMsgs.add("圖片未上傳");
	            	}
//	            if (filename != "") {
//	                String imgPath = imgfolderPath + "/" + filename;
//	                part.write(imgPath);
//	                newsPhoto = getPictureByteArray(imgPath);
//	            }

			LatestNewsVO latestNewsVO = new LatestNewsVO();
			latestNewsVO.setSortId(sortId);
			latestNewsVO.setAdminId(adminId);
			latestNewsVO.setNewsIntro(newsIntro);
			latestNewsVO.setScheduledTime(scheduledTime);
//			latestNewsVO.setOffShelfTime(offShelfTime);
			latestNewsVO.setPostTitle(postTitle);
			latestNewsVO.setNewsPhoto(newsPhoto);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("latestNewsVO", latestNewsVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/addLatestNews.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			LatestNewsService newsSvc = new LatestNewsService();
//			latestNewsVO = newsSvc.addLatestNewsEmp(sortId,  adminId, newsIntro, scheduledTime, offShelfTime, postTitle, newsPhoto);
			latestNewsVO = newsSvc.addLatestNewsEmp(sortId,  adminId, newsIntro, scheduledTime, postTitle, newsPhoto);
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
			newsSvc.deleteLatestNewsEmp(newsId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/admin/news/listAllLatestNews.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
//搜尋
		if("search".equals(action)) {
			Connection con=null;
			String aaa=req.getParameter("select");
			System.out.println(aaa);
		LatestNewsDAO  count= new LatestNewsDAO();
		List<LatestNewsVO> sum =count.findByWords(aaa);
			System.out.println(sum.toString());
			req.setAttribute("Msgs", sum);
			RequestDispatcher successView = req.getRequestDispatcher("/admin/news/XXX.jsp");
			successView.forward(req, res);
			
//			res.setContentType("image/gif");
//			ServletOutputStream out = res.getOutputStream();
//
//			try {
//				Statement stmt = con.createStatement();
//				String id = req.getParameter("id").trim();
//				ResultSet rs = stmt.executeQuery(
//					"Select  NEWS_PHOTO  from  Latest_news  where post_title LIKE ?");
//
//				if (rs.next()) {
//					BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("pic"));
//					byte[] buf = new byte[4 * 1024]; // 4K buffer
//					int len;
//					while ((len = in.read(buf)) != -1) {
//						out.write(buf, 0, len);
//					}
//					in.close();
//				} else {
////					res.sendError(HttpServletResponse.SC_NOT_FOUND);
//					InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
//					byte[] b = new byte[in.available()];
//					in.read(b);
//					out.write(b);
//					in.close();
//
//				}
//				rs.close();
//				stmt.close();
//			} catch (Exception e) {
////				System.out.println(e);
//				InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
//				byte[] b = in.readAllBytes(); // Java 9
//				out.write(b);
//				in.close();
//			}
			}
		}
	}


