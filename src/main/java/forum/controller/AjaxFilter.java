package forum.controller;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/forum/likeclick","/forum/addCategoryCollect","/msg/*","/publish/*"})
public class AjaxFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if(user != null){
            request.setCharacterEncoding("UTF-8");
            chain.doFilter(request,response);
        }else {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(String.valueOf("\"login\""));
        }
    }
}
