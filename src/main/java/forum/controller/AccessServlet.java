package forum.controller;

import forum.pojo.Access;
import forum.service.AccessService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/access/*")
public class AccessServlet extends BaseServlet {
    private AccessService accessService = new AccessService();

    public void access(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        System.out.println("AccessServlet");

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        System.out.println("AccessServlet: "+userName + "," + password);
        Access access = accessService.login(userName, password);
        if (access != null) {
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(60 * 60 * 3);
            session.setAttribute("access", access);
            Cookie cookie = new Cookie("sessionId", session.getId());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            response.sendRedirect("../forum/home");
            System.out.println("登入成功");
        } else {
            System.out.println("登入失敗");
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("accesslogout");
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("../forum/home");
    }
}
