package com.tibame.web.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tibame.web.vo.MemberVO;

@WebFilter(urlPatterns = { "/room/order.html","/meal/user_order.html","/visit/VisitRoomFrontGetAll.html","/visit/ReportEmailFrontGetAll.html","/visit/ReportEmailFrontRSMail.html","/visit/ReportEmailFrontInsert.html","/visit/VisitRoomFrontInsert.html", "/meal/cart.html"})
public class MemFilter extends HttpFilter{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

	if(req.getRequestURI()=="/elitebaby/member/login.html") {
	System.out.println("req.getRequestURI()=="+req.getRequestURI());
	chain.doFilter(req, res);
	}
		
		
		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if (memberVO == null || memberVO.getId() == null) {
			session.setAttribute("location", req.getRequestURI());
			res.sendRedirect(req.getContextPath() + "/member/login.html");
			return;

		} else {
			System.out.println(memberVO.getId() + ", " + req.getRequestURI());
			chain.doFilter(req, res);
		}

		super.doFilter(req, res, chain);
	}

}
