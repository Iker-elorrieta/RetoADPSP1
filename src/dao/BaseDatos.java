package dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import modelo.Datos;
import modelo.Estaciones;
import modelo.Hashes;
import modelo.Municipios;
import modelo.Naturales;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.Usuarios;





public class BaseDatos {

	public boolean insertDatos(Datos datos) {			

		try {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
		
			session.save(datos);			
			
			tx.commit();		
			session.close();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
		public boolean insertEstaciones(Estaciones estacion) {			
			System.out.println("d");
			try {

				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
			
				session.save(estacion);			
				
				tx.commit();		
				session.close();

				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		
	}
		
		public boolean insertMunicipios(Municipios municipio) {			

			try {

				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
			
				session.save(municipio);			
				
				tx.commit();		
				session.close();

				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		
	}
		public boolean insertNaturales(Naturales natural) {			

			try {

				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
			
				session.save(natural);			
				
				tx.commit();		
				session.close();

				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
			
		
	}
		
		public boolean insertProvincias(Provincias provincia) {			

			try {

				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
			
				session.save(provincia);			
				
				tx.commit();		
				session.close();

				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		
	}
	
		public boolean insertUbicaciones(Ubicaciones ubicaciones) {			

			try {

				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
			
				session.save(ubicaciones);			
				
				tx.commit();		
				session.close();

				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		
	}
		public boolean insertUsuarios(Usuarios usuario) {			

			try {

				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
			
				session.save(usuario);			
				
				tx.commit();		
				session.close();

				return true;
			} catch (Exception e) {
				// TODO: handle exception
				return false;
			}
		}
		public boolean insertHash(Hashes hash) {
			try {

				SessionFactory sesion = HibernateUtil.getSessionFactory();
				Session session = sesion.openSession();
				Transaction tx = session.beginTransaction();
			
				session.save(hash);			
				
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
