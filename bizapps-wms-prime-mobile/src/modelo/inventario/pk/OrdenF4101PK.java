package modelo.inventario.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrdenF4101PK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="OITM")
	private Double oitm;

	@Column(name="OORDEN")
	private Integer oorden;

	public OrdenF4101PK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Double getOitm() {
		return oitm;
	}

	public void setOitm(Double oitm) {
		this.oitm = oitm;
	}

	public Integer getOorden() {
		return oorden;
	}

	public void setOorden(Integer oorden) {
		this.oorden = oorden;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oitm == null) ? 0 : oitm.hashCode());
		result = prime * result + ((oorden == null) ? 0 : oorden.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdenF4101PK other = (OrdenF4101PK) obj;
		if (oitm == null) {
			if (other.oitm != null)
				return false;
		} else if (!oitm.equals(other.oitm))
			return false;
		if (oorden == null) {
			if (other.oorden != null)
				return false;
		} else if (!oorden.equals(other.oorden))
			return false;
		return true;
	}


}
