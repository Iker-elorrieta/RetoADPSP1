package modelo;
// Generated 1 feb. 2021 18:32:53 by Hibernate Tools 5.4.21.Final

import java.util.Arrays;

/**
 * FotoespId generated by hbm2java
 */
public class FotoespId implements java.io.Serializable {

	private int codUsu;
	private int codEspacio;
	private byte[] foto;

	public FotoespId() {
	}

	public FotoespId(int codUsu, int codEspacio, byte[] foto) {
		this.codUsu = codUsu;
		this.codEspacio = codEspacio;
		this.foto = foto;
	}

	public int getCodUsu() {
		return this.codUsu;
	}

	public void setCodUsu(int codUsu) {
		this.codUsu = codUsu;
	}

	public int getCodEspacio() {
		return this.codEspacio;
	}

	public void setCodEspacio(int codEspacio) {
		this.codEspacio = codEspacio;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FotoespId))
			return false;
		FotoespId castOther = (FotoespId) other;

		return (this.getCodUsu() == castOther.getCodUsu()) && (this.getCodEspacio() == castOther.getCodEspacio())
				&& ((this.getFoto() == castOther.getFoto()) || (this.getFoto() != null && castOther.getFoto() != null
						&& Arrays.equals(this.getFoto(), castOther.getFoto())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodUsu();
		result = 37 * result + this.getCodEspacio();
		int fotoHashcode = 0;
		byte[] fotoProperty = this.getFoto();
		if (fotoProperty != null) {
			fotoHashcode = 1;
			for (int i = 0; i < fotoProperty.length; i++) {
				fotoHashcode = 37 * fotoHashcode + fotoProperty[i];
			}
		}

		result = 37 * result + fotoHashcode;

		return result;
	}

}