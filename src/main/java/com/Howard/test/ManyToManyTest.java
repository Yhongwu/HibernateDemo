package com.Howard.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.Howard.entity.Customer;
import com.Howard.entity.Employe;
import com.Howard.entity.Role;
import com.Howard.util.HibernateUtils;
/**
 * 多对多测试
 * @author Howard
 * 2017年3月1日
 */
public class ManyToManyTest {
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
			
			Employe employe_1 = new Employe();
			employe_1.setE_name("mary");
			employe_1.setE_address("广州");
			Employe employe_2 = new Employe();
			employe_2.setE_name("mike");
			employe_2.setE_address("深圳");
			
			Role role_1 = new Role();
			role_1.setR_name("总经理");
			role_1.setR_memo("总经理");
			Role role_2 = new Role();
			role_2.setR_name("秘书");
			role_2.setR_memo("秘书");
			Role role_3 = new Role();
			role_3.setR_name("保安");
			role_3.setR_memo("保安");
			
			//employe_1->r1/r2 employe_2->r2/r3
			employe_1.getRoles().add(role_1);
			employe_1.getRoles().add(role_2);
			employe_2.getRoles().add(role_2);
			employe_2.getRoles().add(role_3);
			
			session.save(employe_1);
			session.save(employe_2);
			
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
	 * 多对多级联删除
	 * 前提：在employe的映射文件加：cascade="delete"
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
			
			Employe employe = session.get(Employe.class, 1);
			session.delete(employe);
			
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
	 * 维护第三张表
	 * 让某个用户有某个角色
	 */
	@Test
	public void maintainTheThirdTable(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//持久态 自动保存
			Employe employe = session.get(Employe.class, 2);
			Role role = session.get(Role.class, 2);
			
			employe.getRoles().add(role);
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
	 * 维护第三张表
	 * 让某个用户没有某个角色
	 */
	@Test
	public void maintainTheThirdTable2(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//持久态 自动保存
			Employe employe = session.get(Employe.class, 2);
			Role role = session.get(Role.class, 2);
			
			employe.getRoles().remove(role);
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
