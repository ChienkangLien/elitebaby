package com.tibame.web.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void closeSessionFactory() {
		if(sessionFactory!=null) {
			sessionFactory.close();
		}
	}
	private static SessionFactory buildSessionFactory() {
		final StandardServiceRegistry standardServiceRegistry = 
				new StandardServiceRegistryBuilder().configure().build();
		try {
			return new MetadataSources(standardServiceRegistry)
					.buildMetadata().buildSessionFactory();
		} catch (Throwable ex) {
			System.out.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}
}
