package com.tibame.web.dao;

import java.util.List;

import com.tibame.web.vo.MealOrderDetailVO;

public interface MealOrdreDetailDAO {
	
	public void insert(MealOrderDetailVO MealOrderDetailVO);
    public void update(MealOrderDetailVO MealOrderDetailVO);
    public void delete(Integer mealOrderDetailId);
    public MealOrderDetailVO findByPrimaryKey(Integer mealOrderDetailId);
    public List<MealOrderDetailVO> getAll();
}
