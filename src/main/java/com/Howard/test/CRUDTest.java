package com.Howard.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.Howard.entity.User;
import com.Howard.util.HibernateUtils;

public class CRUDTest {

	@Test
	public void addTest(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//user没有id值 未调用session的save进行保存 此时该实体为瞬时态 与session无关联
		User user = new User();
		user.setAddress("广州");
		user.setUsername("Howard");
		
		User user_1 = new User();
		user_1.setAddress("深圳");
		user_1.setUsername("tom");
		
		User user_2 = new User();
		user_2.setAddress("汕尾");
		user_2.setUsername("tony");
		
		session.save(user);
		session.save(user_1);
		session.save(user_2);
		
		tx.commit();
		
		session.close();
		sessionFactory.close();
		
	}
	
	@Test
	public void GetByIdTest(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		//查询可以不开启事务
//		Transaction tx = session.beginTransaction();
		
		//通过id查询出来的 有id值 与session有关联 此时对象位持久态
		User user = session.get(User.class, 1);
		
		System.out.println(user);
//		tx.commit();
		
		session.close();
		sessionFactory.close();
		
	}
	
	@Test
	public void UpdateTest(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		User user = session.get(User.class, 1);
		user.setAddress("汕头");
		session.update(user);
		
		//下面这种更新方法可以实现 但是有问题 没更新的会变成null
//		User user = new User();
//		user.setUid(1);
//		user.setAddress("汕头");
//		session.update(user);
		
		tx.commit();
		session.close();
		sessionFactory.close();
		
	}
	
	@Test
	public void DeleteTest(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		//方法1
//		User user = session.get(User.class, 3);
//		session.delete(user);
		
		//方法2
		//调用delete方法前 对象有id值 但没有与session关联 为托管态
		User user = new User();
		user.setUid(3);
		session.delete(user);
		
		tx.commit();
		session.close();
		sessionFactory.close();
		
	}
	
	@Test
	public void SaveOrUpdateTest(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		//实体类是瞬时态时 添加操作
//		User user = new User();
//		user.setAddress("汕尾");
//		user.setUsername("tony");
		
		//实体对象为托管态 做修改
//		User user = new User();
//		user.setUid(4);
//		user.setAddress("汕头");
//		user.setUsername("tony");
		
		//对象为持久态 修改操作
		User user = session.get(User.class, 4);
		user.setAddress("湛江");
		
		session.saveOrUpdate(user);
		tx.commit();
		
		session.close();
		sessionFactory.close();
		
	}
	/**
	 * 用获取本地线程方式操作
	 * 由于这里是测试 所以要时常关闭sessionfactory以免内存泄露
	 * 而本地线程方式不需要关闭资源
	 * 所以后面测试还是采用获取sessionfactory
	 */
	@Test
	public void GetByIdWithCurrentSessionTest(){
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtils.getCurrentSession();
			tx = session.beginTransaction();
			User user = session.get(User.class, 1);
			
			System.out.println(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally{
			//下面这句不用写
//		session.close();
		}
		
		
	}
}
