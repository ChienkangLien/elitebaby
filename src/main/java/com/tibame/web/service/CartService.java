package com.tibame.web.service;

import java.util.List;
import java.util.Set;

import com.tibame.web.vo.CartVO;
import com.tibame.web.vo.MealVO;

public interface CartService {
	List<MealVO> getAllMeal(int id);
	int insertMeal(CartVO meal);
	int updateMeal(CartVO meal);
	Set<String> findByPrimaryKey(int id);
	int deleteMeal(CartVO cart);
}
