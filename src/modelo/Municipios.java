package modelo;
// Generated 18 ene. 2021 10:51:22 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Municipios generated by hbm2java
 */
public class Municipios implements java.io.Serializable {

	private Integer codMuniAuto;
	private Provincias provincias;
	private int codMuni;
	private String nombre;
	private String descripcion;
	private Set ubicacioneses = new HashSet(0);

	public Municipios() {
	}

	public Municipios(Provincias provincias, int codMuni, String nombre) {
		this.provincias = provincias;
		this.codMuni = codMuni;
		this.nombre = nombre;
	}

	public Municipios(Provincias provincias, int codMuni, String nombre, String descripcion, Set ubicacioneses) {
		this.provincias = provincias;
		this.codMuni = codMuni;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ubicacioneses = ubicacioneses;
	}

	public Integer getCodMuniAuto() {
		return this.codMuniAuto;
	}

	public void setCodMuniAuto(Integer codMuniAuto) {
		this.codMuniAuto = codMuniAuto;
	}

	public Provincias getProvincias() {
		return this.provincias;
	}

	public void setProvincias(Provincias provincias) {
		this.provincias = provincias;
	}

	public int getCodMuni() {
		return this.codMuni;
	}

	public void setCodMuni(int codMuni) {
		this.codMuni = codMuni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getUbicacioneses() {
		return this.ubicacioneses;
	}

	public void setUbicacioneses(Set ubicacioneses) {
		this.ubicacioneses = ubicacioneses;
	}

}
