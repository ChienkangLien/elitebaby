package com.tibame.web.service.impl;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.tibame.web.dao.EmployeeDao;
import com.tibame.web.dao.impl.EmployeeDaoImpl;
import com.tibame.web.service.EmployeeService;
import com.tibame.web.vo.EmployeeVO;

public class EmployeeServiceImpl implements EmployeeService{
	
	EmployeeDao dao = new EmployeeDaoImpl();


	@Override
	public String register(EmployeeVO employee) {
		final String empname = employee.getEmpname();
		if (empname == null || empname.length() < 1 || empname.length() > 4) {
			return "使用者名稱不符合規則";
		}
		
		final String empaccount = employee.getEmpaccount();
		if (empaccount == null ) {
			return "信箱不符合規則";
		}
		
		final String emppassword = employee.getEmppassword();
		if (emppassword == null || emppassword.length() < 8 ) {
			return "密碼不符合規則";
		}
		
		
		final int resultCount = dao.insert(employee);
		return resultCount > 0 ? "註冊成功" : "註冊失敗";

	}
	
	

	@Override
	public EmployeeVO login(EmployeeVO employee) {
		final String empaccount = employee.getEmpaccount();
		final String emppassword = employee.getEmppassword();
		String secret = encryptPassword(emppassword);
		if (empaccount == null || empaccount.isEmpty() || emppassword == null || emppassword.isEmpty()) {
			return null;
		}else {
			employee.setEmppassword(secret);
			return dao.find(employee);
			
		}

	}
	
	

	@Override
	public EmployeeVO update(EmployeeVO employee) {
		employee.getEmpid();
		employee.getEmppassword();
		employee.getEmppermission();
		dao.updateById(employee);
		return employee;
	}


	@Override
	public EmployeeVO findById(Integer empid) {
		return empid != null ? dao.selectById(empid) : null;
		
	}


	@Override
	public EmployeeVO findById(EmployeeVO employee) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<EmployeeVO> findAll() {
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
}
