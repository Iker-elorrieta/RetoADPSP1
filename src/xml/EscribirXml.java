package xml;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import modelo.Datos;
import modelo.Espacios;
import modelo.Estaciones;
import modelo.Municipios;
import modelo.Provincias;

public class EscribirXml {

	public EscribirXml() {

	}

	public boolean generarXmlMunicipios(ArrayList<Municipios> municipios) {

		File xml = new File("xmlMunicipios.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			// definimos el elemento raíz del documento
			Element eMunicipios = doc.createElement("municipios");
			doc.appendChild(eMunicipios);
			for (Municipios municipio : municipios) {

				// definimos el nodo que contendrá los elementos
				Element eMunicipio = doc.createElement("municipio");
				eMunicipios.appendChild(eMunicipio);

				Element eNombre = doc.createElement("nombre");
				eNombre.appendChild(doc.createTextNode(municipio.getNombre()));
				eMunicipio.appendChild(eNombre);

				Element eCodMuni = doc.createElement("codigo_municipal");
				eCodMuni.appendChild(doc.createTextNode(String.valueOf(municipio.getCodMuni())));
				eMunicipio.appendChild(eCodMuni);

				Element eCodProv = doc.createElement("codigo_provincia");
				eCodProv.appendChild(doc.createTextNode(String.valueOf(municipio.getProvincias().getCodProv())));
				eMunicipio.appendChild(eCodProv);

				Element eDescripcion = doc.createElement("descripcion");
				eDescripcion.appendChild(doc.createTextNode(String.valueOf(municipio.getDescripcion())));
				eMunicipio.appendChild(eDescripcion);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = (Transformer) transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xml);

			((javax.xml.transform.Transformer) transformer).transform(source, result);

			return true;

		} catch (Exception e) {
			System.out.println("!ERROR AL CREAR DOCUMENTO¡");
			e.printStackTrace();
			return false;
		}

	}

	public boolean generarXmlProvincias(ArrayList<Provincias> provincias) {

		File xml = new File("xmlProvincias.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element eProvincias = doc.createElement("provincias");
			doc.appendChild(eProvincias);
			
			for (Provincias provincia : provincias) {

				Element eProvincia = doc.createElement("provincia");
				eProvincias.appendChild(eProvincia);

				Element eNombre = doc.createElement("nombre");
				eNombre.appendChild(doc.createTextNode(provincia.getNombre()));
				eProvincia.appendChild(eNombre);

				Element eCodProv = doc.createElement("codigo_provincia");
				eCodProv.appendChild(doc.createTextNode(String.valueOf(provincia.getCodProv())));
				eProvincia.appendChild(eCodProv);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = (Transformer) transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xml);

			((javax.xml.transform.Transformer) transformer).transform(source, result);

			return true;

		} catch (Exception e) {
			System.out.println("!ERROR AL CREAR DOCUMENTO¡");
			e.printStackTrace();
			return false;
		}

	}

