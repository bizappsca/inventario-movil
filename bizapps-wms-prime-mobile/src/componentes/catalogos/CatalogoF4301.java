package componentes.catalogos;

import java.util.ArrayList;
import java.util.List;

import modelo.inventario.maestros.F4301;

import org.zkoss.zk.ui.Component;

import componentes.utils.Convertidor;

import controlador.maestros.CGenerico;

public class CatalogoF4301 extends CatalogoGenerico<F4301> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CatalogoF4301(Component div, String titulo, List<F4301> lista,
			boolean emergente, String... campos) {
		super(div, titulo, lista, emergente, campos);
	}

	@Override
	protected List<F4301> buscar(List<String> valores) {
		List<F4301> lista = new ArrayList<F4301>();
		for (F4301 f4301 : getLista()) {
			String shan = "", anby = "", dir = "", crd = "", mcu = "";
			if (f4301.getPhhold() != null)
				shan = f4301.getPhhold();
			if (f4301.getPhanby() != null)
				anby = String.valueOf(f4301.getPhanby());
			if (f4301.getPhshan() != null)
				dir = String.valueOf(f4301.getPhshan());
			if (f4301.getPhcrcd() != null)
				crd = f4301.getPhcrcd();
			if (f4301.getPhmcu() != null)
				mcu = f4301.getPhmcu();
			if (String.valueOf(f4301.getId().getPhdoco()).toLowerCase()
					.contains(valores.get(0))
					&& f4301.getId().getPhkcoo().toLowerCase()
							.contains(valores.get(2))
					&& String.valueOf(f4301.getPhan8()).toLowerCase()
							.contains(valores.get(3))
					&& Convertidor.formatoFecha
							.format(Convertidor
									.transformarJulianaAGregoria(f4301
											.getPhtrdj())).toLowerCase()
							.contains(valores.get(5))
					&& dir.toLowerCase().contains(valores.get(6))
					&& anby.toLowerCase().contains(valores.get(7))
					&& shan.toLowerCase().contains(valores.get(8))
					&& crd.toLowerCase().contains(valores.get(9))
					&& mcu.toLowerCase().contains(valores.get(10))) {
				lista.add(f4301);
			}
		}
		return lista;
	}

	@Override
	protected String[] crearRegistros(F4301 f4301) {
		String shan = "", anby = "";
		if (f4301.getPhshan() != null)
			shan = String.valueOf(f4301.getPhshan());
		if (f4301.getPhanby() != null)
			anby = String.valueOf(f4301.getPhanby());
		String[] registros = new String[11];
		registros[0] = String.valueOf(f4301.getId().getPhdoco());
		registros[1] = f4301.getId().getPhdcto();
		registros[2] = f4301.getId().getPhkcoo();
		registros[3] = String.valueOf(f4301.getPhan8());
		registros[4] = CGenerico.getServicioF0101().buscar(f4301.getPhan8())
				.getAbalph();
		registros[5] = Convertidor.formatoFecha.format(Convertidor
				.transformarJulianaAGregoria(f4301.getPhtrdj()));
		registros[6] = shan;
		registros[7] = anby;
		registros[8] = f4301.getPhhold();
		registros[9] = f4301.getPhcrcd();
		registros[10] = f4301.getPhmcu();
		return registros;
	}

}
