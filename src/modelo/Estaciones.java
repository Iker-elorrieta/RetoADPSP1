package modelo;
// Generated 13 ene. 2021 20:28:42 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Estaciones generated by hbm2java
 */
public class Estaciones implements java.io.Serializable {

	private int codEst;
	private Municipios municipios;
	private String nombre;
	private String direccion;
	private Double latitud;
	private Double longitud;
	private Set datoses = new HashSet(0);

	public Estaciones() {
	}

	public Estaciones(int codEst, Municipios municipios) {
		this.codEst = codEst;
		this.municipios = municipios;
	}

	public Estaciones(int codEst, Municipios municipios, String nombre, String direccion, Double latitud,
			Double longitud, Set datoses) {
		this.codEst = codEst;
		this.municipios = municipios;
		this.nombre = nombre;
		this.direccion = direccion;
		this.latitud = latitud;
		this.longitud = longitud;
		this.datoses = datoses;
	}

	public int getCodEst() {
		return this.codEst;
	}

	public void setCodEst(int codEst) {
		this.codEst = codEst;
	}

	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

	public Set getDatoses() {
		return this.datoses;
	}

	public void setDatoses(Set datoses) {
		this.datoses = datoses;
	}

}
