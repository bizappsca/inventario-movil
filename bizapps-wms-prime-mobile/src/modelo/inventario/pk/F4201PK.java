package modelo.inventario.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the F4201 database table.
 * 
 */
@Embeddable
public class F4201PK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SHDOCO")
	private Double shdoco;

	@Column(name="SHDCTO")
	private String shdcto;

	@Column(name="SHKCOO")
	private String shkcoo;

	public F4201PK() {
	}
	public Double getShdoco() {
		return this.shdoco;
	}
	public void setShdoco(Double shdoco) {
		this.shdoco = shdoco;
	}
	public String getShdcto() {
		return this.shdcto;
	}
	public void setShdcto(String shdcto) {
		this.shdcto = shdcto;
	}
	public String getShkcoo() {
		return this.shkcoo;
	}
	public void setShkcoo(String shkcoo) {
		this.shkcoo = shkcoo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof F4201PK)) {
			return false;
		}
		F4201PK castOther = (F4201PK)other;
		return 
			(this.shdoco == castOther.shdoco)
			&& this.shdcto.equals(castOther.shdcto)
			&& this.shkcoo.equals(castOther.shkcoo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (java.lang.Double.doubleToLongBits(this.shdoco) ^ (java.lang.Double.doubleToLongBits(this.shdoco) >>> 32)));
		hash = hash * prime + this.shdcto.hashCode();
		hash = hash * prime + this.shkcoo.hashCode();
		
		return hash;
	}
}