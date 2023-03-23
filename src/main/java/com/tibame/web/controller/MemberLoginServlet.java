package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.MemberService;
import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.MemberVO;


@WebServlet("/member/login")
@MultipartConfig
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberLoginServlet() {
    }
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		MemberVO member = gson.fromJson(req.getReader(), MemberVO.class);
		
		MemberService service = new MemberServiceImpl();
		member = service.login(member);
		resp.setContentType("application/json;charset=UTF-8");

		JsonObject jsonObj = new JsonObject();
		if (member == null) {
			jsonObj.addProperty("message", "登入失敗");
			
		} else {
			if (req.getSession(false) != null) {
				req.changeSessionId();
			} // ←產生新的Session ID}
			jsonObj.addProperty("message", "登入成功");
			req.getSession().setAttribute("memberVO", member);
			//昱安
//			Access access = new Access();
//			access.setUserId(member.getId());
//			access.setUserName(member.getUserName());
//			req.getSession().setAttribute("access", access);
			//昱安結束
			System.out.println(req.getSession().getAttribute("memberVO"));
		}
		resp.getWriter().write(jsonObj.toString());

	}
 }
