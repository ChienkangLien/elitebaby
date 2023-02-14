package com.tibame.web.service.impl;

import com.tibame.web.dao.impl.VisitDAO;

import java.util.List;

import com.tibame.web.dao.VistDAO_interface;
import com.tibame.web.service.VisitRoomService;
import com.tibame.web.vo.VisitVO;

public class VisitRoomServiceImpl implements VisitRoomService {

	private VistDAO_interface dao;

	public VisitRoomServiceImpl() {
		dao = new VisitDAO();
	}

	@Override
	public String visitReserve(VisitVO visitVO) {

		final String username = visitVO.getUserName();
		if (username == null || username.length() < 2 || username.length() > 50) {
			return "名稱不符規則";
		}

		final String email = visitVO.getEmail();
		if (email == null || email.length() < 6 || email.length() > 20) {
			return "信箱不符規則";
		}

		final String phone = visitVO.getPhoneNumber();
		if (phone == null || phone.length() < 1 || phone.length() > 10) {
			return "電話不符規則";
		}

		final String remark = visitVO.getRemark();
		if (remark.length() > 300) {
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
		return list;
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

}
