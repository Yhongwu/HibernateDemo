package com.Howard.test;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Test;

import com.Howard.entity.Customer;
import com.Howard.entity.LinkMan;
import com.Howard.util.HibernateUtils;

/**
 * hibernate的检索策略 懒加载
 * 通过断点 来测试
 * fetch="select" lazy="flase" 可以配置是否启动延迟加载
 * lazy默认为true为延迟 false为不延迟 extra为极其懒惰 即需要什么就查什么 只查需要的字段 
 * @author Howard
 * 2017年3月1日
 */
public class StrategyTest {
	/**
	 * 立即查询
	 */
	@Test
	public void Select1(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//执行到这语句：调用get方法后 马上就发送sql语句 叫立即查询
			Customer customer = session.get(Customer.class, 1);
			
			System.out.println(customer.getCid());
			
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
	 * 延迟加载
	 * 分两类：
	 * 类级别延迟：根据id查询返回实体对象 调用load不会马上发送sql
	 * 关联级别延迟：比如查询客户，之后查询客户的联系人是否还需要发送sql语句
	 */
	@Test
	public void Select2(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//执行到这语句：调用load方法后 
			//不会发送sql语句 
			//返回的值里只有id的值 在调用其它值的时候 才会发送sql语句查询
			Customer customer = session.load(Customer.class, 3);
			//不会发sql语句 因为id值上一步已经查询
			System.out.println(customer.getCid());
			//会发sql语句查询 
			System.out.println(customer.getCustName());
			
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
	 * 关联延迟
	 */
	@Test
	public void Select3(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Customer customer = session.get(Customer.class, 3);
			//不会发送sql语句
			Set<LinkMan> linkMans = customer.getLinkMans();
			//会发sql语句查询 
			System.out.println(linkMans.size());
			
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
	 * 测试批量抓取?????测试没出现多次发送sql查询问题？版本问题？
	 * 
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
			
			Criteria criteria = session.createCriteria(Customer.class);
			List<Customer> list = criteria.list();
			System.out.println(list.size());
			for(Customer customer:list){
				System.out.println(customer.getCustName());
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
