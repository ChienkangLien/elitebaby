package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MealOrderVO;

public interface MealOrderDAO {

	public int insert(MealOrderVO MealOrderVO);
    public int update(MealOrderVO MealOrderVO);
    public int delete(Integer mealOrderId);
    public List<MealOrderVO> findByUserId(Integer userId);
    public List<MealOrderVO> findByMealOrderId(Integer mealOrderId);
    public List<MealOrderVO> findByMealOrderIdWithUser(Integer userid, Integer orderId);
    public List<MealOrderVO> getAll();
}
