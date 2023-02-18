package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.ReportImageVO;

public interface ReportEmailService {

	List<EmailVO> getAllInfo();
	
	int insertEamil(EmailVO emailVO);
	
	String insertPhoto (ReportImageVO reportImg);
}
