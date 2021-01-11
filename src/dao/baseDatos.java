package dao;

import java.sql.Date;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import modelo.EspaciosNaturales;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincia;
import modelo.Usuarios;



public class baseDatos {

	public boolean insert() {	
		
	

		

		try {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();

		
			//session.save(estaciones);	
			
			
			
			
			
			tx.commit();		
			session.close();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	

	

	public static class HibernateUtil {
		private static final SessionFactory sessionFactory = buildSessionFactory();

		private static SessionFactory buildSessionFactory() {
			try {

				return new Configuration().configure()
						.buildSessionFactory(new StandardServiceRegistryBuilder().configure().build());
			} catch (Throwable ex) {

				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
		}

		public static SessionFactory getSessionFactory() {
			return sessionFactory;
		}
	}

}
