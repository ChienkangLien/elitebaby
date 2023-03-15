package com.tibame.web.dao;

import java.util.List;


import com.tibame.web.vo.NewsMessageVO;

public interface NewsMessageDAO_interface {

	public void insert(NewsMessageVO newsMessageVO);
    public void update(NewsMessageVO newsMessageVO);
    public void delete(Integer newsId);
    public NewsMessageVO findByPrimaryKey(Integer newsMessageId);
    public List<NewsMessageVO> getAll();

}
