package com.tibame.web.dao;

import java.util.*;

import com.tibame.web.vo.VisitVO;

public interface VistDAO_interface {
    public void insert(VisitVO visitVO);
    public void update(VisitVO visitVO);
    public void delete(Integer empno);
    public VisitVO findByPrimaryKey(Integer empno);
    public List<VisitVO> getAll();
	
}
