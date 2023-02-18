package com.tibame.web.service.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.tibame.web.dao.EmailDAO;
import com.tibame.web.dao.ReportImageDAO;
import com.tibame.web.dao.impl.EmailDAOImpl;
import com.tibame.web.dao.impl.ReportImageDAOImpl;
import com.tibame.web.service.ReportEmailService;
import com.tibame.web.util.HibernateUtil;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.ReportImageVO;

public class ReportEmailServiceImpl implements ReportEmailService {

	private EmailDAO dao;

	public ReportEmailServiceImpl() {
		dao = new EmailDAOImpl();
	}

	@Override
	public List<EmailVO> getAllInfo() {
		return dao.getAll();
	}

	@Override
	public int insertEamil(EmailVO emailVO) {

		return emailVO != null ? dao.insert(emailVO) : null;
	}

	@Override
	public String insertPhoto(ReportImageVO reportImg) {
		if (reportImg != null ) {
			ReportImageDAO photoDao = new ReportImageDAOImpl();
			int countPhoto = photoDao.insertPhoto(reportImg);
			if (countPhoto >= 1) {
				return "信件新增成功";
			}
		}
		return "信件新增失敗";

	}
}