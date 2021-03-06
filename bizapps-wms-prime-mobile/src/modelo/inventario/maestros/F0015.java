package modelo.inventario.maestros;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import modelo.inventario.pk.F0015PK;


/**
 * The persistent class for the F0015 database table.
 * 
 */
@Entity
@Table(schema = "bizapps_wms_prime.dbo")
public class F0015 implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private F0015PK id;

	@Column(name="CXCLMETH")
	private String cxclmeth;

	@Column(name="CXCRCM")
	private String cxcrcm;

	@Column(name="CXCRR")
	private Double cxcrr;

	@Column(name="CXCRRD")
	private Double cxcrrd;

	@Column(name="CXCSR")
	private String cxcsr;

	@Column(name="CXJOBN")
	private String cxjobn;

	@Column(name="CXPID")
	private String cxpid;

	@Column(name="CXTRCR")
	private String cxtrcr;

	@Column(name="CXUPMJ")
	private BigDecimal cxupmj;

	@Column(name="CXUPMT")
	private Double cxupmt;

	@Column(name="CXUSER")
	private String cxuser;

	public F0015() {
	}

	public F0015PK getId() {
		return this.id;
	}

	public void setId(F0015PK id) {
		this.id = id;
	}

	public String getCxclmeth() {
		return this.cxclmeth;
	}

	public void setCxclmeth(String cxclmeth) {
		this.cxclmeth = cxclmeth;
	}

	public String getCxcrcm() {
		return this.cxcrcm;
	}

	public void setCxcrcm(String cxcrcm) {
		this.cxcrcm = cxcrcm;
	}

	public Double getCxcrr() {
		return this.cxcrr;
	}

	public void setCxcrr(Double cxcrr) {
		this.cxcrr = cxcrr;
	}

	public Double getCxcrrd() {
		return this.cxcrrd;
	}

	public void setCxcrrd(Double cxcrrd) {
		this.cxcrrd = cxcrrd;
	}

	public String getCxcsr() {
		return this.cxcsr;
	}

	public void setCxcsr(String cxcsr) {
		this.cxcsr = cxcsr;
	}

	public String getCxjobn() {
		return this.cxjobn;
	}

	public void setCxjobn(String cxjobn) {
		this.cxjobn = cxjobn;
	}

	public String getCxpid() {
		return this.cxpid;
	}

	public void setCxpid(String cxpid) {
		this.cxpid = cxpid;
	}

	public String getCxtrcr() {
		return this.cxtrcr;
	}

	public void setCxtrcr(String cxtrcr) {
		this.cxtrcr = cxtrcr;
	}

	public BigDecimal getCxupmj() {
		return this.cxupmj;
	}

	public void setCxupmj(BigDecimal cxupmj) {
		this.cxupmj = cxupmj;
	}

	public Double getCxupmt() {
		return this.cxupmt;
	}

	public void setCxupmt(Double cxupmt) {
		this.cxupmt = cxupmt;
	}

	public String getCxuser() {
		return this.cxuser;
	}

	public void setCxuser(String cxuser) {
		this.cxuser = cxuser;
	}

}