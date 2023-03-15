package com.tibame.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tibame.web.vo.MemberVO;

@WebFilter(urlPatterns = { "/elitebaby/room/order.html","/elitebaby/meal/user_order.html","/elitebaby/visit/VisitRoomFrontGetAll.html","/elitebaby/visit/ReportEmailFrontGetAll.html","/elitebaby/visit/ReportEmailFrontRSMail.html","/elitebaby/visit/ReportEmailFrontInsert.html" })
public class MemFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		if (memberVO == null || memberVO.getId() == null) {
			session.setAttribute("location", req.getRequestURI());
			resp.sendRedirect(req.getContextPath() + "/member/login.html");
			return;

		} else {
			System.out.println(memberVO.getId() + ", " + req.getRequestURI());
			chain.doFilter(req, resp);
		}

	}

}
