package com.Howard.test;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.Howard.entity.Customer;
import com.Howard.entity.Employe;
import com.Howard.entity.LinkMan;
import com.Howard.util.HibernateUtils;
/**
 * hql语句查询
 * 语句里的是实体和实体属性
 * @author Howard
 * 2017年3月1日
 */
public class QueryHql {
	/**
	 * 查询全部
	 */
	@Test
	public void SelectAll(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from Customer");
//			List<Customer> list = query.list(); 过期方法
			List<Customer> list = query.getResultList();
//			System.out.println(list.size());
			for(Customer customer:list){
				System.out.println(customer);
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
	 * 条件查询
	 */
	@Test
	public void selectByConditions(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Query query = session.createQuery("from Customer where cid = ? and custName=?");
			//query设置参数 注意是从0开始
			query.setParameter(0, 3);
			query.setParameter(1, "百度");
			
//			List<Customer> list = query.list(); 过期方法
			List<Customer> list = query.getResultList();
			System.out.println(list.size());
			for(Customer customer:list){
				System.out.println(customer);
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
	 * 模糊查询
	 */
	@Test
	public void selectByFuzzy(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//Hql语句支持表别名
			Query query = session.createQuery("from Customer c where c.custName like ?");
			//query设置参数 注意是从0开始
			query.setParameter(0, "%腾%");
			
//			List<Customer> list = query.list(); 过期方法
			List<Customer> list = query.getResultList();
			System.out.println(list.size());
			for(Customer customer:list){
				System.out.println(customer);
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
	 * 排序查询
	 */
	@Test
	public void selectBySort(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//降序
			Query query = session.createQuery("from Customer order by cid desc");
			
//			List<Customer> list = query.list(); 过期方法
			List<Customer> list = query.getResultList();
			System.out.println(list.size());
			for(Customer customer:list){
				System.out.println(customer);
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
	 * 分页查询
	 */
	@Test
	public void selectByPaging(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//降序
			Query query = session.createQuery("from Customer order by cid desc");
			
			query.setFirstResult(0);
			query.setMaxResults(3);
			
//			List<Customer> list = query.list(); 过期方法
			List<Customer> list = query.getResultList();
			System.out.println(list.size());
			for(Customer customer:list){
				System.out.println(customer);
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
	 * 投影查询
	 * 即只查询部分字段
	 */
	@Test
	public void selectByProjection(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//降序
			Query query = session.createQuery("select custName from Customer ");
			
			
//			List<Customer> list = query.list(); 过期方法
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
	/**
	 * 聚集函数查询 即count sum等
	 */
	@Test
	public void selectByFunction(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			//降序
			Query query = session.createQuery("select count(*) from Customer ");
			
//			Object obj = query.uniqueResult();//过期
		    Optional optional = query.uniqueResultOptional();
			System.out.println(optional.get());
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
