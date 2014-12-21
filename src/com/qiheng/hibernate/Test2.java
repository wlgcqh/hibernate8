package com.qiheng.hibernate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Test2 {
	public static SessionFactory sessionFactory;

	static {
		try {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
					.applySettings(cfg.getProperties()).buildServiceRegistry();
			sessionFactory = cfg.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		Team team = null;

		
		try{
			tx = session.beginTransaction();
			team = (Team) session.get(Team.class,"402880e54a6b7b5f014a6b7b60590000" );
			
			

			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
		
		
		}finally{
			session.close();
		}
		System.out.println(team.getMates());
	}
}

