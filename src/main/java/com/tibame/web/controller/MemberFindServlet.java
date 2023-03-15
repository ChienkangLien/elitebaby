package com.tibame.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.MemberVO;

@WebServlet("/member/Find")
public class MemberFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberFindServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id;
		
		if(req.getParameter("id") == null) {
			MemberVO memberLogin = (MemberVO) req.getSession().getAttribute("memberVO");
			id = memberLogin.getId();			
		}else {
			id = Integer.parseInt(req.getParameter("id"));
		}

		try {
			MemberServiceImpl service = new MemberServiceImpl();
			MemberVO member = service.findById(id);

			Gson gson = new Gson();
			resp.setContentType("application/json;charset=UTF-8");
			req.setCharacterEncoding("UTF-8");
			resp.getWriter().write(gson.toJson(member));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
