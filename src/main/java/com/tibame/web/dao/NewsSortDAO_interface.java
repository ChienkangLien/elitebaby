package com.tibame.web.dao;

import java.util.List;
import java.util.Set;

import com.tibame.web.vo.LatestNewsVO;
import com.tibame.web.vo.NewsSortVO;

public interface NewsSortDAO_interface {

 		//被很多類別實作 介面擴充性高
     public void insert(NewsSortVO newsSortVo);
     public void update(NewsSortVO newsSortVo);
     public void delete(Integer sortId);
     public NewsSortVO findByPrimaryKey(Integer sortId);
     public List<NewsSortVO> getAll();
     public Set<LatestNewsVO> getLatestNewsBySortId(Integer sortId);
}
