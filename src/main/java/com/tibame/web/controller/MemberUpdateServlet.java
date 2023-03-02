package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tibame.web.service.MemberService;
import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.MemberVO;

@WebServlet("/member/change")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateServlet() {
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		MemberVO member = gson.fromJson(req.getReader(), MemberVO.class);
		resp.setContentType("application/json;charset=UTF-8");
		JsonObject jsonObj = new JsonObject();
//			Member seMember = (Member) req.getSession().getAttribute("memId");
//		Member seMember = new Member();
//		seMember.setId(9);
		
		int id = (int) req.getSession().getAttribute("memId");
		if(id == 0) {
			jsonObj.addProperty("message", "修改失敗");
		}else {
			MemberService service = new MemberServiceImpl();
			member.setId(id);
			service.update(member);
			jsonObj.addProperty("message", "修改成功");
		}
		resp.getWriter().append(jsonObj.toString());
	}  
}
