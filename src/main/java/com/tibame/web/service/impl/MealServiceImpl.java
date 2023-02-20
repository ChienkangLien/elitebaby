package com.tibame.web.service.impl;

import java.util.List;

import com.tibame.web.dao.MealDAO;
import com.tibame.web.dao.impl.MealDAOImpl;
import com.tibame.web.service.MealService;
import com.tibame.web.vo.MealVO;

public class MealServiceImpl implements MealService {

	MealDAO meal = new MealDAOImpl();

	@Override
	public List<MealVO> getAllMeal() {

		List<MealVO> list = meal.getAll();
		return list != null ? list : null;
	}

}
