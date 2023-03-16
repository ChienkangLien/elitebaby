package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.MealVO;

public interface MealService {
	List<MealVO> getAllMeal();
	int insertMeal(MealVO meal);
	int updateMeal(MealVO meal);
	MealVO findByPrimaryKey(int id);
	int deleteMeal(int id);
	void addAllMealPicToRedis();
	int getMealLength();
	String getPic(int mealId);
}
