package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.MemberService;
import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.MemberVO;


@WebServlet("/member/register")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberRegisterServlet() {
    	
    }

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		MemberVO member = gson.fromJson(req.getReader(), MemberVO.class);
		MemberService service = new MemberServiceImpl();
		final String resultStr = service.register(member);
		
//			System.out.println(resultStr);
		
		resp.setContentType("application/json;charset=UTF-8");
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successful", resultStr.equals("註冊成功"));
		respBody.addProperty("message", resultStr);
		resp.getWriter().append(respBody.toString());
	}

}

