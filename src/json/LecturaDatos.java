package json;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Time;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dao.BaseDatos;
import modelo.Datos;
import modelo.DatosId;

import modelo.Espacios;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.UbicacionesId;
import xml.EscribirXml;

public class LecturaDatos {
	static ArrayList<Estaciones> listaEstaciones = new ArrayList<>();

	public static boolean main(String[] args) {
		// TODO Auto-generated method stub
		String urlMunicipios = "https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/herriak.json";
		String urlEspacios = "https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json";
		String urlEstaciones = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json";
		String urlDatos = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json";

		ComprobarPagina.comprobarPagina(urlMunicipios);
		ComprobarPagina.comprobarPagina(urlEspacios);
		ComprobarPagina.comprobarPagina(urlEstaciones);
		ComprobarPagina.comprobarPagina(urlDatos);

		String respuesta = "";
		try {
			respuesta = peticionHttpGetMunicipios(urlMunicipios);
			respuesta = peticionHttpGetEspacios(urlEspacios);
			respuesta = peticionHttpGetEstaciones(urlEstaciones);
			respuesta = peticionHttpGetDatos(urlDatos);
			// System.out.println(respuesta);
		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
		return true;
	}

	public static String peticionHttpGetMunicipios(String urlParaVisitar) throws Exception {

		ArrayList<Municipios> listaMunicipios = new ArrayList<>();
		ArrayList<Provincias> listProvincias = new ArrayList<>();
		String nombreProv = "";
		String nombreMuni = "";
		int codMuni = 0;
		BaseDatos bd = new BaseDatos();
		Municipios municipio = new Municipios();

		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;

		while ((linea = rd.readLine()) != null) {

			if (linea.contains("jsonCallback")) {
				linea = linea.split(" ")[1];
			} else if (linea.contains("]")) {
				linea = linea.split(" ")[0];

			} else if (linea.contains("municipality") & nombreMuni == "") {
				municipio = new Municipios();
				linea = linea.split("\" ")[1];
				linea = linea.split(" \"")[1];
				linea = linea.split(" ")[0];
				linea = linea.split("\"")[0];
				nombreMuni = linea;
				municipio.setNombre(nombreMuni);

			} else if (linea.contains("municipalitycode")) {
				linea = linea.split(" \"")[2];
				linea = linea.split(" ")[0];
				linea = linea.split(",")[0];
				linea = linea.split("\"")[0];
				codMuni = Integer.parseInt(linea);
				municipio.setCodMuni(codMuni);
				nombreMuni = "";

			} else if (linea.contains("turismDescription")) {
				linea = linea.split(" \"")[2];
				municipio.setDescripcion(linea);

			} else if (linea.contains("territory") & nombreProv == "") {
				linea = linea.split("\" ")[1];
				linea = linea.split(" \"")[1];
				linea = linea.split(" ")[0];
				linea = linea.split("\"")[0];

				nombreProv = linea;

			} else if (linea.contains("territorycode")) {
				linea = linea.split(" \"")[2];
				linea = linea.split(" ")[0];
				linea = linea.split(",")[0];
				linea = linea.split("\"")[0];
				int codProv = Integer.parseInt(linea);
				Provincias provincia = bd.obtenerProvincia(codProv);

				if (provincia == null) {
					provincia = new Provincias(codProv, nombreProv);
					bd.insertProvincias(provincia);
					listProvincias.add(provincia);
					municipio.setProvincias(provincia);
				}

				municipio.setProvincias(provincia);
				nombreProv = "";
				listaMunicipios.add(municipio);
			}
			resultado.append(linea + "\n");
		}

		rd.close();
		bd.insertMunicipios(listaMunicipios);

		EscribirXml ex = new EscribirXml();
		ex.generarXmlMunicipios(listaMunicipios);
		ex.generarXmlProvincias(listProvincias);

		return resultado.toString();
	}

	public static String peticionHttpGetEspacios(String urlParaVisitar) throws Exception {

		ArrayList<Espacios> listaEspacios = new ArrayList<>();
		ArrayList<Ubicaciones> listaUbicaciones = new ArrayList<>();
		ArrayList<Integer> codigosMuniFiltrado = new ArrayList<>();
		ArrayList<Integer> codigosProvFiltrado = new ArrayList<>();
		String[] codigosMuni = null;
		int codMuni = 0;
		int codEspacio = 0;
		BaseDatos bd = new BaseDatos();
		Espacios espacio = new Espacios();

		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;

		while ((linea = rd.readLine()) != null) {

			if (linea.contains("jsonCallback")) {
				linea = linea.split(" ")[1];
			} else if (linea.contains("]")) {
				linea = linea.split(" ")[0];

			} else if (linea.contains("documentName")) {
				espacio = new Espacios();
				codEspacio++;
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				espacio.setNombre(linea);
				espacio.setCodEspacio(codEspacio);
			} else if (linea.contains("turismDescription\" : \"<p>")) {
				linea = linea.split(" \"")[2];
				espacio.setDescripcion(linea);

			} else if (linea.contains("natureType")) {
				linea = linea.split("\" ")[1];
				linea = linea.split(" \"")[1];
				linea = linea.split(" ")[0];
				linea = linea.split("\"")[0];
				espacio.setTipo(linea);
				listaEspacios.add(espacio);

			} else if (linea.contains("municipalitycode")) {
				linea = linea.split(" \"")[2];
				linea = linea.split(",")[0];
				linea = linea.split("\"")[0];
				codigosMuni = linea.split(" ");

			} else if (linea.contains("territorycode")) {
				linea = linea.split(" \"")[2];
				linea = linea.split(",")[0];
				linea = linea.split("\"")[0];
				String[] codigosProv = linea.split(" ");
				int aux = 0;
				int codigo;
				codigosProvFiltrado = new ArrayList<Integer>();
				codigosMuniFiltrado = new ArrayList<Integer>();

				for (int i = 0; i < codigosMuni.length; i++) {
					codigo = Integer.parseInt(codigosMuni[i]);
					if (codigo != aux) {
						codigosMuniFiltrado.add(codigo);
						codigosProvFiltrado.add(Integer.parseInt(codigosProv[i]));
						aux = codigo;
					}
				}
				ArrayList<Municipios> municipios = bd.obtenerMunicipios(codigosMuniFiltrado, codigosProvFiltrado);
				for (Municipios municipio : municipios) {
					UbicacionesId uId = new UbicacionesId(codEspacio, municipio.getCodMuni());
					Ubicaciones ubicacion = new Ubicaciones(uId, espacio, municipio);
					listaUbicaciones.add(ubicacion);
				}
			}

			resultado.append(linea + "\n");
		}
		EscribirXml ex = new EscribirXml();
		ex.generarXmlEspacios(listaEspacios);
		bd.insertEspacios(listaEspacios);
		bd.insertUbicaciones(listaUbicaciones);
		rd.close();
		return resultado.toString();
	}

	public static String peticionHttpGetEstaciones(String urlParaVisitar) throws Exception {

		int codEstacion = 0;
		BaseDatos bd = new BaseDatos();
		Estaciones estacion = new Estaciones();

		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;

		while ((linea = rd.readLine()) != null) {

			if (linea.contains("jsonCallback")) {
				linea = linea.split(" ")[1];

			} else if (linea.contains("]")) {
				linea = linea.split(" ")[0];

			} else if (linea.contains("Name")) {
				estacion = new Estaciones();
				codEstacion++;
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				estacion.setCodEst(codEstacion);
				estacion.setNombre(linea);

			} else if (linea.contains("Province")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				estacion.setProvincia(linea);

			} else if (linea.contains("Town")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				estacion.setMunicipio(linea);

			} else if (linea.contains("Address")) {
				linea = linea.split("\" ")[1];
				linea = linea.split(" \"")[1];
				linea = linea.split("\"")[0];
				estacion.setDireccion(linea);

			} else if (linea.contains("Latitude")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				estacion.setLatitud(linea);

			} else if (linea.contains("Longitude")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				estacion.setLongitud(linea);
				listaEstaciones.add(estacion);
			}

			resultado.append(linea + "\n");
		}
		bd.insertEstaciones(listaEstaciones);
		EscribirXml ex = new EscribirXml();
		ex.generarXmlEstaciones(listaEstaciones);
		rd.close();
		return resultado.toString();
	}

	public static String peticionHttpGetDatos(String urlParaVisitar) throws Exception {

		Estaciones estacion = null;
		String nombreEstacion = "";
		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
		while ((linea = rd.readLine()) != null) {

			if (linea.contains("jsonCallback")) {
				linea = linea.split(" ")[1];
			} else if (linea.contains("]")) {
				linea = linea.split(" ")[0];
			} else if (linea.contains("name")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				nombreEstacion = linea;

			} else if (linea.contains("url") & linea.contains("datos_indice")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				String urlIndice = linea;
				for (Estaciones estacionAux : listaEstaciones) {
					if (estacionAux.getNombre().equals(nombreEstacion.replace("_", " "))) {
						estacion = estacionAux;
						break;
					}
				}
				peticionHttpGetDatosIndice(urlIndice, estacion);

			}

			resultado.append(linea + "\n");
		}

		rd.close();

		return resultado.toString();
	}

	public static String peticionHttpGetDatosIndice(String urlParaVisitar, Estaciones estacion) throws Exception {

		ArrayList<Datos> listaDatos = new ArrayList<>();

		BaseDatos bd = new BaseDatos();
		Datos dato = new Datos();
		dato.setEstaciones(estacion);
		DatosId datoId = null;
		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);

		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
		while ((linea = rd.readLine()) != null) {

			if (linea.contains("jsonCallback")) {
				linea = linea.split(" ")[1];
			} else if (linea.contains("]")) {
				linea = linea.split(" ")[0];

			} else if (linea.contains("Date")) {
				datoId = new DatosId();
				datoId.setCodEst(estacion.getCodEst());

				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
				Date date = formatter1.parse(linea);
				datoId.setFecha(date);

			} else if (linea.contains("Hour")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm");
				Long millis = formatter1.parse(linea).getTime();
				Time hora = new Time(millis);
				datoId.setHora(hora);
				dato.setId(datoId);

			} else if (linea.contains("COmgm3")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setComgm3(Double.parseDouble(linea.replace(",", ".")));

			} else if (linea.contains("CO8hmgm3")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setCo8hmgm3(Double.parseDouble(linea.replace(",", ".")));

			} else if (linea.contains("NOgm3")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setNogm3(Double.parseDouble(linea.replace(",", ".")));
			} else if (linea.contains("NO2\"")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setNo2(Double.parseDouble(linea.replace(",", ".")));

			} else if (linea.contains("NO2ICA")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setNo2ica(linea);
			} else if (linea.contains("NOXgm3")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setNoxgm3(Double.parseDouble(linea.replace(",", ".")));
			} else if (linea.contains("PM10\"")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setPm10(Double.parseDouble(linea.replace(",", ".")));
			} else if (linea.contains("PM10ICA")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setPm10ica(linea);
			} else if (linea.contains("PM25\"")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setPm25(Double.parseDouble(linea.replace(",", ".")));
			} else if (linea.contains("PM25ICA")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setPm25ica(linea);
			} else if (linea.contains("SO2\"")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setSo2(Double.parseDouble(linea.replace(",", ".")));
			} else if (linea.contains("SO2ICA")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setSo2ica(linea);

			} else if (linea.contains("ICAEstacion")) {
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				dato.setIcaestacion(linea);

				listaDatos.add(dato);
			}

			resultado.append(linea + "\n");
		}

		bd.insertDatos(listaDatos);
		EscribirXml xml = new EscribirXml();
		xml.generarXmlDatos(listaDatos);
		rd.close();

		return resultado.toString();
	}
}
