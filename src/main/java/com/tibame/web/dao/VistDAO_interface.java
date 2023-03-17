package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.VisitVO;

public interface VistDAO_interface {
	
	public int insert(VisitVO visitVO);

	public int update(VisitVO visitVO);

	public int delete(Integer empno);

	public VisitVO findByPrimaryKey(Integer empno);
	
	MemberVO getMemeberInfo (Integer userId);

	public List<VisitVO> getAll();

	List<VisitVO> getOneAll(Integer userId);

	List<VisitVO> checkVisitDate(java.sql.Date visitTime);

	List<VisitVO> getAllPage(Integer offset);

	List<VisitVO> getAllPageHistory(Integer offset);


}
