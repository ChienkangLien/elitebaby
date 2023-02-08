package com.tibame.web.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.web.dao.impl.VistDAO;
import com.tibame.web.vo.VisitVO;

/**
 * Servlet implementation class VisitController
 */
@WebServlet("/VisitController")
public class VisitController extends HttpServlet {
	private static final long serialVersionUID = 1123L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String start = request.getParameter("action"); // 收到的請求是什麼 ex:新增,修改,刪除

		/*************************** --新增資料-- **********************/
		// 來自VisitRoomInsert.jsp的請球
		if ("insert".equals(start)) {

			/*************************** 1.接收請求參數 - 還沒做輸入格式的錯誤處理 **********************/
			VistDAO dao = new VistDAO();
			VisitVO visitVO = new VisitVO();
			Integer userid = Integer.valueOf(request.getParameter("userid"));
			String username = request.getParameter("username");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String contecttime = request.getParameter("contecttime");
			Date duedate = java.sql.Date.valueOf(request.getParameter("duedate"));
			Integer kids = Integer.valueOf(request.getParameter("kids"));
			Date visittime = java.sql.Date.valueOf(request.getParameter("visittime"));
			String remark = request.getParameter("remark");

			visitVO.setUserId(userid);
			visitVO.setUserName(username);
			visitVO.setPhoneNumber(phone);
			visitVO.setEmail(email);
			visitVO.setContectTime(contecttime);
			visitVO.setDueDate(duedate);
			visitVO.setKids(kids);
			visitVO.setVisitTime(visittime);
			visitVO.setRemark(remark);

			/*************************** 2.開始新增資料 ***************************************/

			dao.insert(visitVO);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			request.setAttribute("visitVO", visitVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/visit/VistroomGetAll.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); 
			successView.forward(request, response);

		};

		
		
		
		
		/*************************** --找尋單一資料-- **********************/

		if ("getOne_For_Update".equals(start)) {

			/*************************** 1.接收請求參數 ****************************************/
			Integer visitid = Integer.valueOf(request.getParameter("visitid"));

			/*************************** 2.開始查詢資料 ****************************************/
			VistDAO dao = new VistDAO();
			VisitVO visitVO = dao.findByPrimaryKey(visitid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			request.setAttribute("visitid", visitVO); // 資料庫取出的empVO物件,存入req
			String url = "/visit/VisitRoomUpdate.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(request, response);
		};
 
		
		
		
		
		
		/*************************** --更新單一一筆資料資料-- **********************/
		
		if("update".equals(start)) {
			
			
			/*************************** 1.接收請求參數 - 還沒做輸入格式的錯誤處理 **********************/
			VistDAO dao = new VistDAO();
			VisitVO visitVO = new VisitVO();
			
			Integer userid = Integer.valueOf(request.getParameter("userid"));
			String username = request.getParameter("username");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String contecttime = request.getParameter("contecttime");
			Date duedate = java.sql.Date.valueOf(request.getParameter("duedate"));
			Integer kids = Integer.valueOf(request.getParameter("kids"));
			Date visittime = java.sql.Date.valueOf(request.getParameter("visittime"));
			String remark = request.getParameter("remark");
			Integer visitid = Integer.valueOf(request.getParameter("visitid"));

			visitVO.setUserId(userid);
			visitVO.setUserName(username);
			visitVO.setPhoneNumber(phone);
			visitVO.setEmail(email);
			visitVO.setContectTime(contecttime);
			visitVO.setDueDate(duedate);
			visitVO.setKids(kids);
			visitVO.setVisitTime(visittime);
			visitVO.setRemark(remark);
			visitVO.setVisitId(visitid);

			/*************************** 2.開始更新資料 ***************************************/

			dao.update(visitVO);

			/*************************** 3.更新完成,準備轉交(Send the Success view) ***********/

			request.setAttribute("visitVO", visitVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/visit/VistroomGetAll.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(request, response);
		};
		
		
		
		
		
		
		/*************************** --刪除一筆資料資料-- **********************/
		if("delete".equals(start)) {
		
			/*************************** 1.接收請求參數 ****************************************/
			Integer visitid = Integer.valueOf(request.getParameter("visitid"));

			/*************************** 2.開始查詢資料 ****************************************/
			VistDAO dao = new VistDAO();
			dao.delete(visitid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			String url = "/visit/VistroomGetAll.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(request, response);
	
		};
		
		
	}
}
