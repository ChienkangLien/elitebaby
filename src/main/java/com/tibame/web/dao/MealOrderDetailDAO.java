package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.CartVO;
import com.tibame.web.vo.MealOrderDetailVO;

public interface MealOrderDetailDAO {
	
	public int insert(Integer mealId, Integer count, String authCode);
    public void update(MealOrderDetailVO MealOrderDetailVO);
    public void delete(Integer mealOrderDetailId);
    public MealOrderDetailVO findByPrimaryKey(Integer mealOrderDetailId);
    public List<MealOrderDetailVO> findByAuthCode(String authCode);
    public List<MealOrderDetailVO> findByAuthCodeForOrderDetail(String authCode);
    public List<MealOrderDetailVO> getAll();
}
