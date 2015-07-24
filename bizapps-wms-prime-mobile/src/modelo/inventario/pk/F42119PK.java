package modelo.inventario.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the F42119 database table.
 * 
 */
@Embeddable
public class F42119PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SDDOCO")
	private double sddoco;

	@Column(name="SDDCTO")
	private String sddcto;

	@Column(name="SDKCOO")
	private String sdkcoo;

	@Column(name="SDLNID")
	private double sdlnid;

	public F42119PK() {
	}
	public double getSddoco() {
		return this.sddoco;
	}
	public void setSddoco(double sddoco) {
		this.sddoco = sddoco;
	}
	public String getSddcto() {
		return this.sddcto;
	}
	public void setSddcto(String sddcto) {
		this.sddcto = sddcto;
	}
	public String getSdkcoo() {
		return this.sdkcoo;
	}
	public void setSdkcoo(String sdkcoo) {
		this.sdkcoo = sdkcoo;
	}
	public double getSdlnid() {
		return this.sdlnid;
	}
	public void setSdlnid(double sdlnid) {
		this.sdlnid = sdlnid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof F42119PK)) {
			return false;
		}
		F42119PK castOther = (F42119PK)other;
		return 
			(this.sddoco == castOther.sddoco)
			&& this.sddcto.equals(castOther.sddcto)
			&& this.sdkcoo.equals(castOther.sdkcoo)
			&& (this.sdlnid == castOther.sdlnid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.sddoco) ^ (java.lang.Double.doubleToLongBits(this.sddoco) >>> 32)));
		hash = hash * prime + this.sddcto.hashCode();
		hash = hash * prime + this.sdkcoo.hashCode();
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.sdlnid) ^ (java.lang.Double.doubleToLongBits(this.sdlnid) >>> 32)));
		
		return hash;
	}
}