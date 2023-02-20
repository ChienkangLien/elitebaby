package com.tibame.web.service.impl;

import java.util.ArrayList;
import java.util.Base64;
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
	public String insertEamil(EmailVO emailVO) {

		if (emailVO != null) {
			return dao.insert(emailVO) >= 1 ? "文字新增成功" : "文字新增失敗";
		}
		return "沒收到文字";
	}

	@Override
	public String insertPhoto(ReportImageVO reportImg) {

		if (reportImg != null) {
			ReportImageDAO photoDao = new ReportImageDAOImpl();
			int countPhoto = photoDao.insertPhoto(reportImg);
			if (countPhoto >= 1) {
				return "信件全部新增成功";
			}
		}
		return "照片新增失敗";

	}

	@Override
	public EmailVO getOneEmail(Integer mailId) {

		return mailId != null ? dao.findByPrimaryKey(mailId) : null;
	}

	@Override
	public ReportImageVO getOneAllPhoto(String authCode) {
		
		if(authCode!=null) {
			ReportImageDAO photoDao = new ReportImageDAOImpl();
			ReportImageVO reportImageVO = new ReportImageVO();
			List<ReportImageVO> getOneAllPhoto = photoDao.getOneAllPhoto(authCode);
			
			if(getOneAllPhoto!=null) {
				String[] base64Array = new String[3];
				System.out.print(getOneAllPhoto.size());
				for(int i = 0 ; i<getOneAllPhoto.size();i++ ) {
									
					byte[] base64 = getOneAllPhoto.get(i).getReportImage();
					String base64Str = Base64.getMimeEncoder().encodeToString(base64);
					base64Array[i] = base64Str;
					
				}
				reportImageVO.setTestBase64(base64Array);
				return reportImageVO;
			}
			
		}
		return null;
	}
}