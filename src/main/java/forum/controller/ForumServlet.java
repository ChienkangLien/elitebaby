package forum.controller;

import com.alibaba.fastjson.JSON;
import forum.pojo.Category;
import forum.pojo.Post;
import forum.pojo.PostBean;
import forum.service.CategoryService;
import forum.service.MsgService;
import forum.service.PostService;
import login.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.ArrayList;

@WebServlet("/forum/*")
public class ForumServlet extends BaseServlet {
    private CategoryService categoryService = new CategoryService();
    private PostService postService = new PostService();
    private MsgService msgService = new MsgService();

    public void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //取得參數
        request.setCharacterEncoding("UTF-8");
        boolean order = Boolean.parseBoolean(request.getParameter("order"));//null=false
        String swi = request.getParameter("switch");
        if (swi != null) {
            order = !order;
        }
        String categoryId = request.getParameter("categoryId");
        String topic = request.getParameter("topic");
//        System.out.println("order:" + order + ", categoryId:" + categoryId + ", topic:" + topic);
        //呼叫+封裝
        ArrayList<Post> posts = postService.getAll(order, categoryId, topic);
        request.setAttribute("posts", posts);

        ArrayList<ArrayList<Category>> Cs = categoryService.getAll();
        request.setAttribute("LCs", Cs.get(0));
        request.setAttribute("HCs", Cs.get(1));

        request.setAttribute("categoryId", categoryId);
        request.setAttribute("order", order);
        request.setAttribute("topic", topic);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int userId = user.getUserId();
            ArrayList<Category> collectedCategories = categoryService.getCollectedCategories(userId);
            request.setAttribute("CCs", collectedCategories);
        }
        //傳送
        request.getRequestDispatcher("/myForum/forum.jsp").forward(request, response);
    }

    //文章按讚
    public void likeclick(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int postId = Integer.parseInt(request.getParameter("postId"));
        int count = postService.likeClick(postId, userId);
        responseJOSN(response, String.valueOf(count));
    }


    //獲取USER已加入的收藏話題
    public void collectedCategories(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        ArrayList<Category> collectedCategories = categoryService.getCollectedCategories(userId);
        String J_categories = JSON.toJSONString(collectedCategories);
        responseJOSN(response, J_categories);
    }

    //加入收藏話題
    public void addCategoryCollect(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        boolean exist = categoryService.collect(userId, categoryId);
        responseJOSN(response, String.valueOf(exist));
    }

    //獲得文章及留言串
    public void postBean(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int postId = Integer.parseInt(request.getParameter("postId"));
        PostBean postBean = postService.getPostBean(postId);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        if (user != null) {
            int userId = user.getUserId();
            System.out.println(userId);
            postBean.setUserId(userId);
        }
        String s = JSON.toJSONString(postBean);
        responseJOSN(response, s);
    }

}
