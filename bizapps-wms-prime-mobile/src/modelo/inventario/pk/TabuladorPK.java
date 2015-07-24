package modelo.inventario.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TabuladorPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "TTIPO")
	private String ttipo;

	@Column(name = "TCAT")
	private String tcat;

	@Column(name = "TAB")
	private Double tab;

	@Column(name = "TBIS")
	private Double tbis;

	@Column(name = "TROUND")
	private String tround;

	public TabuladorPK() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTtipo() {
		return ttipo;
	}

	public void setTtipo(String ttipo) {
		this.ttipo = ttipo;
	}

	public String getTcat() {
		return tcat;
	}

	public void setTcat(String tcat) {
		this.tcat = tcat;
	}

	public String getTround() {
		return tround;
	}

	public void setTround(String tround) {
		this.tround = tround;
	}

	public Double getTab() {
		return tab;
	}

	public void setTab(Double tab) {
		this.tab = tab;
	}

	public Double getTbis() {
		return tbis;
	}

	public void setTbis(Double tbis) {
		this.tbis = tbis;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tab == null) ? 0 : tab.hashCode());
		result = prime * result + ((tbis == null) ? 0 : tbis.hashCode());
		result = prime * result + ((tcat == null) ? 0 : tcat.hashCode());
		result = prime * result + ((tround == null) ? 0 : tround.hashCode());
		result = prime * result + ((ttipo == null) ? 0 : ttipo.hashCode());
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
		TabuladorPK other = (TabuladorPK) obj;
		if (tab == null) {
			if (other.tab != null)
				return false;
		} else if (!tab.equals(other.tab))
			return false;
		if (tbis == null) {
			if (other.tbis != null)
				return false;
		} else if (!tbis.equals(other.tbis))
			return false;
		if (tcat == null) {
			if (other.tcat != null)
				return false;
		} else if (!tcat.equals(other.tcat))
			return false;
		if (tround == null) {
			if (other.tround != null)
				return false;
		} else if (!tround.equals(other.tround))
			return false;
		if (ttipo == null) {
			if (other.ttipo != null)
				return false;
		} else if (!ttipo.equals(other.ttipo))
			return false;
		return true;
	}
}
