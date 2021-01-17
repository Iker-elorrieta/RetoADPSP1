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

import modelo.Municipios;
import modelo.Naturales;
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

	public boolean generarXmlEspacios(ArrayList<Naturales> naturales) {

		File xml = new File("xmlEspaciosNaturales.xml");
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.newDocument();
			Element eNaturales = doc.createElement("municipios");
			doc.appendChild(eNaturales);
			
			for (Naturales natural : naturales) {

				Element eNatural = doc.createElement("municipio");
				eNaturales.appendChild(eNatural);

				Element eNombre = doc.createElement("nombre");
				eNombre.appendChild(doc.createTextNode(natural.getNombre()));
				eNatural.appendChild(eNombre);

				Element eCodigo = doc.createElement("codigo_espacio");
				eCodigo.appendChild(doc.createTextNode(String.valueOf(natural.getCodEspacio())));
				eNatural.appendChild(eCodigo);

				Element eTipo = doc.createElement("tipo");
				eTipo.appendChild(doc.createTextNode(String.valueOf(natural.getTipo())));
				eNatural.appendChild(eTipo);

				Element eDescripcion = doc.createElement("descripcion");
				eDescripcion.appendChild(doc.createTextNode(String.valueOf(natural.getDescripcion())));
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
}
