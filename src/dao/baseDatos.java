package dao;

import java.sql.Date;
import java.util.ArrayList;

import org.hibernate.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import modelo.Estaciones;
import modelo.Municipios;
import modelo.Naturales;
import modelo.Provincias;
import modelo.Usuarios;

public class baseDatos {

	public boolean insert() {

		
	
		Estaciones estaciones = new Estaciones();
		estaciones.setDireccion("sdfs");
		estaciones.setLatitud(2.0);
		estaciones.setLongitud(3.0);
		
		Provincias provincias = new Provincias();
		provincias.setNombre("sdfsf");
		
		Municipios municipios = new Municipios();
		municipios.setNombre("sadasd");
		municipios.setDescripcion("asd");
		municipios.getEstacioneses();
		
		Naturales naturales = new Naturales();		
		naturales.setDescripcion("sadas");
		naturales.setNombre("asdas");
		
		Usuarios usuarios = new Usuarios();
		usuarios.setPassword("asds");
		usuarios.setNombre("zsdasdasd");
		
	

		

		try {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();

		
			session.save(estaciones);		
			
			
			session.save(provincias);		
	  		
			
			session.save(municipios);		
			
			session.save(naturales);	
			
			
			
			session.save(usuarios);	
			
			
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
