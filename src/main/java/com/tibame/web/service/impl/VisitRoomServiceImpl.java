package com.tibame.web.service.impl;

import java.sql.Date;
import java.util.List;

import com.tibame.web.dao.VistDAO_interface;
import com.tibame.web.dao.impl.VisitDAO;
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.VisitVO;

public class VisitRoomServiceImpl implements VisitRoomService {

	private VistDAO_interface dao;

	public VisitRoomServiceImpl() {
		dao = new VisitDAO();
	}

	@Override
	public String visitReserve(VisitVO visitVO) {

		final String username = visitVO.getUserName();
		if (username == null || username.length() == 0 || username.length() > 100) {
			return "名稱不符規則";
		}

		final String email = visitVO.getEmail();
		if (email == null || email.length() == 0 || email.length() > 100) {
			return "信箱不符規則";
		}

		final String phone = visitVO.getPhoneNumber();
		if (phone == null || phone.length() < 10 || phone.length() > 10) {
			return "電話不符規則";
		}

		final String remark = visitVO.getRemark();
		if (remark.length() >= 300) {
			return "300字以內";
		}

		final String dueDate = visitVO.getStrDueDate();
		if (dueDate == null || dueDate.isEmpty()) {
			return "預產期請選擇";
		}

		final String visitTime = visitVO.getStrVisitTime();
		if (visitTime == null || visitTime.isEmpty()) {
			return "預約日期請選擇";
		}

		final int resultCount = dao.insert(visitVO);
		return resultCount > 0 ? "預約成功" : "預約失敗";

	}

	@Override
	public List<VisitVO> getAllInfo() {
		List<VisitVO> list = dao.getAll();
		return list != null ? list : null;
	}

	@Override
	public VisitVO getOneInfo(Integer visitId) {
		VisitVO visitVO = dao.findByPrimaryKey(visitId);
		return visitVO;
	}

	@Override
	public String oneVisitUpdate(VisitVO visitVO) {

		final Integer visitId = visitVO.getVisitId();
		final Integer userId = visitVO.getUserId();
		if (visitId == null || userId == null) {
			return "無效會員";
		}

		final String userName = visitVO.getUserName();
		if (userName == null || userName.isEmpty()) {
			return "請輸入名字";
		}

		final String email = visitVO.getEmail();
		if (email == null || email.isEmpty()) {
			return "請輸入信箱";
		}

		final String phone = visitVO.getPhoneNumber();
		if (phone == null || phone.isEmpty()) {
			return "請輸入電話";

		}

		final String remark = visitVO.getRemark();
		if (remark.length() > 300) {
			return "備註內容300字以內";
		}

		final String dueDate = visitVO.getStrDueDate();
		if (dueDate == null || dueDate.isEmpty()) {
			return "預產期請選擇";
		}

		final String visitTime = visitVO.getStrVisitTime();
		if (visitTime == null || visitTime.isEmpty()) {
			return "預約日期請選擇";
		}

		return dao.update(visitVO) > 0 ? "更新成功" : "更新失敗";
	}

	@Override
	public String deleteOneVisit(Integer visitId) {

		if (visitId != null) {
			final int countrow = dao.delete(visitId);
			return countrow > 0 ? "刪除成功" : "刪除失敗";
		}
		return "找不到信件";

	}

	@Override
	public List<VisitVO> getOneAll(Integer userId) {

		return userId != null ? dao.getOneAll(userId) : null;

	}

	@Override
	public String checkVisitDate(Date visitTime) {

		if (visitTime != null) {
			List<VisitVO> list = dao.checkVisitDate(visitTime);
			if (list.size() < 2 || list == null) {
				return "可以預約";
			}
			return dao.checkVisitDate(visitTime).size() > 1 ? "當日預約已滿" : "可以預約";
		}
		return "沒收到日期";
	}

	@Override
	public List<VisitVO> getAllInfoPage(Integer offset) {

		return offset != null ? dao.getAllPage(offset) : null;
	}

	@Override
	public List<VisitVO> getAllInfoPageHistory(Integer offset) {

		return offset != null ? dao.getAllPageHistory(offset) : null;
	}

	@Override
	public MemberVO getOneMemberInfo(Integer userId) {
		
		if (userId != null && userId != 0) {
			
			MemberVO memberVO = dao.getMemeberInfo(userId);
			return memberVO != null ? memberVO : null;
			
		}
		return null;
	}

}
