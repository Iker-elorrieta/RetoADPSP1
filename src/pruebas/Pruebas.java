package pruebas;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Time;

import static org.junit.Assert.assertFalse;

import org.junit.jupiter.api.Test;

import dao.BaseDatos;
import modelo.Datos;
import modelo.DatosId;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Naturales;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.UbicacionesId;

public class Pruebas {

	@Test
	public void testInsercionNaturales() {
		BaseDatos bd = new BaseDatos();

		Provincias provincia = new Provincias(0, "b");
		bd.insertProvincias(provincia);

		Municipios municipio = new Municipios(0, provincia, "bil");
		bd.insertMunicipios(municipio);

		Naturales natural = new Naturales(0, "natural");
		boolean b = bd.insertNaturales(natural);

		assertEquals(true, b);
	}

	@Test
	public void testInsercionUbicacion() {
		BaseDatos bd = new BaseDatos();

		Provincias provincia = new Provincias(1, "b");
		bd.insertProvincias(provincia);

		Municipios municipio = new Municipios(1, provincia, "bil");
		bd.insertMunicipios(municipio);

		Naturales natural = new Naturales(1, "natural");
		bd.insertNaturales(natural);

		UbicacionesId uid = new UbicacionesId(0, 0);
		Ubicaciones ubicacion = new Ubicaciones(uid, municipio, natural);
		boolean b = bd.insertUbicaciones(ubicacion);

		assertEquals(true, b);
	}

	@Test
	public void testInsercionEstacion() {
		BaseDatos bd = new BaseDatos();

		Provincias provincia = new Provincias(2, "b");
		bd.insertProvincias(provincia);

		Municipios municipio = new Municipios(2, provincia, "bil");
		bd.insertMunicipios(municipio);

		Naturales natural = new Naturales(2, "natural");
		bd.insertNaturales(natural);

		Estaciones estacion = new Estaciones(0, municipio, "d", "s", 2.0, 2.0, null);
		boolean b = bd.insertEstaciones(estacion);

		assertEquals(true, b);
	}

	@Test
	public void testInsercionProvincia() {

		BaseDatos bd = new BaseDatos();

		Provincias provincia = new Provincias(3, "a");
		boolean b = bd.insertProvincias(provincia);

		assertEquals(true, b);
	}

	@Test
	public void testInsercionMunicipio() {
		BaseDatos bd = new BaseDatos();

		Provincias provincia = new Provincias(4, "b");
		bd.insertProvincias(provincia);

		Municipios municipio = new Municipios(4, provincia, "bil");
		boolean b = bd.insertMunicipios(municipio);

		assertEquals(true, b);
	}

	@Test
	public void testInsercionDato() {

		BaseDatos bd = new BaseDatos();
		Datos datos = new Datos();

		long millis = System.currentTimeMillis();

		Date fecha = new Date(millis);
		Time hora = new Time(millis);

		Provincias provincia = new Provincias(5, "Bizkaia");
		bd.insertProvincias(provincia);

		Municipios municipio = new Municipios(5, provincia, "bil");
		bd.insertMunicipios(municipio);

		Naturales natural = new Naturales(5, "natural");
		bd.insertNaturales(natural);

		UbicacionesId uid = new UbicacionesId(5,5);
		Ubicaciones ubicacion = new Ubicaciones(uid, municipio, natural);
		bd.insertUbicaciones(ubicacion);

		Estaciones estacion = new Estaciones(5, municipio, "d", "s", 2.0, 2.0, null);
		bd.insertEstaciones(estacion);

		DatosId datosId = new DatosId(fecha, hora.toString(), 0);

		Datos dato2 = new Datos(datosId, estacion, 3, 3, 3, 3, "sd", 3, 3, 3, 3);

		boolean b = bd.insertDatos(dato2);

		assertEquals(true, b);

	}

	@Test
	public void testServidorOK() {
		String[] args = null;
		PSP.Servidor.main(args);
		PSP.Servidor s = new PSP.Servidor();

		boolean boo = s.iniciar();

		assertTrue(boo);

	}

	@Test
	public void testServidorMAL1() {
		PSP.Servidor s1 = new PSP.Servidor();

		boolean boo = s1.conectar("33", "jdbc:mysql://localhost:3306/euskalmet");

		assertFalse(boo);

	}

	@Test
	public void testServidorMAL2() {
		PSP.Servidor s2 = new PSP.Servidor();

		boolean boo = s2.conectar("com.mysql.jdbc.Driver", "33");

		assertFalse(boo);

	}

	@Test
	public void testCliente() {

		PSP.Servidor sc = new PSP.Servidor();
		sc.iniciar();

		String[] args = null;
		PSP.Cliente.main(args);
		PSP.Cliente c = new PSP.Cliente();

		boolean boo = c.iniciar();

		assertTrue(boo);
	}

}
