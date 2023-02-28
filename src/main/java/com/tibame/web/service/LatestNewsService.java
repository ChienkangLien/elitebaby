package com.tibame.web.service;

import java.sql.Date;
import java.util.List;

import com.tibame.web.dao.LatestNewsDAO;
import com.tibame.web.dao.LatestNewsDAO_interface;
import com.tibame.web.dao.impl.LatestNewsJDBCDAO;
import com.tibame.web.vo.LatestNewsVO;

public class LatestNewsService {
	
	
	private LatestNewsDAO_interface dao;

	public LatestNewsService() {
		dao= new LatestNewsDAO();
	}
	
	public LatestNewsVO addLatestNewsEmp(Integer sortId, Integer adminId,String newsIntro,
			java.sql.Date  publishedTime,java.sql.Date onNews, java.sql.Date offNews, String postTitle) {

		LatestNewsVO latestNewsVO = new LatestNewsVO();

		latestNewsVO.setSortId(sortId);
		latestNewsVO.setAdminId(adminId);
		latestNewsVO.setNewsIntro(newsIntro);
		latestNewsVO.setPublishedTime(publishedTime);
		latestNewsVO.setOnNews(onNews);
		latestNewsVO.setOffNews(offNews);
		latestNewsVO.setPostTitle(postTitle);
		dao.insert(latestNewsVO);

		return latestNewsVO;
	}

	public LatestNewsVO updateLatestNewsEmp(Integer newsId, Integer sortId, Integer adminId,String newsIntro,
			java.sql.Date  publishedTime,java.sql.Date onNews, java.sql.Date offNews, String postTitle) {

		LatestNewsVO latestNewsVO = new LatestNewsVO();
		
		latestNewsVO.setNewsId(newsId);
		latestNewsVO.setSortId(sortId);
		latestNewsVO.setAdminId(adminId);
		latestNewsVO.setNewsIntro(newsIntro);
		latestNewsVO.setPublishedTime(publishedTime);
		latestNewsVO.setOnNews(onNews);
		latestNewsVO.setOffNews(offNews);
		latestNewsVO.setPostTitle(postTitle);
		dao.update(latestNewsVO);

		return latestNewsVO;
	}

//	public void updateEmp(LatestNewsVO latestNewsVO) {
//		dao.update(latestNewsVO);
//	}
	public void deleteLatestNewsEmp(Integer newsId) {
		dao.delete(newsId);
	}

	public LatestNewsVO getOneLatestNews(Integer newsId) {
		return dao.findByPrimaryKey(newsId);
	}

	public List<LatestNewsVO> getAll() {
		return dao.getAll();
	}

}
