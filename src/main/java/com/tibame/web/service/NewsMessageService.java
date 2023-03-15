package com.tibame.web.service;

import java.sql.Timestamp;
import java.util.List;

import com.tibame.web.dao.NewsMessageDAO;
import com.tibame.web.dao.NewsMessageDAO_interface;
import com.tibame.web.vo.NewsMessageVO;

public class NewsMessageService {

	private NewsMessageDAO_interface dao;

	public NewsMessageService() {

		dao = new NewsMessageDAO();
	}

	public NewsMessageVO addNewsMessage(Integer userId, String messageContent, Timestamp contentTime,
			Integer newsId) {

		NewsMessageVO newsMessageVO = new NewsMessageVO();

		newsMessageVO.setUserId(userId);
		newsMessageVO.setMessageContent(messageContent);
		newsMessageVO.setContentTime(contentTime);
		newsMessageVO.setNewsId(newsId);
		dao.insert(newsMessageVO);

		return newsMessageVO;
	}

	
	
	// 預留給 Struts 2 或 Spring MVC 用
	public void addNewsMessage(NewsMessageVO newsMessageVO) {
		dao.insert(newsMessageVO);
	}

	public NewsMessageVO updateNewsMessage(Integer newsMessageId, Integer userId, String messageContent, Timestamp contentTime,
			Integer newsId) {

		NewsMessageVO newsMessageVO = new NewsMessageVO();

		newsMessageVO.setNewsMessageId(newsMessageId);
		newsMessageVO.setUserId(userId);
		newsMessageVO.setMessageContent(messageContent);
		newsMessageVO.setContentTime(contentTime);
		newsMessageVO.setNewsId(newsId);
		dao.update(newsMessageVO);

		return dao.findByPrimaryKey(newsMessageId);
	}

	// 預留給 Struts 2 用的
	public void updateNewsMessage(NewsMessageVO newsMessageVO) {
		dao.update(newsMessageVO);
	}

	public void deleteNewsMessage(Integer newsMessageId) {
		dao.delete(newsMessageId);
	}

	public NewsMessageVO getOneNewsMessage(Integer newsMessageId) {
		return dao.findByPrimaryKey(newsMessageId);
	}

	public List<NewsMessageVO> getAll() {
		return dao.getAll();
	}
//另外新增
//	public void insert(NewsMessageVO message) {
//		dao.insert(message);
//		
//	}
}
