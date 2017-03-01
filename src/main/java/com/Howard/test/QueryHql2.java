package com.Howard.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.Howard.entity.Customer;
import com.Howard.util.HibernateUtils;

public class QueryHql2 {
	/**
	 * 内连接查询
	 */
	@Test
	public void selectInnerJoin(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//Hql语句支持表别名
			//普通sql：select * from Customer ,linkman where lid = cid或者 select * from customer inner join linkman on lid = cid
			//注意与普通sql语句的区别
			//c.linkMans为Customer实体类里对应set的名称
//			Query query = session.createQuery("from Customer c inner join c.linkMans");
			//迫切内连接
			Query query = session.createQuery("from Customer c inner join fetch c.linkMans");
			
			
			//普通内连接返回的是Object 迫切内链接返回的是数组
			List<Object> list = query.getResultList();
			System.out.println(list.size());
			for(Object object:list){
				System.out.println(object.toString());
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
	 * 左外连接查询
	 * 普通：select * from customer left outer join linkman on lid = cid
	 */
	@Test
	public void selectLeftOuterJoin(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//Hql语句支持表别名
			//注意与普通sql语句的区别
			//c.linkMans为Customer实体类里对应set的名称
//			Query query = session.createQuery("from Customer c left outer join c.linkMans");
			//迫切左外连接 注意 没有迫切右外连接
			Query query = session.createQuery("from Customer c left outer join fetch c.linkMans");
			
			
			//普通内连接返回的是Object 迫切内链接返回的是数组
			List<Object> list = query.getResultList();
			System.out.println(list.size());
			for(Object object:list){
				System.out.println(object);
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

}
