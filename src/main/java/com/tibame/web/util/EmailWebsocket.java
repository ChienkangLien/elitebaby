package com.tibame.web.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.tibame.web.dao.EmailDAO;
import com.tibame.web.dao.impl.EmailDAOImpl;
import com.tibame.web.vo.EmailBellBean;
import com.tibame.web.vo.EmailVO;

@ServerEndpoint("/emailBell/{userName}")
public class EmailWebsocket {

	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();

	@OnOpen
	public void onOpen(@PathParam("userName")String userName, Session userSession) {

		System.out.println("WebSocket opened: ");
		String user = "user"+userName;
		sessionsMap.put(user, userSession);
		Set<String> userNames = sessionsMap.keySet();
		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				user, userNames);
		System.out.println(text);

	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		Gson gson = new Gson();
		EmailBellBean emailBellData = gson.fromJson(message, EmailBellBean.class);
		Integer userId = emailBellData.getUserId();
		System.out.println(userId);
		String sender = emailBellData.getFrom();
		String user = "user"+userId;
		EmailJedis.saveBellData(user, sender, message);

		Session receiverSession = sessionsMap.get(user);
		if (receiverSession != null && receiverSession.isOpen()) {
			receiverSession.getAsyncRemote().sendText(message);
		}

	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("WebSocket closed: ");

	}

	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("WebSocket error: "+error);
	}
}
