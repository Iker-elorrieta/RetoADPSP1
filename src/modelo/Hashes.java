package modelo;
// Generated 17 ene. 2021 22:11:29 by Hibernate Tools 5.4.21.Final

/**
 * Hashes generated by hbm2java
 */
public class Hashes implements java.io.Serializable {

	private int codHash;
	private String url;
	private String hash;
	private String nombreJson;

	public Hashes() {
	}

	public Hashes(int codHash, String url, String hash, String nombreJson) {
		this.codHash = codHash;
		this.url = url;
		this.hash = hash;
		this.nombreJson = nombreJson;
	}

	public int getCodHash() {
		return this.codHash;
	}

	public void setCodHash(int codHash) {
		this.codHash = codHash;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getNombreJson() {
		return this.nombreJson;
	}

	public void setNombreJson(String nombreJson) {
		this.nombreJson = nombreJson;
	}

}
