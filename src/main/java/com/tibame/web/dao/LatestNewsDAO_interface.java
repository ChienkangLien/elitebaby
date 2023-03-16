package com.tibame.web.dao;


    import java.util.List;

import com.tibame.web.vo.LatestNewsVO;

    public interface LatestNewsDAO_interface {
    		//被很多類別實作 介面擴充性高
        public void insert(LatestNewsVO latestNewsVo);
        public void update(LatestNewsVO latestNewsVo);
        public void delete(Integer newsId);
        public LatestNewsVO findByPrimaryKey(Integer newsId);
        public LatestNewsVO findByPrimaryKey1(Integer newsId);
        public LatestNewsVO findByPrimaryKey2(Integer newsId);
        public List<LatestNewsVO> getAll();
    }
