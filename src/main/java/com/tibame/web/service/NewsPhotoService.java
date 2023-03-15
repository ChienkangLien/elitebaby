package com.tibame.web.service;

import java.util.List;

import com.tibame.web.dao.NewsPhotoDAO;
import com.tibame.web.dao.NewsPhotoDAO_interface;
import com.tibame.web.vo.NewsPhotoVO;

public class NewsPhotoService {

	private NewsPhotoDAO_interface dao;
	
	public NewsPhotoService() {
		dao=new NewsPhotoDAO();
	}

	public NewsPhotoVO addNewsPhoto(Integer newsId,byte[] newsPhoto) {
		
		NewsPhotoVO newsPhotoVO = new NewsPhotoVO();
		
		newsPhotoVO.setNewsId(newsId); 
		newsPhotoVO.setNewsPhoto(newsPhoto);
		dao.insert(newsPhotoVO);
		
		return newsPhotoVO;
	}
	
	//預留給 Struts 2 或 Spring MVC 用
		public void addNewsPhoto(NewsPhotoVO newsPhotoVO) {
			dao.insert(newsPhotoVO);
		}
		
		public NewsPhotoVO updateNewsPhoto(Integer photoId, Integer newsId,byte[] newsPhoto ) {

			NewsPhotoVO newsPhotoVO = new NewsPhotoVO();

			newsPhotoVO.setPhotoId(photoId);
			newsPhotoVO.setNewsId(newsId);
			newsPhotoVO.setNewsPhoto(newsPhoto);
			
			dao.update(newsPhotoVO);

			return newsPhotoVO;
		}
		
			public void deleteNewsPhoto(Integer photoId) {
			dao.delete(photoId);
		}

		public NewsPhotoVO getOneNewsPhoto(Integer photoId) {
			return dao.findByPrimaryKey(photoId);
		}

		public List<NewsPhotoVO> getAll() {
			return dao.getAll();
		}
	}