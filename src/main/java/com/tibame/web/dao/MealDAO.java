package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MealVO;

public interface MealDAO {
	public int insert(MealVO MealVO);
    public void update(MealVO MealVO);
    public void delete(Integer mealId);
    public MealVO findByPrimaryKey(Integer mealId);
    public List<MealVO> getAll();
}
