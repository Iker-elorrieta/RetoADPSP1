package modelo;
// Generated 19 ene. 2021 11:02:31 by Hibernate Tools 5.4.21.Final

/**
 * Ubicaciones generated by hbm2java
 */
public class Ubicaciones implements java.io.Serializable {

	private UbicacionesId id;
	private Espacios espacios;
	private Municipios municipios;

	public Ubicaciones() {
	}

	public Ubicaciones(UbicacionesId id, Espacios espacios, Municipios municipios) {
		this.id = id;
		this.espacios = espacios;
		this.municipios = municipios;
	}

	public UbicacionesId getId() {
		return this.id;
	}

	public void setId(UbicacionesId id) {
		this.id = id;
	}

	public Espacios getEspacios() {
		return this.espacios;
	}

	public void setEspacios(Espacios espacios) {
		this.espacios = espacios;
	}

	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

}
