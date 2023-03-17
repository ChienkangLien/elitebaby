package com.tibame.web.util;

import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.tibame.web.dao.MealDAO;
import com.tibame.web.dao.impl.MealDAOImpl;
import com.tibame.web.vo.MealVO;

import redis.clients.jedis.Jedis;

@WebListener
public class MyServletContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		System.out.println("伺服器啟動成功，開始檢查Redis內的所有產品照片是否存在");
		Jedis jedis = new Jedis("localhost", 6379);
		MealDAO dao = new MealDAOImpl();
		// 在MealDAO寫一個取得商品個數的方法 (未寫)
		System.out.println(dao.getlength());
		System.out.println(jedis.keys("meal*").size());
		if (dao.getlength() != jedis.keys("meal*").size()) {
			List<MealVO> list = dao.getAll();
			System.out.println(list.size());
			System.out.println(list);
			for (int i = 0; i < list.size(); i++) {
				if (jedis.hget("meal:" + list.get(i).getMealId() + ":pic", "pic") == null) {
					byte[] pic = dao.getpic(list.get(i).getMealId());
					System.out.println(pic);
					if (pic != null) {
						String mealPhoto = Base64.getMimeEncoder().encodeToString(pic);
						jedis.hset("meal:" + list.get(i).getMealId() + ":pic", "pic", mealPhoto);
						System.out.println(jedis.hget("meal:" + list.get(i).getMealId() + ":pic", "pic"));
					} else {
						System.out.println("資料庫內由沒有照片，請新增照片");
					}
				}
			}
		}
		jedis.close();
	}
}
