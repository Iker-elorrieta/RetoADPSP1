package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import org.junit.Test;
import cliente_servidor.Cliente;
import cliente_servidor.VentanaLogin;
import cliente_servidor.VentanaServidor;
import dao.BaseDatos;
import json.LecturaDatos;
import modelo.Datos;
import modelo.DatosId;
import modelo.Espacios;
import modelo.Estaciones;
import modelo.Hashes;
import modelo.Municipios;
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
	public void testInsercionBaseDatosYXml() {
		long millis = System.currentTimeMillis();

		Date fecha = new Date(millis);
		Time hora = new Time(millis);

		Provincias provincia = new Provincias(5, "Bizkaia");
		provincia.setCodProv(6);
		provincia.setNombre("asd");
		provincia.setMunicipioses(null);

		Municipios municipio = new Municipios(provincia, 5, "bil");
		municipio.setCodMuni(1);
		municipio.setDescripcion("asdasd");

		municipio.setNombre("sadasd");

		Espacios natural = new Espacios(5, "natural");
		natural.setCodEspacio(6);
		natural.setDescripcion("asdasd");
		natural.setNombre("asdasd");
		natural.setUbicacioneses(null);
		natural.getUbicacioneses();

		UbicacionesId uid = new UbicacionesId(5, 5);
		uid.setCodEspacio(6);
		uid.setCodMuni(8);
		uid.equals(null);
		uid.equals("s");
		uid.hashCode();
		uid.getClass();
		uid.getCodEspacio();
		uid.getCodMuni();

		Ubicaciones ubicacion = new Ubicaciones();

		Estaciones estacion = new Estaciones();
		estacion.setCodEst(8);
		estacion.setDatoses(null);
		estacion.setDireccion("sadasd");
		estacion.setNombre("asdsd");
		Estaciones estacionPrueba = new Estaciones(999, "nombre", "provincia", "municipio", "direccion", "latitud", "longitud", null);

		DatosId datosId = new DatosId(fecha, hora, estacion.getCodEst());
		datosId.setCodEst(8);
		datosId.setFecha(fecha);
		datosId.equals(null);
		datosId.equals("s");
		datosId.getCodEst();
		datosId.getCodEst();
		datosId.getClass();
		datosId.hashCode();

		Datos dato2 = new Datos(datosId, estacion, null, null, null, null, null, null, null, null, null, null, null,
				null, null);

		dato2.setCo8hmgm3(2.0);
		dato2.setComgm3(5.0);
		dato2.setEstaciones(estacion);
		dato2.setIcaestacion("s");
		dato2.setId(datosId);
		dato2.setNo2(5.0);
		dato2.setNo2ica("");
		dato2.setNogm3(5.0);
		dato2.setNoxgm3(5.0);
		dato2.setPm10(4.0);
		dato2.setPm10ica("");
		dato2.setPm25(4.0);
		dato2.setSo2(4.0);
		dato2.setSo2ica("");

		LecturaDatos ld = new LecturaDatos();
		String[] args = null;
		LecturaDatos.main(args);
		BaseDatos bd = new BaseDatos();
		Municipios municipioPrueba = bd.obtenerMunicipio(1, 1);
		//boolean b = LecturaDatos.main(args);
		//assertEquals(true, b);
	}

	
	@Test
	public void testInsertarHash() {

		BaseDatos bd = new BaseDatos();
		Hashes hash = new Hashes(0, "asd", "s2", "sd");
		boolean b = false;
			b=	bd.insertHash(hash);

		assertEquals(true, b);
	}

	@Test
	public void testInsercionUsuario() {

		BaseDatos bd = new BaseDatos();
		Usuarios usu = new Usuarios();
		Usuarios usuario = new Usuarios("prueba", "prueba");
		boolean b = bd.insertUsuarios(usuario);

		assertEquals(true, b);

	}

//	@Test
//	public void testServidorOK() {
//		
//		cliente_servidor.VentanaServidor s = new cliente_servidor.VentanaServidor();
//		
//		boolean  boo = s.iniciar()
//		
//		
//		
//		
//		assertTrue(boo);
//		
//	}

//	@Test
//	public void testServidorCliente() {
//		boolean b = false;
//		boolean a = false;
//		VentanaServidor servidor = new VentanaServidor();
//		VentanaLogin vCliente = new VentanaLogin();
//		String[] args = null;
//		b = VentanaLogin.main(args);
//		Cliente cliente = new Cliente();
//		Socket s = new Socket();
//	
//		
//		assertEquals(true, b);
//
//	}

}
