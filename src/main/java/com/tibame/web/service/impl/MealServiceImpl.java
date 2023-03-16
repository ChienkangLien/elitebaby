package com.tibame.web.service.impl;

import java.util.Base64;
import java.util.List;

import com.tibame.web.dao.MealDAO;
import com.tibame.web.dao.impl.MealDAOImpl;
import com.tibame.web.service.MealService;
import com.tibame.web.vo.MealVO;

import redis.clients.jedis.Jedis;

public class MealServiceImpl implements MealService {

	MealDAO dao = new MealDAOImpl();

	@Override
	public List<MealVO> getAllMeal() {
		Jedis jedis = new Jedis("localhost", 6379);
		List<MealVO> list = dao.getAll();
		for (int i = 0; i < list.size(); i++) {
			String mealPic = null;
			MealService ms = new MealServiceImpl();
			if (jedis.hget("meal:" + list.get(i).getMealId() + ":pic", "pic") == null) {
				System.out.println("進來取照片的迴圈");
				mealPic = ms.getPic(list.get(i).getMealId());
//				byte[] pic = list.get(i).getMealPic();
//				mealPic = Base64.getMimeEncoder().encodeToString(pic);
				jedis.hset("meal:" + list.get(i).getMealId() + ":pic", "pic", mealPic);
			} else {
				mealPic = jedis.hget("meal:" + list.get(i).getMealId() + ":pic", "pic");
			}
			list.get(i).setBase64(mealPic);
		}
		jedis.close();
		return list != null ? list : null;
	}

	@Override
	public int insertMeal(MealVO meal) {
		if (meal != null) {
			String base64 = meal.getBase64();
//			byte[] pic = list.get(i).getMealPic();
//			mealPic = Base64.getMimeEncoder().encodeToString(pic);
			byte[] pic = Base64.getMimeDecoder().decode(base64);
			meal.setMealPic(pic);
			return dao.insert(meal);
		} else {
			return -1;
		}
	}

	@Override
	public int updateMeal(MealVO meal) {
		Jedis jedis = new Jedis("localhost", 6379);
		if (meal != null) {
			jedis.hset("meal:" + meal.getMealId() + ":pic", "pic", meal.getBase64());
			jedis.close();
			return dao.update(meal);
		} else {
			jedis.close();
			return -1;
		}
	}

	@Override
	public MealVO findByPrimaryKey(int id) {
		if (id >= 0) {
			Jedis jedis = new Jedis("localhost", 6379);
			MealVO meal = dao.findByPrimaryKey(id);
			if(meal == null) {
				return null;
			}
			String mealPic = jedis.hget("meal:" + meal.getMealId() + ":pic", "pic");
			meal.setBase64(mealPic);
			jedis.close();
			return meal;
		}
		return null;
	}

	@Override
	public int deleteMeal(int id) {
		Jedis jedis = new Jedis("localhost", 6379);
		if (id >= 0) {
			jedis.hdel("meal:" + id + ":pic", "pic");
			jedis.close();
			return dao.delete(id);
		}
		jedis.close();
		return -1;
	}

	@Override
	public void addAllMealPicToRedis() {
		List<MealVO> list = dao.getAll();
		Jedis jedis = new Jedis("localhost", 6379);
		for (int i = 0; i < list.size(); i++) {
			byte[] pic = list.get(i).getMealPic();
			String mealPhoto = Base64.getMimeEncoder().encodeToString(pic);
			jedis.hset("meal:" + list.get(i).getMealId() + ":pic", "pic", mealPhoto);
			System.out.println(jedis.hget("meal:" + list.get(i).getMealId() + ":pic", "pic"));
		}
		jedis.close();
	}

	@Override
	public int getMealLength() {
		return dao.getlength();
	}

	@Override
	public String getPic(int mealId) {
		byte[] pic = dao.getpic(mealId);
		String mealPhoto = Base64.getMimeEncoder().encodeToString(pic);
		return mealPhoto;
	}

}