	public boolean generarXmlEspacios(ArrayList<Espacios> espacios) {

		File xml = new File("xmlEspaciosNaturales.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element eNaturales = doc.createElement("municipios");
			doc.appendChild(eNaturales);
			
			for (Espacios espacio : espacios) {

				Element eNatural = doc.createElement("municipio");
				eNaturales.appendChild(eNatural);

				Element eNombre = doc.createElement("nombre");
				eNombre.appendChild(doc.createTextNode(espacio.getNombre()));
				eNatural.appendChild(eNombre);

				Element eCodigo = doc.createElement("codigo_espacio");
				eCodigo.appendChild(doc.createTextNode(String.valueOf(espacio.getCodEspacio())));
				eNatural.appendChild(eCodigo);

				Element eTipo = doc.createElement("tipo");
				eTipo.appendChild(doc.createTextNode(String.valueOf(espacio.getTipo())));
				eNatural.appendChild(eTipo);

				Element eDescripcion = doc.createElement("descripcion");
				eDescripcion.appendChild(doc.createTextNode(String.valueOf(espacio.getDescripcion())));
				eNatural.appendChild(eDescripcion);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = (Transformer) transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xml);

			((javax.xml.transform.Transformer) transformer).transform(source, result);

			return true;

		} catch (Exception e) {
			System.out.println("!ERROR AL CREAR DOCUMENTO¡");
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean generarXmlEstaciones(ArrayList<Estaciones> estaciones) {

		File xml = new File("xmlEspaciosEstaciones.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element eEstaciones = doc.createElement("estaciones");
			doc.appendChild(eEstaciones);
			
			for (Estaciones estacion : estaciones) {

				Element eEstacion = doc.createElement("estacion");
				eEstaciones.appendChild(eEstacion);

				Element eNombre = doc.createElement("nombre");
				eNombre.appendChild(doc.createTextNode(estacion.getNombre()));
				eEstacion.appendChild(eNombre);

				Element eCodigo = doc.createElement("codigo_estacion");
				eCodigo.appendChild(doc.createTextNode(String.valueOf(estacion.getCodEst())));
				eEstacion.appendChild(eCodigo);

				Element eProvincia = doc.createElement("provincia");
				eProvincia.appendChild(doc.createTextNode(estacion.getProvincia()));
				eEstacion.appendChild(eProvincia);

				Element eMunicipio = doc.createElement("municipio");
				eMunicipio.appendChild(doc.createTextNode(String.valueOf(estacion.getMunicipio())));
				eEstacion.appendChild(eMunicipio);
				
				Element eDireccion = doc.createElement("direccion");
				eDireccion.appendChild(doc.createTextNode(estacion.getDireccion()));
				eEstacion.appendChild(eDireccion);
				
				Element eLatitud = doc.createElement("latitud");
				eLatitud.appendChild(doc.createTextNode(estacion.getLatitud()));
				eEstacion.appendChild(eLatitud);
				
				Element eLongitud = doc.createElement("longitud");
				eLongitud.appendChild(doc.createTextNode(estacion.getLongitud()));
				eEstacion.appendChild(eLongitud);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = (Transformer) transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xml);

			((javax.xml.transform.Transformer) transformer).transform(source, result);

			return true;

		} catch (Exception e) {
			System.out.println("!ERROR AL CREAR DOCUMENTO¡");
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean generarXmlDatos(ArrayList<Datos> datos) {

		File xml = new File("xmlDatos.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			// definimos el elemento raíz del documento
			Element eDatos = doc.createElement("municipios");
			doc.appendChild(eDatos);
			for (Datos dato : datos) {

				// definimos el nodo que contendrá los elementos
				Element eDato = doc.createElement("dato");
				eDatos.appendChild(eDato);

				Element eCodEst = doc.createElement("codigo_estacion");
				eCodEst.appendChild(doc.createTextNode(String.valueOf(dato.getId().getCodEst())));
				eDato.appendChild(eCodEst);

				Element eFecha = doc.createElement("fecha");
				eFecha.appendChild(doc.createTextNode(String.valueOf(dato.getId().getFecha())));
				eDato.appendChild(eFecha);

				Element eHora = doc.createElement("hora");
				eHora.appendChild(doc.createTextNode(String.valueOf(dato.getId().getHora())));
				eDato.appendChild(eHora);				
				
				Element eCOmgm3 = doc.createElement("COmgm3");
				eCOmgm3.appendChild(doc.createTextNode(String.valueOf(dato.getComgm3())));
				eDato.appendChild(eCOmgm3);				
				
				Element eCO8hmgm3 = doc.createElement("CO8hmgm3");
				eCO8hmgm3.appendChild(doc.createTextNode(String.valueOf(dato.getCo8hmgm3())));
				eDato.appendChild(eCO8hmgm3);
				Element eNOgm3 = doc.createElement("NOgm3");
				eNOgm3.appendChild(doc.createTextNode(String.valueOf(dato.getNogm3())));
				eDato.appendChild(eNOgm3);
				Element eNO2 = doc.createElement("NO2");
				eNO2.appendChild(doc.createTextNode(String.valueOf(dato.getNo2())));
				eDato.appendChild(eNO2);
				Element eNO2ICA = doc.createElement("NO2ICA");
				eNO2ICA.appendChild(doc.createTextNode(dato.getNo2ica()));
				eDato.appendChild(eNO2ICA);			
				
				Element eNOXgm3 = doc.createElement("NOXgm3");
				eNOXgm3.appendChild(doc.createTextNode(String.valueOf(dato.getNoxgm3())));
				eDato.appendChild(eNOXgm3);
				Element ePM10 = doc.createElement("PM10");
				ePM10.appendChild(doc.createTextNode(String.valueOf(dato.getPm10())));
				eDato.appendChild(ePM10);
				Element ePM10ICA = doc.createElement("PM10ICA");
				ePM10ICA.appendChild(doc.createTextNode(dato.getPm10ica()));
				eDato.appendChild(ePM10ICA);
				Element ePM25 = doc.createElement("PM25");
				ePM25.appendChild(doc.createTextNode(String.valueOf(dato.getPm25())));
				eDato.appendChild(ePM25);
				Element ePM25ICA = doc.createElement("PM25ICA");
				ePM25ICA.appendChild(doc.createTextNode(dato.getPm25ica()));
				eDato.appendChild(ePM25ICA);
				Element eSO2 = doc.createElement("SO2");
				eSO2.appendChild(doc.createTextNode(String.valueOf(dato.getSo2())));
				eDato.appendChild(eSO2);
				Element eSO2ICA = doc.createElement("SO2ICA");
				eSO2ICA.appendChild(doc.createTextNode(dato.getSo2ica()));
				eDato.appendChild(eSO2ICA);
				Element eICAEstacion = doc.createElement("ICAEstacion");
				eICAEstacion.appendChild(doc.createTextNode(dato.getIcaestacion()));
				eDato.appendChild(eICAEstacion);

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = (Transformer) transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(xml);

			((javax.xml.transform.Transformer) transformer).transform(source, result);

			return true;

		} catch (Exception e) {
			System.out.println("!ERROR AL CREAR DOCUMENTO¡");
			e.printStackTrace();
			return false;
		}

	}
}
