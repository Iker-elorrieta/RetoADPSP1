package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import modelo.Datos;
import modelo.Espacios;
import modelo.Estaciones;
import modelo.Hashes;
import modelo.Municipios;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.Usuarios;

public class BaseDatos {

	public boolean insertDatos(ArrayList<Datos> datos) {
		try {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();
			for (Datos dato : datos) {
				session.save(dato);
			}
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
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertMunicipios(List<Municipios> listaMunicipios) {
		try {
			SessionFactory sesion = HibernateUtil.getSessionFactory();
			Session session = sesion.openSession();
			Transaction tx = session.beginTransaction();			
		
			for (Municipios municipio : listaMunicipios) {
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
			e.printStackTrace();
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

	public Usuarios obtenerUsuario(String nombre, String contrasena) {
		Usuarios usuario = null;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = " from Usuarios where Nombre = '" + nombre + "' and Password = '" + contrasena + "'";
		Query q = session.createQuery(hql);
		usuario = (Usuarios) q.uniqueResult();
		session.close();
		return usuario;
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

	public ArrayList<Provincias> obtenerProvincias() {
		Provincias provincia = null;
		ArrayList<Provincias> provincias = new ArrayList<Provincias>();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = " from Provincias";
		Query q = session.createQuery(hql);
		List<Provincias> prov = q.list();
		for (int x = 0; x < prov.size(); x++) {
			provincia = prov.get(x);
			provincias.add(provincia);
		}
		session.close();
		return provincias;
	}

	public List<Espacios> obtenerEspacios(Integer codMuni, Integer codProv) {
		Espacios espacio = null;
		Ubicaciones ubicacion = null;
		String hql = "";
		List<Espacios> espacios = null ;
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		if (codMuni == null && codProv == null) {
			hql = " from Espacios";
			Query q = session.createQuery(hql);
			espacios = q.list();
			
		} else if (codMuni == null && codProv != null) {
			hql = " from Ubicaciones where municipios.provincias.codProv = " + codProv;
			Query q = session.createQuery(hql);
			List<Ubicaciones> ubi = q.list();
			for (int x = 0; x < ubi.size(); x++) {
				ubicacion = ubi.get(x);
				hql = " from Espacios where codEspacio = " + ubicacion.getEspacios().getCodEspacio();
				Query q2 = session.createQuery(hql);
				espacios.add( (Espacios) q2.list());
				
				
			}
		}
		session.close();
		return espacios;
	}

	public ArrayList<Ubicaciones> obtenerUbicaciones() {
		Ubicaciones ubicacion = null;
		ArrayList<Ubicaciones> ubicaciones = new ArrayList<Ubicaciones>();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();

		String hql = " from Ubicaciones";
		Query q = session.createQuery(hql);
		List<Ubicaciones> ubi = q.list();
		for (int x = 0; x < ubi.size(); x++) {
			ubicacion = ubi.get(x);
			ubicaciones.add(ubicacion);
		}
		session.close();
		return ubicaciones;
	}

	public ArrayList<Estaciones> obtenerEstaciones(Integer codProv, Integer codMuni) {
		Estaciones estacion = null;
		ArrayList<Estaciones> estaciones = new ArrayList<Estaciones>();
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "";
		if (codProv == null && codMuni == null) {
			hql = " from Estaciones";
			Query q = session.createQuery(hql);
			List<Estaciones> esta = q.list();
			for (int x = 0; x < esta.size(); x++) {
				estacion = esta.get(x);
				estaciones.add(estacion);
				session.close();
			}
		} else if (codMuni == null && codProv != null) {
			hql = " from Estaciones where municipios.provincias.codProv = " + codProv;
			Query q = session.createQuery(hql);
			List<Estaciones> esta = q.list();
			for (int x = 0; x < esta.size(); x++) {
				estacion = esta.get(x);
				estaciones.add(estacion);
			}
		}
		session.close();
		return estaciones;
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

	public List<Municipios> obtenerMunicipios(ArrayList<Integer> codigosMuni, ArrayList<Integer> codigosProv) {
		SessionFactory sesion = HibernateUtil.getSessionFactory();
		Session session = sesion.openSession();
		String hql = "";
		List<Municipios> muni = null;

		if (codigosMuni == null && codigosProv == null) {
			hql = "from Municipios";
			Query q = session.createQuery(hql);
			muni = q.list();
		}

		else if (codigosMuni == null && codigosProv != null) {
			hql = "from Municipios where provincias.codProv = " + codigosProv.get(0);
			Query q = session.createQuery(hql);
			muni = q.list();

		} else {
			for (int i = 0; i < codigosMuni.size(); i++) {
				hql = " from Municipios where CodProv = " + codigosProv.get(i) + " and CodMuni = " + codigosMuni.get(i)
						+ "";
				Query q = session.createQuery(hql);
				muni = q.list();
			}
		}
		session.close();
		return muni;
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
