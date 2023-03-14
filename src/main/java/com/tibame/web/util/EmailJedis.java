package com.tibame.web.util;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import com.tibame.web.vo.EmailBellBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class EmailJedis {

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<EmailBellBean> getBellData(String userId) {
		Gson gson = new Gson();
		List<EmailBellBean> emailBellData = new ArrayList<EmailBellBean>();
		String Key = new StringBuilder(userId).append("bell").append("*").toString();
		Jedis jedis = null;
		jedis = pool.getResource();
//		System.out.println( jedis.keys(Key));
		Set<String> keys = jedis.keys(Key);
		for(String key : keys) {
		EmailBellBean emailBellValue =  gson.fromJson( jedis.get(key), EmailBellBean.class);
		emailBellData.add(emailBellValue);
		}
//		System.out.println(emailBellData);
		jedis.close();
		return emailBellData;
	}

	public static void saveBellData(String userId, String sender, String data) {

		Gson gson = new Gson();
		String senderKey = new StringBuilder(userId).append("bell").append(":").append(sender).toString();
		Jedis jedis = pool.getResource();
//		System.out.println(senderKey);
		String checkKey = jedis.get(senderKey);
//		System.out.println(checkKey);
		if (checkKey == null) {

			jedis.set(senderKey, data);

		} else {

			EmailBellBean emailBellData = gson.fromJson(checkKey, EmailBellBean.class);
			Integer unreadCount = emailBellData.getUnreadCount() + 1;
			emailBellData.setStatus("unread");
			emailBellData.setUnreadCount(unreadCount);
			String jObjStr = gson.toJson(emailBellData);
			jedis.set(senderKey, jObjStr);

		}
		jedis.close();
	}

	public static void changeStatus(String userId) {
		
		Gson gson = new Gson();
		String Key = new StringBuilder(userId).append("bell").append("*").toString();
		Jedis jedis = null;
		jedis = pool.getResource();
//		System.out.println( jedis.keys(Key));
		Set<String> keys = jedis.keys(Key);
		for(String key : keys) {
			EmailBellBean emailBellData = gson.fromJson(jedis.get(key), EmailBellBean.class);
			emailBellData.setStatus("read");
			emailBellData.setUnreadCount(0);
			String jObjStr = gson.toJson(emailBellData);
			jedis.set(key,jObjStr);
//			System.out.println(emailBellData);
		}
		jedis.close();
		
	}



}
