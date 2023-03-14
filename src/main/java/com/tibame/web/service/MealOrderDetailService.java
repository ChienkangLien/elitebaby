package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.MealOrderDetailVO;

public interface MealOrderDetailService {
	List<MealOrderDetailVO> getOrderDetail(String authCode);
}
