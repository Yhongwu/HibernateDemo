package com.Howard.test;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.junit.Test;

import com.Howard.entity.Customer;
import com.Howard.util.HibernateUtils;
/**
 * QBC查询
 * 不需要写hql或者sql语句
 * 只需要调用Criteria的方法即可
 * @author Howard
 * 2017年3月1日
 */
public class QueryQBC {
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
			//貌似不止5.几开始这个方法就过时了
			Criteria criteria = session.createCriteria(Customer.class);
			List<Customer> list = criteria.list();
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
			
			Criteria criteria = session.createCriteria(Customer.class);
			//调用criteria.add，使用Restrictions的静态方法实现条件查询
			criteria.add(Restrictions.eq("cid", 3));
			criteria.add(Restrictions.eq("custName", "百度"));
			
			List<Customer> list = criteria.list();
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
			
			Criteria criteria = session.createCriteria(Customer.class);
			//调用criteria.add，使用Restrictions的静态方法实现条件查询
			criteria.add(Restrictions.like("custName", "%腾%"));
			
			
			List<Customer> list = criteria.list();
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
			
			Criteria criteria = session.createCriteria(Customer.class);
			//调用criteria.add，使用Restrictions的静态方法实现条件查询
			criteria.addOrder(Order.desc("cid"));
			
			
			List<Customer> list = criteria.list();
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
			
			Criteria criteria = session.createCriteria(Customer.class);
			//调用criteria.add，使用Restrictions的静态方法实现条件查询
			criteria.addOrder(Order.desc("cid"));
			criteria.setFirstResult(0);
			criteria.setMaxResults(3);
			
			List<Customer> list = criteria.list();
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
			
			Criteria criteria = session.createCriteria(Customer.class);
			//调用criteria.add，使用Restrictions的静态方法实现条件查询
			
			criteria.setProjection(Projections.rowCount());
			Object uniqueResult = criteria.uniqueResult();

			System.out.println(uniqueResult);
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
	 * 离线查询
	 */
	@Test
	public void selectByOffline(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			DetachedCriteria forClass = DetachedCriteria.forClass(Customer.class);
			
			//最终执行时才需要session
			Criteria criteria = forClass.getExecutableCriteria(session);
			
			List<Customer> list = criteria.list();
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
	
}
