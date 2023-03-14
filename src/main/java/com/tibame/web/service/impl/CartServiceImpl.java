package com.tibame.web.service.impl;

import java.util.List;
import java.util.Set;

import com.tibame.web.dao.CartDAO;
import com.tibame.web.dao.impl.CartDAOImpl;
import com.tibame.web.service.CartService;
import com.tibame.web.vo.CartVO;
import com.tibame.web.vo.MealVO;

public class CartServiceImpl implements CartService {

	CartDAO dao = new CartDAOImpl();

	@Override
	public List<MealVO> getAllMeal(int id) {
		return dao.getAll(id);
	}

	@Override
	public int insertMeal(CartVO cart) {
		return dao.insert(cart);
	}

	@Override
	public int updateMeal(CartVO cart) {
		return dao.update(cart);
	}

	@Override
	public Set<String> findByPrimaryKey(int id) {
		return dao.findByPrimaryKey(id);
	}

	@Override
	public int deleteMeal(CartVO cart) {
		return dao.delete(cart);
	}

}
