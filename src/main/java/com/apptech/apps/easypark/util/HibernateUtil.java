package com.apptech.apps.easypark.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sFactory = buildSFactory();

	private static SessionFactory buildSFactory() {
		try {
			Configuration hConfig = new Configuration()
					.configure("hibernate.cfg.xml");
			StandardServiceRegistryBuilder rBuilder = new StandardServiceRegistryBuilder();
			rBuilder.applySettings(hConfig.getProperties());
			ServiceRegistry sRegistry = rBuilder.build();
			sFactory = hConfig.buildSessionFactory();
			
			return sFactory;
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getHSessionFactory() {
		return sFactory;
	}

	public static void closeSFactory() {
		sFactory.close();
	}

}
