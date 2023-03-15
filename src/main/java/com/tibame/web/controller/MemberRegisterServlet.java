package com.tibame.web.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.MemberService;
import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.MemberVO;

@WebServlet("/member/register")
public class MemberRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MemberRegisterServlet() {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		MemberVO member = gson.fromJson(req.getReader(), MemberVO.class);
		MemberService service = new MemberServiceImpl();
		
		String secretPassword =encryptPassword(member.getPassword());
		System.out.println(secretPassword.length());
		member.setPassword(secretPassword);
		
		final String resultStr = service.register(member);

		resp.setContentType("application/json;charset=UTF-8");

		JsonObject respBody = new JsonObject();
		respBody.addProperty("successful", resultStr.equals("註冊成功"));
		respBody.addProperty("message", resultStr);
		resp.getWriter().append(respBody.toString());
	}
	
	private String encryptPassword(String password) {
        String encryptedPassword = null;
        try {
            // 使用SHA-256算法做加密
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            // 將加密後的字節數組轉換成16進制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            encryptedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encryptedPassword;
    }

}
