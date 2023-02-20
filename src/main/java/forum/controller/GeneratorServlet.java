package forum.controller;

import forum.service.CategoryService;
import forum.service.MsgService;
import forum.service.PostService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/generator/*")
public class GeneratorServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();
    private PostService postService = new PostService();
    private MsgService msgService = new MsgService();

    @Override
    public void init() throws ServletException {
        super.init();
        postService.PostGenerator();
        postService.likeGenerator();
        msgService.MsgGenerator();
    }

    public void HelloWorld(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }
    public void post(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.PostGenerator();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }
    public void postlike(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.likeGenerator();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void likeclean(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        postService.likeClean();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }

    public void msg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        msgService.MsgGenerator();
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }



}
