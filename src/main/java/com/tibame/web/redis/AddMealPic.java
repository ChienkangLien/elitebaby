package com.tibame.web.redis;

import java.util.List;

import com.tibame.web.dao.MealDAO;
import com.tibame.web.dao.impl.MealDAOImpl;
import com.tibame.web.vo.MealVO;

public class AddMealPic {
	public static void main(String[] args) {
		MealDAO dao = new MealDAOImpl();
		List<MealVO> meal = dao.getAll();
		System.out.println(meal);
		
//		System.out.println(meal.get(0).getMealPic());
	}
}
