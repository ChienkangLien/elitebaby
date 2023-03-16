package com.tibame.web.service;

import java.util.List;

import com.tibame.web.vo.MealOrderVO;

public interface MealOrderService {
	List<MealOrderVO> getAllMeal();
	int insertMeal(MealOrderVO meal);
	int updateMeal(MealOrderVO meal);
	int updateMealWithAddress(MealOrderVO meal);
	List<MealOrderVO> findByPrimaryKey(Integer id);
	List<MealOrderVO> findByMealOrder(Integer id);
	List<MealOrderVO> findByMealOrderwithuser(Integer userid, Integer orderid);
	int deleteMeal(int id);
}
