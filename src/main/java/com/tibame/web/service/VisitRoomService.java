package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.VisitVO;

public interface VisitRoomService {
	
	String visitReserve(VisitVO visitVO);

	List<VisitVO> getAllInfo();

	VisitVO getOneInfo(Integer visitId);
	
	String oneVisitUpdate(VisitVO visitVO);
	
	String deleteOneVisit(Integer visitId);
	
	List<VisitVO> getOneAll(Integer userId);
	
	String checkVisitDate(java.sql.Date visitTime );
	
	List<VisitVO> getAllInfoPage(Integer offset);
	
	List<VisitVO> getAllInfoPageHistory(Integer offset);
	
	MemberVO  getOneMemberInfo(Integer userId);
	
}
