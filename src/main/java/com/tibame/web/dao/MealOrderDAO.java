package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MealOrderVO;

public interface MealOrderDAO {

	public void insert(MealOrderVO MealOrderVO);
    public void update(MealOrderVO MealOrderVO);
    public void delete(Integer mealOrderId);
    public MealOrderVO findByPrimaryKey(Integer mealOrderId);
    public List<MealOrderVO> getAll();
}
