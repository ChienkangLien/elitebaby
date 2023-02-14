package com.tibame.web.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tibame.web.dao.EmailDAO;
import com.tibame.web.vo.EmailVO;

public class EmailDAOImpl implements EmailDAO {

	private SessionFactory sessionFactory;
	
	public EmailDAOImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;

	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	@Override
	public List<EmailVO> slectAll() {
		
		
		return this.getSession();
	}


}
