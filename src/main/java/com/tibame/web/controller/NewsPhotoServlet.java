package com.tibame.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.web.dao.NewsPhotoDAO;
import com.tibame.web.service.NewsPhotoService;
import com.tibame.web.vo.NewsPhotoVO;

@MultipartConfig
@WebServlet(urlPatterns = { "/NewsPhotoServlet", "/NewsPhoto.do" })
public class NewsPhotoServlet extends HttpServlet{

	public static byte[] getPictureByteArray(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        return buffer;
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		int photoId = Integer.parseInt(req.getParameter("photoId"));
		NewsPhotoDAO newsPhotoDAO = new NewsPhotoDAO();

		try {
		    NewsPhotoVO newsPhotoVO = newsPhotoDAO.getphotoById(photoId);
		    res.setContentType("image/png");
		    OutputStream out = res.getOutputStream();
		    out.write(newsPhotoVO.getNewsPhoto());
		    out.close();
		} catch (SQLException | ClassNotFoundException e) {
		    e.printStackTrace();
		}
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
			String str = req.getParameter("photoId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.put("photoId","請輸入照片編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admin/news/selectNewsPhoto.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer photoId = null;
			try {
				photoId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.put("photoId","照片編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admin/news/selectNewsPhoto.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************2.開始查詢資料*****************************************/
			NewsPhotoService  photoSvc = new NewsPhotoService();
			NewsPhotoVO newsPhotoVO = photoSvc.getOneNewsPhoto(photoId);
			if (newsPhotoVO == null) {
				errorMsgs.put("photoId","查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admin/news/selectNewsPhoto.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("newsPhotoVO", newsPhotoVO); // 資料庫取出的empVO物件,存入req
			String url = "/admin/news/listOneNewsPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
	}
	
	
	if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
			/***************************1.接收請求參數****************************************/
			Integer photoId = Integer.valueOf(req.getParameter("photoId"));
			
			/***************************2.開始查詢資料****************************************/
			NewsPhotoService photoSvc = new NewsPhotoService();
			NewsPhotoVO newsPhotoVO = photoSvc.getOneNewsPhoto(photoId);
							
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			String param = "?photoId="  +newsPhotoVO.getPhotoId()+
					       "&newsId="  +newsPhotoVO.getNewsId()+
					       "&newsPhoto="+newsPhotoVO.getNewsPhoto();
					       
			String url = "/admin/news/update_NewsPhoto_input.jsp"+param;
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
	}
	
	
	if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);
	
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			Integer photoId = Integer.valueOf(req.getParameter("photoId").trim());
			Integer newsId = Integer.valueOf(req.getParameter("newsId").trim());
			
			String savepath = req.getServletContext().getRealPath("/DB-image");
            File imgfolderPath = new File(savepath);
            if (!imgfolderPath.exists()) {
                imgfolderPath.mkdirs();
            }
            javax.servlet.http.Part part = req.getPart("newsPhoto");
            String filename = part.getSubmittedFileName();
            byte[] newsPhoto = null;
            if (filename != "") {
                String imgPath = imgfolderPath + "/" + filename;
                part.write(imgPath);
                newsPhoto = getPictureByteArray(imgPath);
            }
//			byte[] newsPhoto = req.getParameter("newsPhoto").trim();
//			if (newsPhoto == null || newsPhoto.trim().length() == 0) {
//				errorMsgs.put("newsPhoto","照片請勿空白");
//			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admin/news/update_NewsPhoto_input.jsp");
				failureView.forward(req, res);
				return; //程式中斷
			}
			
			/***************************2.開始修改資料*****************************************/
			NewsPhotoService photoSvc = new NewsPhotoService();
			NewsPhotoVO newsPhotoVO = photoSvc.updateNewsPhoto(photoId,newsId,newsPhoto);
			
			/***************************3.修改完成,準備轉交(Send the Success view)*************/
			req.setAttribute("newsPhotoVO", newsPhotoVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/admin/news/listOneNewsPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
	}

    if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
		
		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
//		Integer photoId = Integer.valueOf(req.getParameter("photoId").trim());
		Integer newsId = Integer.valueOf(req.getParameter("newsId").trim());
		
			String savepath = req.getServletContext().getRealPath("/DB-image");
            File imgfolderPath = new File(savepath);
            if (!imgfolderPath.exists()) {
                imgfolderPath.mkdirs();
            }
            javax.servlet.http.Part part = req.getPart("newsPhoto");
            String filename = part.getSubmittedFileName();
            byte[] newsPhoto = null;
            if (filename != "") {
                String imgPath = imgfolderPath + "/" + filename;
                part.write(imgPath);
                newsPhoto = getPictureByteArray(imgPath);
            }
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/admin/news/addNewsPhoto.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			NewsPhotoService photoSvc = new NewsPhotoService();
			photoSvc.addNewsPhoto(newsId,newsPhoto);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			String url = "/admin/news/listAllNewsPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);				
	}
	
	
	if ("delete".equals(action)) { // 來自listAllEmp.jsp

		Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
		req.setAttribute("errorMsgs", errorMsgs);

			/***************************1.接收請求參數***************************************/
			Integer photoId = Integer.valueOf(req.getParameter("photoId"));
			
			/***************************2.開始刪除資料***************************************/
			NewsPhotoService photoSvc = new NewsPhotoService();
			photoSvc.deleteNewsPhoto(photoId);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			String url = "/admin/news/listAllNewsPhoto.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
	}
}
}
