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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.tibame.web.common.SecretPassword;
import com.tibame.web.service.MemberService;
import com.tibame.web.service.impl.MemberServiceImpl;
import com.tibame.web.vo.MemberVO;

@WebServlet("/member/change")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	public MemberUpdateServlet() {
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		MemberVO member = gson.fromJson(req.getReader(), MemberVO.class);
		resp.setContentType("application/json;charset=UTF-8");
		JsonObject jsonObj = new JsonObject();

		String secretPassword = SecretPassword.encryptPassword(member.getPassword());
//		System.out.println(secretPassword.length());
		member.setPassword(secretPassword);

		Integer id;
		
		if(req.getParameter("id") == null) {
			MemberVO memberLogin = (MemberVO) req.getSession().getAttribute("memberVO");
			id = memberLogin.getId();			
		}else {
			id = Integer.parseInt(req.getParameter("id"));
		}
		
		if (id == 0) {
			jsonObj.addProperty("message", "修改失敗");
		} else {
			MemberService service = new MemberServiceImpl();
			member.setId(id);
			service.update(member);
			jsonObj.addProperty("message", "修改成功");
		}
		resp.getWriter().append(jsonObj.toString());
	}
	
//	private String encryptPassword(String password) {
//        String encryptedPassword = null;
//        try {
//            // 使用SHA-256算法做加密
//            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//            byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
//            // 將加密後的字節數組轉換成16進制字符串
//            StringBuilder sb = new StringBuilder();
//            for (byte b : bytes) {
//                sb.append(String.format("%02x", b));
//            }
//            encryptedPassword = sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return encryptedPassword;
//    }

}
