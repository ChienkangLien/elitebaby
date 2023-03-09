package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MealOrderVO;

public interface MealOrderDAO {

	public int insert(MealOrderVO MealOrderVO);
    public int update(MealOrderVO MealOrderVO);
    public int delete(Integer mealOrderId);
    public MealOrderVO findByPrimaryKey(Integer mealOrderId);
    public List<MealOrderVO> getAll();
}
