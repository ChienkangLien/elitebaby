package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.EmailVO;
import com.tibame.web.vo.VisitVO;

public interface EmailDAO {

	public int insert(EmailVO emailVO);

	public int update(EmailVO emailVO);
	
	public int updateFromUser(EmailVO emailVO);

	public int delete(Integer mailId);

	public EmailVO findByPrimaryKey(Integer mailId);

	public List<EmailVO> getAll(Integer offset);
	
	public List<EmailVO> getAllByAdmin(Integer offset);

	public List<EmailVO> findByUserId(Integer userId);

	public List<EmailVO> getAllCount();

	public List<EmailVO> getAllFromback(Integer offset);

	public int insertFromBack(EmailVO emailVO);
}
