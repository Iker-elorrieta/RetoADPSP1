package modelo;
// Generated 29 ene. 2021 15:52:31 by Hibernate Tools 5.4.21.Final

/**
 * Fotomun generated by hbm2java
 */
public class Fotomun implements java.io.Serializable {

	private FotomunId id;
	private Municipios municipios;
	private Usuarios usuarios;

	public Fotomun() {
	}

	public Fotomun(FotomunId id, Municipios municipios, Usuarios usuarios) {
		this.id = id;
		this.municipios = municipios;
		this.usuarios = usuarios;
	}

	public FotomunId getId() {
		return this.id;
	}

	public void setId(FotomunId id) {
		this.id = id;
	}

	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}
