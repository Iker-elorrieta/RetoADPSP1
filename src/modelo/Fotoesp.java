package modelo;
// Generated 29 ene. 2021 15:52:31 by Hibernate Tools 5.4.21.Final

/**
 * Fotoesp generated by hbm2java
 */
public class Fotoesp implements java.io.Serializable {

	private FotoespId id;
	private Espacios espacios;
	private Usuarios usuarios;

	public Fotoesp() {
	}

	public Fotoesp(FotoespId id, Espacios espacios, Usuarios usuarios) {
		this.id = id;
		this.espacios = espacios;
		this.usuarios = usuarios;
	}

	public FotoespId getId() {
		return this.id;
	}

	public void setId(FotoespId id) {
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
