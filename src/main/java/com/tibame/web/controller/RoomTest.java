package com.tibame.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.dao.RoomDAO;
import com.tibame.web.dao.impl.RoomDAOImpl;
import com.tibame.web.dao.impl.RoomDAOJNDIImpl;
import com.tibame.web.vo.RoomVO;


@WebServlet("/RoomTest")
public class RoomTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		RoomVO room = gson.fromJson(req.getReader(), RoomVO.class);
//		RoomDAO dao = new RoomDAOImpl(); //原版
		RoomDAO dao = new RoomDAOJNDIImpl(); //JNDI
		
//		room = dao.findByPrimaryKey(room);  //取得一筆資料
//		System.out.println(room);
		
		
		List<RoomVO> list = dao.getAll();  //取得全部資料
		System.out.println(list);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		RoomVO room = gson.fromJson(req.getReader(), RoomVO.class);
//		RoomDAO dao = new RoomDAOImpl(); //原版
		RoomDAO dao = new RoomDAOJNDIImpl(); //JNDI
		int insertRow = dao.insert(room);
		System.out.println(insertRow==1?"新增成功":"新增失敗");
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		RoomVO room = gson.fromJson(req.getReader(), RoomVO.class);
//		RoomDAO dao = new RoomDAOImpl(); //原版
		RoomDAO dao = new RoomDAOJNDIImpl(); //JNDI
		int insertRow = dao.update(room);
		System.out.println(insertRow==1?"修改成功":"修改失敗");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		RoomVO room = gson.fromJson(req.getReader(), RoomVO.class);
//		RoomDAO dao = new RoomDAOImpl(); //原版
		RoomDAO dao = new RoomDAOJNDIImpl(); //JNDI
		int insertRow = dao.delete(room);
		System.out.println(insertRow==1?"刪除成功":"刪除失敗");
	}

}
