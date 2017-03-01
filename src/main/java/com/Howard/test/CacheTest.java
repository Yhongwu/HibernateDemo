package com.Howard.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.Howard.entity.User;
import com.Howard.util.HibernateUtils;

/**
 * hibernate一级缓存
 * 一级缓存默认开启
 * 存储的数据是持久态的 缓存范围是session范围，即session创建到关闭这个范围
 * 二级缓存默认不开启
 * 二级缓存范围是sessionFactory
 * 现在一般很少用 以redis代替
 * @author Howard
 * 2017年2月28日
 */
public class CacheTest {
	/**
	 * 验证一级缓存
	 * 两次查询同一对象 第一次需要执行sql语句 第二次不需要 但是缓存的内容不是对象 类似字符串的
	 * 值 再次取出来的时候重新装成对象返回 所以两次返回不是同一个对象
	 */
	@Test
	public void CheckCacheTest(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		User user_1 = session.get(User.class, 1);
		User user_2 = session.get(User.class, 1);
		System.out.println(user_1);
		System.out.println(user_2);
		
		session.close();
		sessionFactory.close();
		
	}
	/**
	 * 测试一级缓存特性
	 * 每次从数据库取出数据时 把返回user持久态并把对象放到一级缓存中
	 * 同时还会把对象放到一级缓存对应的快照里
	 * 如果此时修改了持久态的对象 同时一级缓存的内容也改了 但是对应的快照不会改
	 * 提交事务时 会把当前持久态对象的缓存与快照区的对象进行比较 如果不同 则进行update 否则不会修改
	 */
	@Test
	public void LevelOneFeatures(){
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		User user_1 = session.get(User.class, 1);

		user_1.setAddress("广州");
		
		//一级缓存特性 这里不需要显式update 会自动更新
//		session.update(user_1);
		tx.commit();
		
		session.close();
		sessionFactory.close();
	}
	
}
