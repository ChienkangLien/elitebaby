package forum.controller;

import com.alibaba.fastjson.JSON;
import forum.pojo.Access;
import forum.pojo.FormBean;
import forum.pojo.Msg;
import forum.pojo.Post;
import forum.service.PostService;
import forum.service.PublishService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

@MultipartConfig
@WebServlet("/publish/*")
public class PublishServlet extends BaseServlet {
    private PublishService publishService = new PublishService();
    private PostService postService = new PostService();

    public void getForm(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Access access = (Access) session.getAttribute("access");
        int userId = access.getUserId();
        FormBean formBean = publishService.getFormBean(userId);

        String J_formBean = JSON.toJSONString(formBean);
        responseJOSN(response, J_formBean);
    }

    public void publishPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Access access = (Access) session.getAttribute("access");
        int userId = access.getUserId();
        String category = request.getParameter("category");
        String topic = request.getParameter("topic");
        String content = request.getParameter("content");
        Post post = new Post(userId, category, topic, content);
        ArrayList<InputStream> ins = getImageInputStreams(request.getParts());
        publishService.insertPost(post, ins);
        response.sendRedirect("../forum/home");
    }

    public void publishMsg(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Access access = (Access) session.getAttribute("access");
        int userId = access.getUserId();
        int postId = Integer.parseInt(request.getParameter("postId"));
        String content = request.getParameter("content");
        Msg msg = new Msg(postId, userId, content);
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

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //取得參數
        HttpSession session = request.getSession();
        Access access = (Access) session.getAttribute("access");
        int userId = access.getUserId();
        int postId = Integer.parseInt(request.getParameter("postId"));

        if (postService.checkUserAndPostMatch(userId, postId)) {//有編輯權限
            String category = request.getParameter("category");
            String topic = request.getParameter("topic");
            String content = request.getParameter("content");
            Post post = new Post(userId, category, topic, content);
            post.setPostId(postId);
            postService.update(post);
            response.sendRedirect("../forum/home");
        }
        responseJOSN(response, "false");//無編輯權限
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("publish/delete");
        //取得參數
        int postId = Integer.parseInt(request.getParameter("postId"));
            publishService.delete(postId);
            response.sendRedirect("../forum/home");
        }

}





