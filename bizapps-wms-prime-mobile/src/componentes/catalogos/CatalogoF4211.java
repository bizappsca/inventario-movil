package componentes.catalogos;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.transacciones.F4211;

import org.zkoss.zk.ui.Component;

public class CatalogoF4211 extends CatalogoGenerico<F4211>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogoF4211(Component cGenerico, String titulo, List<F4211> lista,
			boolean emergente, String... campos) {
		super(cGenerico, titulo, lista, emergente, campos);
	}

	@Override
	protected List<F4211> buscar(List<String> valores) {
		List<F4211> listF0005_2 = new ArrayList<F4211>();

		for (F4211 f0005 : getLista()) {
			String cuatro, quince, once, ocho, seis, catorce, nueve, siete, doce, trece;
			if (f0005.getSdan8() != null) {
				cuatro = String.valueOf(f0005.getSdan8().longValue());
			} else
				cuatro = "";
			if (f0005.getSditm() != null) {
				seis = String.valueOf(f0005.getSditm().longValue());
			} else
				seis = "";
			if (f0005.getSduorg() != null)
				ocho = String.valueOf(f0005.getSduorg().longValue());
			else
				ocho = "";
			if (f0005.getSdshan() != null)
				once = String.valueOf(f0005.getSdshan().longValue());
			else
				once = "";
			if (f0005.getSdshpn() != null)
				catorce = String.valueOf(f0005.getSdshpn().longValue());
			else
				catorce = "";
			if (f0005.getSdpsn() != null)
				quince = String.valueOf(f0005.getSdpsn().longValue());
			else
				quince = "";
			if (f0005.getSdlitm() != null)
				siete = f0005.getSdlitm();
			else
				siete = "";
			if (f0005.getSduom() != null)
				nueve = f0005.getSduom();
			else
				nueve = "";
			if (f0005.getSdlttr() != null)
				doce = f0005.getSdlttr();
			else
				doce = "";
			if (f0005.getSdnxtr() != null)
				trece = f0005.getSdnxtr();
			else
				trece = "";
			if (String.valueOf(f0005.getId().getSddoco().longValue())
					.toLowerCase()
					.contains(valores.get(0).toLowerCase())
					&& f0005.getId().getSddcto().toLowerCase()
							.contains(valores.get(1).toLowerCase())
					&& f0005.getId().getSdkcoo().toLowerCase()
							.contains(valores.get(2).toLowerCase())
					&& String
							.valueOf(
									f0005.getId().getSdlnid()
											.longValue()).toLowerCase()
							.contains(valores.get(3).toLowerCase())
					&& cuatro.toLowerCase().contains(
							valores.get(4).toLowerCase())
					&& seis.toLowerCase().contains(
							valores.get(6).toLowerCase())
					&& siete.toLowerCase().contains(
							valores.get(7).toLowerCase())
					&& ocho.toLowerCase().contains(
							valores.get(8).toLowerCase())
					&& nueve.toLowerCase().contains(
							valores.get(9).toLowerCase())
					&& f0005.traerFecha().toLowerCase()
							.contains(valores.get(10).toLowerCase())
					&& once.toLowerCase().contains(
							valores.get(11).toLowerCase())
					&& doce.toLowerCase().contains(
							valores.get(12).toLowerCase())
					&& trece.toLowerCase().contains(
							valores.get(13).toLowerCase())
					&& catorce.toLowerCase().contains(
							valores.get(14).toLowerCase())
					&& quince.toLowerCase().contains(
							valores.get(15).toLowerCase())) {
				listF0005_2.add(f0005);
			}
		}
		return listF0005_2;
	}

	@Override
	protected String[] crearRegistros(F4211 f0005) {
		String[] registros = new String[16];
		registros[0] = String.valueOf(f0005.getId().getSddoco()
				.longValue());
		registros[1] = f0005.getId().getSddcto();
		registros[2] = f0005.getId().getSdkcoo();
		registros[3] = String.valueOf(f0005.getId().getSdlnid()
				.longValue());
		if (f0005.getSdan8() != null) {
			registros[4] = String.valueOf(f0005.getSdan8().longValue());
			registros[5] = f0005.getSdir01();
		} else
			registros[4] = registros[5] = "";
		if (f0005.getSditm() != null) {
			registros[6] = String.valueOf(f0005.getSditm().longValue());
			registros[7] = f0005.getSdlitm();
		} else
			registros[6] = registros[7] = "";
		if (f0005.getSduorg() != null)
			registros[8] = String
					.valueOf(f0005.getSduorg().longValue());
		else
			registros[8] = "";
		registros[9] = f0005.getSduom();
		registros[10] = f0005.traerFecha();
		if (f0005.getSdshan() != null)
			registros[11] = String.valueOf(f0005.getSdshan()
					.longValue());
		else
			registros[11] = "";
		registros[12] = f0005.getSdlttr();
		registros[13] = f0005.getSdnxtr();
		if (f0005.getSdshpn() != null)
			registros[14] = String.valueOf(f0005.getSdshpn()
					.longValue());
		else
			registros[14] = "";
		if (f0005.getSdpsn() != null)
			registros[15] = String
					.valueOf(f0005.getSdpsn().longValue());
		else
			registros[15] = "";
		return registros;
	}

}
