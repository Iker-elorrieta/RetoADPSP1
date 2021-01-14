package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;

import dao.BaseDatos;
import modelo.Datos;
import modelo.DatosId;
import modelo.Estaciones;
import modelo.Hashes;
import modelo.Municipios;
import modelo.Naturales;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.UbicacionesId;
import modelo.Usuarios;

public class Pruebas {

	@Test
	public void testInsercionHash() {

		BaseDatos bd = new BaseDatos();
		Hashes hash = new Hashes(0, "asd", "s2", "sd");
		boolean b = bd.insertHash(hash);

		assertEquals(true, b);
	}

	@Test
	public void testInsercionUsuario() {

		BaseDatos bd = new BaseDatos();
		Usuarios usuario = new Usuarios(0, "prueba", "abc");
		boolean b = bd.insertUsuarios(usuario);

		assertEquals(true, b);
	}

	@Test
	public void testInsercionDato() {

		BaseDatos bd = new BaseDatos();

		long millis = System.currentTimeMillis();

		Date fecha = new Date(millis);
		Time hora = new Time(millis);

		Provincias provincia = new Provincias(5, "Bizkaia");
		provincia.setCodProv(6);
		provincia.setNombre("asd");
		provincia.setMunicipioses(null);
		bd.insertProvincias(provincia);

		Municipios municipio = new Municipios(5, provincia, "bil");
		municipio.setCodMuni(1);
		municipio.setDescripcion("asdasd");
		municipio.setEstacioneses(null);
		municipio.setNombre("sadasd");
		bd.insertMunicipios(municipio);

		Naturales natural = new Naturales(5, "natural");
		natural.setCodEspacio(6);
		natural.setDescripcion("asdasd");
		natural.setNombre("asdasd");
		natural.setUbicacioneses(null);
		natural.getUbicacioneses();
		bd.insertNaturales(natural);

		UbicacionesId uid = new UbicacionesId(5, 5);
		uid.setCodEspacio(6);
		uid.setCodMuni(8);
		uid.equals(null);
		uid.equals("s");
		uid.hashCode();
		uid.getClass();
		uid.getCodEspacio();
		uid.getCodMuni();

		Ubicaciones ubicacion = new Ubicaciones(uid, municipio, natural);
		bd.insertUbicaciones(ubicacion);

		Estaciones estacion = new Estaciones(5, municipio, "d", "s", 2.0, 2.0, null);
		estacion.setCodEst(8);
		estacion.setDatoses(null);
		estacion.setDireccion("sadasd");
		estacion.setLatitud(2.0);
		estacion.setLongitud(5.3);
		estacion.setNombre("asdsd");
		bd.insertEstaciones(estacion);

		DatosId datosId = new DatosId(fecha, hora.toString(), 0);
		datosId.setCodEst(8);
		datosId.setHora("15:00");
		datosId.setFecha(fecha);
		datosId.equals(null);
		datosId.equals("s");
		datosId.getCodEst();
		datosId.getCodEst();
		datosId.getClass();
		datosId.hashCode();

		Datos dato2 = new Datos(datosId, estacion, 3, 3, 3, 3, "sd", 3, 3, 3, 3);

		dato2.setDireccionViento("sadasd");
		dato2.setH(5);
		dato2.setNo2gm3(8);
		dato2.setNogm3(5);
		dato2.setPm10gm3(8);
		dato2.setPrecipitaciones(9);
		dato2.setTemperatura(7);
		dato2.setVelocidadViento(2);

		boolean b = bd.insertDatos(dato2);

		assertEquals(true, b);

	}

}
