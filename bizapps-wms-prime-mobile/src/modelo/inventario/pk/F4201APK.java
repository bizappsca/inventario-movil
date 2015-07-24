package modelo.inventario.pk;

import java.io.Serializable;

import javax.persistence.Column;

public class F4201APK implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="verprg")
	private Long verprg;

	@Column(name="verfield", length = 10)
	private String verfield;

	public F4201APK() {
		super();
	}

	public Long getVerprg() {
		return verprg;
	}

	public void setVerprg(Long verprg) {
		this.verprg = verprg;
	}

	public String getVerfield() {
		return verfield;
	}

	public void setVerfield(String verfield) {
		this.verfield = verfield;
	}
	
	
}
