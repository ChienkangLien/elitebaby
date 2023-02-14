package forum.controller;

import com.alibaba.fastjson.JSON;
import forum.pojo.FormBean;
import forum.pojo.User;
import forum.service.PublishService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/publish/*")
public class PublishServlet extends BaseServlet {
    private PublishService publishService =new PublishService();

    public void getForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        FormBean formBean = publishService.getForm(userId);
//        System.out.println(formBean);
        String J_formBean = JSON.toJSONString(formBean);
        responseJOSN(response, J_formBean);
    }

    public void formTest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("get");
        request.setCharacterEncoding("UTF-8");
        System.out.println(request.getParameter("topic"));
    }
}
