package modelo.inventario.seguridad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import modelo.inventario.pk.F4201APK;

@Entity
@Table(schema = "bizapps_wms_prime.dbo")
public class F4201A implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private F4201APK id;
	
	@Column(name="vervals", length = 500)
	private String vervals;
	
	@Column(name="verstringa", length = 500)
	private String verstringa;
	
	@Column(name="verstringb", length = 500)
	private String verstringb;
	
	@Column(name="vernumerica")
	private Double vernumerica;
	
	@Column(name="vernumericb")
	private Double vernumericb;

	public F4201A() {
		super();
	}

	public F4201APK getId() {
		return id;
	}

	public void setId(F4201APK id) {
		this.id = id;
	}

	public String getVervals() {
		return vervals;
	}

	public void setVervals(String vervals) {
		this.vervals = vervals;
	}

	public String getVerstringb() {
		return verstringb;
	}

	public void setVerstringb(String verstringb) {
		this.verstringb = verstringb;
	}

	public Double getVernumerica() {
		return vernumerica;
	}

	public void setVernumerica(Double vernumerica) {
		this.vernumerica = vernumerica;
	}

	public Double getVernumericb() {
		return vernumericb;
	}

	public void setVernumericb(Double vernumericb) {
		this.vernumericb = vernumericb;
	}

	public String getVerstringa() {
		return verstringa;
	}

	public void setVerstringa(String verstringa) {
		this.verstringa = verstringa;
	}
	
	
}
