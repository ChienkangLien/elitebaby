package com.tibame.web.service.impl;

import java.util.List;

import com.tibame.web.dao.MealOrderDAO;
import com.tibame.web.dao.MealOrderDetailDAO;
import com.tibame.web.dao.impl.MealOrderDAOImpl;
import com.tibame.web.dao.impl.MealOrderDetailDAOImpl;
import com.tibame.web.service.MealOrderService;
import com.tibame.web.vo.MealOrderDetailVO;
import com.tibame.web.vo.MealOrderVO;

public class MealOrderServiceImpl implements MealOrderService {

	MealOrderDAO modao = new MealOrderDAOImpl();
	MealOrderDetailDAO moddao = new MealOrderDetailDAOImpl();

	@Override
	public List<MealOrderVO> getAllMeal() {
		List<MealOrderVO> list = modao.getAll();
//		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			int total = 0;
			List<MealOrderDetailVO> modlist = moddao.findByMealOrderId(list.get(i).getMealOrderId());
			for (int j = 0; j < modlist.size(); j++) {
				total=modlist.get(j).getMealPrice()*modlist.get(j).getOrderCount();
			}
			list.get(i).setTotal(total);
			String strOrderStatus = list.get(i).getOrderStatus() == 0?"未付款":"已付款";
			list.get(i).setStrstatus(strOrderStatus);
		}
		return list != null ? list : null;
	}

	@Override
	public int insertMeal(MealOrderVO meal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMeal(MealOrderVO meal) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MealOrderVO findByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteMeal(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
