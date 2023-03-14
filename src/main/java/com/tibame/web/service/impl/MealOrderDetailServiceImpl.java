package com.tibame.web.service.impl;

import java.util.Collections;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.tibame.web.dao.MealOrderDetailDAO;
import com.tibame.web.dao.impl.MealOrderDetailDAOImpl;
import com.tibame.web.service.MealOrderDetailService;
import com.tibame.web.vo.MealOrderDetailVO;

import redis.clients.jedis.Jedis;

public class MealOrderDetailServiceImpl implements MealOrderDetailService {
	MealOrderDetailDAO dao = new MealOrderDetailDAOImpl();

	@Override
	public List<MealOrderDetailVO> getOrderDetail(String authCode) {
		Jedis jedis = new Jedis("localhost", 6379);
		List<MealOrderDetailVO> list = dao.findByAuthCodeForOrderDetail(authCode);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setBase64(jedis.hget("meal:"+list.get(i).getMealId()+":pic","pic"));
		}
		
		return list;
	}

}
