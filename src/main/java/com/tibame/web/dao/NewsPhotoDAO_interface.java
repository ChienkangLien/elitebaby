package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.NewsPhotoVO;

public interface NewsPhotoDAO_interface {

	public void insert(NewsPhotoVO newsPhotoVO);

	public void update(NewsPhotoVO newsPhotoVO);

	public void delete(Integer photoID);

	public NewsPhotoVO findByPrimaryKey(Integer PhotoID);

	public List<NewsPhotoVO> getAll();
}
