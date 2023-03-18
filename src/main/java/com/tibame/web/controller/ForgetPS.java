package com.tibame.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.MemberVO;

import redis.clients.jedis.Jedis;
import com.tibame.web.common.SecretPassword;

@WebServlet("/member/forgetps")
public class ForgetPS extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Jedis jedis = new Jedis("localhost", 6379);
		req.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		MemberVO membervo = gson.fromJson(req.getReader(), MemberVO.class);

		JsonObject errorMsgs = new JsonObject();
		MemberServiceImpl service = new MemberServiceImpl();
		membervo = service.findByEmail(membervo);
		
		if (membervo == null) {
			resp.setContentType("application/json;charset=UTF-8");
			errorMsgs.addProperty("msg", "此帳號不存在");
			resp.getWriter().append(errorMsgs.toString());
		} else {
			

			StringBuilder db = new StringBuilder("Member:").append(membervo.getId());
			String code = returnAuthCode();
			jedis.set(db.toString(), code);
			jedis.expire(db.toString(), 1800);
			
			req.getSession().setAttribute("forgetMemId", membervo.getId());

			String toEmail = membervo.getEmail();
			String subject = "寄送驗證碼";
			String text = "您想要修改密碼，請輸入以下的驗證碼來修改您的密碼 ，" + "驗證碼: " + code;
			
			SendEmail.sendMail(toEmail, subject, text);

			String secretPassword = SecretPassword.encryptPassword(code);
//			System.out.println(secretPassword.length());
			membervo.setPassword(secretPassword);
			service.update(membervo);
			
			resp.sendRedirect("login.html");
		} 

	}
	
	private static String returnAuthCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 8; i++) {
			int condition = (int) (Math.random() * 3) + 1;
			switch (condition) {
			case 1:
				char c1 = (char)((int)(Math.random() * 26) + 65);
				sb.append(c1);
				break;
			case 2:
				char c2 = (char)((int)(Math.random() * 26) + 97);
				sb.append(c2);
				break;
			case 3:
				sb.append((int)(Math.random() * 10));
			}
		}
		return sb.toString();
	}
	

}
