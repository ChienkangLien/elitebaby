package com.tibame.web;

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
		// 在这里添加需要在Tomcat启动时执行的代码
		// ...
		System.out.println("伺服器啟動成功，開始檢查Redis內的所有產品照片是否存在");
//		
		// 在MealDAO寫一個取得商品個數的方法 (未寫)
		Jedis jedis = new Jedis("localhost", 6379);
		MealDAO dao = new MealDAOImpl();
		if (dao.getlength() != jedis.keys("meal*").size()) {
			List<MealVO> list = dao.getAll();
			System.out.println(list.size());
			for (int i = 0; i < list.size(); i++) {
				if (jedis.hget("meal:" + list.get(i).getMealId() + ":pic", "pic") == null) {
					byte[] pic = list.get(i).getMealPic();
					System.out.println(pic);
					String mealPhoto = Base64.getMimeEncoder().encodeToString(pic);
					jedis.hset("meal:" + list.get(i).getMealId() + ":pic", "pic", mealPhoto);
					System.out.println(jedis.hget("meal:" + list.get(i).getMealId() + ":pic", "pic"));
				}
			}
		}
//		if (list.size() != jedis.hlen("meal")) {
//			for (int i = 0; i < list.size(); i++) {
//				byte[] pic = list.get(i).getMealPic();
//				String mealPhoto = Base64.getMimeEncoder().encodeToString(pic);
//				jedis.hset("meal:" + list.get(i).getMealId() + ":pic", "pic", mealPhoto);
//				System.out.println(jedis.hget("meal:" + list.get(i).getMealId() + ":pic", "pic"));
//			}
//			System.out.println("照片加入成功,關閉Jedis連線");	
//		}
		jedis.close();
	}
}
