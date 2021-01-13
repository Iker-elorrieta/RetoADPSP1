package modelo;
// Generated 13 ene. 2021 20:28:42 by Hibernate Tools 5.4.21.Final

import java.util.Date;

/**
 * DatosId generated by hbm2java
 */
public class DatosId implements java.io.Serializable {

	private Date fecha;
	private String hora;
	private int codEst;

	public DatosId() {
	}

	public DatosId(Date fecha, String hora, int codEst) {
		this.fecha = fecha;
		this.hora = hora;
		this.codEst = codEst;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return this.hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public int getCodEst() {
		return this.codEst;
	}

	public void setCodEst(int codEst) {
		this.codEst = codEst;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DatosId))
			return false;
		DatosId castOther = (DatosId) other;

		return ((this.getFecha() == castOther.getFecha()) || (this.getFecha() != null && castOther.getFecha() != null
				&& this.getFecha().equals(castOther.getFecha())))
				&& ((this.getHora() == castOther.getHora()) || (this.getHora() != null && castOther.getHora() != null
						&& this.getHora().equals(castOther.getHora())))
				&& (this.getCodEst() == castOther.getCodEst());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getFecha() == null ? 0 : this.getFecha().hashCode());
		result = 37 * result + (getHora() == null ? 0 : this.getHora().hashCode());
		result = 37 * result + this.getCodEst();
		return result;
	}

}
