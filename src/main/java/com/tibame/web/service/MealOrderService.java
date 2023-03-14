package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.MealOrderVO;

public interface MealOrderService {
	List<MealOrderVO> getAllMeal();
	int insertMeal(MealOrderVO meal);
	int updateMeal(MealOrderVO meal);
	List<MealOrderVO> findByPrimaryKey(int id);
	List<MealOrderVO> findByMealOrder(int id);
	int deleteMeal(int id);
}
