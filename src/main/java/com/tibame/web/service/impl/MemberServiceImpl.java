package com.tibame.web.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.tibame.web.dao.MemberDao;
import com.tibame.web.dao.impl.MemberDaoImpl;
import com.tibame.web.service.MemberService;
import com.tibame.web.vo.MemberVO;

public class MemberServiceImpl implements MemberService{
	MemberDao dao = new MemberDaoImpl();

	@Override
	public String register(MemberVO member) {
		final String username = member.getUserName();
		if (username == null || username.length() < 1 || username.length() > 20) {
			return "使用者名稱不合規則";
		}
		final String password = member.getPassword();
		if (password == null || password.length() < 8 ) {
			return "密碼不符合規則";
		}
		final String email = member.getEmail();
		if (email == null ) {
			return "信箱不符合規則";
		}
		
		final String phonenumber = member.getPhoneNumber();
		if (phonenumber == null || phonenumber.length() < 10 || phonenumber.length() >12) {
			return "手機不符合規則";
		}
		
		final int resultCount = dao.insert(member);
		return resultCount > 0 ? "註冊成功" : "註冊失敗";

	}
	
	
	@Override
	public MemberVO login(MemberVO member) {
		final String email = member.getEmail();
		final String password = member.getPassword();
		String secret = encryptPassword(password);
		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			return null;
		}else {
			member.setPassword(secret);
			return dao.find(member);
			
		}

	}
		

	@Override
	public MemberVO update(MemberVO member) {
		member.getId();
		member.getPassword();
		member.getAddress();
		member.getPhoneNumber();
		dao.updateById(member);
		return member;
	}


	@Override
	public MemberVO findById(Integer id) {
		return id != null ? dao.selectById(id) : null;
		
	}

	
	


	@Override
	public MemberVO findById(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<MemberVO> findAll() {
		return dao.selectAll();
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


	@Override
	public MemberVO findByEmail(MemberVO member) {
		String email = member.getEmail();
		return email != null ? dao.selectByEmail(email) : null;
	}
	
	
}
