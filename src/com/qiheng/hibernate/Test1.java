package com.qiheng.hibernate;

import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Test1 {
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
		
		Person person1 = new Person();
		person1.setAddress("Los Angles");
		
		Person person2 = new Person();
		person2.setAddress("Bacelona");
		
		Team team1 = new Team();
		team1.setTeamName("Lakers");
		team1.setMates(new HashMap());
		team1.getMates().put("Kobe", person1);
		team1.getMates().put("Shaq", person2);
		person1.setTeam(team1);
		person2.setTeam(team1);
		
		
		try{
			tx = session.beginTransaction();
			session.save(team1);
			
			tx.commit();
		}catch (Exception e) {
			e.printStackTrace();
			if(tx!=null){
				tx.rollback();
			}
		
		
		}finally{
			session.close();
		}
		
	}
}

