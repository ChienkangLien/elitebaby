package com.tibame.web.dao;

import java.util.*;

import com.tibame.web.vo.VisitVO;

public interface VistDAO_interface {
    public int insert(VisitVO visitVO);
    public int update(VisitVO visitVO);
    public int delete(Integer empno);
    public VisitVO findByPrimaryKey(Integer empno);
    public List<VisitVO> getAll();
    List<VisitVO> getOneAll(Integer userId);
	
}
