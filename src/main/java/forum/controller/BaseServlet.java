package forum.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/Base")
public class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        System.out.println(this.getClass() + " - uri:" + uri);
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);
//        System.out.println(this.getClass() + " - methodName:" + methodName);
        Class aclass = this.getClass();
        try {
            Method method = aclass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    protected void responseJOSN(HttpServletResponse response, String JOSN)  {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write(JOSN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
