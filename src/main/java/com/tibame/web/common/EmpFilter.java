package com.tibame.web.common;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tibame.web.vo.EmployeeVO;

@WebFilter({
	"/member/background_nav.html",
	"/admin/forum/*",
	"/admin/meal/*",
	"/admin/member/management.html",
	"/admin/member/search.html",
	"/admin/news/*",
	"/admin/room/*",
	"/admin/visit/*"
})
public class EmpFilter extends HttpFilter {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("進入濾器");

		if (req.getRequestURI().equals("/elitebaby/admin/member/emplogin.html")) {
			System.out.println("req.getRequestURI()==" + req.getRequestURI());
			chain.doFilter(req, resp);
		}

		HttpSession session = req.getSession();
		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
		System.out.println("employeeVO=" + employeeVO);
		if (employeeVO == null || employeeVO.getEmpid() == null) {
			session.setAttribute("location", req.getRequestURI());
			System.out.println("req.getRequestURI():" + req.getRequestURI());
			System.out.println("req.getContextPath():" + req.getContextPath());
			resp.sendRedirect(req.getContextPath() + "/admin/member/emplogin.html");
			return;

		} else {
			System.out.println(employeeVO.getEmpid() + ", " + req.getRequestURI());
			chain.doFilter(req, resp);
		}
	}
}
