package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import modelo.Datosdiarios;
import modelo.Datoshorarios;
import modelo.Espacios;
import modelo.Estaciones;
import modelo.Hashes;
import modelo.Municipios;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.Usuarios;

public class BaseDatos {

	SessionFactory sesion = HibernateUtil.getSessionFactory();
	Session session = sesion.openSession();
	Transaction tx = session.beginTransaction();

	public boolean insertDatosHorarios(Datoshorarios datos) {

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

	public boolean insertDatosDiarios(Datosdiarios datos) {

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

	public boolean insertEstaciones(ArrayList<Estaciones> estaciones) {
		try {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			for (Estaciones estacion : estaciones) {
				session.save(estacion);
			}

			tx.commit();
			session.close();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean insertMunicipios(ArrayList<Municipios> municipios) {

		try {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();

			for (Municipios municipio : municipios) {
				// System.out.println(municipio.getCodMuni()+municipio.getDescripcion()+municipio.getNombre()+municipio.getProvincias().getNombre());
				session.save(municipio);
			}

			tx.commit();
			session.close();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}

	public boolean insertEspacios(ArrayList<Espacios> espacios) {

		try {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();

			for (Espacios espacio : espacios) {
				session.save(espacio);
			}

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

	public boolean insertUbicaciones(ArrayList<Ubicaciones> ubicaciones) {

		try {

			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			for (Ubicaciones ubicacion : ubicaciones) {
				session.save(ubicacion);
			}

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

	public Provincias obtenerProvincia(int cod) {
		Provincias provincia = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = " from Provincias where CodProv = " + cod + "";

		Query q = session.createQuery(hql);
		provincia = (Provincias) q.uniqueResult();

		session.close();
		return provincia;
	}

	public Provincias obtenerProvincias(int cod) {
		Provincias provincia = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = " from Provincias where CodProv = " + cod + "";

		Query q = session.createQuery(hql);
		provincia = (Provincias) q.uniqueResult();

		session.close();
		return provincia;
	}

	public Municipios obtenerMunicipio(int codMuni, int codProv) {
		Municipios municipio = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = " from Municipios where CodProv = " + codProv + " and CodMuni = " + codMuni + "";
		Query q = session.createQuery(hql);
		municipio = (Municipios) q.uniqueResult();

		session.close();
		return municipio;
	}

	public ArrayList<Municipios> obtenerMunicipios(ArrayList<Integer> codigosMuni, ArrayList<Integer> codigosProv) {
		Municipios municipio = null;
		ArrayList<Municipios> municipios = new ArrayList<Municipios>();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		for (int i = 0; i < codigosMuni.size(); i++) {
			String hql = " from Municipios where CodProv = " + codigosProv.get(i) + " and CodMuni = "
					+ codigosMuni.get(i) + "";
			Query q = session.createQuery(hql);
			List<Municipios> emple = q.list();

			for (int x = 0; x < emple.size(); x++) {

				municipio = emple.get(i);
				municipios.add(municipio);
			}

		}

		session.close();

		return municipios;
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
