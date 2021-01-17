package JSON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import dao.BaseDatos;
import modelo.Espacios;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;
import modelo.Ubicaciones;
import modelo.UbicacionesId;
import xml.EscribirXml;

public class lecturaDatos {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String urlMunicipios = "https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/herriak.json";
		String urlEspacios = "https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json";
		String urlEstaciones = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json";
		String urlDatos = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/index.json";

		comprobarPagina.comprobarPagina(urlMunicipios);
		comprobarPagina.comprobarPagina(urlEspacios);
		comprobarPagina.comprobarPagina(urlEstaciones);

//		comprobarPagina.comprobarPagina(urlDatos);

		String respuesta = "";
		try {
			respuesta = peticionHttpGetMunicipios(urlMunicipios);
			respuesta = peticionHttpGetEspacios(urlEspacios);
			respuesta = peticionHttpGetEstaciones(urlEstaciones);
			// System.out.println(respuesta);
		} catch (Exception e) {
			// Manejar excepción
			e.printStackTrace();
		}
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
		ArrayList<String> listaCodMuni = new ArrayList<>();
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
					Ubicaciones ubicacion = new Ubicaciones(uId,espacio,municipio);
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

		ArrayList<Municipios> listaEspacios = new ArrayList<>();
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

			} else if (linea.contains("name")) {
				estacion = new Estaciones();
				codEstacion++;
				linea = linea.split(" \"")[2];
				linea = linea.split("\"")[0];
				estacion.setNombre(linea);
			} else if (linea.contains("url")) {
				linea = linea.split(" \"")[1];
				linea = linea.split("\"")[0];
				//estacion.se(linea);

			
				

			}

			resultado.append(linea + "\n");
		}
		
		rd.close();
		bd.insertMunicipios(listaEspacios);
		return resultado.toString();
	}
}
