package com.staging.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	public synchronized static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			
			StandardServiceRegistry registry =
					new StandardServiceRegistryBuilder().configure().build();
			
			Metadata meta = new MetadataSources(registry)
					.getMetadataBuilder()
					.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
					.build();
			
			sessionFactory = meta.getSessionFactoryBuilder().build();
		}
		return sessionFactory;
	}
	
	public static Session openSession() {
		return getSessionFactory().openSession();
	}
}