package modelo;
// Generated 19 ene. 2021 11:02:31 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Estaciones generated by hbm2java
 */
public class Estaciones implements java.io.Serializable {

	private int codEst;
	private String nombre;
	private String provincia;
	private String municipio;
	private String direccion;
	private String latitud;
	private String longitud;
	private Set datoses = new HashSet(0);

	public Estaciones() {
	}

	public Estaciones(int codEst) {
		this.codEst = codEst;
	}

	public Estaciones(int codEst, String nombre, String provincia, String municipio, String direccion, String latitud,
			String longitud, Set datoses) {
		this.codEst = codEst;
		this.nombre = nombre;
		this.provincia = provincia;
		this.municipio = municipio;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public Set getDatoses() {
		return this.datoses;
	}

	public void setDatoses(Set datoses) {
		this.datoses = datoses;
	}

}
