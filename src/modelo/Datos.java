package modelo;
// Generated 13 ene. 2021 20:28:42 by Hibernate Tools 5.4.21.Final

/**
 * Datos generated by hbm2java
 */
public class Datos implements java.io.Serializable {

	private DatosId id;
	private Estaciones estaciones;
	private Integer nogm3;
	private Integer no2gm3;
	private Integer noxgm3;
	private Integer pm10gm3;
	private String direccionViento;
	private Integer h;
	private Integer precipitaciones;
	private Integer temperatura;
	private Integer velocidadViento;

	public Datos() {
	}

	public Datos(DatosId id, Estaciones estaciones) {
		this.id = id;
		this.estaciones = estaciones;
	}

	public Datos(DatosId id, Estaciones estaciones, Integer nogm3, Integer no2gm3, Integer noxgm3, Integer pm10gm3,
			String direccionViento, Integer h, Integer precipitaciones, Integer temperatura, Integer velocidadViento) {
		this.id = id;
		this.estaciones = estaciones;
		this.nogm3 = nogm3;
		this.no2gm3 = no2gm3;
		this.noxgm3 = noxgm3;
		this.pm10gm3 = pm10gm3;
		this.direccionViento = direccionViento;
		this.h = h;
		this.precipitaciones = precipitaciones;
		this.temperatura = temperatura;
		this.velocidadViento = velocidadViento;
	}

	public DatosId getId() {
		return this.id;
	}

	public void setId(DatosId id) {
		this.id = id;
	}

	public Estaciones getEstaciones() {
		return this.estaciones;
	}

	public void setEstaciones(Estaciones estaciones) {
		this.estaciones = estaciones;
	}

	public Integer getNogm3() {
		return this.nogm3;
	}

	public void setNogm3(Integer nogm3) {
		this.nogm3 = nogm3;
	}

	public Integer getNo2gm3() {
		return this.no2gm3;
	}

	public void setNo2gm3(Integer no2gm3) {
		this.no2gm3 = no2gm3;
	}

	public Integer getNoxgm3() {
		return this.noxgm3;
	}

	public void setNoxgm3(Integer noxgm3) {
		this.noxgm3 = noxgm3;
	}

	public Integer getPm10gm3() {
		return this.pm10gm3;
	}

	public void setPm10gm3(Integer pm10gm3) {
		this.pm10gm3 = pm10gm3;
	}

	public String getDireccionViento() {
		return this.direccionViento;
	}

	public void setDireccionViento(String direccionViento) {
		this.direccionViento = direccionViento;
	}

	public Integer getH() {
		return this.h;
	}

	public void setH(Integer h) {
		this.h = h;
	}

	public Integer getPrecipitaciones() {
		return this.precipitaciones;
	}

	public void setPrecipitaciones(Integer precipitaciones) {
		this.precipitaciones = precipitaciones;
	}

	public Integer getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(Integer temperatura) {
		this.temperatura = temperatura;
	}

	public Integer getVelocidadViento() {
		return this.velocidadViento;
	}

	public void setVelocidadViento(Integer velocidadViento) {
		this.velocidadViento = velocidadViento;
	}

}
