package com.tibame.web.service.impl;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.tibame.web.dao.MealOrderDAO;
import com.tibame.web.dao.MealOrderDetailDAO;
import com.tibame.web.dao.impl.MealOrderDAOImpl;
import com.tibame.web.dao.impl.MealOrderDetailDAOImpl;
import com.tibame.web.service.MealOrderService;
import com.tibame.web.vo.MealOrderDetailVO;
import com.tibame.web.vo.MealOrderVO;

import redis.clients.jedis.Jedis;

public class MealOrderServiceImpl implements MealOrderService {

	MealOrderDAO modao = new MealOrderDAOImpl();
	MealOrderDetailDAO moddao = new MealOrderDetailDAOImpl();

	@Override
	public List<MealOrderVO> getAllMeal() {
		List<MealOrderVO> list = modao.getAll();
		System.out.println(list);
		for (int i = 0; i < list.size(); i++) {
			int total = 0;
			System.out.println(list.get(i).getAuthCode());
			List<MealOrderDetailVO> modlist = moddao.findByAuthCode(list.get(i).getAuthCode());
			for (int j = 0; j < modlist.size(); j++) {
				System.out.println(modlist.get(j).getMealPrice());
				System.out.println(modlist.get(j).getOrderCount());
				total += modlist.get(j).getMealPrice() * modlist.get(j).getOrderCount();
			}
			list.get(i).setTotal(total);
			if (list.get(i).getOrderStatus() == 0) {
				list.get(i).setStrstatus("未付款");
			} else if (list.get(i).getOrderStatus() == 1) {
				list.get(i).setStrstatus("已付款");
			} else if (list.get(i).getOrderStatus() == 2) {
				list.get(i).setStrstatus("取消");
			} else if (list.get(i).getOrderStatus() == 3) {
				list.get(i).setStrstatus("已完成");
			}
			if (list.get(i).getOrderPayment() == 1) {
				list.get(i).setStrPayment("現金");
			} else if (list.get(i).getOrderPayment() == 2) {
				list.get(i).setStrPayment("信用卡");
			} else {
				list.get(i).setStrPayment("LinePay");
			}
		}
		return list != null ? list : null;
	}

	@Override
	public int insertMeal(MealOrderVO mealOrder) {
		final String authCode = mealOrder.getAuthCode();
		int udm = modao.insert(mealOrder);
		if (udm > 0) {
			Jedis jedis = new Jedis("localhost", 6379);
			Set<String> set = jedis.keys("user" + mealOrder.getUserId() + ":*");
			int times = 0;
			for (String str : set) {
				System.out.println(str);
				times += moddao.insert(Integer.valueOf(jedis.hget(str, "mealId")),
						Integer.valueOf(jedis.hget(str, "count")), authCode);
			}
			if (times == set.size()) {
				for (String str : set) {
					jedis.del(str);
				}
				jedis.close();
				return 1;
			} else {
				jedis.close();
				return -1;
			}
		}
		return -1;
	}

	@Override
	public int updateMeal(MealOrderVO meal) {
		if (meal.getStrstatus().equals("未付款")) {
			System.out.println("成功判斷Strstatus是未付款，設定order");
			meal.setOrderStatus(2);
		}
		return modao.update(meal);
	}

	@Override
	public int updateMealWithAddress(MealOrderVO meal) {
		return modao.update(meal);
	}

	@Override
	public int deleteMeal(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MealOrderVO> findByPrimaryKey(Integer id) {
		List<MealOrderVO> listmo = modao.findByUserId(id);
		for (MealOrderVO li1 : listmo) {
			int total = 0;
			if (li1.getOrderPayment() == 1) {
				li1.setStrPayment("現金");
			} else if (li1.getOrderPayment() == 2) {
				li1.setStrPayment("信用卡");
			} else {
				li1.setStrPayment("LinePay");
			}

			if (li1.getOrderStatus() == 0) {
				li1.setStrstatus("未付款");
			} else if (li1.getOrderStatus() == 1) {
				li1.setStrstatus("已付款");
			} else if (li1.getOrderStatus() == 2) {
				li1.setStrstatus("取消");
			} else if (li1.getOrderStatus() == 3) {
				li1.setStrstatus("已完成");
			}
			List<MealOrderDetailVO> listmod = moddao.findByAuthCode(li1.getAuthCode());
			for (MealOrderDetailVO li2 : listmod) {
				total += li2.getMealPrice() * li2.getOrderCount();
			}
			li1.setTotal(total);
		}
		return listmo;
	}

	@Override
	public List<MealOrderVO> findByMealOrder(Integer id) {
		List<MealOrderVO> list = modao.findByMealOrderId(id);
		for (int i = 0; i < list.size(); i++) {
			int total = 0;
			System.out.println(list.get(i).getAuthCode());
			List<MealOrderDetailVO> modlist = moddao.findByAuthCode(list.get(i).getAuthCode());
			for (int j = 0; j < modlist.size(); j++) {
				System.out.println(modlist.get(j).getMealPrice());
				System.out.println(modlist.get(j).getOrderCount());
				total += modlist.get(j).getMealPrice() * modlist.get(j).getOrderCount();
			}
			list.get(i).setTotal(total);
			if (list.get(i).getOrderStatus() == 0) {
				list.get(i).setStrstatus("未付款");
			} else if (list.get(i).getOrderStatus() == 1) {
				list.get(i).setStrstatus("已付款");
			} else if (list.get(i).getOrderStatus() == 2) {
				list.get(i).setStrstatus("取消");
			} else if (list.get(i).getOrderStatus() == 3) {
				list.get(i).setStrstatus("已完成");
			}
			if (list.get(i).getOrderPayment() == 1) {
				list.get(i).setStrPayment("現金");
			} else if (list.get(i).getOrderPayment() == 2) {
				list.get(i).setStrPayment("信用卡");
			} else {
				list.get(i).setStrPayment("LinePay");
			}
		}
		return list;
	}

	@Override
	public List<MealOrderVO> findByMealOrderwithuser(Integer userid, Integer orderid) {
//		System.out.println("userid!=null,進到service userid=" + userid + " orderid=" + orderid);
		List<MealOrderVO> list = modao.findByMealOrderIdWithUser(userid, orderid);
		for (int i = 0; i < list.size(); i++) {
			int total = 0;
			System.out.println(list.get(i).getAuthCode());
			List<MealOrderDetailVO> modlist = moddao.findByAuthCode(list.get(i).getAuthCode());
			for (int j = 0; j < modlist.size(); j++) {
				System.out.println(modlist.get(j).getMealPrice());
				System.out.println(modlist.get(j).getOrderCount());
				total += modlist.get(j).getMealPrice() * modlist.get(j).getOrderCount();
			}
			list.get(i).setTotal(total);
			if (list.get(i).getOrderStatus() == 0) {
				list.get(i).setStrstatus("未付款");
			} else if (list.get(i).getOrderStatus() == 1) {
				list.get(i).setStrstatus("已付款");
			} else if (list.get(i).getOrderStatus() == 2) {
				list.get(i).setStrstatus("取消");
			} else if (list.get(i).getOrderStatus() == 3) {
				list.get(i).setStrstatus("已完成");
			}
			if (list.get(i).getOrderPayment() == 1) {
				list.get(i).setStrPayment("現金");
			} else if (list.get(i).getOrderPayment() == 2) {
				list.get(i).setStrPayment("信用卡");
			} else {
				list.get(i).setStrPayment("LinePay");
			}
		}
		return list;
	}


}
