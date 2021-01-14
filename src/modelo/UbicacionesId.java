package modelo;
// Generated 13 ene. 2021 20:28:42 by Hibernate Tools 5.4.21.Final

/**
 * UbicacionesId generated by hbm2java
 */
public class UbicacionesId implements java.io.Serializable {

	private int codEspacio;
	private int codMuni;

	

	public UbicacionesId(int codEspacio, int codMuni) {
		this.codEspacio = codEspacio;
		this.codMuni = codMuni;
	}

	public int getCodEspacio() {
		return this.codEspacio;
	}

	public void setCodEspacio(int codEspacio) {
		this.codEspacio = codEspacio;
	}

	public int getCodMuni() {
		return this.codMuni;
	}

	public void setCodMuni(int codMuni) {
		this.codMuni = codMuni;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UbicacionesId))
			return false;
		UbicacionesId castOther = (UbicacionesId) other;

		return (this.getCodEspacio() == castOther.getCodEspacio()) && (this.getCodMuni() == castOther.getCodMuni());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCodEspacio();
		result = 37 * result + this.getCodMuni();
		return result;
	}

}
