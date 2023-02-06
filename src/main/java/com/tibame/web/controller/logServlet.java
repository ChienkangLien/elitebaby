package com.tibame.web.controller;

import com.tibame.web.dao.UserDao;
import com.tibame.web.vo.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "logServlet", value = "/logServlet")
public class logServlet extends HttpServlet {
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");

        User user = userDao.login(userName, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60 * 60 * 3);
            session.setAttribute("user", user);
            Cookie cookie = new Cookie("sessionId", session.getId());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            response.sendRedirect("http://localhost:8080/elitebaby/自行設定");
            System.out.println("登入成功");
        } else {
            System.out.println("登入失敗");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
