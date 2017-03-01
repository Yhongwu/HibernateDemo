package com.Howard.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.Howard.entity.User;
import com.Howard.util.HibernateUtils;

public class HelloTest {
	
	@Test
	public void testAdd(){
		//1、加载hibernate核心配置文件
		//默认在src下找hibernate.cfg.xml
//		Configuration cfg = new Configuration().configure();
		Configuration cfg = new Configuration().configure("hibernate01.cfg.xml");
		//2、创建SessionFactory对象
		//创建该对象时会根据映射文件建表 比较消耗资源
		//一般一个项目建议建立一个 web项目一般不会close 
		//这里是测试 经常启动关闭 所以要close 以免泄露内存
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//3、使用SessionFactory创建Session对象
		Session session = sessionFactory.openSession();
		//4、开启事务
		Transaction tx = session.beginTransaction();
		//5、写具体crud操作
		User user = new User();
		user.setAddress("深圳");
		user.setUsername("tom");
		session.save(user);
		//6、提交事务
		tx.commit();
		//7、关闭资源
		session.close();
		sessionFactory.close();
	}
	/**
	 * 事务的规范写法
	 * try..catch..finally
	 * 回滚
	 */
	@Test
	public void testStandard(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			User user = session.get(User.class, 4);
			user.setAddress("广州");
			//抛异常 回滚
			int i = 10/0;
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
