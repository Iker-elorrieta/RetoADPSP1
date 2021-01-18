package modelo;
// Generated 13 ene. 2021 20:28:42 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Naturales generated by hbm2java
 */
public class Naturales implements java.io.Serializable {

	private int codEspacio;
	private String nombre;
	private String descripcion;
	private Set ubicacioneses = new HashSet(0);

	

	public Naturales(int codEspacio, String nombre) {
		this.codEspacio = codEspacio;
		this.nombre = nombre;
	}

	public Naturales(int codEspacio, String nombre, String descripcion, Set ubicacioneses) {
		this.codEspacio = codEspacio;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ubicacioneses = ubicacioneses;
	}

	public int getCodEspacio() {
		return this.codEspacio;
	}

	public void setCodEspacio(int codEspacio) {
		this.codEspacio = codEspacio;
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
