package com.Howard.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	private static Configuration cfg = null;
	private static SessionFactory sessionFactory = null;
	
	static{
		cfg = new Configuration().configure("hibernate01.cfg.xml");
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	//返回与本地线程绑定的session
	//前提:配置文件里配置了：<property name="current_session_context_class">thread</property>
	public static Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public static void main(String[] args) {
		
	}
}
