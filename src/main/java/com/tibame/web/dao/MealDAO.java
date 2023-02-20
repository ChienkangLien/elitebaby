package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MealVO;

public interface MealDAO {
	public int insert(MealVO meal);
    public int update(MealVO meal);
    public int delete(MealVO meal);
    public MealVO findByPrimaryKey(MealVO meal);
    public List<MealVO> getAll();
}
