package com.tibame.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tibame.web.vo.EmployeeVO;

public class EmpFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		HttpSession session = req.getSession();
		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
		if (employeeVO == null || employeeVO.getEmpid() == null) {
			session.setAttribute("location", req.getRequestURI());
			resp.sendRedirect(req.getContextPath() + "/employee/login.html");
			return;

		} else {
			System.out.println(employeeVO.getEmpid() + ", " + req.getRequestURI());
			chain.doFilter(req, resp);
		}

	}

}
