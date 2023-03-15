package forum.controller;

import forum.pojo.Access;
import forum.service.MsgService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/msg/*")
public class MsgServlet extends BaseServlet {
    private MsgService msgService = new MsgService();


    public void like(HttpServletRequest request, HttpServletResponse response) throws IOException {
        msgService.likeGenerator();
        response.sendRedirect("../forum/home");
    }

    public void likeclean(HttpServletRequest request, HttpServletResponse response) throws IOException {
        msgService.likeClean();
        response.sendRedirect("../forum/home");
    }

    public void likeclick(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Access access = (Access) session.getAttribute("access");
        int userId = access.getUserId();
        int msgId = Integer.parseInt(request.getParameter("msgId"));
        int count = msgService.likeClick(msgId, userId);
        responseJOSN(response, String.valueOf(count));
    }
}
