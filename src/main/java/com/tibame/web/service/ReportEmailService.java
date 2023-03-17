package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.AnswerImageVO;
import com.tibame.web.vo.EmailDTO;
import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.MemberVO;
import com.tibame.web.vo.ReportImageVO;

public interface ReportEmailService {

	List<EmailVO> getAllInfo(Integer offset);

	List<EmailVO> getAllInfoByAdmin(Integer offset);

	String insertEamil(EmailVO emailVO);

	String insertPhoto(ReportImageVO reportImg);

	EmailVO getOneEmail(Integer mailId);

	ReportImageVO getOneAllPhoto(String authCode);

	String getOneAnswer(EmailVO emailVO);

	String getOneUserAnswer(EmailVO emailVO);

	String insertAnswerPhoto(AnswerImageVO answerImageVO);

	AnswerImageVO getAllAnswerPhoto(String authCode);

	List<EmailVO> getAllByUserId(Integer userId);

	List<EmailVO> getAllByUserIdMember(Integer userId);

	List<EmailVO> getAllCount();

	String insertEamilFromBack(EmailVO emailVO);

	List<MemberVO> getAllMemberInfo();

	public List<EmailVO> serchInfoMember(EmailDTO dto);

	public List<EmailVO> serchInfoAdmin(EmailDTO dto);

	String deleteAllEmailData(Integer mailId, String authCode);

}
