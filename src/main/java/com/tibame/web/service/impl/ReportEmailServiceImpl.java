package com.tibame.web.service.impl;

import java.util.Base64;
import java.util.List;

import com.tibame.web.dao.EmailDAO;
import com.tibame.web.dao.ReportImageDAO;
import com.tibame.web.dao.impl.EmailDAOImpl;
import com.tibame.web.dao.impl.ReportImageDAOImpl;
import com.tibame.web.service.ReportEmailService;
import com.tibame.web.vo.AnswerImageVO;
import com.tibame.web.vo.EmailDTO;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.ReportImageVO;

public class ReportEmailServiceImpl implements ReportEmailService {

	private EmailDAO dao;

	public ReportEmailServiceImpl() {
		dao = new EmailDAOImpl();
	}

	@Override
	public List<EmailVO> getAllInfo(Integer offset) {
		return offset != null ? dao.getAll(offset) : null;
	}

	@Override
	public String insertEamil(EmailVO emailVO) {

		if (emailVO != null) {
			return dao.insert(emailVO) >= 1 ? "文字新增成功" : "文字新增失敗";
		}
		return "沒收到文字";
	}

	@Override
	public String insertPhoto(ReportImageVO reportImg) {

		if (reportImg != null) {
			ReportImageDAO photoDao = new ReportImageDAOImpl();
			int countPhoto = photoDao.insertPhoto(reportImg);
			if (countPhoto >= 1) {
				return "信件全部新增成功";
			}
		}
		return "照片新增失敗";

	}

	@Override
	public EmailVO getOneEmail(Integer mailId) {

		return mailId != null ? dao.findByPrimaryKey(mailId) : null;
	}

	@Override
	public ReportImageVO getOneAllPhoto(String authCode) {

		if (authCode != null) {
			ReportImageDAO photoDao = new ReportImageDAOImpl();
			ReportImageVO reportImageVO = new ReportImageVO();
			List<ReportImageVO> getOneAllPhoto = photoDao.getOneAllPhoto(authCode);

			if (getOneAllPhoto != null) {
				String[] base64Array = new String[3];
				for (int i = 0; i < getOneAllPhoto.size(); i++) {

					byte[] base64 = getOneAllPhoto.get(i).getReportImage();
					String base64Str = Base64.getMimeEncoder().encodeToString(base64);
					base64Array[i] = base64Str;

				}
				reportImageVO.setTestBase64(base64Array);
				return reportImageVO;
			}

		}
		return null;
	}

	@Override
	public String getOneAnswer(EmailVO emailVO) {

		return dao.update(emailVO) >= 1 ? "回覆成功" : "回覆失敗";
	}

	@Override
	public String insertAnswerPhoto(AnswerImageVO answerImageVO) {
		if (answerImageVO != null) {
			ReportImageDAO photoDao = new ReportImageDAOImpl();
			int countPhoto = photoDao.inserAnswerPhoto(answerImageVO);
			if (countPhoto > 0) {
				return "回覆全部新增成功";
			}
		}
		return "照片新增失敗";
	}

	@Override
	public AnswerImageVO getAllAnswerPhoto(String authCode) {

		if (authCode != null) {
			ReportImageDAO photoDao = new ReportImageDAOImpl();
			AnswerImageVO answerImageVO = new AnswerImageVO();
			List<AnswerImageVO> getOneAllPhoto = photoDao.getOneAllAnswerPhoto(authCode);

			if (getOneAllPhoto != null) {
				String[] base64Array = new String[5];
				for (int i = 0; i < getOneAllPhoto.size(); i++) {

					byte[] base64 = getOneAllPhoto.get(i).getAnswerImage();
					String base64Str = Base64.getMimeEncoder().encodeToString(base64);
					base64Array[i] = base64Str;

				}
				answerImageVO.setTestBase64(base64Array);
				return answerImageVO;
			}

		}
		return null;
	}

	@Override
	public List<EmailVO> getAllByUserId(Integer userId) {

		return userId != null ? dao.findByUserId(userId) : null;
	}

	@Override
	public List<EmailVO> getAllCount() {

		return dao.getAllCount();
	}

	@Override
	public String insertEamilFromBack(EmailVO emailVO) {

		if (emailVO != null) {
			return dao.insertFromBack(emailVO) >= 1 ? "文字新增成功" : "文字新增失敗";
		}
		return "沒收到文字";
	}

	@Override
	public String getOneUserAnswer(EmailVO emailVO) {

		return dao.updateFromUser(emailVO) >= 1 ? "回覆成功" : "回覆失敗";
	}

	@Override
	public List<EmailVO> getAllInfoByAdmin(Integer offset) {

		return offset != null ? dao.getAllByAdmin(offset) : null;
	}

	@Override
	public List<MemberVO> getAllMemberInfo() {
		List<MemberVO> list = dao.getAllMember();
		return list != null ? list : null;
	}

	@Override
	public List<EmailVO> serchInfoMember(EmailDTO dto) {

		return dto != null ? dao.serchInfo(dto) : null;

	}

	@Override
	public List<EmailVO> serchInfoAdmin(EmailDTO dto) {

		return dto != null ? dao.serchInfoAdmin(dto) : null;
	}

	@Override
	public List<EmailVO> getAllByUserIdMember(Integer userId) {

		return userId != null ? dao.findByUserIdMember(userId) : null;
	}

	@Override
	public String deleteAllEmailData(Integer mailId, String authCode) {

		if (mailId != null && mailId != 0 && authCode != null && !authCode.isEmpty()) {

			ReportImageDAO photoDao = new ReportImageDAOImpl();

			int test1 = photoDao.deleteAnswerImg(authCode);

			int test2 = photoDao.deleteReportImg(authCode);

			if (test1 + test2 >= 0) {
				return dao.delete(mailId) > 0 ? "刪除成功" : "請確認信件是否存在";
			} else {
				return null;
			}

		}
		return null;
	}

}