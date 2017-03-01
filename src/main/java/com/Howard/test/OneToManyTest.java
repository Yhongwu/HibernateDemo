package com.Howard.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.Howard.entity.Customer;
import com.Howard.entity.LinkMan;
import com.Howard.entity.User;
import com.Howard.util.HibernateUtils;

public class OneToManyTest {

	@Test
	public void testAdd(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Customer customer = new Customer();
			customer.setCustName("tom");
			customer.setCustLevel("vip");
			
			LinkMan linkMan = new LinkMan();
			linkMan.setlName("小张");
			linkMan.setLphone("1234");
			
			customer.getLinkMans().add(linkMan);
			linkMan.setCustomer(customer);

			session.save(customer);
			session.save(linkMan);
			
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
	 * 级联保存
	 * 前提是1的那方配置了cascade="save-update"
	 */
	@Test
	public void testCascadeAdd(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Customer customer = new Customer();
			customer.setCustName("腾飞");
			customer.setCustLevel("vip");
			
//			LinkMan linkMan = new LinkMan();
//			linkMan.setlName("大刘");
//			linkMan.setLphone("5678123");
//			
//			LinkMan linkMan_2 = new LinkMan();
//			linkMan_2.setlName("小姚");
//			linkMan_2.setLphone("523");
			
			//级联保存方式 可省去一些步骤 
			//前提是1的那方配置了cascade="save-update"
//			customer.getLinkMans().add(linkMan);
//			customer.getLinkMans().add(linkMan_2);
//			linkMan.setCustomer(customer);

			session.save(customer);
//			session.save(linkMan);
			
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
	 * 级联删除
	 * 前提是1的那方配置了cascade="delete"
	 */
	@Test
	public void testCascadeDelete(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Customer customer = session.get(Customer.class, 2);
				
			//级联删除 会自动把联系人删除
			session.delete(customer);
			
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
	 * 修改操作
	 * 但是由于双方都维护外键的关系 这里会导致发出两次update语句
	 * 修改客户时修改一次外键 修改联系人时修改一次外键
	 * 在1的那方set里配置inverse="true"可使1的那方放弃维护外键 这时再次执行update 会发现只执行一次update 
	 */
	@Test
	public void testUpdate(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			Customer customer = session.get(Customer.class, 1);
			LinkMan linkMan = session.get(LinkMan.class, 3);
			
			customer.getLinkMans().add(linkMan);
			linkMan.setCustomer(customer);
			
			
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
