package modelo;
// Generated 29 ene. 2021 15:52:31 by Hibernate Tools 5.4.21.Final

/**
 * Favesp generated by hbm2java
 */
public class Favesp implements java.io.Serializable {

	private FavespId id;
	private Espacios espacios;
	private Usuarios usuarios;

	public Favesp() {
	}

	public Favesp(FavespId id, Espacios espacios, Usuarios usuarios) {
		this.id = id;
		this.espacios = espacios;
		this.usuarios = usuarios;
	}

	public FavespId getId() {
		return this.id;
	}

	public void setId(FavespId id) {
		this.id = id;
	}

	public Espacios getEspacios() {
		return this.espacios;
	}

	public void setEspacios(Espacios espacios) {
		this.espacios = espacios;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}
