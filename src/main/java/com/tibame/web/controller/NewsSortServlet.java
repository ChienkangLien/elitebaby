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

import com.tibame.web.service.NewsSortService;
import com.tibame.web.vo.NewsSortVO;

@WebServlet("/NewsSort.do")
public class NewsSortServlet extends HttpServlet {

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
			String str = req.getParameter("sortId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入總名稱");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/selectNewsSort.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer sortId = null;
			try {
				sortId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("總類名稱格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/selectNewsSort.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			NewsSortService sortSvc = new NewsSortService();
			NewsSortVO newsSortVO = sortSvc.getOneSort(sortId);
			if (newsSortVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/selectNewsSort.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("newsSortVO", newsSortVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/news/listOneNewsSort.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer sortId = Integer.valueOf(req.getParameter("sortId"));

			/*************************** 2.開始查詢資料 ****************************************/
			NewsSortService sortSvc = new NewsSortService();
			NewsSortVO newsSortVO = sortSvc.getOneSort(sortId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("newsSortVO", newsSortVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/news/updateNewsSort.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer sortId = Integer.valueOf(req.getParameter("sortId").trim());

			String sortName = req.getParameter("sortName");
			String sortNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (sortName == null || sortName.trim().length() == 0) {
				errorMsgs.add("總類名稱: 請勿空白");
			} else if (!sortName.trim().matches(sortNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("總類名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			NewsSortVO newsSortVO = new NewsSortVO();
			newsSortVO.setSortId(sortId);
			newsSortVO.setSortName(sortName);

			// Send the use back to the form, if there were errors

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("newsSortVO", newsSortVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/updateNewsSort.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			NewsSortService sortSvc = new NewsSortService();
			newsSortVO = sortSvc.updateNewsSortEmp(sortId, sortName);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("newsSortVO", newsSortVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/admin/news/listOneNewsSort.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			// 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String sortName = req.getParameter("sortName");
			String sortNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (sortName == null || sortName.trim().length() == 0) {
				errorMsgs.add("種類名稱: 請勿空白");
			} else if (!sortName.trim().matches(sortNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("種類名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			NewsSortVO newsSortVO = new NewsSortVO();
			newsSortVO.setSortName(sortName);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("newsSortVO", newsSortVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/admin/news/addNewsSort.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			NewsSortService sortSvc = new NewsSortService();
			newsSortVO = sortSvc.addNewsSortEmp(sortName);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/admin/news/listAllNewsSort.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer sortId = Integer.valueOf(req.getParameter("sortId"));

			/*************************** 2.開始刪除資料 ***************************************/
			NewsSortService sortSvc = new NewsSortService ();
			 sortSvc.deleteNewsSortEmp(sortId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/admin/news/listAllNewsSort.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}