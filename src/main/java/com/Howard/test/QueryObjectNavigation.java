package com.Howard.test;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import com.Howard.entity.Customer;
import com.Howard.entity.Employe;
import com.Howard.entity.LinkMan;
import com.Howard.entity.Role;
import com.Howard.util.HibernateUtils;

/**
 * 对象导航查询   OID查询 
 * @author Howard
 * 2017年3月1日
 */
public class QueryObjectNavigation {

	@Test
	public void testSelect(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		try {
			sessionFactory = HibernateUtils.getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			
			//OID查询 调用session的Get方法
			Customer customer = session.get(Customer.class, 1);
			//对象导航查询 通过客户查询联系人
			Set<LinkMan> linkMans = customer.getLinkMans();
			System.out.println(linkMans.size());
			for (Iterator iterator = linkMans.iterator(); iterator.hasNext();) {
				LinkMan linkMan = (LinkMan) iterator.next();
				System.out.println(linkMan);
				
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
