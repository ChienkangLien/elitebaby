package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.AnswerImageVO;
import com.tibame.web.vo.ReportImageVO;

public interface ReportImageDAO {
	int insertPhoto(ReportImageVO reportImg);

	List<ReportImageVO> getOneAllPhoto(String authCode);

	int inserAnswerPhoto(AnswerImageVO answerImageVO);

	List<AnswerImageVO> getOneAllAnswerPhoto(String authCode);

	int deleteReportImg(String authCode);

	int deleteAnswerImg(String authCode);
	
}
