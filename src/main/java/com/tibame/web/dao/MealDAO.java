package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MealVO;

public interface MealDAO {
	public int insert(MealVO meal);
    public int update(MealVO meal);
    public int delete(Integer id);
    public MealVO findByPrimaryKey(Integer id);
    public List<MealVO> getAll();
    public int getlength();
    public byte[] getpic(Integer mealId);
}
