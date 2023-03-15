package forum.controller;

import forum.service.MsgService;
import forum.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/operation/*")
public class OperationServlet extends BaseServlet {
    private PostService postService = new PostService();
    private MsgService msgService = new MsgService();

//    @Override
//    public void init() throws ServletException {
//        super.init();
//
//    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        postService.PostGenerator();
        postService.likeGenerator();
        msgService.MsgGenerator();
        response.sendRedirect("../forum/home");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        postService.deleteAll();
        response.sendRedirect("../forum/home");
    }
    public void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.PostGenerator();
        response.sendRedirect("../forum/home");
    }
    public void postlike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.likeGenerator();
        response.sendRedirect("../forum/home");
    }

    public void likeclean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.likeClean();
        response.sendRedirect("../forum/home");
    }

    public void msg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msgService.MsgGenerator();
        response.sendRedirect("../forum/home");
    }



}
