package com.Howard.test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.junit.Test;

import com.Howard.entity.User;
import com.Howard.util.HibernateUtils;

public class QueryDemo {
	/**
	 * Query方式查询 使用hql语句
	 * 注意hql操作实体和属性
	 */
	@Test
	public void testQuary(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from User");
			
			List<User> list = query.list();
			
			for(User user:list){
				System.out.println(user);
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
			sessionFactory.close();
		}
	}
	/**
	 * criteria方式 不需要写hql和sql语句 直接调用方法
	 */
	@Test
	public void testCriteria(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Criteria criteria = session.createCriteria(User.class);
			
			List<User> list = criteria.list();
			
			for(User user:list){
				System.out.println(user);
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
			sessionFactory.close();
		}
	}
	/**
	 * criteria方式 不需要写hql和sql语句 直接调用方法
	 */
	@Test
	public void testSqlQuery(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//直接使用底层sql 注意用的是数据表和字段
			NativeQuery SQLQuery = session.createSQLQuery("select * from t_user");
			
			SQLQuery.addEntity(User.class);
			List<User> list = SQLQuery.list();
			for(User user: list){
				System.out.println(user);
			}
			
//			List<Object[]> list = SQLQuery.list();
//			for(Object[] object:list){
//				System.out.println(Arrays.toString(object));
//			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
			sessionFactory.close();
		}
	}
}
