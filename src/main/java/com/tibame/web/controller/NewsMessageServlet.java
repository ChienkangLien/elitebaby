package com.tibame.web.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.web.dao.LatestNewsDAO;
import com.tibame.web.dao.NewsMessageDAO;
import com.tibame.web.service.NewsMessageService;
import com.tibame.web.vo.LatestNewsVO;
import com.tibame.web.vo.NewsMessageVO;
@WebServlet("/NewsMessage.do")
public class NewsMessageServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("newsMessageId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.put("newsMessageId","請輸入消息留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/news/selectNewsMessage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer newsMessageId = null;
				try {
					newsMessageId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.put("newsMessageId","消息留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/news/selectNewsMessage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				NewsMessageService  messageSvc = new NewsMessageService();
				NewsMessageVO newsMessageVO = messageSvc.getOneNewsMessage(newsMessageId);
				if (newsMessageVO == null) {
					errorMsgs.put("newsMessageId","查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/news/selectNewsMessage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("newsMessageVO", newsMessageVO); // 資料庫取出的empVO物件,存入req
				String url = "/admin/news/listOneNewsMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer newsMessageId= Integer.valueOf(req.getParameter("newsMessageId"));
				
				/***************************2.開始查詢資料****************************************/
				NewsMessageService messageSvc = new NewsMessageService();
				NewsMessageVO newsMessageVO = messageSvc.getOneNewsMessage(newsMessageId);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				String param = "?newsMessageId="  +newsMessageVO.getNewsMessageId()+
						       "&userId="  +newsMessageVO.getUserId()+
						       "&messageContent="+newsMessageVO.getMessageContent()+
						       "&contentTime="+newsMessageVO.getContentTime()+
						       "&newsId="+newsMessageVO.getNewsId();
						       
				String url = "/admin/news/updateNewsMessage.jsp"+param;
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer newsMessageId = Integer.valueOf(req.getParameter("newsMessageId").trim());
				Integer userId = Integer.valueOf(req.getParameter("userId").trim());
				
				String messageContent = req.getParameter("messageContent");
				String messageContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (messageContent == null || messageContent.trim().length() == 0) {
					errorMsgs.put("messageContent","留言內容: 請勿空白");
				} else if(!messageContent.trim().matches(messageContentReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("messageContent","留言內容: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				java.sql.Timestamp contentTime = null;
				try {
					contentTime = java.sql.Timestamp.valueOf(req.getParameter("contentTime").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.put("contentTime","請輸入時間");
				}
				
				Integer newsId = Integer.valueOf(req.getParameter("newsId").trim());
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/admin/news/updateNewsMessage.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				NewsMessageService messageSvc = new NewsMessageService();
				NewsMessageVO newsMessageVO = messageSvc.updateNewsMessage(newsMessageId, userId,messageContent, contentTime, newsId);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("newsMessageVO", newsMessageVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/admin/news/listOneNewsMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			
			String messageContent = req.getParameter("messageContent");
				String messageContentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (messageContent == null ||messageContent.trim().length() == 0) {
					errorMsgs.put("messageContent","留言內容: 請勿空白");
				} else if(!messageContent.trim().matches(messageContentReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.put("messageContent","留言內容: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
								
				java.sql.Timestamp contentTime = null;
				try {
					LocalDateTime now = LocalDateTime.now();
			        contentTime = Timestamp.valueOf(now);
				} catch (IllegalArgumentException e) {
					errorMsgs.put("contentTime","請輸入日期時間");
				}
								
				Integer newsId = Integer.valueOf(req.getParameter("newsId").trim());

				/***************************2.開始新增資料***************************************/
				NewsMessageService messageSvc = new NewsMessageService();
				messageSvc.addNewsMessage(userId, messageContent, contentTime, newsId);
				
				LatestNewsDAO count = new LatestNewsDAO();
				LatestNewsVO sum = count.findByPrimaryKey1(newsId);
				NewsMessageDAO aaa= new NewsMessageDAO();
				List<NewsMessageVO>bbb=aaa.getMessage(newsId);
				System.err.println(sum.toString());
				req.setAttribute("Msgs1", bbb);
				req.setAttribute("Msgs", sum);
				RequestDispatcher successView = req.getRequestDispatcher("/news/front_Onenews.jsp");
				successView.forward(req, res);
        }
        
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer newsMessageId = Integer.valueOf(req.getParameter("newsMessageId"));
				
				/***************************2.開始刪除資料***************************************/
				NewsMessageService messageSvc = new NewsMessageService();
				messageSvc.deleteNewsMessage(newsMessageId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/admin/news/listAllNewsMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
//	if ("FindBynewsId".equals(action)) { // 來自listAllEmp.jsp
//			System.out.println("---------------------------");
//			req.getParameter("comment_input");
//			String context=req.getParameter("comment_input");
//			NewsMessageDAO news= new NewsMessageDAO();
//			news.insert(context);
//		}
	}

}