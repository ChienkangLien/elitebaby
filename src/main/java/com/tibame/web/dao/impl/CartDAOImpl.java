package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.CartDAO;
import com.tibame.web.vo.CartVO;
import com.tibame.web.vo.MealVO;

import redis.clients.jedis.Jedis;

public class CartDAOImpl implements CartDAO {

	private DataSource ds;

	public CartDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(CartVO cart) {
		Jedis jedis = new Jedis("localhost", 6379);
		Integer mealId = cart.getMealId();
		String cartpk = "user" + cart.getUserId();
		String mealpk = "meal" + mealId;
		HashMap<String, String> data = new HashMap<>();
		Set<String> keys = jedis.keys(cartpk + "*");
//		Iterator<String> iterator = keys.iterator();
		if (keys == null || keys.isEmpty()) {
			System.out.println("進入了沒有購物車的處理流程,建立購物車並加入商品");
			data.put("mealId", String.valueOf(mealId));
			data.put("count", "1");
			jedis.hmset(cartpk + ":" + mealpk, data);
		} else {
			if (jedis.hget(cartpk + ":" + mealpk, "mealId") == null) {
				System.out.println("進入此代表還沒將此商品加入購物車過，故加入");
				data.put("mealId", String.valueOf(mealId));
				data.put("count", "1");
				jedis.hmset(cartpk + ":"+mealpk, data);
			} else {
				Integer count = Integer.valueOf(jedis.hget(cartpk + ":meal" + mealId, "count"));
				System.out.println(count);
				count += 1;
				System.out.println(count);
				data.put("mealId", jedis.hget(cartpk + ":meal" + mealId, "mealId"));
				data.put("count", String.valueOf(count));
				jedis.hmset(cartpk + ":meal" + mealId, data);
			}
		}
		jedis.close();
		return 1;
	}

	@Override
	public int update(CartVO cart) {
		if (cart != null) {
			Jedis jedis = new Jedis("localhost", 6379);
			String userId = String.valueOf(cart.getUserId());
			String mealId = String.valueOf(cart.getMealId());
			String count = String.valueOf(cart.getCount());
			HashMap<String, String> data = new HashMap<>();
			data.put("mealId", mealId);
			data.put("count", count);
			jedis.hmset("user" + userId + ":meal" + mealId, data);
			jedis.close();
			return 1;
		}
		return -1;
	}

	@Override
	public int delete(CartVO cart) {
		Jedis jedis = new Jedis("localhost", 6379);
		System.out.println("user" + cart.getUserId() + ":meal" + cart.getMealId());
		jedis.del("user" + cart.getUserId() + ":meal" + cart.getMealId());
		jedis.close();
		return 1;
	}

	@Override
	public Set<String> findByPrimaryKey(Integer id) {
		Jedis jedis = new Jedis("localhost", 6379);
		Set<String> set = jedis.keys("user" + id + ":*");
		jedis.close();
		return set != null ? set : null;
	}

	@Override
	public List<MealVO> getAll(Integer id) {
		StringBuilder sb = new StringBuilder("SELECT MEAL_ID, MEAL_NAME, MEAL_PRICE FROM MEAL WHERE MEAL_ID = ");
		Jedis jedis = new Jedis("localhost", 6379);
		Set<String> data = jedis.keys("user" + id + ":*");
		Iterator<String> iterator = data.iterator();
		ArrayList<String> list = new ArrayList<String>();
		HashMap<String, String> hm = new HashMap<String, String>();
		while (iterator.hasNext()) {
			sb.append("? OR MEAL_ID = ");
			String value = iterator.next();
			String mealId = jedis.hget(value, "mealId");
			list.add(mealId);
		}
		sb.delete(sb.length() - 14, sb.length());
		String sql = String.valueOf(sb);
//		System.out.println(sql);
		ArrayList<MealVO> mealList = new ArrayList<MealVO>();
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			for (int i = 1; i < data.size() + 1; i++) {
				ps.setInt(i, Integer.valueOf(list.get(i - 1)));
			}
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MealVO meal = new MealVO();
					meal.setMealId(rs.getInt(1));
					meal.setMealName(rs.getString(2));
					meal.setMealPrice(rs.getInt(3));
					meal.setCount(Integer.valueOf(jedis.hmget("user" + id + ":meal" + rs.getInt(1), "count").get(0)));
					meal.setBase64(jedis.hget("meal:" + rs.getInt(1) + ":pic", "pic"));
					mealList.add(meal);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		jedis.hget
//		System.out.println(mealList);
		jedis.close();
		return mealList != null ? mealList : null;
	}

}
