package forum.controller;

import com.alibaba.fastjson.JSON;

import forum.pojo.*;
import forum.service.*;
import login.User;

import javax.servlet.ServletException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

@MultipartConfig
@WebServlet("/publish/*")
public class PublishServlet extends BaseServlet {
    private PublishService publishService = new PublishService();

    public void getForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        FormBean formBean = publishService.getForm(userId);

        String J_formBean = JSON.toJSONString(formBean);
        responseJOSN(response, J_formBean);
    }

    public void publishPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        String category = request.getParameter("category");
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        Post post = new Post(userId, category, topic, content);
        ArrayList<InputStream> ins = getImageInputStreams(request.getParts());
        publishService.insertPost(post, ins);
        response.sendRedirect("http://localhost:8080/elitebaby/forum/home");
    }
    public void publishMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int postId = Integer.parseInt(request.getParameter("postId"));
        String content = request.getParameter("content");
        Msg msg = new Msg(postId,userId,content);
        ArrayList<InputStream> ins = getImageInputStreams(request.getParts());
        System.out.println(ins);

        msg = publishService.insertMsg(msg, ins);
        String s = JSON.toJSONString(msg);
        responseJOSN(response, s);
    }

    public ArrayList<InputStream> getImageInputStreams(Collection<Part> parts) throws IOException {
        ArrayList<InputStream> ins = new ArrayList<>();
        for (Part p : parts) {
            if (!p.getName().equals("image")) {
                continue;
            }
            String fileName = p.getSubmittedFileName();
            if (fileName == null || fileName.isEmpty()) {
                continue;
            }
            ins.add(p.getInputStream());
        }
        return ins;
    }
}





