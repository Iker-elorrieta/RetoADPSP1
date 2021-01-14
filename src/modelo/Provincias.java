package modelo;
// Generated 13 ene. 2021 20:28:42 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Provincias generated by hbm2java
 */
public class Provincias implements java.io.Serializable {

	private int codProv;
	private String nombre;
	private Set municipioses = new HashSet(0);

	

	public Provincias(int codProv, String nombre) {
		this.codProv = codProv;
		this.nombre = nombre;
	}

	public Provincias(int codProv, String nombre, Set municipioses) {
		this.codProv = codProv;
		this.nombre = nombre;
		this.municipioses = municipioses;
	}

	public int getCodProv() {
		return this.codProv;
	}

	public void setCodProv(int codProv) {
		this.codProv = codProv;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getMunicipioses() {
		return this.municipioses;
	}

	public void setMunicipioses(Set municipioses) {
		this.municipioses = municipioses;
	}

}
