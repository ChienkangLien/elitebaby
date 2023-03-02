package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.MemberVO;

@WebServlet("/Member/Find")
public class MemberFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberFindServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	final String id = req.getParameter("id");
    	try {
			MemberServiceImpl service = new MemberServiceImpl();
			MemberVO member = service.findById(Integer.parseInt(id));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    
    
    }
}
