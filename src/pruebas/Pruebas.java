package pruebas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import org.junit.Test;
import aplicacion.Aplicacion;
import dao.BaseDatos;
import json.LecturaDatos;
import modelo.Datos;
import modelo.Espacios;
import modelo.Estaciones;
import modelo.Favesp;
import modelo.FavespId;
import modelo.Favmun;
import modelo.FavmunId;
import modelo.Fotoesp;
import modelo.FotoespId;
import modelo.Fotomun;
import modelo.FotomunId;
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
		Ubicaciones ubicacion = new Ubicaciones();
		Espacios natural = new Espacios();
		natural.setCodEspacio(6);
		natural.setDescripcion("asdasd");
		natural.setNombre("asdasd");
		natural.setUbicacioneses(null);
		natural.getUbicacioneses();

		UbicacionesId uid = new UbicacionesId(5, 5);
		uid.setCodEspacio(6);

		uid.equals(null);
		uid.equals("s");
		uid.hashCode();
		uid.getClass();
		uid.getCodEspacio();
		uid.getCodMuniAuto();

		Estaciones estacion = new Estaciones();
		estacion.setCodEst(8);
		estacion.setDatoses(null);
		estacion.setDireccion("sadasd");
		estacion.setNombre("asdsd");
		Estaciones estacionPrueba = new Estaciones(null);

		Datos dato2 = new Datos(null, null, null);

		dato2.setCo8hmgm3(2.0);
		dato2.setComgm3(5.0);
		dato2.setEstaciones(estacion);
		dato2.setIcaestacion("s");
		dato2.setNo2(5.0);
		dato2.setNo2ica("");
		dato2.setNogm3(5.0);
		dato2.setNoxgm3(5.0);
		dato2.setPm10(4.0);
		dato2.setPm10ica("");
		dato2.setPm25(4.0);
		dato2.setSo2(4.0);
		dato2.setSo2ica("");
		Usuarios usu = new Usuarios();
		Usuarios usuario = new Usuarios("prueba", "prueba");
		Favesp f1 = new Favesp();
		FavespId fId = new FavespId(5, 5);
		Favesp f2 = new Favesp(fId, natural, usu);
		FotoespId fid = new FotoespId();
		FotoespId fid2 = new FotoespId(6, 5, null);
		Fotoesp fsp = new Fotoesp(fid2, natural, usuario);
		FotomunId fnu = new FotomunId(0, 0, null);
		Fotomun fnu2 = new Fotomun(fnu, municipio, usuario);
		FavmunId fmFavmunId = new FavmunId(6, 6);
		Favmun fm = new Favmun(fmFavmunId, municipio, usuario);
		LecturaDatos ld = new LecturaDatos();
		String[] args = null;
		LecturaDatos.main(args);
		BaseDatos bd = new BaseDatos();
		
		f1.getClass();
		f2.equals(null);
		fid.getClass();
		fid.getFoto();
		fnu.getClass();
		fnu.getCodUsu();
		fm.equals(null);
		fm.getClass();
		fm.getId();
		f1.getEspacios();
		
		// boolean b = LecturaDatos.main(args);
		// assertEquals(true, b);
	}

	@Test
	public void testInsertarHash() {

		BaseDatos bd = new BaseDatos();
		Hashes hash = new Hashes(0, "asd", "s2", "sd");
		boolean b = false;
		b = bd.insertHash(hash);

		assertEquals(true, b);
	}

	@Test
	public void testObtenerEspacios() {

		BaseDatos bd = new BaseDatos();
		ArrayList<Espacios> espacios = bd.obtenerEspacios(5, 1);
		ArrayList<Espacios> espacios2 = bd.obtenerEspacios(null, 1);
		ArrayList<Espacios> espacios3 = bd.obtenerEspacios(null, null);
		ArrayList<Espacios> espaciosAux = new ArrayList<>();

		assertEquals(espaciosAux.getClass(), espacios.getClass());
	}

	@Test
	public void testObtenerEstaciones() {

		BaseDatos bd = new BaseDatos();
		ArrayList<Estaciones> estaciones = bd.obtenerEstaciones(1, 1);
		ArrayList<Estaciones> estaciones2 = bd.obtenerEstaciones(null, 1);
		ArrayList<Estaciones> estacionesAux = new ArrayList<>();

		assertEquals(estacionesAux.getClass(), estaciones.getClass());
	}

	@Test
	public void testObtenerUsuario() {

		BaseDatos bd = new BaseDatos();

		Usuarios b = bd.obtenerUsuario("", "");

		assertEquals(null, b);
	}

	@Test
	public void testObtenerUbicaciones() {

		BaseDatos bd = new BaseDatos();

		ArrayList<Ubicaciones> b = bd.obtenerUbicaciones();
		ArrayList<Ubicaciones> ubicacionessAux = new ArrayList<>();

		assertEquals(ubicacionessAux.getClass(), b.getClass());
	}
	@Test
	public void testObtenerMunicipio() {

		BaseDatos bd = new BaseDatos();
		ArrayList<Integer> codm = new ArrayList<>();
		codm.add(1);

		Municipios municipio = bd.obtenerMunicipio(1, 1);
		
		assertEquals(1, municipio.getCodMuni());
	}
//	@Test
//	public void testObtenerMunicipios() {
//
//		BaseDatos bd = new BaseDatos();
//		ArrayList<Integer> codm = new ArrayList<>();
//		codm.add(1);
//
//		ArrayList<Municipios> espacios = bd.obtenerMunicipios(codm, codm);
//		ArrayList<Municipios> espacios2 = bd.obtenerMunicipios(null, codm);
//		ArrayList<Municipios> espacios3 = bd.obtenerMunicipios(null, null);
//		ArrayList<Municipios> espaciosAux = new ArrayList<>();
//
//		assertEquals(espaciosAux.getClass(), espacios.getClass());
//	}

	@Test
	public void testInsercionUsuario() {

		BaseDatos bd = new BaseDatos();
		Usuarios usu = new Usuarios();
		Usuarios usuario = new Usuarios("prueba", "prueba");
		boolean b = bd.insertUsuarios(usuario);

		assertEquals(true, b);

	}

	@Test
	public void testServidorCliente() {
		boolean b = Aplicacion.PruebasServidorCliente();

		assertEquals(true, b);

	}

}
